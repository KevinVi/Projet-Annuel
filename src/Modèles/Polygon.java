package Modèles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Vue.Menu;
/**
 * Estimate maker java application with GUI. 
 * This class create a polygon, displays these dimensions,
 * calculate its area and multiple it in fonction of the price
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Polygon extends JPanel implements MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;

	protected static int[] xcoord;
	protected static int[] ycoord;
	protected static double price = 0;
	protected static int echelle = 100;
	private int draggedPoint = -1;
	protected static String color;
	protected static int sommet = 0;
	private Image img;
	private Point mouse = new Point();
	private Font font;

	java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");

	/**
	 * Default Constructor of the class Polygon
	 */
	public Polygon() {

		img = Toolkit.getDefaultToolkit().createImage("img/quadri.jpg");
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		color=  "#2766A1";
		int fontsize = 13;
        font = new Font("Helvetica",Font.BOLD,fontsize);
	}

	/**
	 * Methode paintComponent
	 * design the drawing , and display it with the area and the value and the position of the mouse
	 * @param g : type graphique
	 */
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
		
		g2.setColor(Color.decode(color));
		g2.fillPolygon(xcoord, ycoord, sommet);

		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(2));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawPolygon(xcoord, ycoord, sommet);
		for (int i = 0; i < sommet; i++) {
			g2.fillRect(xcoord[i] - 3, ycoord[i] - 3, 7, 7);

		}

		g2.setColor(Color.decode("#2766A1"));
		g2.drawRect(0, 0, 60, 15);
		g2.setColor(Color.WHITE);
		g2.fillRect(1, 1, 59, 14);
		g2.setColor(Color.decode("#2766A1"));
		g2.drawString((mouse.x) + ", " + (mouse.y), 5, 13);
		
		g2.setColor(Color.decode("#2766A1"));
		g2.drawRect(724, 639,103, 16);
		g2.setColor(Color.WHITE);
		g2.fillRect(725, 640, 102, 15);
		g2.setColor(Color.decode("#2766A1"));
		g2.drawString("Valeur : " + df.format(prix()) + " €", 728, 653);
		
		g2.setColor(Color.decode("#2766A1"));
		g2.drawRect(2, 639,91, 16);
		g2.setColor(Color.WHITE);
		g2.fillRect(3, 640, 90, 15);
		g2.setColor(Color.decode("#2766A1"));
		g2.drawString("Aire : " + df.format(area()) + " m²", 5,653);
		
		g2.setColor(Color.BLACK);
		int i = 0;
		while (i < sommet - 1) {
			g2.setFont(font); 
			g2.drawString("" + df.format(longueur(i)), moitierX(i), moitierY(i));
			i++;
			if (i == sommet - 1) {
				g2.drawString("" + df.format(longueurlast(i)), moitierXlast(i),
						moitierYlast(i));
			}

		}
		
		repaint();
	}
	/**
	 *Methode scaleX
	 *set the position  X
	 *@param x  : position  X
	 *@return the position X
	 */
	private int scaleX(double x) {
		return (int) (x * getWidth());
	}
	/**
	 *Methode scaleY
	 *set the position  Y
	 *@param y  : position  Y
	 *@return the position Y
	 */
	private int scaleY(double y) {
		return (int) (y * getHeight());
	}

	/**
	 *Methode mousePressed
	 *set the position of one of the corner 
	 *@param e : Mouse event
	 */
	public void mousePressed(MouseEvent e) {
		draggedPoint = -1;
		for (int i = 0; i < sommet; i++) {
			if (Math.abs(xcoord[i] - e.getX()) < 4
					&& Math.abs(ycoord[i] - e.getY()) < 4) {
				draggedPoint = i;
				break;
			}
		}
		Menu.getSave().setEnabled(true);
	}
	/**
	 *Methode mouseDragged
	 *set the position of one of the corner 
	 *while the mouse moving
	 *@param e : Mouse event
	 */
	
	public void mouseDragged(MouseEvent e) {
		if (draggedPoint < 0)
			return;
		int x = Math.max(0, Math.min(e.getX(), getWidth()));
		int y = Math.max(0, Math.min(e.getY(), getHeight()));
		xcoord[draggedPoint] = x;
		ycoord[draggedPoint] = y;

		repaint();

	}

	
	public void mouseReleased(MouseEvent e) {
	}
	/**
	 *Methode mouseMoved
	 *set the position of the mouse 
	 *while the mouse moving
	 *@param e : Mouse event
	 */
	public void mouseMoved(MouseEvent e) {
		mouse = e.getPoint();
		e.getComponent().repaint();
	}
	/**
	 *Methode mouseClicked
	 *methode know if the mouse has been clicked
	 *@param e : Mouse event
	 */
	public void mouseClicked(MouseEvent e) {
	}
	/**
	 *Methode mouseClicked
	 *methode know if the mouse entered
	 *@param e : Mouse event
	 */
	public void mouseEntered(MouseEvent e) {
	}
	/**
	 *Methode mouseClicked
	 *methode know if the mouse exist
	 *@param e : Mouse event
	 */
	public void mouseExited(MouseEvent e) {
	}
	/**
	 *Methode area
	 *calculate the area of the drawing using the formula to calculate the area of a polygon
	 *@return the area
	 */
	public double area() {
		int i, j, n = sommet;
		double area = 0;

		for (i = 0; i < n; i++) {
			j = (i + 1) % n;
			area += xcoord[i] * ycoord[j];
			area -= xcoord[j] * ycoord[i];
		}
		area /= 2.0;
		return (Math.abs(area) / (echelle * 100));
	}
	/**
	 *Methode prix
	 *Multiple the area with the price
	 *@return result : the price
	 */
	public double prix() {
		double area = area();
		double result = 0;
		//double prix = Panneau.getPrix();
		result = area * price;
		
		//System.out.println(price);
		return result;
	}

	/**
	 *Methode longueur
	 *calculate the lenght of the line between 2 corners
	 *@param sommet : corner of the drawing
	 *@return the lenght
	 */
	public double longueur(int sommet) {
		double res, lx, ly = 0.0;
		lx = xcoord[sommet] - xcoord[sommet + 1];
		ly = ycoord[sommet] - ycoord[sommet + 1];
		res = Math.sqrt((lx * lx) + (ly * ly));
		return res / echelle;

	}
	
	/**
	 *Methode moitierX
	 *calculate the middle point X of the line between 2 corners
	 *@param sommet : corner of the drawing
	 *@return lx    : point X
	 */
	public int moitierX(int sommet) {
		int lx = 0;
		lx = xcoord[sommet] - xcoord[sommet + 1];
		lx = Math.abs(lx);
		lx = lx / 2;
		if (xcoord[sommet] < xcoord[sommet + 1]) {
			lx += xcoord[sommet];
		} else {
			lx += xcoord[sommet + 1];
		}
		return lx;
	}
	/**
	 *Methode moitierY
	 *calculate the middle point Y of the line between 2 corners
	 *@param sommet : corner of the drawing
	 *@return ly    : point Y
	 */
	public int moitierY(int sommet) {
		int ly = 0;
		ly = ycoord[sommet] - ycoord[sommet + 1];
		ly = Math.abs(ly);
		ly = ly / 2;
		if (ycoord[sommet] < ycoord[sommet + 1]) {
			ly += ycoord[sommet];
		} else {
			ly += ycoord[sommet + 1];
		}
		return ly;
	}
	/**
	 *Methode longueurlast
	 *calculate the lenght of the line between the first and the last
	 *@param sommet : corner of the drawing
	 *@return the lenght
	 */
	public double longueurlast(int sommet) {
		double res, lx, ly = 0.0;
		lx = xcoord[sommet] - xcoord[0];
		ly = ycoord[sommet] - ycoord[0];
		res = Math.sqrt((lx * lx) + (ly * ly));
		return res / echelle;

	}
	/**
	 *Methode moitierXlast
	 *calculate the middle point X of the line between the first and the last
	 *@param sommet : corner of the drawing
	 *@return lx    : point X
	 */
	public int moitierXlast(int sommet) {
		int lx = 0;
		lx = xcoord[sommet] - xcoord[0];
		lx = Math.abs(lx);
		lx = lx / 2;
		if (xcoord[sommet] < xcoord[0]) {
			lx += xcoord[sommet];
		} else {
			lx += xcoord[0];
		}
		return lx;
	}
	/**
	 *Methode moitierYlast
	 *calculate the middle point Y of the line between the first and the last
	 *@param sommet : corner of the drawing
	 *@return ly    : point Y
	 */
	public int moitierYlast(int sommet) {
		int ly = 0;
		ly = ycoord[sommet] - ycoord[0];
		ly = Math.abs(ly);
		ly = ly / 2;
		if (ycoord[sommet] < ycoord[0]) {
			ly += ycoord[sommet];
		} else {
			ly += ycoord[0];
		}
		return ly;
	}
	/**
	 *Methode get_json
	 * use the json object to stock the value of the drawing
	 *@return obj2 : the json object
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject get_json() {
		int i = 0;

		JSONObject obj2 = new JSONObject();
		JSONArray ArrayOnglet = new JSONArray();

		for (i = 0; i < sommet; i++) {
			ArrayOnglet.add("" + xcoord[i]);

		}
		for (i = 0; i < sommet; i++) {
			ArrayOnglet.add("" + ycoord[i]);

		}
		obj2.put("Nom_onglet", ArrayOnglet);
		return obj2;
	}
	/**
	 *Methode createImage
	 *create a buffered image of the jpanel
	 *@return bi  :  buffered image
	 */
	public BufferedImage createImage(JPanel panel) {

		BufferedImage bi = new BufferedImage(830, 653,
				BufferedImage.TYPE_INT_ARGB);

		Graphics g = bi.getGraphics();

		paintComponent(g);

		return (bi);

	}
	/**
	 * Getter for the price
	 * @return price : 	the price of the selected material
	 * 
	 **/
	public double getPrice() {
		return price;
	}
	/**
	 * Setter for the price
	 * @param prix : 	the price of the selected material
	 * 
	 **/
	public static void setPrice(double prix) {
		price = prix;
	}
	/**
	 * Setter for the number of  corners
	 * @param s : 	number of corners
	 * 
	 **/
	public void setSommet(int s) {
		Polygon.sommet = s;
	}
	/**
	 * Getter for the color of the drawing
	 * @return color : 	color of the drawing
	 * 
	 **/
	public String getColor() {
		return color;
	}
	/**
	 * Setter for the color of the drawing
	 * @param c : 	color of the drawing
	 * 
	 **/
	public static void setColor(String c) {
		color = c;
	}


}
