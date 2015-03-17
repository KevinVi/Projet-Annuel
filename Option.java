package essai;

/*
 * Classe des bouttons pour les menus
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Option extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	Essai p = new Essai();
	
	public Option() {
		
		//ImageIcon img = new ImageIcon(getClass().getResource("bouton.png"));
		JButton bouton1 = new JButton("Ligne");
		bouton1.setPreferredSize(new Dimension(180,150));
		
		JButton bouton2 = new JButton("Rectangle");
		bouton2.setPreferredSize(new Dimension(180,150));
		
		Box panneauBouton = Box.createVerticalBox();
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(bouton1);
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(new JSeparator(JSeparator.HORIZONTAL));
		panneauBouton.add(Box.createVerticalStrut(20));
		panneauBouton.add(bouton2);
		panneauBouton.add(Box.createVerticalStrut(20));
		
		add(panneauBouton,BorderLayout.CENTER );
		
		panneauBouton.add(Box.createGlue());
		bouton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ActionPerformedBtn1(evt);
			}
		});
		bouton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ActionPerformedBtn2(evt);
			}
		});
	 } 
	
	private void ActionPerformedBtn1(ActionEvent evt){
		//p.paint(getGraphics());
	}
	
	private void ActionPerformedBtn2(ActionEvent evt){
		//p.paintComponent(getGraphics());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

