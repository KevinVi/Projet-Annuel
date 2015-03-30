package Vue;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Modèles.Panneau;

/**
* Estimate maker java application with GUI.
* This class includes the materials pannel.
*
* @author Mohammad Saman, Vivor Kevin
*/
 public class Materiaux extends JPanel {
	
	private JTabbedPane onglet;
	public static Resume resume;
	   
	/**	
	* Constructor
	* @param
	* @return
	* 
	**/
	public Materiaux(){
	//Création de notre conteneur d'onglets
    onglet = new JTabbedPane();
	    onglet.add("Sol", new Panneau("sol"));
	    onglet.add("Mur", new Panneau("mur"));
    
    //On ajoute ensuite les onglets au Jpanel
    this.add(onglet);
    this.setPreferredSize(new Dimension(180,0));
  }
	public Materiaux(Resume res){
		this.resume =res;
		//Création de notre conteneur d'onglets
	    onglet = new JTabbedPane();
		    onglet.add("Sol", new Panneau("sol"));
		    onglet.add("Mur", new Panneau("mur"));
	    
	    //On ajoute ensuite les onglets au Jpanel
	    this.add(onglet);
	    this.setPreferredSize(new Dimension(180,0));
	  }
	 
 }