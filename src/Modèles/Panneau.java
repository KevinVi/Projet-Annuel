package Modèles;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Estimate maker java application with GUI. This class includes radio button
 * for the materials.
 *
 * @author Mohammad Saman, Vivor Kevin
 */
public class Panneau extends JPanel {

	private static final long serialVersionUID = 6986414199300820696L;
	private String nom;
	private static ButtonGroup bgroup = new ButtonGroup();
	

	//public URL chemin = getClass().getResource("/img/mat/icon/sol1.jpg");

	public Panneau(String nom) {
		this.setNom(nom);
		this.setPreferredSize(new Dimension(180, 70));
	}
	
	public void AddBoutonPanneau(String title, String imgChemin, String img, int i){
		this.setLayout(new GridLayout(i, 1));
		JRadioButton t = new JRadioButton(title);
		bgroup.add(t);
		this.add(t);
		JLabel j = new JLabel(new ImageIcon(imgChemin));
		j.setToolTipText("<html><body><img src='"+img+"' width=500 height=500></body></html>");
		this.add(j);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
