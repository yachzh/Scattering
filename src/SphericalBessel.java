
import jdistlib.math.Bessel;

/**
 * @author yachao
 * @apiNote Evaluate spherical Bessel functions of order n: first kind jn and
 *          second kind yn (sometimes also denoted nn)
 * @version last update Apr 27, 2022 5:13:24 PM
 *
 */
public class SphericalBessel {
   int n;

   public SphericalBessel(int n) {
      this.n = n;
   }

   public double getjn(double x) {
      return Math.sqrt(Math.PI / (2 * x)) * Bessel.j(x, n + 0.5);
   }

   public double getyn(double x) {
      return Math.sqrt(Math.PI / (2 * x)) * Bessel.y(x, n + 0.5);
   }

   public static double jn(int order, double x) {
      SphericalBessel bj = new SphericalBessel(order);
      return bj.getjn(x);
   }

   public static double yn(int order, double x) {
      SphericalBessel bj = new SphericalBessel(order);
      return bj.getyn(x);
   }
}
