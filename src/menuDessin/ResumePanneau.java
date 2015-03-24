package menuDessin;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

import resume.Resume;

public class ResumePanneau implements ActionListener{

	private JRadioButton jrMur1 = Panneau.getJrMur1();
	private TextArea t = Resume.getT();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == jrMur1){
			t = new TextArea("Lmdt");
			//String newText="mur 1";
			//t.setText(t.getText() + newText); 
			Resume.setT(t);
        }
		
	}

}
