package Vue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Modèles.Base;
import pluging.MainFrame;
import main.Main;
/**
* Estimate maker java application with GUI
* This class includes the menu bar.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Menu extends JMenuBar{
	JMenu file;
    JMenuItem fermer;
    JMenuItem nouveau;
    JMenuItem ouvrir;
    JMenuItem save; 
    JMenuItem plugin;
    JMenu view;
    JMenu options; 
    JMenu help;
    JMenu info;
    Toolkit kit = Toolkit.getDefaultToolkit();
    
    public Menu(){
    	
    	Menu.ItemHandler itemHandler = new Menu.ItemHandler();

        file   = new JMenu("File");
        view   = new JMenu("View");
        options=new JMenu("Options");
        help   = new JMenu("Help");
        info   = new JMenu("?");

        nouveau = new JMenuItem("Nouveau");
        ouvrir = new JMenuItem("Ouvrir");
        save = new JMenuItem("Sauvegarde");
        fermer = new JMenuItem("Fermer");
        
        plugin = new JMenuItem("Gestion de plugins");

        nouveau.addActionListener(itemHandler);
        ouvrir.addActionListener(itemHandler);
        save.addActionListener(itemHandler);
        fermer.addActionListener(itemHandler);
        
        plugin.addActionListener(itemHandler);
        help.addActionListener(itemHandler);
        
        file.add(nouveau);
        file.add(ouvrir);
        file.add(save);
        file.addSeparator();
        file.add(fermer);
        
        options.add(plugin);

        this.add(file);
        this.add(view);
        this.add(options);
        this.add(help);
        this.add(info);
    }
    
    private class ItemHandler implements ActionListener
    {
    	@Override
		public void actionPerformed(ActionEvent event) {
 
            if ( event.getSource() == fermer){
                Main.test.dispose();
                System.exit(0); 
            }
            if ( event.getSource() == nouveau ){
            	JOptionPane boite = new JOptionPane();
            	int result = boite.showConfirmDialog(null, "Attention, vous allez quitter votre devis en cours", "Message", JOptionPane.OK_CANCEL_OPTION);
    		    if(result == 0){
    		    	Main.test.dispose();
    		    	//Main.test.dessin = new Essai();
                	Base b = new Base();
                	b.setLocationRelativeTo(null); // centrer la fenetre
    	        }
            }
            if ( event.getSource() == save ){
              //  Main.test.saveFile();
            }
            if ( event.getSource() == ouvrir ){
               
            }
            if (event.getSource() == help){
            	
            }
            if (event.getSource() == plugin){
            	MainFrame p = new MainFrame();
            	p.setLocationRelativeTo(null); // centrer la fenetre
            	p.setVisible(true);
            	
            	Image img = kit.getImage("img/Desvis.png");
        		p.setIconImage(img);
        		setVisible(true);
            }
    	}
    }
    
}
