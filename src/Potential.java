import org.opensourcephysics.frames.PlotFrame;

public interface Potential {
   public double getValue(double r);

   default void visualize(double[] rArray) {
      PlotFrame frame = new PlotFrame("r", "V(r)", "Potential");
      for (double r : rArray) {
         frame.append(0, r, getValue(r));
      }
      frame.setVisible(true);
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
   }
}
