package vue;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modeles.Dialog;
import modeles.Onglet;
import modeles.Polygon;
import controleur.Connexion;

/**
 * Estimate maker java application with GUI. This class create Display the
 * Classe Dialogue on click on "Créer Devis" , and send all the information to
 * de data base
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Devis extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnCreerDevis;
	private Polygon dessin;
	private Onglet onglet;
	private String id = null;
	private File outputfile;
	private FileInputStream istreamImage;
	private static String nom_projet, surface, commentaire, login, mdp,
			nom = null;
	private static int cancel;

	/**
	 * 
	 * Constructor of the Classe with 2 parameters
	 * @param draw:  Polygon designed
	 * @param ong:   ong where the polygon is
	 */
	public Devis(Polygon draw, Onglet ong) {
		dessin = draw;
		onglet = ong;
		btnCreerDevis = new JButton("Créer Devis");

		this.add(btnCreerDevis);
		this.setBackground(Color.decode("#2766A1"));
		btnCreerDevis.addActionListener(this);

	}

	/**
	 * On click on "Créer Devis" verifictation of the elements and connection to
	 * the account of the user then send all the information to the database
	 * 
	 * @param arg0: the trigger of the event
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// dessin.testcreateimage();
		// dessin.name;
		Dialog zd = new Dialog(null, "Création Devis", true);
		zd.setVisible(true);
		@SuppressWarnings("unused")
		Image image = dessin.createImage(dessin);
		image = dessin.createImage(dessin);
		System.out.println(onglet.getTitleAt(0));
		try {
			// retrieve image
			BufferedImage bi = dessin.createImage(dessin);
			outputfile = new File("img/saved.png");
			ImageIO.write(bi, "png", outputfile);
			BufferedImage bi2 = dessin.createImage(dessin);
			outputfile = new File("img/saved.png");
			ImageIO.write(bi2, "png", outputfile);
		} catch (IOException e) {

		}
		try {
			istreamImage = new FileInputStream(outputfile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if (getCancel() != 1) {
			getNom_projet();
			getLogin();
			getMdp();
			if (getNom_projet() == null || getLogin() == null
					|| getMdp() == null) {
				JOptionPane.showMessageDialog(null,
						"Veuillez remplir les champs obligatoire", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					if (nom != null) {
						Connection con = Connexion.get();
						Statement mon_statement = con.createStatement();
						ResultSet mon_resultat = mon_statement
								.executeQuery("SELECT idClient FROM Client WHERE loginClient ='"
										+ getLogin()
										+ "' AND mdpClient = '"
										+ getMdp() + "'");
						while (mon_resultat.next()) {
							id = mon_resultat.getString("idClient");
						}
						if (id != null) {

							String sql = "INSERT INTO Devis ( nom_projet,nom_dessin, surface ,commentaire ,prix ,materiel, image,idClient) VALUES(?, ?,?,?,?,?,?,'"
									+ id + "')";
							java.sql.PreparedStatement statement = con
									.prepareStatement(sql);
							statement.setString(1, getNom_projet());
							statement.setString(2, onglet.getTitleAt(0));
							statement.setString(3, getSurface());
							statement.setString(4, getCommentaire());
							statement.setDouble(5, dessin.prix());
							statement.setString(6, nom);
							statement.setBinaryStream(7, istreamImage,
									(int) outputfile.length());
							statement.executeUpdate();
						} else {
							JOptionPane.showMessageDialog(null,
									"Mauvais Login ou Mot de Passe", "Erreur",
									JOptionPane.ERROR_MESSAGE);

						}
					} else {
						JOptionPane.showMessageDialog(null, "Pas de matériaux",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (id != null) {
				JOptionPane
						.showMessageDialog(
								null,
								"Votre Devis a bien été enregistré \n"
										+ "Retrouvez le dès maintenant sur votre espace personnel ;)",
								"Info", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Getter for the name of the projet
	 * @return nom_projet : name of the projet
	 **/
	public String getNom_projet() {
		return nom_projet;
	}

	/**
	 * Setter for the name of the projet
	 * @param nom: name of the projet
	 * 
	 **/
	public static void setNom_projet(String nom) {
		nom_projet = nom;
	}

	/**
	 * Getter for the surface choosen
	 * @return surface : surface choosen
	 * 
	 **/
	public String getSurface() {
		return surface;
	}

	/**
	 * Setter for the surface choosen
	 * @param sur: surface choosen
	 * 
	 **/
	public static void setSurface(String sur) {
		surface = sur;
	}

	/**
	 * Getter for the Comment of the design
	 * @return commentaire : Comment of the design
	 * 
	 **/
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * Setter for the Comment of the design
	 * @param com: Comment of the design
	 * 
	 **/
	public static void setCommentaire(String com) {
		commentaire = com;
	}

	/**
	 * Getter for the login of the user
	 * @return log : login of the user
	 * 
	 **/
	public String getLogin() {
		return login;
	}

	/**
	 * Setter for the login of the user
	 * @param log: login of the user
	 * 
	 **/
	public static void setLogin(String log) {
		login = log;
	}

	/**
	 * Getter for the password of the user
	 * @return mdp : password of the user
	 * 
	 **/
	public String getMdp() {
		return mdp;
	}

	/**
	 * Setter for the password of the user
	 * @param pwd: password of the user
	 * 
	 **/
	public static void setMdp(String pwd) {
		mdp = pwd;
	}

	/**
	 * Getter for the window canceled
	 * @return cancel : window canceled
	 * 
	 **/
	public int getCancel() {
		return cancel;
	}

	/**
	 * Setter for the window canceled
	 * @param c: window canceled
	 * 
	 **/
	public static void setcancel(int c) {
		cancel = c;
	}

	/**
	 * Getter for the name of the design
	 * @return nom : name of the design
	 * 
	 **/
	public String getNom() {
		return nom;
	}

	/**
	 * Setter for the name of the design
	 * @param n: name of the design
	 * 
	 **/
	public static void setNom(String n) {
		nom = n;
	}

}
