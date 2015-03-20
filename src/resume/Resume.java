package resume;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JPanel;
/**
* Estimate maker java application with GUI.
* This class handles the left side, which summarizes the materials used.
*
* @author Mohammad Saman, Vivor Kevin
*/

public class Resume extends JPanel {
	
	private TextArea t;
	
	public Resume(){
		t = new TextArea("Lorem ipsum dolor sit amet");
		t.setEditable(false);
		
		this.setPreferredSize(new Dimension(180,0));
		this.setLayout(new GridLayout(2, 1));
		this.add (t);
		this.setBackground(Color.decode("#2766A1"));
	}
}
