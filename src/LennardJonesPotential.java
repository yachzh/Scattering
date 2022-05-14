
/**
 * @author yachao
 * @apiNote Lennard-Jones potential V(r)=epsilon*[(rho/r)^12 - 2*(rho/r)^6]
 *          epsilon=5.9 meV, rho=3.57 Ang the potential is expressed in units of
 *          meV and rho for energy and distance
 * @version last update Apr 23, 2022 11:10:17 PM
 *
 */
public class LennardJonesPotential implements Potential {
   double epsilon;
   double rho;

   public LennardJonesPotential(double epsilon, double rho) {
      this.epsilon = epsilon;
      this.rho = rho;
   }

   @Override
   public double getValue(double r) {
      double V = epsilon * (Math.pow(rho / r, 12) - 2 * Math.pow(rho / r, 6));
      return V;
   }

}
