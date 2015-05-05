package controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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

	/**
	 * Constructor of the Class with 1 parameters
	 *
	 * @param draw
	 *            : the target of the button
	 **/
	public Option(Onglet draw) {
		this.dessin = draw;

		btnDessiner = new JButton("Dessiner");
		btnRename = new JButton("Renommer");
		pan = new JPanel();
		pan2 = new JPanel();
		image = new JPanel();

		pan.setLayout(new GridLayout(1, 1));
		pan.add(btnDessiner);
		btnDessiner.addActionListener(this);

		pan2.setLayout(new GridLayout(1, 1));
		pan2.add(btnRename);
		btnRename.addActionListener(this);

		image.setLayout(new GridLayout(1, 1));
		JLabel img = new JLabel(new ImageIcon("imDesvis.png"));

		image.add(img);

		panneauBouton = Box.createVerticalBox();
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(pan);
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(new JSeparator(JSeparator.HORIZONTAL));
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(pan2);
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(new JSeparator(JSeparator.HORIZONTAL));
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(image);

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
