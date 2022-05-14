import org.opensourcephysics.frames.PlotFrame;

/**
 * @author yachao
 * @apiNote Calculate the total cross section as a function of the energy for a
 *          Lennard-Jones potential
 * @version last update May 2, 2022 8:49:39 PM
 *
 */
public class ScatterApp {

   public static void main(String[] args) {
      int lmax = 6;
      double emin = 0.2;
      double emax = 3.5;
      int ePoint = 1000;
      double eh = (emax - emin) / (ePoint - 1);
      double r0 = 0.85;
      double rCut = 5.0;
      double rMax = 10.0;
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

      PlotFrame frame = new PlotFrame("Energy [meV]", "Sigma [rho]", "Total cross section");

      for (int i = 0; i < ePoint; i++) {
         double energy = emin + eh * i;
         Scatter sc = new Scatter(lmax, energy, ljPotential, rCut, alpha);
         double sigma = sc.getCrossSection(R, u0, dotu0);
         frame.append(0, energy, sigma);
      }
      frame.setVisible(true);
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
   }

}
