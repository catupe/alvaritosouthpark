import javax.swing.*;        // For JPanel, etc.
import java.awt.*;           // For Graphics, etc.
import java.awt.geom.*;      // For Ellipse2D, etc.

/*  Ejemplo simple usando  java2d
*/

public class EjemploSimple extends JPanel {
  private Rectangle2D.Double square1 =
    new Rectangle2D.Double(10, 10, 150, 200);

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.setPaint(Color.black);
    g2d.draw(square1);
    g2d.translate(185.0, 0.0);
    g2d.draw(square1);
    g2d.setPaint(Color.gray);
    g2d.fill(new Rectangle2D.Double(40,80,100,100));
    g2d.rotate(0.2);
    setFont(new Font("Helvetica",0,18));
    g2d.drawString("HOLA MUNDO", 25, 40);
  }

  public static void main(String[] args) {
    WindowUtilities.openInJFrame(new EjemploSimple(), 500, 500);
  }
}
