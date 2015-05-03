package Modèles;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Vue.Menu;
import Vue.Resume;


public class Polygon extends JPanel implements MouseListener, MouseMotionListener  {
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int[] xcoord;

	static int[] ycoord;
	String[] color;
	private Label label;
	
	static int echelle=100;
    int draggedPoint = -1;
      static int sommet =0;
      Point2D[] point;
      JPanel coordonnee ;
      private static TextArea t;
      Graphics2D g3;
      Image img;
      private Point mouse = new Point();
     
      java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
      //getSelectedIndex() recupération de l'onglet en cour
      //Constructeur
      public Polygon() {
    	  
         this.setBackground(Color.WHITE);
         //this.add(new JLabel(new ImageIcon("img/quadri.jpg")));
         img = Toolkit.getDefaultToolkit().createImage("img/quadri.jpg");
         this.addMouseListener(this);
         this.addMouseMotionListener(this);
         /*int w =this.getWidth();

 		int h = this.getHeight();

 		//BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
      
 		paintImage = new BufferedImage(500,500, BufferedImage.TYPE_3BYTE_BGR);
         */
         
      }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Option;
		
		g.drawImage(img, 0, 0, null);

		if (xcoord == null) {
			xcoord = new int[] { scaleX(0.1), scaleX(0.3), scaleX(0.4),
					scaleX(0.75), scaleX(0.9), scaleX(0.75), scaleX(0.6),
					scaleX(0.3), scaleX(0.2), scaleX(0.1) };
			ycoord = new int[] { scaleY(0.5), scaleY(0.4), scaleY(0.2),
					scaleY(0.3), scaleY(0.6), scaleY(0.7), scaleY(0.9),
					scaleY(0.7), scaleY(0.6), scaleY(0.55) };
		}
		color = new String[] { "Color.BLUE", "Color.RED", "Color.YELLOW",
				"Color.BLACK" };
		// g2.setColor(Color.GREEN);
		
