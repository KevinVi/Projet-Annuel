package vue;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modeles.Panneau;
import pluging.JDOM;

/**
 * Estimate maker java application with GUI. 
 * This class includes the materials panel.
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Materiaux extends JPanel {

	private static final long serialVersionUID = 8351621811785841748L;
	private static JTabbedPane onglet;

	/**
	 * Default Constructor of the class Materiaux
	 * 
	 **/
	public Materiaux() {
		// Création de notre conteneur d'onglets
		onglet = new JTabbedPane();
		new JDOM();
		
		// On ajoute ensuite les onglets au Jpanel
		this.add(onglet);
		this.setPreferredSize(new Dimension(180, 0));
	}
	
	/**
	 * Methode for insert a new tab in onglet
	 * @param title	:	title of the new tab
	 * @param p	:		the content of the new tab
	 * 
	 **/
	public static void AddOnglet(String title, Panneau p){
		onglet.add(title, p);
	}

}