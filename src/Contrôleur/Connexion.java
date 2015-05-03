package Contrôleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Estimate maker java application with GUI. 
 * This class manages the connection to the database.
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Connexion {

	private static Connection con = null;

	/**
	 * Methode for create a connection
	 * @return con :	connection at the database
	 **/
	private static Connection open() {
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("please install your jdbc pilote : " + e);
		}
		try 
		{
			con = DriverManager.getConnection(
					"jdbc:mysql://netxis.ddns.net/Devisme", "app", "123soleil");
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}
		return con;
	}

	/**
	 * Methode for get the current connection
	 * @return con :	the current connection at the database
	 **/
	public static Connection get() {
		try {
			if (con == null || con.isClosed()) {
				con = Connexion.open();
			}
		} catch (SQLException ex) {
			Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return con;
	}

	/**
	 * Methode close the current connection
	 * 
	 **/
	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur SQL:" + e);
		}
	}

}
