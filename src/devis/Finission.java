package devis;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
* Estimate maker java application with GUI.
* This class covers the lower part of the application.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Finission extends JPanel {
	
	JButton btnCr�erDevis;
	
	public Finission(){
		
		btnCr�erDevis = new JButton("Cr�er Devis");
		
		this.add(btnCr�erDevis);
		this.setBackground(Color.decode("#2766A1"));
	}
}
