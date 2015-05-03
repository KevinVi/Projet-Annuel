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
import java.nio.channels.FileChannel;
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
		this.TelechargerTextAreaInfo
				.setText("Welcome to the download plugin manager interface \n");

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

		if (e.getSource() == this.exitMenuItem) {
			this.dispose();
		} else if (e.getSource() == this.loadMenuItem) {
			this.f = new JFileChooser();
			// FileNameExtensionFilter filter = new
			// FileNameExtensionFilter(".xml", ".xml");
			// f.setFileFilter(filter);

			if (this.f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

				this.dir = new File(this.f.getSelectedFile().getAbsolutePath());
				this.TelechargerTextAreaInfo
						.setText(getStringTelechargerTextAreaInfo()
								+ "The download will start \n ");
				CopyFile(dir);

			}
		}
	}

	public void CopyFile(File f) {

		FileChannel in = null; // canal d'entr�e
		FileChannel out = null; // canal de sortie

		try {
			// Init
			in = new FileInputStream(f).getChannel();
			out = new FileOutputStream("fichiers/panneau.xml").getChannel();

			// Copie depuis le in vers le out
			in.transferTo(0, in.size(), out);
			this.TelechargerTextAreaInfo
					.setText(getStringTelechargerTextAreaInfo()
							+ "The download was successful ! \n"
							+ "You must restart the application to make the change in accounts :) ");
		} catch (Exception e) {
			e.printStackTrace(); // n'importe quelle exception
		} finally { // finalement on ferme
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
