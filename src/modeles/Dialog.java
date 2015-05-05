package modeles;

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
import javax.swing.JTextField;

import vue.Devis;

/**
 * Estimate maker java application with GUI. 
 * This class create a JDialog to fill the name project
 * login , password to connect to the data base
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Dialog extends JDialog {
  
  private static final long serialVersionUID = 1L;
  private JLabel nomLabel, surfaceLabel, mdpLabel, comLabel, loginLabel, icon;
  private JComboBox<String> surface;
  private JTextField nom, login,com, mdp;

     /**
	 * Default constructor of the classe Dialog
	 * set set size of the frame
	 * @param parent 	: 		parent of the frame
	 * @param title     :       title of the frame
	 * @param modal 	:        true or false
	 **/
  public Dialog(JFrame parent, String title, boolean modal){
    super(parent, title, modal);
    this.setSize(550, 470);
    this.setBackground(Color.decode("#2766A1"));
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    this.initComponent();
    
  }
  
  	/**
	 * Initialisation of the different part of the frame
	 * 
	 **/
  @SuppressWarnings({ "unchecked", "rawtypes"})
private void initComponent(){
    //Icône
    icon = new JLabel(new ImageIcon("img/Desvis.png"));//image généré
    JPanel panIcon = new JPanel();
    panIcon.setBackground(Color.white);
    panIcon.setLayout(new BorderLayout());
    panIcon.add(icon);

    //Le nom
    JPanel panNom = new JPanel();
    panNom.setBackground(Color.white);
    panNom.setPreferredSize(new Dimension(220, 60));
    nom = new JTextField();
    nom.setPreferredSize(new Dimension(100, 25));
    panNom.setBorder(BorderFactory.createTitledBorder("Nom du projet"));
    nomLabel = new JLabel("Saisir un nom :");
    panNom.add(nomLabel);
    panNom.add(nom);

    //Le surface
    JPanel panSurface = new JPanel();
    panSurface.setBackground(Color.white);
    panSurface.setPreferredSize(new Dimension(220, 60));
    panSurface.setBorder(BorderFactory.createTitledBorder("Type de surface"));
    surface = new JComboBox();
    surface.addItem("Mur");
    surface.addItem("Sol");
    surfaceLabel = new JLabel("Surface : ");
    panSurface.add(surfaceLabel);
    panSurface.add(surface);

    //Commentaire
    JPanel panCom = new JPanel();
    panCom.setBackground(Color.white);
    panCom.setPreferredSize(new Dimension(300, 100));
    com = new JTextField();
    com.setPreferredSize(new Dimension(180, 25));
    panCom.setBorder(BorderFactory.createTitledBorder("Commentaire"));
    comLabel = new JLabel("Saisir d'un commentaire :");
    panCom.add(comLabel);
    panCom.add(com);

    //Le login
    JPanel panLogin = new JPanel();
    panLogin.setBackground(Color.white);
    panLogin.setPreferredSize(new Dimension(220, 60));
    panLogin.setBorder(BorderFactory.createTitledBorder("Login de votre compte"));
    loginLabel = new JLabel("Login : ");
    login = new JTextField();
    login.setPreferredSize(new Dimension(90, 25));
    panLogin.add(loginLabel);
    panLogin.add(login);

    //La Mdp
    JPanel panMdp = new JPanel();
    panMdp.setBackground(Color.white);
    panMdp.setPreferredSize(new Dimension(220, 60));
    panMdp.setBorder(BorderFactory.createTitledBorder("Mot de passe du compte"));
    mdpLabel = new JLabel("Mot de passe");
    mdp = new JTextField();
    mdp.setPreferredSize(new Dimension(90, 25));
    panMdp.add(mdpLabel);
    panMdp.add(mdp);

    JPanel content = new JPanel();
    content.setBackground(Color.decode("#2766A1"));
    content.add(panNom);
    content.add(panSurface);
    content.add(panLogin);
    content.add(panMdp);
    content.add(panCom);

    JPanel control = new JPanel();
    JButton okBouton = new JButton("OK");
    
    okBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {  
        Devis.setNom_projet(nom.getText());
        Devis.setSurface((String)surface.getSelectedItem());
        Devis.setCommentaire(com.getText());
        Devis.setLogin(login.getText());
        Devis.setMdp(mdp.getText());
        setVisible(false);
      }  
    });

    JButton cancelBouton = new JButton("Annuler");
    cancelBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        Devis.setcancel(1);
      }      
    });

    control.add(okBouton);
    control.add(cancelBouton);

    this.getContentPane().add(panIcon, BorderLayout.WEST);
    this.getContentPane().add(content, BorderLayout.CENTER);
    this.getContentPane().add(control, BorderLayout.SOUTH);
  }  
}
