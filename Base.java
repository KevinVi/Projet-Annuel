package essai;
import java.awt.BorderLayout;
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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Base extends JFrame {
	public ObjectOutputStream output;
	public ObjectInputStream input;
	public Essai dessin;
	protected Menu menuBar;
	public Option menugauche;
	public Resume menudroit;
	public Finission bas;

	public Base() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.setTitle("Devis'Me");
		setSize(1024, 768);
		setLayout(new BorderLayout());
		dessin = new Essai();
		menuBar = new Menu();
		menugauche = new Option();
		menudroit = new Resume();
		bas = new Finission();
		add(new JScrollPane(dessin), BorderLayout.CENTER);
		
		
		add(menugauche, BorderLayout.WEST);
		
		add(new JScrollPane(menudroit), BorderLayout.EAST);
		add(menuBar, BorderLayout.NORTH);
		add(bas,BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img = kit.getImage("Info.png");
		setIconImage(img);
		setVisible(true);
		
		
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

	public void saveFile() {
		try {
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
		}
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
