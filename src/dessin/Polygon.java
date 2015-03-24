package dessin;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JPanel;


public class Polygon_app extends JPanel implements MouseListener, MouseMotionListener  {
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	int[] xcoord, ycoord;
      int draggedPoint = -1;
      int sommet = Option.getSommet();
      Point2D[] point;
      
      Graphics2D g3;
      
      private Point mouse = new Point();
      Polygon_app() {
         setBackground(Color.WHITE);
         
         addMouseListener(this);
         addMouseMotionListener(this);
         
      }
      
    /*  Polygon_app(int s) {
          setBackground(Color.WHITE);
          
          addMouseListener(this);
          addMouseMotionListener(this);
          
       }*/
	
	public void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2 = (Graphics2D)g;
         if (xcoord == null) {
            xcoord = new int[] { scaleX(0.2), scaleX(0.8), scaleX(0.5),scaleX(0.6),scaleX(0.4),
                               scaleX(0.95), scaleX(0.35), scaleX(0.1) ,scaleX(0.3),scaleX(0.55)};
            ycoord = new int[] { scaleY(0.15), scaleY(0.1), scaleY(0.5),scaleY(0.75),scaleY(0.4),
                               scaleY(0.45), scaleY(0.9), scaleY(0.7), scaleY(0.3),scaleY(0.25)};
         }
        
         g2.fillPolygon(xcoord, ycoord, sommet);
         g2.setColor(Color.BLACK);
         g2.setStroke(new BasicStroke(2));
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                   RenderingHints.VALUE_ANTIALIAS_ON);
         g2.drawPolygon(xcoord, ycoord, sommet);
         for (int i = 0; i < sommet; i++)
            g2.fillRect(xcoord[i] - 3, ycoord[i] - 3, 7, 7);
         g2.drawString( (mouse.x) + ", " + (mouse.y) , 0, 10);
         repaint();
      }
      private int scaleX(double x) {
         return (int)(x * getWidth());
      }
      private int scaleY(double y) {
         return (int)(y * getHeight());
      }
      public void mousePressed(MouseEvent e) {
         draggedPoint = -1;
         for (int i = 0; i < sommet; i++) {
            if (Math.abs(xcoord[i] - e.getX()) < 4 && Math.abs(ycoord[i] - e.getY()) < 4) {
               draggedPoint = i;
               break;
            }
         }
       
      }
      public void mouseDragged(MouseEvent e) {
         if (draggedPoint < 0)
            return;
         int x = Math.max(0, Math.min(e.getX(),getWidth()));
         int y = Math.max(0, Math.min(e.getY(),getHeight()));
         xcoord[draggedPoint] = x;
         ycoord[draggedPoint] = y;
         
         repaint();
      }
      public void mouseReleased(MouseEvent e) { }
      public void mouseMoved(MouseEvent e) {  mouse = e.getPoint();
      e.getComponent().repaint();}
      public void mouseClicked(MouseEvent e) { }
      public void mouseEntered(MouseEvent e) { }
      public void mouseExited(MouseEvent e) { }
      
      
      /*
       * Fonction de calcul d'aire
      public static double area(Point2D[] polyPoints) {
  		int i, j, n = polyPoints.length;
  		double area = 0;

  		for (i = 0; i < n; i++) {
  			j = (i + 1) % n;
  			area += polyPoints[i].getX() * polyPoints[j].getY();
  			area -= polyPoints[j].getX() * polyPoints[i].getY();
  		}
  		area /= 2.0;
  		return (area);
  	}*/
   

  
   
   
  

 


}

