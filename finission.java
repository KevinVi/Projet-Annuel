package essai;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Finission extends JPanel {
	
	JButton btnCr�erDevis;
	
	public Finission(){
		
		btnCr�erDevis = new JButton("Cr�er Devis");
		
		this.add(btnCr�erDevis);
		this.setBackground(Color.decode("#2766A1"));
	}
}
