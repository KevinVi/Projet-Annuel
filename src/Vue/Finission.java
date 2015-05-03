package Vue;
import java.awt.Color;
import java.awt.Graphics2D;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mysql.jdbc.PreparedStatement;

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
	String id;
	File outputfile;
	FileInputStream istreamImage;
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
		    // retrieve image
		    BufferedImage bi = dessin.createImage(dessin);
		     outputfile = new File("saved.png");
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		    
		}
		try {
			istreamImage = new FileInputStream(outputfile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Connection con = Connexion.get();
			Statement mon_statement = con.createStatement();
			ResultSet mon_resultat = mon_statement.executeQuery("SELECT idClient FROM Client WHERE loginClient ='"+getLogin()+"' AND mdpClient = '"+getMdp()+"'");
			while(mon_resultat.next()){
				id = mon_resultat.getString("idClient");
				System.out.println("hello je suis là biatch"+id);
			}
			
			if(mon_resultat!=null){
				String sql="INSERT INTO Devis ( nom_projet,nom_dessin, surface ,commentaire ,prix , image,idClient) VALUES(?, ?,?,?,?,?,'"+id+"')";
				java.sql.PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1,getNom_projet() );
				statement.setString(2,onglet.getTitleAt(0) );
				statement.setString(3,getSurface() );
				statement.setString(4,getCommentaire() );
				statement.setDouble(5,dessin.prix() );
				statement.setBinaryStream(6, istreamImage, (int)outputfile.length());
				statement.executeUpdate();
				//mon_statement.executeUpdate("INSERT INTO Devis ( nom_projet,nom_dessin, surface ,commentaire ,prix , image,idClient) VALUES('"+onglet.getTitleAt(0)+"', '"+getNom_projet()+"','"+getSurface()+"','"+getCommentaire()+"','"+dessin.prix()+"','"+image+"','"+id+"')");
			}else{
				JOptionPane.showMessageDialog(null,"Mauvais Login ou Mot de Passe","Erreur",JOptionPane.ERROR_MESSAGE);

			
			}
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
