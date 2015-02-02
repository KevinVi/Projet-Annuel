import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Menu extends JMenuBar{
	JMenu file;
    JMenuItem fermer;
    JMenuItem nouveau;
    JMenuItem ouvrir;
    JMenuItem save; 

    JMenu view;
    
    JMenu options; 

    JMenu help;
    
    Menu(){
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

        

        add(file);
        add(view);
        add(options);
        add(help);
    }
    private class ItemHandler implements ActionListener
    {
    	@Override
		public void actionPerformed(ActionEvent event) {
        
            if ( event.getSource() == fermer)
            {
                /*Main.test.dispose();
                System.exit(0); */
            }

            if ( event.getSource() == nouveau )
            {
               /* Main.test.drawPanel.elements = new ArrayList();
                Main.test.drawPanel.elements.add(new FillerElement(Color.white));*/
            }

            if ( event.getSource() == save )
            {
              //  Main.test.saveFile();
            }

            if ( event.getSource() == ouvrir )
            {
               /* File fileName = Main.paint.getFileName();
                Main.test.loadFile(fileName);
                Main.test.loadElementsFromFile();
                Main.test.closeFile();*/
            }
           /* Main.test.repaint();
            setFocusable(false);*/
        }

		
    }
}
