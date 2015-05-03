package Vue;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Modèles.Base;
import pluging.ChargerPlugin;
import main.Main;

/**
 * Estimate maker java application with GUI This class includes the menu bar.
 *
 * @author Mohammad Saman, Vivor Kevin
 */

public class Menu extends JMenuBar {

	private static final long serialVersionUID = -437102206245752298L;
	/**
	 * 
	 */
	JMenu file;
	JMenuItem fermer;
	JMenuItem nouveau;
	JMenuItem ouvrir;
	static JMenuItem save;
	JMenuItem plugin;
	JMenuItem site;
	JMenu view;
	JMenu options;
	JMenu help;
	JMenu info;
	Toolkit kit = Toolkit.getDefaultToolkit();

	public Menu() {

		Menu.ItemHandler itemHandler = new Menu.ItemHandler();

		file = new JMenu("File");
		view = new JMenu("View");
		options = new JMenu("Options");
		help = new JMenu("Help");
		info = new JMenu("?");

		nouveau = new JMenuItem("Nouveau");
		ouvrir = new JMenuItem("Ouvrir");
		save = new JMenuItem("Sauvegarde");
		save.setEnabled(false);
		fermer = new JMenuItem("Fermer");

		plugin = new JMenuItem("Gestion de plugins");
		site = new JMenuItem("Manuel d'utilisation");

		nouveau.addActionListener(itemHandler);
		ouvrir.addActionListener(itemHandler);
		save.addActionListener(itemHandler);
		fermer.addActionListener(itemHandler);

		plugin.addActionListener(itemHandler);
		site.addActionListener(itemHandler);
		info.addActionListener(itemHandler);

		file.add(nouveau);
		file.add(ouvrir);
		file.add(save);
		file.addSeparator();
		file.add(fermer);

		options.add(plugin);
		help.add(site);

		this.add(file);
		this.add(view);
		this.add(options);
		this.add(help);
		this.add(info);
	}

	public static JMenuItem getSave() {
		return save;
	}

	private class ItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == fermer) {
				Main.test.dispose();
				System.exit(0);
			}
			if (event.getSource() == nouveau) {
				int result = JOptionPane.showConfirmDialog(null,
						"Attention, vous allez quitter votre devis en cours",
						"Message", JOptionPane.OK_CANCEL_OPTION);
				if (result == 0) {
					Main.test.dispose();
					// Main.test.dessin = new Essai();
					Base b = new Base();
					b.setLocationRelativeTo(null); // centrer la fenetre
				}
			}
			if (event.getSource() == save) {

			}
			if (event.getSource() == ouvrir) {

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
			if (event.getSource() == info) {
				JOptionPane.showMessageDialog(null, "Autor : Saman MOHAMMAD \n"
			    		+ "Kevin VIVOR \n"
			    		+ "Devis'Me@2015", "Infos Devis'Me", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
