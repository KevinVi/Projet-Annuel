package Modèles;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Vue.Resume;
import Contrôleur.Option;


public class Polygon extends JPanel implements MouseListener, MouseMotionListener  {
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int[] xcoord, ycoord;
	String[] color;
	private Label label;
    int draggedPoint = -1;
      int sommet =0;
      Point2D[] point;
      JPanel coordonnee ;
      private static TextArea t;
      Graphics2D g3;
      private Point mouse = new Point();
      //getSelectedIndex() recupération de l'onglet en cour
      //Constructeur
      public Polygon() {
    	  
         this.setBackground(Color.WHITE);
         //this.add(new JLabel(new ImageIcon("img/quadri.jpg")));
         this.addMouseListener(this);
         this.addMouseMotionListener(this);
         
      }
	
	public void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2 = (Graphics2D)g;
         if (xcoord == null) {
            xcoord = new int[] { scaleX(0.1), scaleX(0.3), scaleX(0.4),scaleX(0.75),scaleX(0.9),
                               scaleX(0.75), scaleX(0.6), scaleX(0.3) ,scaleX(0.2),scaleX(0.1)};
            ycoord = new int[] { scaleY(0.5), scaleY(0.4), scaleY(0.2),scaleY(0.3),scaleY(0.6),
                               scaleY(0.7), scaleY(0.9), scaleY(0.7), scaleY(0.6),scaleY(0.55)};
         }
         color = new String[]{"Color.BLUE","Color.RED","Color.YELLOW","Color.BLACK"};
         g2.fillPolygon(xcoord, ycoord, sommet);
         g2.setColor(Color.BLUE);
         g2.setStroke(new BasicStroke(2));
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                   RenderingHints.VALUE_ANTIALIAS_ON);
         g2.drawPolygon(xcoord, ycoord, sommet);
         for (int i = 0; i < sommet; i++){
            g2.fillRect(xcoord[i] - 3, ycoord[i] - 3, 7, 7);
           
         }
         g2.drawString( (mouse.x) + ", " + (mouse.y) , 5, 13);
         g2.setColor(Color.decode("#2766A1"));
         g2.drawRect(0, 0, 60, 15);
         repaint();
         double area = this.area();
         t = new TextArea(""+area);
         Resume.setT(t);
         //System.out.println(this.area());
         
         //affichage des longueur segment
         java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
         moitierX();
         moitierY();
         g2.setColor(Color.decode("#E90101"));
         
        
         g2.drawString( ""+df.format(longueur()) ,moitierX(), moitierY());
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
      // System.out.println(xcoord[draggedPoint]+" et "+ycoord[draggedPoint]);
       System.out.println(draggedPoint);
       System.out.println(this.area());
       double area = this.area();
       t = new TextArea(""+area);
       Resume.setT(t);
       
       longueur();
       
       
      }
      
      public void setSommet(int s){
    	  this.sommet =s;
      }
      
      public void mouseReleased(MouseEvent e) { }
      public void mouseMoved(MouseEvent e) {  mouse = e.getPoint();
      e.getComponent().repaint();}
      public void mouseClicked(MouseEvent e) { }
      public void mouseEntered(MouseEvent e) { }
      public void mouseExited(MouseEvent e) { }
    
      public double area() {
			int i, j, n = sommet;
		double area = 0;

		for (i = 0; i < n; i++) {
			j = (i + 1) % n;
			area += xcoord[i] * ycoord[j];
			area -=xcoord[j] * ycoord[i];
		}
		area /= 2.0;
		return (Math.abs(area));
		}
	public double longueur(){
	    double res,lx,ly = 0.0;
	    lx = xcoord[1] - xcoord[2];
	    ly = ycoord[1] - ycoord[2];
	    res= Math.sqrt((lx*lx)+(ly*ly));
	    return res;
	
	}
	public int moitierX(){
		int lx= 0;
	    lx = xcoord[1] - xcoord[2];
	    lx = Math.abs(lx);
	    lx = lx / 2;
	    if (xcoord[1]<xcoord[2]){
       	 lx += xcoord[1];
        }else{
       	 lx += xcoord[2];
        }
	    
	    return lx;
	}
	public int moitierY(){
		int ly= 0;
		ly = ycoord[1] - ycoord[2];
		ly = Math.abs(ly);
	    ly = ly / 2;
	    if (ycoord[1]<ycoord[2]){
       	 ly += ycoord[1];
        }else{
       	 ly += ycoord[2];
        }
	    return ly;
	}
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
  		public double area() {
  			int i, j, n = sommet;
  		double area = 0;

  		for (i = 0; i < n; i++) {
  			j = (i + 1) % n;
  			area += xcoord[i] * ycoord[j];
  			area -=xcoord[j] * ycoord[i];
  		}
  		area /= 2.0;
  		return (area);
  		}
  	}*/
      /*
       public double area() {
        double sum = 0.0;
        for (int i = 0; i < 5; i++) {
            sum = sum + (xcoord[i] * ycoord[i+1]) - (ycoord[i] * xcoord[i+1]);
        }
        return 0.5 * sum;
    }
       */
	 /* public double area() {
    double sum = 0.0;
    for (int i = 0; i < sommet; i++) {
        sum = sum + (xcoord[i] * ycoord[i+1]) - (ycoord[i] * xcoord[i+1]);
    }
    return 0.5 * sum;
}*/

  
   
   
  

 


}

