public class Solver {
   /**
    * Numerov method for solving equations of the type \ddot x(t) = f(t)x(t)
    *
    * @param f  a <code>double[]</code> value
    * @param h  a <code>double</code> value
    * @param x0 a <code>double</code> value
    * @param x1 a <code>double</code> value
    * @return a <code>double[]</code> value
    */
   public static double[] numerov(double[] f, double h, double x0, double x1) {
      double[] x = new double[f.length];
      x[0] = x0;
      x[1] = x1;
      double h2 = h * h;
      double h12 = h2 / 12;
      double w0 = x[0] * (1 - h12 * f[0]);
      double w1 = x[1] * (1 - h12 * f[1]);
      double w2 = 2 * w1 - w0 + h2 * x[1] * f[1];

      for (int i = 2; i < f.length; ++i) {
         x[i] = w2 / (1 - h12 * f[i]);
         // check for divergence
         if (Math.abs(x[i]) > 1.e6) {
            throw new ArithmeticException("NUMEROV solver did not converge!");
         }
         w0 = w1;
         w1 = w2;
         w2 = 2 * w1 - w0 + h2 * x[i] * f[i];
      }

      return x;
   }
}
