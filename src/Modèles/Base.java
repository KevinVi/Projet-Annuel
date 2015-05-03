package Modèles;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Contrôleur.Option;
import Vue.Finission;
import Vue.Materiaux;
import Vue.Menu;
import main.Main;

/**
 * Estimate maker java application with GUI. 
 * This class includes all panels in one JFrame.
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Base extends JFrame {

	private static final long serialVersionUID = 1230040793682512232L;
	@SuppressWarnings("unused")
	private ObjectOutputStream output;
	@SuppressWarnings("unused")
	private ObjectInputStream input;
	private static Onglet dessin;
	protected Menu menuBar;
	private Polygon poly;
	private Option menugauche;
	private Materiaux materiauxgauche;
	private Finission bas;
	private static String name;
	private JPanel gauche;
	private static int tabX[] = new int[10];
	private static int tabY[] = new int[10];

	/**
	 * Default constructor of the class Base
	 * 
	 **/
	public Base() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.setTitle("Devis'Me");
		this.setSize(1024, 768);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Image img = kit.getImage("img/Desvis.png");
		this.setIconImage(img);

		dessin = new Onglet();
		this.poly = new Polygon();
		this.menuBar = new Menu(dessin);
		this.menugauche = new Option(dessin);
		this.materiauxgauche = new Materiaux();
		this.bas = new Finission(poly, dessin);
		this.gauche = new JPanel();

		this.gauche.setLayout(new GridLayout(2, 1));
		this.gauche.add(menugauche);
		this.gauche.add(materiauxgauche);

		this.setLayout(new BorderLayout());
		this.add(dessin, BorderLayout.CENTER);
		this.add(gauche, BorderLayout.WEST);
		this.add(menuBar, BorderLayout.NORTH);
		this.add(bas, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	/**
	 * Methode to ...
	 * 
	 * @return fileName :
	 * 
	 **/
	public File getFileName() {
		// TODO : tu t'en sert de celle là?
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File(""));
		File fileName = fileChooser.getSelectedFile();

		return fileName;
	}

	/**
	 * Methode to load ...
	 * 
	 * @param fileName :
	 * 
	 **/
	public void loadFile(File fileName) {
		// TODO : tu t'en sert de celle là?
		try {
			this.input = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException ioException) {
			System.err.println("Error loading file: " + fileName);
			return;
		}
	}

	/**
	 * Methode to open a project file.
	 * 
	 **/
	public static void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File(""));
		fileChooser.showOpenDialog(Main.test);
		File fileName = fileChooser.getSelectedFile();
		int j = 0;

		try {
			FileReader reader = new FileReader(fileName);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(reader);
			JSONArray onglet = (JSONArray) obj.get("Nom_onglet");
			String nom = (String) obj.get("nom");
			dessin.setTitleAt(0, nom);
			for (int i = 0; i < onglet.size(); i++) {
				// System.out.println("coordonnée"+onglet.get(i));
				if (i < onglet.size() / 2) {
					System.out.println("coordonnée x " + i + " : "
							+ onglet.get(i));
					tabX[i] = Integer.parseUnsignedInt((String) onglet.get(i));

				} else {
					System.out.println("coordonnée y" + onglet.get(i));
					tabY[j] = Integer.parseUnsignedInt((String) onglet.get(i));
					j++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Polygon.xcoord = tabX;
		Polygon.ycoord = tabY;
		Polygon.sommet = j;
		Menu.getSave().setEnabled(false);
	}

	/**
	 * Methode to save a project on the user computer
	 * 
	 **/
	@SuppressWarnings("unchecked")
	public static void saveFile() {
		name = dessin.getTitleAt(0);
		JSONObject obj = new JSONObject();
		obj = Polygon.get_json();
		obj.put("nom", name);
		
		try {
			JFileChooser chooseDirec = new JFileChooser();
			chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooseDirec.showSaveDialog(Main.test);
			
			File file = chooseDirec.getSelectedFile();
			FileWriter jsonFileWriter = new FileWriter(file + ".json");

			jsonFileWriter.write(obj.toJSONString());
			jsonFileWriter.flush();
			jsonFileWriter.close();

			Menu.getSave().setEnabled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//TODO : C'est quoi se gros bloque vert ?
		
		/*
		 * try { JFileChooser chooseDirec = new JFileChooser();
		 * chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 * chooseDirec.showSaveDialog(Main.test); File file =
		 * chooseDirec.getSelectedFile(); file = new File(file + ".dmf");
		 * BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
		 * file)); bufferedWriter.close(); openFile(file); //
		 * writeSketchToFile(file); closeFile(); } catch (IOException exception)
		 * { System.err.println("Error saving to new file."); }
		 */
	}

	/**
	 * Methode to close the current project		
	 * 
	 **/
	public static void closeFile() {

		if (Menu.getSave().isEnabled() == true) 
		{
			saveFile();
		} else {
			Main.test.dispose();
			System.exit(0);
		}
	}
	
}
