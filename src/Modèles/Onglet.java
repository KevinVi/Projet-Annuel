package Modèles;

import javax.swing.JTabbedPane;

/**
 * Estimate maker java application with GUI. 
 * This class create a tab on the middle frame
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Onglet extends JTabbedPane {
	
	private static final long serialVersionUID = 8788925136522063704L;
	public Polygon poly;
	
	/**
	 * Default constructor of the class Onglet
	 * 
	 **/
	public Onglet(){
		this.poly = new Polygon();
		this.addTab("Dessin 1", poly);
	}
	
}
