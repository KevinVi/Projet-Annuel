package essai;

/*
 * Classe des bouttons pour les menus
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;


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
		add(panneauBouton,BorderLayout.CENTER );
	 } 
}