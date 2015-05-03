package pluging;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
* Estimate maker java application with GUI.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class ChargerPlugin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 4932662545205980307L;

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	private JMenuItem loadMenuItem;
	private JTextArea TelechargerTextAreaInfo;
	private JFileChooser f;
	private FileNameExtensionFilter filter;
	private File dir;

	public ChargerPlugin() {
		this.initialize();
	}

	private void initialize() {
		this.menuBar = new JMenuBar();
		this.TelechargerTextAreaInfo = new JTextArea();

		this.fileMenu = new JMenu();
		this.exitMenuItem = new JMenuItem();
		this.loadMenuItem = new JMenuItem();
		// menuBar
		this.menuBar.add(this.fileMenu);

		// fileMenu
		this.fileMenu.setText("Menu");
		this.fileMenu.add(this.loadMenuItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(this.exitMenuItem);

		this.TelechargerTextAreaInfo.setBorder(new LineBorder(Color.black));
		this.TelechargerTextAreaInfo.setEditable(false);
		this.TelechargerTextAreaInfo.setBackground(Color.decode("#5E8BB5"));
		this.TelechargerTextAreaInfo.setFont(new Font("Serif", Font.BOLD, 13));
		this.TelechargerTextAreaInfo.setText("Welcome to the download plugin manager interface \n");

		// exitMenuItem
		this.exitMenuItem.setText("Fermer");
		this.exitMenuItem.addActionListener(this);

		// loadMenuItem
		this.loadMenuItem.setText("Charger un plugins");
		this.loadMenuItem.addActionListener(this);

		// Configuration du JFrame
		this.setSize(400, 300);
		this.setJMenuBar(this.menuBar);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(1, 1));
		this.getContentPane().add(this.TelechargerTextAreaInfo);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.exitMenuItem) 
		{
			this.dispose();
		} 
		else if (e.getSource() == this.loadMenuItem) 
		{
			this.f = new JFileChooser();
			//FileNameExtensionFilter filter = new
			//FileNameExtensionFilter(".xml", ".xml");
			//f.setFileFilter(filter);

			if (this.f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

				this.dir = new File(this.f.getSelectedFile().getAbsolutePath());
				this.TelechargerTextAreaInfo.setText(getStringTelechargerTextAreaInfo()+"The download will start \n ");
				CopyFile(dir);
				 
			}
		}
	}
	
	public void CopyFile(File f){
		// Nous déclarons nos objets en dehors du bloc try/catch
	      FileInputStream lireF = null;
	      FileOutputStream copieF = null;

	      try {
	         // On instancie nos objets :
	         // lireF va lire le fichier
	         // copieF va écrire dans le nouveau 
	         lireF = new FileInputStream(f);
	         copieF = new FileOutputStream("fichiers/panneau.xml");

	         // On crée un tableau de byte pour indiquer le nombre de bytes lus à
	         // chaque tour de boucle
	         byte[] buf = new byte[8];

	         // On crée une variable de type int pour y affecter le résultat de
	         // la lecture
	         // Vaut -1 quand c'est fini
	         int n = 0;

	         // Tant que l'affectation dans la variable est possible, on boucle
	         // Lorsque la lecture du fichier est terminée l'affectation n'est
	         // plus possible !
	         // On sort donc de la boucle
	         while ((n = lireF.read(buf)) >= 0) {
	             // On écrit dans notre deuxième fichier avec l'objet adéquat
	             copieF.write(buf);
	             // On affiche ce qu'a lu notre boucle au format byte et au
	             // format char
	             for (byte bit : buf) {
	                System.out.print("\t" + bit + "(" + (char) bit + ")");
	                System.out.println("");
	             }
	             //Nous réinitialisons le buffer à vide
	             //au cas où les derniers byte lus ne soient pas un multiple de 8
	             //Ceci permet d'avoir un buffer vierge à chaque lecture et ne pas avoir de doublon en fin de fichier
	             buf = new byte[8];

	          }
			this.TelechargerTextAreaInfo.setText(getStringTelechargerTextAreaInfo()+"The download was successful ! \n"
					+ "You must restart the application to make the change in accounts :) ");

	      } catch (FileNotFoundException e) {
	         // Cette exception est levée si l'objet FileInputStream ne trouve
	         // aucun fichier
	         e.printStackTrace();
	      } catch (IOException e) {
	         // Celle-ci se produit lors d'une erreur d'écriture ou de lecture
	         e.printStackTrace();
	      } finally {
	         // On ferme nos flux de données dans un bloc finally pour s'assurer
	         // que ces instructions seront exécutées dans tous les cas même si
	         // une exception est levée !
	         try {
	            if (lireF != null)
	            	lireF.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }

	         try {
	            if (copieF != null)
	            	copieF.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	}
	
	public String getStringTelechargerTextAreaInfo() {
		return TelechargerTextAreaInfo.getText();
	}

	public void setStringTelechargerTextAreaInfo(String s) {
		TelechargerTextAreaInfo.setText(s);
	}

}
