package controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import vue.Menu;
import modeles.Onglet;

/**
 * Estimate maker java application with GUI. This class handles the buttons on
 * the right side, so choose the types of drawings.
 *
 * @author Mohammad Saman, Vivor Kevin
 */

public class Option extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnDessiner, btnRename;
	private String[] point = { "3", "4", "5", "6", "7", "8", "9", "10" };
	private JPanel pan, pan2, image;
	private Box panneauBouton;
	private Onglet dessin;
	private ImageIcon icon;
	private JLabel img;

	/**
	 * Constructor of the Class with 1 parameters
	 *
	 * @param draw: the target of the button
	 **/
	public Option(Onglet draw) {
		this.dessin = draw;

		this.btnDessiner = new JButton("Dessiner");
		this.btnRename = new JButton("Renommer");
		this.pan = new JPanel();
		this.pan2 = new JPanel();
		this.image = new JPanel();

		this.pan.setLayout(new GridLayout(1, 1));
		this.pan.add(btnDessiner);
		this.btnDessiner.addActionListener(this);

		this.pan2.setLayout(new GridLayout(1, 1));
		this.pan2.add(btnRename);
		this.btnRename.addActionListener(this);

		this.image.setLayout(new GridLayout(1, 1));
		this.icon = new ImageIcon(new ImageIcon("img/Desvis.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		this.img =new JLabel( icon);

		this.image.add(img);

		this.panneauBouton = Box.createVerticalBox();
		this.panneauBouton.add(Box.createVerticalStrut(20));
		this.panneauBouton.add(pan);
		this.panneauBouton.add(Box.createVerticalStrut(20));
		this.panneauBouton.add(new JSeparator(JSeparator.HORIZONTAL));
		this.panneauBouton.add(Box.createVerticalStrut(20));
		this.panneauBouton.add(pan2);
		this.panneauBouton.add(Box.createVerticalStrut(20));
		this.panneauBouton.add(new JSeparator(JSeparator.HORIZONTAL));
		this.panneauBouton.add(Box.createVerticalStrut(20));
		this.panneauBouton.add(image);

		// this = JPanel
		this.add(panneauBouton, BorderLayout.CENTER);
		this.setBackground(Color.decode("#2766A1"));
	}

	/**
	 * Methode called by the action listener
	 * @param e: the trigger of the event
	 * 
	 **/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDessiner) {

			int sommet = 0;

			String a = null;
			a = (String) JOptionPane.showInputDialog(null,
					"Combien de sommets posséde votre pièce ?",
					"Créer un polygone", JOptionPane.QUESTION_MESSAGE, null,
					point, point[0]);

			if (a != null) {
				sommet = Integer.parseInt(a);
				this.dessin.poly.setSommet(sommet);
				Menu.getSave().setEnabled(true);
			}
		} else if (e.getSource() == btnRename) {
			String i = JOptionPane.showInputDialog(null,
					"Veuillez entrer le nom de l'onglet", "Nom de l'onglet",
					JOptionPane.QUESTION_MESSAGE);

			this.dessin.setTitleAt(this.dessin.getSelectedIndex(), i);
		}

	}

}
