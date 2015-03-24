package pluging;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Frame principale du programme de test des plugins
 * @author Lainé Vincent (dev01, http://vincentlaine.developpez.com/ )
 *
 */
public class MainFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 4932662545205980307L;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	
	private JMenuItem exitMenuItem;
	private JMenuItem loadMenuItem;
	
	private JTextArea TelechargerTextAreaInfo;
	private PluginsLoader pluginsLoader;
	
	private JFileChooser f;
	//private FileNameExtensionFilter filter;
	private File dir;
	private URL loadPath;
	private URL[] classUrl;
	private ClassLoader cl;
	private Class loadedClass;
	
	public MainFrame(){
		this.initialize();
	}
	
	private void initialize(){
		this.menuBar = new JMenuBar();
		this.TelechargerTextAreaInfo = new JTextArea();
		
		this.fileMenu = new JMenu();
			this.exitMenuItem = new JMenuItem();
			this.loadMenuItem = new JMenuItem();
		//menuBar
		this.menuBar.add(this.fileMenu);
		
		//fileMenu
		this.fileMenu.setText("Menu");
		this.fileMenu.add(this.loadMenuItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(this.exitMenuItem);
		
		this.TelechargerTextAreaInfo.setBorder(new LineBorder(Color.black));
		this.TelechargerTextAreaInfo.setEditable(false);
		this.TelechargerTextAreaInfo.setBackground(Color.decode("#5E8BB5") );
		this.TelechargerTextAreaInfo.setFont(new Font("Serif", Font.BOLD, 13));
		
		//exitMenuItem
		this.exitMenuItem.setText("Fermer");
		this.exitMenuItem.addActionListener(this);
		
		//loadMenuItem
		this.loadMenuItem.setText("Charger un plugins");
		this.loadMenuItem.addActionListener(this);
		
		//Configuration du JFrame
		this.setSize(400,300);
		this.setJMenuBar(this.menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,1));
		this.getContentPane().add(this.TelechargerTextAreaInfo);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.exitMenuItem ){
			this.setVisible(false);
		}
		else if( e.getSource() == this.loadMenuItem ) {
				this.f = new JFileChooser();
				//FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .java", ".java");
				//f.setFileFilter(filter);
				
				if(this.f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
					//this.files.add(f.getSelectedFile().getAbsolutePath());
					
					this.dir = new File(this.f.getSelectedFile().getAbsolutePath());
				
					try {
						this.loadPath = this.dir.toURI().toURL();
						this.classUrl = new URL[]{this.loadPath};
						
						this.cl = new URLClassLoader(this.classUrl);
						this.TelechargerTextAreaInfo.setText("la class télécharger est "+this.cl+". \n Vous pouvez lancer le chargement.");
						
						loadedClass = cl.loadClass("testclock"); // must be in package.class name format
						this.TelechargerTextAreaInfo.setText("la class télécharger est "+this.loadedClass+". \n Le téléchargement a été lancé.");
					} 
					catch (MalformedURLException ex) {
						ex.printStackTrace();
					} 
					catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} 
				}
		}
	}
	
}

