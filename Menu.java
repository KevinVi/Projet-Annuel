package essai;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class Menu extends JMenuBar{
	JMenu file;
    JMenuItem fermer;
    JMenuItem nouveau;
    JMenuItem ouvrir;
    JMenuItem save; 
    JMenu view;
    JMenu options; 
    JMenu help;
    String url = "http://devisme.fr/devis.php"; 
    
    public Menu(){
    	
    	Menu.ItemHandler itemHandler = new Menu.ItemHandler();

        file   = new JMenu("File");
        help   = new JMenu("Help");
        view   = new JMenu("View");
        options=new JMenu("Options");

        nouveau = new JMenuItem("Nouveau");
        ouvrir = new JMenuItem("Ouvrir");
        save = new JMenuItem("Sauvegarde");
        fermer = new JMenuItem("Fermer");

        nouveau.addActionListener(itemHandler);
        ouvrir.addActionListener(itemHandler);
        save.addActionListener(itemHandler);
        fermer.addActionListener(itemHandler);

        file.add(nouveau);
        file.add(ouvrir);
        file.add(save);
        file.addSeparator();
        file.add(fermer);

        this.add(file);
        this.add(view);
        this.add(options);
        this.add(help);
        //this.setBackground(Color.decode("#E1E6FA"));
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
        }
    }
}
