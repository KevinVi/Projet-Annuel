package Vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JPanel;

import Modèles.Onglet;
import Modèles.Polygon;
/**
* Estimate maker java application with GUI.
* This class handles the left side, which summarizes the materials used.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Resume extends JPanel {
	
	private static TextArea t;
	private Polygon dessin;
	
	public Resume(Polygon poly){
		this.dessin=poly;
		double area = this.dessin.area();
		t = new TextArea(""+area);
		t.setEditable(false);
		
		this.setPreferredSize(new Dimension(180,0));
		this.setLayout(new GridLayout(2, 1));
		this.add (t);
		//this.setText(this.dessin.area());
		this.setBackground(Color.decode("#2766A1"));
	}

	public static TextArea getT() {
		return t;
	}

	public static void setT(TextArea text) {
		t = text;
	}
	
}
