package Modèles;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.simple.JSONObject;

import Contrôleur.Option;
import Vue.Finission;
import Vue.Materiaux;
import Vue.Menu;
import Vue.Resume;
import main.Main;
/**
* Estimate maker java application with GUI.
* This class includes all panels in one JFrame.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Base extends JFrame {
	
	public ObjectOutputStream output;
	public ObjectInputStream input;
	public Onglet dessin;
	protected Menu menuBar;
	public Polygon poly;
	public Option menugauche;
	public Materiaux materiauxgauche;
	public Resume menudroit; //setTexte sur ça
	public Finission bas;

	public Base() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.setTitle("Devis'Me");
		setSize(1024, 768);
		setLayout(new BorderLayout());
		dessin = new Onglet();
		poly = new Polygon();
		menuBar = new Menu();
		menugauche = new Option(dessin);
		materiauxgauche = new Materiaux(menudroit);
		menudroit = new Resume(poly);
		bas = new Finission();
		add(dessin, BorderLayout.CENTER);
		
		JPanel gauche = new JPanel();
		gauche.setLayout(new GridLayout(2,1));
		gauche.add(menugauche);
		gauche.add(materiauxgauche);
		add(gauche, BorderLayout.WEST);
		
		add(new JScrollPane(menudroit), BorderLayout.EAST);
		add(menuBar, BorderLayout.NORTH);
		add(bas,BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image img = kit.getImage("img/Desvis.png");
		setIconImage(img);
		setVisible(true);
		
		//this.add(new JLabel(new ImageIcon("img/fond.jpg")));	
		//this.pack();
		
	}

	public File getFileName() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File(""));
		int result = fileChooser.showOpenDialog(this);
		File fileName = fileChooser.getSelectedFile();
		return fileName;
	}

	public void loadFile(File fileName) {
		try {
			input = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException ioException) {
			System.err.println("Error loading file: " + fileName);
			return;
		}
	}

	public void openFile(File fileName) {
		try {
			output = new ObjectOutputStream(new FileOutputStream(fileName));
		} catch (IOException ioException) {
			System.err.println("Error loading file: " + fileName);
			return;
		}
	}

	public static void saveFile() {
		
		JSONObject obj = new JSONObject();
		obj=Polygon.get_json();
		 try {
			
			 	 
			
			 	            FileWriter jsonFileWriter = new FileWriter("C:\\Users\\kevin\\Desktop\\file1.txt");
			
			 	            jsonFileWriter.write(obj.toJSONString());
			
			 	            jsonFileWriter.flush();
			 
			 	            jsonFileWriter.close();
			
			 	 
			
			 	            System.out.print(obj);
			
			 	 
			
			 	        } catch (IOException e) {
			 
			 	            e.printStackTrace();
			
			 	        }
		/*try {
			JFileChooser chooseDirec = new JFileChooser();
			chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooseDirec.showSaveDialog(Main.test);
			File file = chooseDirec.getSelectedFile();
			file = new File(file + ".dmf");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					file));
			bufferedWriter.close();
			openFile(file);
			// writeSketchToFile(file);
			closeFile();
		} catch (IOException exception) {
			System.err.println("Error saving to new file.");
		}*/
	}

	public void closeFile() {
		try {
			if (output != null)
				output.close();
		} catch (IOException exception) {
			System.err.println("Error closing file");
			System.exit(1);
		}
	}
	
}
