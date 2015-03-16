import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JPanel;


public class Resume extends JPanel {
	private TextArea t;
	Resume(){
		t = new TextArea("hdzdnlzkl,ed,zlek,fklez,,edkl,zelkd,");
		setPreferredSize(new Dimension(50,50));
		add(t);
		 
	}
}
