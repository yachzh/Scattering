
public class SphericalBesselTestDrive {

   public static void main(String[] args) {
       SphericalBessel jBessel = new SphericalBessel(5);
      
       double j5 = jBessel.getjn(1.5);
       double y5 = jBessel.getyn(1.5);
//      double j5 = SphericalBessel.jn(5, 1.5);
//      double y5 = SphericalBessel.yn(5, 1.5);
      System.out.println(j5);
      System.out.println(y5);
   }

}
