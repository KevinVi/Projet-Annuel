package menuDessin;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
* Estimate maker java application with GUI.
* This class includes radio button for the materials.
*
* @author Mohammad Saman, Vivor Kevin
*/
public class Panneau extends JPanel {
	
  private String nom;
  private ButtonGroup bgSol = new ButtonGroup();
  private JRadioButton jrSol1 = new JRadioButton("Sol 1");
  private JRadioButton jrSol2 = new JRadioButton("Sol 2");
  
  private ButtonGroup bgMur = new ButtonGroup();
  private JRadioButton jrMur1 = new JRadioButton("Mur 1");
  private JRadioButton jrMur2 = new JRadioButton("Mur 2");
  
  public Panneau(String nom){
	    this.nom = nom;
	  
	    if(this.nom == "sol")
	    {
	    	//Pré-selection d'un bouton        
	    	jrSol1.setSelected(true);
		    //On ajoute les boutons au groupe
		    bgSol.add(jrSol1);
		    bgSol.add(jrSol2);
		    this.setLayout(new GridLayout(2,1));
		    this.add(jrSol1);
		    this.add(jrSol2);
		    this.setPreferredSize(new Dimension(180,0));
	    }
	    else
	    {
	    	//Pré-selection d'un bouton       
	    	jrMur1.setSelected(true);
		    //On ajoute les boutons au groupe
	    	bgMur.add(jrMur1);
	    	bgMur.add(jrMur2);
		    this.setLayout(new GridLayout(2,1));
		    this.add(jrMur1);
		    this.add(jrMur2);
	    }
  }
 
}