		g2.fillPolygon(xcoord, ycoord, sommet);
		
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(2));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawPolygon(xcoord, ycoord, sommet);
		for (int i = 0; i < sommet; i++) {
			g2.fillRect(xcoord[i] - 3, ycoord[i] - 3, 7, 7);

		}
		g2.drawString((mouse.x) + ", " + (mouse.y), 5, 13);
		g2.setColor(Color.decode("#2766A1"));
		g2.drawRect(0, 0, 60, 15);
		repaint();

		// System.out.println(this.area());

		// affichage des longueur segment

		g2.setColor(Color.decode("#E90101"));
		int i = 0;
		// g2.drawString( ""+df.format(longueurlast(2)) ,moitierXlast(2),
		// moitierYlast(2));
		while (i < sommet - 1) {
			g2.drawString("" + df.format(longueur(i)), moitierX(i), moitierY(i));
			i++;
			if (i == sommet - 1) {
				g2.drawString("" + df.format(longueurlast(i)), moitierXlast(i),
						moitierYlast(i));
			}

		}
		g2.setColor(Color.decode("#2766A1"));
		g2.drawRect(739, 634, 88, 16);
		g2.setColor(Color.WHITE);
		g2.fillRect(740, 635, 87, 15);
		g2.setColor(Color.decode("#2766A1"));
		g2.drawString("Valeur : "+df.format(prix())+" €" , 742, 648);
        //System.out.println(i);
        //g2.drawString( ""+df.format(longueurlast(i)) ,moitierXlast(i), moitierYlast(i));
        
      }
	public BufferedImage clip(Shape s, BufferedImage image)
	{
	Rectangle r = s.getBounds();
	int w = r.width;
	int h = r.height;
	int type = BufferedImage.TYPE_INT_ARGB_PRE;
	BufferedImage dst = new BufferedImage(w, h, type);
	Graphics2D g2 = dst.createGraphics();
	g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	g2.translate(-r.x, -r.y);
	g2.setClip(s);
	g2.drawImage(image, 0, 0, null);
	g2.dispose();
	return dst;
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
         Menu.getSave().setEnabled(true);
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
		return (Math.abs(area)/(echelle*100));
		}
      public double prix(){
    	  double price=0;
    	  double area=area();
    	  //double prix = Panneau.getPrice();
    	 // price=area*prix;
    	  
    	  return price;
      }
	public double longueur(int sommet){
	    double res,lx,ly = 0.0;
	    lx = xcoord[sommet] - xcoord[sommet+1];
	    ly = ycoord[sommet] - ycoord[sommet+1];
	    res= Math.sqrt((lx*lx)+(ly*ly));
	    return res/echelle;
	
	}
	public int moitierX(int sommet){
		int lx= 0;
	    lx = xcoord[sommet] - xcoord[sommet+1];
	    lx = Math.abs(lx);
	    lx = lx / 2;
	    if (xcoord[sommet]<xcoord[sommet+1]){
       	 lx += xcoord[sommet];
        }else{
       	 lx += xcoord[sommet+1];
        }
	   // System.out.println("voici lx:"+lx);
	    return lx;
	}
	public int moitierY(int sommet){
		int ly= 0;
		ly = ycoord[sommet] - ycoord[sommet+1];
		ly = Math.abs(ly);
	    ly = ly / 2;
	    if (ycoord[sommet]<ycoord[sommet+1]){
       	 ly += ycoord[sommet];
        }else{
       	 ly += ycoord[sommet+1];
        }
	   // System.out.println("voici ly:"+ly);
	    return ly;
	}
	public double longueurlast(int sommet){
	    double res,lx,ly = 0.0;
	    lx = xcoord[sommet] - xcoord[0];
	    ly = ycoord[sommet] - ycoord[0];
	    res= Math.sqrt((lx*lx)+(ly*ly));
	    return res/echelle;
	
	}
	public int moitierXlast(int sommet){
		int lx= 0;
	    lx = xcoord[sommet] - xcoord[0];
	    lx = Math.abs(lx);
	    lx = lx / 2;
	    if (xcoord[sommet]<xcoord[0]){
       	 lx += xcoord[sommet];
        }else{
       	 lx += xcoord[0];
        }
	  //  System.out.println("voici lx:"+lx);
	    return lx;
	}
	public int moitierYlast(int sommet){
		int ly= 0;
		ly = ycoord[sommet] - ycoord[0];
		ly = Math.abs(ly);
	    ly = ly / 2;
	    if (ycoord[sommet]<ycoord[0]){
       	 ly += ycoord[sommet];
        }else{
       	 ly += ycoord[0];
        }
	   // System.out.println("voici ly:"+ly);
	    return ly;
	}
	public static JSONObject get_json(){
		int i=0;
		JSONObject objx = new JSONObject();
		JSONObject objy = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONArray ArrayCoorX = new JSONArray();
		JSONArray ArrayCoorY = new JSONArray();
		JSONArray ArrayOnglet = new JSONArray();
		//for (i=0;i<sommet;i++){ pour chaque onglet
			
			for (i=0;i<sommet;i++){
				ArrayOnglet.add(""+xcoord[i]);
				
			}
			for (i=0;i<sommet;i++){
				ArrayOnglet.add(""+ycoord[i]);
				
			}
			obj2.put("Nom_onglet",ArrayOnglet);
			//obj2.put("Nom", this.dess)
			//obj.put("Sommet "+i,"Coordonné X :"+xcoord[i] +", Coordonnée Y : "+ycoord[i]);
		//}
		return obj2;
	}
	public  JSONObject create_devis(){

		JSONObject obj = new JSONObject();
		Dimension size = new Dimension(100,100);
		BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(myImage)));
	    return obj ;
	}

	public BufferedImage createImage(JPanel panel) {

		int w = panel.getWidth();

		int h = panel.getHeight();

		BufferedImage bi = new BufferedImage(830,653, BufferedImage.TYPE_INT_ARGB);

		Graphics g = bi.getGraphics();

		paintComponent(g);

		return (bi);

	}
	
	public Shape getshape(int som, int[]xcoor,int[]ycoor){
		
		
		
		
		return null;
		
		
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

