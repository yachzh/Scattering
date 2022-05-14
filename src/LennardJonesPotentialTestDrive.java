
public class LennardJonesPotentialTestDrive {

   public static void main(String[] args) {
      double rMin = 0.85;
      double rMax = 2.0;
      int numberOfPoint = 1000;
      double h = (rMax - rMin) / (numberOfPoint - 1);
      double[] R = new double[numberOfPoint];
      for (int i = 0; i < R.length; i++) {
         R[i] = rMin + h * i;
      }
      Potential ljPotential = new LennardJonesPotential(5.9, 1.0);
      ljPotential.visualize(R);
   }

}
