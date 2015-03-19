package essai;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Finission extends JPanel {
	
	JButton btnCréerDevis;
	
	public Finission(){
		
		btnCréerDevis = new JButton("Créer Devis");
		
		this.add(btnCréerDevis);
		this.setBackground(Color.decode("#2766A1"));
	}
}
