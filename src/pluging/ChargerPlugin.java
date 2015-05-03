package pluging;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * Estimate maker java application with GUI.
 * This class download the plugin
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class ChargerPlugin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 4932662545205980307L;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	private JMenuItem loadMenuItem;
	private JTextArea TelechargerTextAreaInfo;
	private JFileChooser f;
	private File dir;

	/**
	 * Default Constructor of the class ChargerPlugin
	 **/
	public ChargerPlugin() {
		this.initialize();
	}

	/**
	 * Methode to create a new frame to download
	 **/
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

	/**
	 * Methode called by the action listener
	 * @param e : 	the trigger of the event
	 * 
	 **/
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.exitMenuItem) 
		{
			this.dispose();
		} 
		else if (e.getSource() == this.loadMenuItem) 
		{
			this.f = new JFileChooser();

			if (this.f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
			{
				this.dir = new File(this.f.getSelectedFile().getAbsolutePath());
				this.TelechargerTextAreaInfo.setText(getStringTelechargerTextAreaInfo()
								+ "The download will start \n ");
				CopyFile(dir);
			}
		}
	}

	/**
	 * Methode for copy the new params on the param file
	 * @param f : 	the downloaded file
	 * 
	 **/
	public void CopyFile(File f) {

		FileChannel in = null; // canal d'entrée
		FileChannel out = null; // canal de sortie

		try {
			// Init
			in = new FileInputStream(f).getChannel();
			out = new FileOutputStream("fichiers/panneau.xml").getChannel();

			// Copie depuis le in vers le out
			in.transferTo(0, in.size(), out);
			this.TelechargerTextAreaInfo.setText(getStringTelechargerTextAreaInfo()
							+ "The download was successful ! \n"
							+ "You must restart the application to make the change in accounts :) ");
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
		finally { // finalement on ferme
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

	/**
	 * Getter for TelechargerTextAreaInfo
	 * @return TelechargerTextAreaInfo : 	the text of the textArea
	 * 
	 **/
	public String getStringTelechargerTextAreaInfo() {
		return TelechargerTextAreaInfo.getText();
	}

	/**
	 * Setter for TelechargerTextAreaInfo
	 * @param s : 	the new text for the textArea
	 * 
	 **/
	public void setStringTelechargerTextAreaInfo(String s) {
		TelechargerTextAreaInfo.setText(s);
	}

}
