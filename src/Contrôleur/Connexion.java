package Contr�leur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Programme Java de Gestion des notes.
 *Ce programme se connecte � une BDD, effectue les traitements n�cessaire en sql.
 *Il a pour objectif de permettre la saisie des notes de n �l�ves, 
 *afin de pouvoir calculer la moyenne de l'ann�e de ces derniers.
 *
 *Connexion � la base de donn�es gestionnote
 *
 *@author Saman Mohammad
 */

public class Connexion {

	private static Connection con=null;
	    
	private static Connection open()
	{
		try{
	        Class.forName("com.mysql.jdbc.Driver");
	    }
		catch(ClassNotFoundException e)
		{
			System.out.println("Pilote mal install�..."+e);
		}
		try{
			con = DriverManager.getConnection("jdbc:mysql://netxis.ddns.net/Devisme", "app", "123soleil");
			System.out.println("je suis co");
		}
		catch(SQLException e){
			System.out.println("Erreur SQL :"+e);
		}
	        return con;
	}
	    
	public static Connection get()
	{
		try 
		{
			if(con==null ||con.isClosed())
			{
				con=Connexion.open();
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
		}
	        return con;
	}
	    
	public static void close()
	{
		try
		{
			con.close();
		}
		catch(SQLException e){
			System.out.println("Erreur SQL:"+e);
	    }
	}

}
