
/**
 * @author yachao
 * @apiNote Solve the radial Schrodinger equation with Numerov method
 * @version last update Apr 23, 2022 11:28:24 AM
 *
 */
public class RadialSchrodinger {
   int l;
   double E;
   Potential vPotential;
   double cutoffRadius;
   double alpha;

   /**
    * @param l,            angular quantum number
    * @param E,            energy
    * @param vPotential,   potential
    * @param cutoffRadius, potential vanishes beyond cutoffRadius
    * @param alpha,        2m/hbar^2
    */
   public RadialSchrodinger(int l, double E, Potential vPotential, double cutoffRadius, double alpha) {
      this.l = l;
      this.E = E;
      this.vPotential = vPotential;
      this.cutoffRadius = cutoffRadius;
      this.alpha = alpha;
   }

   public RadialSchrodinger(int l, double E, Potential vPotential, double cutoffRadius) {
      this.l = l;
      this.E = E;
      this.vPotential = vPotential;
      this.cutoffRadius = cutoffRadius;
      this.alpha = 1.0;
   }

   /**
    * @param r
    * @return F(l,r,E) = alpha * [V(r) + l(l+1)/(alpha*r^2) - E] consisting of the
    *         energy, potential and centrifugal barrier, where alpha=2m/hbar^2
    */
   public double getF(double r) {
      // to avoid divergence
      if (Math.abs(r) < 1.e-12) {
         r = 1.e-12;
      }
      double V = 0;
      if (r < cutoffRadius) {
         V = vPotential.getValue(r);
      }
      double F = alpha * (V + l * (l + 1) / (alpha * r * r) - E);
      return F;
   }

   /**
    * @param u0
    * @param dotu0
    * @param stepSize
    * @return u1
    */
   public double getInitialState(double r0, double u0, double dotu0, double stepSize) {
      // implement Thijssen P574 A.54
      double x0 = u0;
      double dx0 = dotu0;
      double h = stepSize;
      double h2 = h * h;
      double f0 = getF(r0);
      double fh = getF(r0 + h);
      double fmh = getF(r0 - h);
      double numerator = (2 + 5 * h2 * f0 / 6) * (1 - h2 * fmh / 12) * x0 + 2 * h * dx0 * (1 - h2 * fmh / 6);
      double denominator = (1 - h2 * fh / 12) * (1 - h2 * fmh / 6) + (1 - h2 * fmh / 12) * (1 - h2 * fh / 6);

      return numerator / denominator;
   }

   /**
    * @param rArray, array of position
    * @param u0,     state[0]
    * @param u1,     state[1]
    * @return psi, array of eigenstate
    */
   public double[] getState(double[] rArray, double u0, double u1) {
      int numberOfPoint = rArray.length;
      double stepSize = rArray[1] - rArray[0];
      double[] fArray = new double[numberOfPoint];

      for (int i = 0; i < numberOfPoint; i++) {
         fArray[i] = getF(rArray[i]);
      }
      return Solver.numerov(fArray, stepSize, u0, u1);
   }

   public double getPhaseShift(double[] rArray, double u0, double dotu0) {
      double stepSize = rArray[1] - rArray[0];
      int r1Index = 0;
      int r2Index = 0;
      // find the first integration point beyond cutoffRadius
      for (int i = 0; i < rArray.length; i++) {
         if (rArray[i] >= cutoffRadius) {
            r1Index = i;
            break;
         }
      }
      double k = Math.sqrt(E * alpha);
      double halfLambda = Math.PI / k;
      // find the first integration point half a wavelength beyond r1
      for (int i = 0; i < rArray.length; i++) {
         if (rArray[i] >= rArray[r1Index] + halfLambda) {
            r2Index = i;
            break;
         }
      }
      if (r2Index == 0) {
         r2Index = rArray.length - 1;
      }
      double u1 = getInitialState(rArray[0], u0, dotu0, stepSize);
      double[] phi = getState(rArray, u0, u1);
      double r1 = rArray[r1Index];
      double r2 = rArray[r2Index];
      double w1 = phi[r1Index];
      double w2 = phi[r2Index];
      double factor = r1 * w2 / (r2 * w1);
      double j1 = SphericalBessel.jn(l, k * r1);
      double j2 = SphericalBessel.jn(l, k * r2);
      double n1 = SphericalBessel.yn(l, k * r1);
      double n2 = SphericalBessel.yn(l, k * r2);
      double tanDeltal = (factor * j1 - j2) / (factor * n1 - n2);
      double Deltal = Math.atan(tanDeltal);
      return Deltal;
   }
}
