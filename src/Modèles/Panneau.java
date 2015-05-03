package Modèles;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Estimate maker java application with GUI. 
 * This class create radio buttons for the materials.
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class Panneau extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6986414199300820696L;
	@SuppressWarnings("unused")
	private static String nom;
	private static double prix;
	private String img;
	private int j=0;
	private JRadioButton t,t2,t3,t4,t5,f;
	private double p,p2,p3,p4,p5;
	private String s,s2,s3,s4,s5;
	private ButtonGroup bgroup = new ButtonGroup();

	/**
	 * Constructor of the class Panneau
	 * @param nom :		name of the new tab
	 **/
	public Panneau(String nom) {
		this.setNom(nom);
		this.setPreferredSize(new Dimension(180, 250));
	}
	
	/**
	 * Methode for insert a new radio button in the new tab's panel
	 * @param title	:		title of the new radio button
	 * @param imgChemin	:	path of the icon
	 * @param img :			path of the largest picture
	 * @param i:			total number of radio button for this tab
	 * 
	 **/
	public void AddBoutonPanneau(String title, String imgChemin, String img, int i,int prix){
		this.setLayout(new GridLayout(i, 1));
		//this.t = new JRadioButton(title);
		
		f=(JRadioButton) createJ(title,j,prix);
		System.out.println(j);
		this.bgroup.add(f);
		this.add(f);
		
		JLabel j = new JLabel(new ImageIcon(imgChemin));
		this.setImg(imgChemin);
		j.setToolTipText("<html><body><img src='"+img+"' width=500 height=500></body></html>");
		this.add(j);
	}
	public  AbstractButton createJ(String nom,int i,int prix){
		switch (i){
		case 0:
			this.t = new JRadioButton(nom);
			t.addActionListener(this);
			p=prix;
			s=nom;
			//this.setPrix(prix);
			j++;
			return t;
		case 1:
			this.t2 = new JRadioButton(nom);
			t2.addActionListener(this);
			p2=prix;
			s2=nom;
			//this.setPrix(prix);
			j++;
			return t2;
		case 2:
			this.t3 = new JRadioButton(nom);
			t3.addActionListener(this);
			p3=prix;
			s3=nom;
			//this.setPrix(prix);
			j++;
			return t3;
		case 3:
			this.t4 = new JRadioButton(nom);
			t4.addActionListener(this);
			p4=prix;
			s4=nom;
			//this.setPrix(prix);
			j++;
			return t4;
		default:
			this.t5 = new JRadioButton(nom);
			t5.addActionListener(this);
			p5=prix;
			s5=nom;
			//this.setPrix(prix);
			j++;
			return t5;
		}
		
		
	}
	
	
	/**
	 * Setter for the name of the tab
	 * @param nom :		name of the tab
	 * 
	 **/
	public void setNom(String nom) {
		this.nom = nom;
	}
	public static  String getNom(){
		return nom;
	}

	public void setPrix(int p) {
		this.prix = p;
	}

	public static double getPrix() {
		return prix;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==t){
			Polygon.setPrice(p);
			System.out.println(p);
		}
		if(e.getSource()==t2){
			Polygon.setPrice(p2);
			System.out.println(p2);
		}
		if (e.getSource() == t3) {
			Polygon.setPrice(p3);
			System.out.println(p3);
		}
		if (e.getSource() == t4) {
			Polygon.setPrice(p4);
			System.out.println(p4);
		}
		if (e.getSource() == t5) {
			Polygon.setPrice(p5);
			System.out.println(p5);
		}
	
	}
	

}
