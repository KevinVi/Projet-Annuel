package Modèles;

import javax.swing.JTabbedPane;

public class Onglet extends JTabbedPane {
	
	public Polygon poly;
	
	public Onglet(){
		this.poly = new Polygon();
		this.addTab("Dessin 1", poly);
	}
	
}
