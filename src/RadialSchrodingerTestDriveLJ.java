import org.opensourcephysics.frames.PlotFrame;

/**
 * @author yachao
 * @apiNote Eigenstate for LJ potential
 * @version last update Apr 25, 2022 10:39:13 PM
 *
 */
public class RadialSchrodingerTestDriveLJ {

   public static void main(String[] args) {
      int l = 0;
      double eigenValue = 1.0;
      double r0 = 0.85;
      double rCut = 1.5;
      double rMax = 2.0;
      int N = 1000;
      double h = (rMax - r0) / (N - 1);
      double epsilon = 5.9;
      double alpha = 6.12;
      double factor = Math.sqrt(epsilon * alpha / 25);
      double u0 = Math.exp(-factor * Math.pow(r0, -5));
      double dotu0 = u0 * 5 * factor * Math.pow(r0, -6);

      double[] R = new double[N];
      for (int i = 0; i < N; i++) {
         R[i] = r0 + h * i;
      }
      Potential ljPotential = new LennardJonesPotential(5.9, 1.0);
      RadialSchrodinger ljRadialSchrodinger = new RadialSchrodinger(l, eigenValue, ljPotential, rCut, alpha);
      double u1 = ljRadialSchrodinger.getInitialState(r0, u0, dotu0, h);
      double[] phi = ljRadialSchrodinger.getState(R, u0, u1);
      PlotFrame frame = new PlotFrame("r", "phi", "eigenstate");

      for (int i = 0; i < N; i++) {
         frame.append(0, R[i], phi[i]);
      }
      frame.setVisible(true);
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
   }

}
