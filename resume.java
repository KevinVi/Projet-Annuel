package essai;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import javax.swing.JPanel;


public class Resume extends JPanel {
	
	private TextArea t;
	
	public Resume(){
		t = new TextArea("Lorem ipsum dolor sit amet");
		t.setEditable(false);
		
		setPreferredSize(new Dimension(180,0));
		setLayout(new GridLayout(2, 1));
		add (t);
	}
}
