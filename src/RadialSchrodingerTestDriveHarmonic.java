import org.opensourcephysics.frames.PlotFrame;

/**
 * @author yachao
 * @apiNote Eigenstate for harmonic potential
 * @version last update Apr 23, 2022 7:00:54 PM
 *
 */
public class RadialSchrodingerTestDriveHarmonic {

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
      for (int i = 0; i < N; i++) {
         R[i] = h * i;
      }
      Potential harPotential = new HarmonicPotential();
      RadialSchrodinger harRadialSchrodinger = new RadialSchrodinger(l, eigenValue, harPotential, rCut);
      double[] phi = harRadialSchrodinger.getState(R, u0, u1);
      PlotFrame frame = new PlotFrame("r", "phi", "eigenstate");

      for (int i = 0; i < N; i++) {
         frame.append(0, R[i], phi[i]);
      }
      frame.setVisible(true);
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
   }

}
