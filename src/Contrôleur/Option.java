package Contrôleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import Modèles.Onglet;
import Modèles.Polygon;
import Vue.Menu;
/**
* Estimate maker java application with GUI.
* This class handles the buttons on the right side, so choose the types of drawings.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Option extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JButton btnDessiner,btnEffacer, btnAjoutOnglet;
	private String[] point = {"3","4","5","6","7","8","9","10"};
	private JPanel pan;
	private Box panneauBouton;
	private Onglet dessin;
	private Polygon poly;
	private int i=1;
	/**	
	* Constructor
	*@param
	*@return
	*
	**/
	
	public Option(Onglet draw)
	{
		this.dessin=draw;
		
		btnDessiner = new JButton("Dessiner");
		btnAjoutOnglet = new JButton("New Onglet");
		btnEffacer = new JButton("Effacer");
 		pan = new JPanel();
 		
		pan.setLayout(new GridLayout(3,1));
		pan.add(btnDessiner);
		btnDessiner.addActionListener(this);
		pan.add(btnAjoutOnglet);
		btnAjoutOnglet.addActionListener(this);
		pan.add(btnEffacer);
		btnEffacer.addActionListener(this);
		
		panneauBouton = Box.createVerticalBox();
		panneauBouton.add(pan);
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(new JSeparator(JSeparator.HORIZONTAL));
		
		//this = JPanel
		this.add(panneauBouton,BorderLayout.CENTER );
		this.setBackground(Color.decode("#2766A1"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btnDessiner){
			   
			this.dessin.poly.setSommet(Integer.parseInt((String) JOptionPane.showInputDialog(null, "Combien de sommets posséde votre pièce ?", "Créer un polygone", JOptionPane.QUESTION_MESSAGE, null, point,point[0])));    
			Menu.getSave().setEnabled(true);;
		}
		else if(e.getSource()==btnEffacer){	 
			
		}
		else if(e.getSource()==btnAjoutOnglet){	 
			i = i+1;
			poly = new Polygon();
			this.dessin.addTab("Dessin "+i, poly);
			//this.dessin.getSelectedComponent().equals("Dessin "+i);
			//this.dessin.poly.setSommet(Integer.parseInt((String) JOptionPane.showInputDialog(null, "Nombre de sommet", "Polygone", JOptionPane.QUESTION_MESSAGE, null, point,point[2])));
		}
	}
	
}
