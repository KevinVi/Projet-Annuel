package vue;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import modeles.Base;
import modeles.Onglet;
import pluging.ChargerPlugin;

/**
 * Estimate maker java application with GUI This class includes the menu bar.
 * This class create the top menu.
 * @author Mohammad Saman, Vivor Kevin
 */

public class Menu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = -437102206245752298L;
	private JMenu file, options, help, info;
	private JMenuItem fermer, nouveau, ouvrir, plugin, site,information;
	protected static  JMenuItem save;
	private String[] point = { "3", "4", "5", "6", "7", "8", "9", "10" };
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Onglet dessin;
	
	/**	
	* Constructor of the Class with 1 parameters
	*@param draw :   the target of the button
	**/
	public Menu(Onglet draw) {
		dessin = draw;

		file = new JMenu("File");
		options = new JMenu("Options");
		help = new JMenu("Help");
		info = new JMenu("?");

		nouveau = new JMenuItem("New Project");
		ouvrir = new JMenuItem("Open Project");
		save = new JMenuItem("Save Project");
		save.setEnabled(false);
		fermer = new JMenuItem("Close Project");

		plugin = new JMenuItem("Gestion de plugins");
		site = new JMenuItem("Manuel d'utilisation");
		
		information = new JMenuItem("Information");

		// Racourci clavier
		file.setMnemonic('F');
		options.setMnemonic('O');
		help.setMnemonic('H');

		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				KeyEvent.CTRL_MASK));
		information.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				KeyEvent.CTRL_MASK));

		nouveau.addActionListener(this);
		ouvrir.addActionListener(this);
		save.addActionListener(this);
		fermer.addActionListener(this);

		plugin.addActionListener(this);
		site.addActionListener(this);
		information.addActionListener(this);

		file.add(nouveau);
		file.add(ouvrir);
		file.add(save);
		file.addSeparator();
		file.add(fermer);

		options.add(plugin);
		help.add(site);
		
		info.add(information);

		this.add(file);
		this.add(options);
		this.add(help);
		this.add(info);
	}

	/**
	 * Methode called by the action listener
	 * @param event : 	the trigger of the event
	 * 
	 **/
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == fermer) {
			Base.closeFile();
		}
		if (event.getSource() == nouveau) {
			int sommet = 0;

			String a = null;
			a = (String) JOptionPane.showInputDialog(null,
					"Combien de sommets poss�de votre pi�ce ?",
					"Cr�er un polygone", JOptionPane.QUESTION_MESSAGE, null,
					point, point[0]);

			if (a != null) {
				sommet = Integer.parseInt(a);
				this.dessin.poly.setSommet(sommet);
				Menu.getSave().setEnabled(true);
			}
		}
		if (event.getSource() == save) {
			Base.saveFile();
		}
		if (event.getSource() == ouvrir) {
			Base.openFile();
		}
		if (event.getSource() == site) {
			URI uri = URI.create("http://devisme.fr/devis.php");
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (event.getSource() == plugin) {
			ChargerPlugin p = new ChargerPlugin();
			p.setLocationRelativeTo(null); // centrer la fenetre
			p.setVisible(true);

			Image img = kit.getImage("img/Desvis.png");
			p.setIconImage(img);
			setVisible(true);
		}
		if (event.getSource() == information) {
			JOptionPane.showMessageDialog(null, "Autor : \nSaman MOHAMMAD \n"
					+ "Kevin VIVOR \n" + "Devis'Me @ 2015", "Infos Devis'Me",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Getter for the JMenuItem save
	 * @return save : 	JMenuItem save
	 * 
	 **/
	public static  JMenuItem getSave() {
		return save;
	}

}
