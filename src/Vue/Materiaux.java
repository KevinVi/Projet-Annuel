package Vue;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Modèles.Panneau;

/**
 * Estimate maker java application with GUI. This class includes the materials
 * pannel.
 *
 * @author Mohammad Saman, Vivor Kevin
 */
public class Materiaux extends JPanel {

	private static final long serialVersionUID = 8351621811785841748L;
	private static JTabbedPane onglet;
	public Resume resume;

	/**
	 * Default Constructor
	 * 
	 **/
	public Materiaux() {
		// Création de notre conteneur d'onglets
		onglet = new JTabbedPane();
		new JDOM2();
		//onglet.add("Sol", new Panneau("sol"));
		//onglet.add("Mur", new Panneau("mur"));
		
		// On ajoute ensuite les onglets au Jpanel
		this.add(onglet);
		this.setPreferredSize(new Dimension(180, 0));
	}
	
	public static void AddOnglet(String title, Panneau p){
		onglet.add(title, p);
	}

}