package menuDessin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
/**
* Estimate maker java application with GUI.
* This class handles the buttons on the right side, so choose the types of drawings.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Option extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public Option() 
	{
		JButton bouton1 = new JButton("Rectangle");
		JButton bouton2 = new JButton("Trait");
		
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(2,1));
		pan.add(bouton1);
		pan.add(bouton2);
		
		Box panneauBouton = Box.createVerticalBox();
		panneauBouton.add(pan);
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(new JSeparator(JSeparator.HORIZONTAL));
		//panneauBouton.add(Box.createGlue());
		this.add(panneauBouton,BorderLayout.CENTER );
		this.setBackground(Color.decode("#2766A1"));
		//this.setPreferredSize(new Dimension(180,0));
	 } 
}