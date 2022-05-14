
public class HarmonicPotentialTestDrive {

   public static void main(String[] args) {
      double rmin = 1.0;
      double rmax = 5.0;
      int nPoint = 100;
      double dr = (rmax - rmin) / (nPoint - 1);
      double[] R = new double[nPoint];
      double[] F = new double[nPoint];

      for (int i = 0; i < nPoint; ++i) {
         R[i] = rmin + dr * i;
      }

      Potential harPotFunction = new HarmonicPotential();
      F = force(harPotFunction, R);
      JavaWrite jWrite = new JavaWrite(R, F);
      jWrite.plot();

   }

   public static double[] force(Potential f, double[] x) {
      int N = x.length;
      double[] testFunc = new double[N];

      for (int i = 0; i < N; i++) {
         testFunc[i] = f.getValue(x[i]);
      }
      return testFunc;
   }

}
