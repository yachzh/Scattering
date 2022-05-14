import org.opensourcephysics.frames.PlotFrame;

/**
 * @author yachao
 * @apiNote Eigenfunctions for square well potentials with different depth
 * @version last update Apr 23, 2022 6:59:47 PM
 *
 */
public class RadialSchrodingerTestDriveSquare {

   public static void main(String[] args) {
      int l = 0;
      double potWidth = 1.0;
      double eigenValue = 2.0;
      double rMax = 5.0;
      int N = 1000;
      double h = rMax / (N - 1);
      double u0 = 0;
      double u1 = Math.pow(h, l + 1);
      double[] R = new double[N];
      double initialPosition = 0;
      for (int i = 0; i < N; i++) {
         R[i] = initialPosition + h * i;
      }
      PlotFrame frame = new PlotFrame("r", "phi", "eigenstate");
      double[] potDepth = { 0, 10, 205 };
      int k = 0;
      for (double d : potDepth) {
         Potential sqPotential = new SquareWell(potWidth, d);
         RadialSchrodinger sqRadialSchrodinger = new RadialSchrodinger(l, eigenValue, sqPotential, potWidth);
         double[] phi = sqRadialSchrodinger.getState(R, u0, u1);
         for (int i = 0; i < N; i++) {
            frame.append(k, R[i], phi[i]);
         }
         k++;
      }

      frame.setVisible(true);
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
   }

}
