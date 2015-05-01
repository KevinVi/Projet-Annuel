package pluging;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

/**
* Estimate maker java application with GUI.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 4932662545205980307L;

	private JMenuBar menuBar;
	private JMenu fileMenu;

	private JMenuItem exitMenuItem;
	private JMenuItem loadMenuItem;

	private JTextArea TelechargerTextAreaInfo;

	private JFileChooser f;
	// private FileNameExtensionFilter filter;
	private File dir;
	private ClassLoader cl;
	private Enumeration<?> enu;
	private JarFile jarFile;
	private JarEntry je;
	private String className;
	private Class<?> c;

	public MainFrame() {
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
			// FileNameExtensionFilter(".jar", ".jar");
			// f.setFileFilter(filter);

			if (this.f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

				this.dir = new File(this.f.getSelectedFile().getAbsolutePath());

				try {
					jarFile = new JarFile(this.dir);
					System.out.println(jarFile);
					enu = jarFile.entries();
					URL[] urls = { new URL("jar:file:" + this.dir + "!/") };
					cl = URLClassLoader.newInstance(urls);
					System.out.println(cl);
					while (enu.hasMoreElements()) {
						je = (JarEntry) enu.nextElement();
						if (je.isDirectory()|| !je.getName().endsWith(".class")) 
						{
							continue;
						}
						// -6 because of .class
						className = je.getName().substring(0,je.getName().length() - 6);
						className = className.replace('/', '.');
						System.out.println(className);
						c = cl.loadClass(className);
						this.TelechargerTextAreaInfo.setText("la class télécharger est " + this.c+ ". \n");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} 
			}
		}
	}

}
