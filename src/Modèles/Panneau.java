package Mod�les;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class Panneau extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6986414199300820696L;
	@SuppressWarnings("unused")
	private String nom;
	private static double prix;
	private String img;
	private JRadioButton t;
	private ButtonGroup bgroup = new ButtonGroup();
	private String tabMatiere[][]= new String[10][10];

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
	public void AddBoutonPanneau(String title, String imgChemin, String img, int i, int prix){
		this.setLayout(new GridLayout(i, 1));
		this.t = new JRadioButton(title);
		this.bgroup.add(t);
		this.add(t);
		this.t.addActionListener(this);
		JLabel j = new JLabel(new ImageIcon(imgChemin));
		this.setImg(imgChemin);
		this.setPrix(prix);
		j.setToolTipText("<html><body><img src='"+img+"' width=500 height=500></body></html>");
		this.add(j);
		
	}
	
	
	/**
	 * Setter of the name of the tab
	 * @param nom :		name of the tab
	 * 
	 **/
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Setter of the price
	 * @param p :	the price 
	 * 
	 **/
	public void setPrix(int p) {
		this.prix = p;
	}

	/**
	 * Getter of the price
	 * @return prix :	the price 
	 * 
	 **/
	public static double getPrix() {
		return prix;
	}

	/**
	 * Getter of the price
	 * @return img :	the picture path 
	 * 
	 **/
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			Polygon.setPrice(getPrix());
	}
	

}
