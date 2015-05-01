package Modèles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolTip;

import Contrôleur.ResumePanneau;

/**
 * Estimate maker java application with GUI. This class includes radio button
 * for the materials.
 *
 * @author Mohammad Saman, Vivor Kevin
 */
public class Panneau extends JPanel {

	private static final long serialVersionUID = 6986414199300820696L;
	private String nom;

	private ButtonGroup bgroup = new ButtonGroup();
	private JRadioButton jrSol1 = new JRadioButton("Tapis doux");
	private JRadioButton jrSol2 = new JRadioButton("Parquet");

	private JRadioButton jrMur1 = new JRadioButton("Briques");
	private JRadioButton jrMur2 = new JRadioButton("Baroque");

	private JLabel imgSol1;
	private JLabel imgSol2;
	private JLabel imgMur1;
	private JLabel imgMur2;

	public URL chemin = getClass().getResource("/img/mat/icon/sol1.jpg");

	public Panneau(String nom) {
		this.nom = nom;
		this.setPreferredSize(new Dimension(180, 70));

		if (this.nom == "sol") {
			// On ajoute les boutons au groupe
			bgroup.add(jrSol1);
			bgroup.add(jrSol2);

			imgSol1 = new JLabel(new ImageIcon("img/mat/icon/sol1.jpg"));
			imgSol1.setToolTipText("<html><body><img src='http://cdn4.micasa.ch/medias/sys_master/zoom/c/5/6/7/id_9526227402782_zoom.jpg' width=500 height=500></body></html>");

			imgSol2 = new JLabel(new ImageIcon("img/mat/icon/sol2.jpg"));
			imgSol2.setToolTipText("<html><img src='http://www.bauwerk-parkett.com/parquet-images/floor/1200x800/3907/parquet-doussie-megapark-long-plank-1250x100x11mm.jpg' width=500 height=500></html>");

			this.setLayout(new GridLayout(2, 2));
			this.add(jrSol1);
			this.add(imgSol1);

			this.add(jrSol2);
			this.add(imgSol2);
		} else {
			// On ajoute les boutons au groupe
			bgroup.add(jrMur1);
			bgroup.add(jrMur2);
			imgMur1 = new JLabel(new ImageIcon("img/mat/icon/mur1.jpg"));
			imgMur1.setToolTipText("<html><body><img src='http://www.scenolia.com/3780/papier-peint-mur-brique.jpg' width=500 height=500></body></html>");

			imgMur2 = new JLabel(new ImageIcon("img/mat/icon/mur2.jpg"));
			imgMur2.setToolTipText("<html><body><img src='http://st.gdefon.com/wallpapers_original/368847_oboi_tekstura_fon_rabochij-stol_6500x4582_www.Gde-Fon.com.jpg' width=500 height=500></body></html>");

			this.setLayout(new GridLayout(2, 1));
			this.add(jrMur1);
			this.add(imgMur1);

			this.add(jrMur2);
			this.add(imgMur2);
		}
	}

	public JRadioButton getJrSol1() {
		return jrSol1;
	}

	public JRadioButton getJrSol2() {
		return jrSol2;
	}

	public JRadioButton getJrMur1() {
		return jrMur1;
	}

	public JRadioButton getJrMur2() {
		return jrMur2;
	}

}
