package Modèles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Vue.Menu;

public class Polygon extends JPanel implements MouseListener,
		MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static int[] xcoord;
	protected static int[] ycoord;
	private String[] color;
	protected static double price = 0;
	protected static int echelle = 100;
	private int draggedPoint = -1;
	protected static int sommet = 0;
	private Image img;
	private Point mouse = new Point();

	java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");

	public Polygon() {

		img = Toolkit.getDefaultToolkit().createImage("img/quadri.jpg");
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

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

		g2.setColor(Color.decode("#E90101"));
		int i = 0;
		while (i < sommet - 1) {
			g2.drawString("" + df.format(longueur(i)), moitierX(i), moitierY(i));
			i++;
			if (i == sommet - 1) {
				g2.drawString("" + df.format(longueurlast(i)), moitierXlast(i),
						moitierYlast(i));
			}

		}
		g2.setColor(Color.decode("#2766A1"));
		g2.drawRect(724, 634,103, 16);
		g2.setColor(Color.WHITE);
		g2.fillRect(725, 635, 102, 15);
		g2.setColor(Color.decode("#2766A1"));
		g2.drawString("Valeur : " + df.format(prix()) + " €", 728, 648);
		repaint();
	}

	private int scaleX(double x) {
		return (int) (x * getWidth());
	}

	private int scaleY(double y) {
		return (int) (y * getHeight());
	}

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

	public void mouseDragged(MouseEvent e) {
		if (draggedPoint < 0)
			return;
		int x = Math.max(0, Math.min(e.getX(), getWidth()));
		int y = Math.max(0, Math.min(e.getY(), getHeight()));
		xcoord[draggedPoint] = x;
		ycoord[draggedPoint] = y;

		repaint();

	}

	public void setSommet(int s) {
		Polygon.sommet = s;
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		mouse = e.getPoint();
		e.getComponent().repaint();
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

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

	public double prix() {
		double area = area();
		double result = 0;
		double prix = Panneau.getPrix();
		result = area * price;
		
		//System.out.println(price);
		return result;
	}

	public double longueur(int sommet) {
		double res, lx, ly = 0.0;
		lx = xcoord[sommet] - xcoord[sommet + 1];
		ly = ycoord[sommet] - ycoord[sommet + 1];
		res = Math.sqrt((lx * lx) + (ly * ly));
		return res / echelle;

	}

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

	public double longueurlast(int sommet) {
		double res, lx, ly = 0.0;
		lx = xcoord[sommet] - xcoord[0];
		ly = ycoord[sommet] - ycoord[0];
		res = Math.sqrt((lx * lx) + (ly * ly));
		return res / echelle;

	}

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

	public BufferedImage createImage(JPanel panel) {

		BufferedImage bi = new BufferedImage(830, 653,
				BufferedImage.TYPE_INT_ARGB);

		Graphics g = bi.getGraphics();

		paintComponent(g);

		return (bi);

	}

	public double getPrice() {
		return price;
	}

	public static void setPrice(double prix) {
		price = prix;
	}

}
