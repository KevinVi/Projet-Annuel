package menuDessin;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
* Estimate maker java application with GUI.
* This class includes the materials pannel.
*
* @author Mohammad Saman, Vivor Kevin
*/
 public class Materiaux extends JPanel {
	
	private JTabbedPane onglet;
	   
	public Materiaux(){
		
    //Création de notre conteneur d'onglets
    onglet = new JTabbedPane();
	    onglet.add("Sol", new Panneau("sol"));
	    onglet.add("Mur", new Panneau("mur"));
    
    //On ajoute ensuite les onglets au Jpanel
    this.add(onglet);
	//this.setBackground(Color.decode("#2766A1"));
    this.setPreferredSize(new Dimension(180,0));
  }
	 
 }