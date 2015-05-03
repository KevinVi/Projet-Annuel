package Modèles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DialogAide extends JDialog {
		
	 private boolean sendData;
	  private JLabel dessinLabel, surfaceLabel, mdpLabel, comLabel, loginLabel,login2Label, icon;
	  private JRadioButton tranche1, tranche2, tranche3, tranche4;
	  private JComboBox surface;
	  private JTextField dessin, login,com, mdp;

	  public DialogAide(JFrame parent, String title, boolean modal){
	    super(parent, title, modal);
	    this.setSize(550, 300);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	  }

	

	  private void initComponent(){
	    //Icône
	    icon = new JLabel(new ImageIcon("img/Desvis.png"));//image généré
	    JPanel panIcon = new JPanel();
	    panIcon.setBackground(Color.white);
	    panIcon.setLayout(new BorderLayout());
	    panIcon.add(icon);

	  //Le Dessin
	    JPanel pandessin = new JPanel();
	    icon = new JLabel(new ImageIcon("images/icone.jpg"));
	    pandessin.setBackground(Color.white);
	    pandessin.setPreferredSize(new Dimension(220, 60));
	    pandessin.setBorder(BorderFactory.createTitledBorder("Créatio Dessin"));
	    dessinLabel = new JLabel("Clic sur Dessiner");
	    pandessin.add(dessinLabel);
	    pandessin.add(icon);
	    
	    

	    JPanel content = new JPanel();
	    content.setBackground(Color.white);
	    content.add(pandessin);
	    

	    JPanel control = new JPanel();
	    JButton okBouton = new JButton("OK");
	    
	    okBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {        
	        
	        setVisible(false);
	      }

	      
	    });

	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });

	    control.add(okBouton);
	    control.add(cancelBouton);

	    this.getContentPane().add(panIcon, BorderLayout.WEST);
	    this.getContentPane().add(content, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	  }  
	}