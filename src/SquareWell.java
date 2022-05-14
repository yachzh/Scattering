
public class SquareWell implements Potential {
   double width;
   double depth;

   public SquareWell(double width, double depth) {
      this.width = width;
      this.depth = depth;
   }

   @Override
   public double getValue(double r) {
      if (r < width) {
         return -depth;
      } else {
         return 0;
      }
   }

}
