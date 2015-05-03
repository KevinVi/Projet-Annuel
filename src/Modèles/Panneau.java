package Modèles;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Estimate maker java application with GUI. 
 * This class create radio buttons for the materials.
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Panneau extends JPanel {

	private static final long serialVersionUID = 6986414199300820696L;
	@SuppressWarnings("unused")
	private String nom;
	private ButtonGroup bgroup = new ButtonGroup();

	/**
	 * Constructor of the class Panneau
	 * @param nom :		name of the new tab
	 **/
	public Panneau(String nom) {
		this.setNom(nom);
		this.setPreferredSize(new Dimension(180, 250));
	}
	
	/**
	 * Methode for insert a new radio button in the new tab's panel
	 * @param title	:		title of the new radio button
	 * @param imgChemin	:	path of the icon
	 * @param img :			path of the largest picture
	 * @param i:			total number of radio button for this tab
	 * 
	 **/
	public void AddBoutonPanneau(String title, String imgChemin, String img, int i){
		this.setLayout(new GridLayout(i, 1));
		JRadioButton t = new JRadioButton(title);
		this.bgroup.add(t);
		this.add(t);
		JLabel j = new JLabel(new ImageIcon(imgChemin));
		j.setToolTipText("<html><body><img src='"+img+"' width=500 height=500></body></html>");
		this.add(j);
	}
	
	/**
	 * Setter for the name of the tab
	 * @param nom :		name of the tab
	 * 
	 **/
	public void setNom(String nom) {
		this.nom = nom;
	}

}
