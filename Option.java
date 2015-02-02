import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


public class Option extends JMenuBar {
	JButton bouton1;
	 JButton bouton2;
	 JButton bouton3;
	 
	 Option(){
		 Option.ItemHandler itemHandler = new Option.ItemHandler();
		 bouton1 = new JButton("bouton1"); 
		 bouton2 = new JButton("bouton2"); 
		 bouton3 = new JButton("bouton3"); 
		 
		 bouton1.addActionListener(itemHandler);
		 bouton2.addActionListener(itemHandler);
		 bouton3.addActionListener(itemHandler);
		 
	 }

  
    

	 
    private class ItemHandler implements ActionListener
    {
    	@Override
		public void actionPerformed(ActionEvent event) {
        
          
        }


}
}
