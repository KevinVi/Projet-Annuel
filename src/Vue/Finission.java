package Vue;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Contrôleur.Connexion;
import Modèles.Dialog;
import Modèles.Onglet;
import Modèles.Polygon;
import Modèles.ZDialogInfo;
/**
* Estimate maker java application with GUI.
* This class covers the lower part of the application.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Finission extends JPanel implements ActionListener{
	
	JButton btnCreerDevis;
	Polygon dessin;
	Onglet onglet;
	static String nom_projet,surface,commentaire,login,mdp;
	



	public Finission(Polygon draw,Onglet ong){
		dessin=draw;
		onglet=ong;
		btnCreerDevis = new JButton("Créer Devis");
		
		this.add(btnCreerDevis);
		this.setBackground(Color.decode("#2766A1"));
		btnCreerDevis.addActionListener(this);
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//dessin.testcreateimage();
		//dessin.name;
		Dialog zd = new Dialog(null, "Création Devis", true);
		zd.setVisible(true);
		System.out.println(getMdp());
		 Image image = dessin.createImage(dessin);
		try {
			Connection con = Connexion.get();
			Statement mon_statement = con.createStatement();
			ResultSet mon_resultat = mon_statement.executeQuery("SELECT * FROM Client WHERE loginClient ="+getLogin()+" AND mdpClient = "+getMdp());
			/*if(mon_resultat!=null){
				mon_statement.executeUpdate("INSERT INTO Devis (Id, Nom_projet,Nom_dessin, Surface ,Commentaire ,Prix , Image) VALUES(DEFAULT,'"+onglet.getTitleAt(0)+" '"+getNom_projet()+"','"+getSurface()+"','"+getCommentaire()+"','"+dessin.prix()+"','"+image+"')");
			}else{
				JOptionPane.showMessageDialog(null,"Mauvais Login ou Mot de Passe","Erreur",JOptionPane.ERROR_MESSAGE);

			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//Getters and Setters
	public String getNom_projet() {
		return nom_projet;
	}



	public static void setNom_projet(String nom) {
		nom_projet = nom;
	}



	public String getSurface() {
		return surface;
	}



	public static void setSurface(String sur) {
		surface = sur;
	}



	public String getCommentaire() {
		return commentaire;
	}



	public static void setCommentaire(String com) {
		commentaire = com;
	}



	public String getLogin() {
		return login;
	}



	public static void setLogin(String log) {
		login = log;
	}



	public String getMdp() {
		return mdp;
	}



	public static void setMdp(String md) {
		mdp = md;
	}
}
