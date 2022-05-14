import org.opensourcephysics.frames.PlotFrame;

/**
 * @author yachao
 * @apiNote For harmonic potential V(r)=r^2, error of numeric solution with
 *          respect to the analytic
 * @version last update Apr 23, 2022 7:09:49 PM
 *
 */
public class RadialSchrodingerTestDriveError {

   public static void main(String[] args) {
      int l = 0;
      double eigenValue = 3.0;
      double rCut = 5.0;
      double rMax = 4.0;
      int N = 1000;
      double h = rMax / (N - 1);
      double u0 = 0;
      double u1 = Math.pow(h, l + 1);
      double[] R = new double[N];
      double[] phi = new double[N];
      double[] psi = new double[N];
      double initialPosition = 0;
      Potential harPotential = new HarmonicPotential();
      RadialSchrodinger harRadialSchrodinger = new RadialSchrodinger(l, eigenValue, harPotential, rCut);
      PlotFrame frame = new PlotFrame("r", "error on phi", "For comparison");

      for (int i = 0; i < N; i++) {
         R[i] = initialPosition + h * i;
         psi[i] = refFunction(R[i], h);
      }
      phi = harRadialSchrodinger.getState(R, u0, u1);
      for (int i = 0; i < N; i++) {
         frame.append(1, R[i], Math.abs(phi[i] - psi[i]));
      }
      frame.setVisible(true);
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
   }

   public static double refFunction(double r, double h) {
      double A = Math.exp(h * h / 2);
      return A * r * Math.exp(-r * r / 2);
   }

}
