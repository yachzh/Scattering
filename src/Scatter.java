/**
 * @author yachao
 * @apiNote Calculate the total cross section at a particular energy
 * @version last update May 2, 2022 8:49:39 PM
 *
 */
public class Scatter {
   int lmax;
   double energy;
   double cutoffRadius;
   double alpha;
   Potential vPotential;

   public Scatter(int lmax, double energy, Potential vPotential, double cutoffRadius, double alpha) {
      this.lmax = lmax;
      this.energy = energy;
      this.cutoffRadius = cutoffRadius;
      this.alpha = alpha;
      this.vPotential = vPotential;
   }

   public double getCrossSection(double[] rArray, double u0, double dotu0) {
      double crossSection = 0.0;
      double k = Math.sqrt(energy * alpha);
      for (int l = 0; l <= lmax; l++) {
         RadialSchrodinger rs = new RadialSchrodinger(l, energy, vPotential, cutoffRadius, alpha);
         double dl = rs.getPhaseShift(rArray, u0, dotu0);
         double term = 4 * Math.PI / (k * k) * (2 * l + 1) * Math.pow(Math.sin(dl), 2);
         crossSection += term;
      }
      return crossSection;
   }

}
