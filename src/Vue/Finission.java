package Vue;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modèles.Onglet;
import Modèles.Polygon;
/**
* Estimate maker java application with GUI.
* This class covers the lower part of the application.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Finission extends JPanel implements ActionListener{
	
	JButton btnCreerDevis;
	Polygon dessin;
	public Finission(Polygon draw){
		dessin=draw;
		btnCreerDevis = new JButton("Créer Devis");
		
		this.add(btnCreerDevis);
		this.setBackground(Color.decode("#2766A1"));
		btnCreerDevis.addActionListener(this);
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//dessin.testcreateimage();
		//dessin.name;
		dessin.area();
		JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(dessin.createImage(dessin))));
		System.out.println(dessin.createImage(dessin));
	}
}
