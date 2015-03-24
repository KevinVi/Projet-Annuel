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

public class Option extends JPanel implements ActionListener {
private static final long serialVersionUID = 1L;
JButton bouton1 ;
JButton bouton2;
String[] point = {"3","4","5","6","7","8","9","10"};
static int sommet;
Polygon_app poly;
public Option()
	{
		bouton1 = new JButton("polygon");
 		bouton2 = new JButton("Effacer");
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(2,1));
		pan.add(bouton1);
		bouton1.addActionListener(this);
		pan.add(bouton2);
		bouton2.addActionListener(this);
		Box panneauBouton = Box.createVerticalBox();
		panneauBouton.add(pan);
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(new JSeparator(JSeparator.HORIZONTAL));
		//panneauBouton.add(Box.createGlue());
		this.add(panneauBouton,BorderLayout.CENTER );
		this.setBackground(Color.decode("#2766A1"));
		//setPreferredSize(new Dimension(180,0));
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()== bouton1){
		   
	}
	if(e.getSource()==bouton2){
		
			    
			    sommet=Integer.parseInt((String) JOptionPane.showInputDialog(null, "Nombre de sommet", "Polygone", JOptionPane.QUESTION_MESSAGE, null, point,point[2]));
			   /* poly=new Polygon_app();
			    Option.setDessin(poly);
				sommet=5;*/
			    
	}
}
public static int getSommet() {
	
	return sommet;
}
public static void setSommet(int s) {
		sommet = s;
}
}
