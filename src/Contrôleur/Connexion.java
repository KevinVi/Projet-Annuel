package Contrôleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Programme Java de Gestion des notes.
 *Ce programme se connecte à une BDD, effectue les traitements nécessaire en sql.
 *Il a pour objectif de permettre la saisie des notes de n élèves, 
 *afin de pouvoir calculer la moyenne de l'année de ces derniers.
 *
 *Connexion à la base de données gestionnote
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
			System.out.println("Pilote mal installé..."+e);
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
