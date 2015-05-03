package Vue;
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

import Modèles.Base;
import Modèles.DialogAide;
import pluging.MainFrame;
import main.Main;
/**
* Estimate maker java application with GUI
* This class includes the menu bar.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Menu extends JMenuBar{
	
	private static final long serialVersionUID = -437102206245752298L;
	/**
	 * 
	 */
	JMenu file;
    JMenuItem fermer,nouveau,ouvrir,plugin,site,aide;
  
    static JMenuItem save; 
  
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

        nouveau = new JMenuItem("New Project");
        ouvrir = new JMenuItem("Open Project");
        save = new JMenuItem("Save Project");
        	save.setEnabled(false);
        fermer = new JMenuItem("Close Project");
        
        plugin = new JMenuItem("Plugins management");
        site = new JMenuItem("User's manual");
        
        aide = new JMenuItem("Demo");
        
        nouveau.addActionListener(itemHandler);
        ouvrir.addActionListener(itemHandler);
        save.addActionListener(itemHandler);
        fermer.addActionListener(itemHandler);
        
        plugin.addActionListener(itemHandler);
        site.addActionListener(itemHandler);
        
        aide.addActionListener(itemHandler);
        
        //Raccourci clavier
        file.setMnemonic('F');
        view.setMnemonic('V');
        options.setMnemonic('O');
        help.setMnemonic('H');
        info.setMnemonic('I');
        
        nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
        
        file.add(nouveau);
        file.add(ouvrir);
        file.add(save);
        file.addSeparator();
        file.add(fermer);
        
        options.add(plugin);
        help.add(site);
        
        info.add(aide);
       
        
        this.add(file);
        this.add(view);
        this.add(options);
        this.add(help);
        this.add(info);
        
        
       
        
    }
    
    public static JMenuItem getSave() {
		return save;
	}

	private class ItemHandler implements ActionListener
    {
    	@Override
		public void actionPerformed(ActionEvent event) {
 
            if ( event.getSource() == fermer){
            	
               Base.closeFile();
            }
            if ( event.getSource() == nouveau ){
            	int result = JOptionPane.showConfirmDialog(null, "Attention, vous allez quitter votre devis en cours", "Message", JOptionPane.OK_CANCEL_OPTION);
    		    if(result == 0){
    		    	Main.test.dispose();
    		    	//Main.test.dessin = new Essai();
                	Base b = new Base();
                	b.setLocationRelativeTo(null); // centrer la fenetre
    	        }
            }
            if ( event.getSource() == save ){
            	Base.saveFile();
            }
            if ( event.getSource() == ouvrir ){
               Base.openFile();
            }
            if (event.getSource() == site){
            	System.out.println("hello");
            	URI uri = URI.create("http://devisme.fr/");
            	try {
					Desktop.getDesktop().browse(uri);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            if (event.getSource() == plugin){
            	MainFrame p = new MainFrame();
            	p.setLocationRelativeTo(null); // centrer la fenetre
            	p.setVisible(true);
            	
            	Image img = kit.getImage("img/Desvis.png");
        		p.setIconImage(img);
        		setVisible(true);
            }
            if(event.getSource() == aide){
            	System.out.println("coucou");
            	DialogAide da = new DialogAide(null, "Coucou les ZérOs", true);
            	da.setVisible(true);

            }
    	}
    }
    
}
