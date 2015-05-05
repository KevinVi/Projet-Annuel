package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modeles.Base;

/**
 * Estimate maker java application with GUI.
 *
 * @author Mohammad Saman, Vivor Kevin
 */

public class Main {
	
	public static Base test;

	public static void main(String[] args) {
		test = new Base();
		test.setLocationRelativeTo(null); // centrer la fenetre

		try {
			// On force à utiliser le « look and feel » du système
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// Ici on force tous les composants de notre fenêtre (test) à se
			// redessiner avec le « look and feel » du système
			SwingUtilities.updateComponentTreeUI(test);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
	}
}
