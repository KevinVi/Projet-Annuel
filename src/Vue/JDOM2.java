package Vue;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Modèles.Panneau;

public class JDOM2 {
	
	public JDOM2() {
		/*
		 * Etape 1 : récupération d'une instance de la classe
		 * "DocumentBuilderFactory"
		 */
		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();

		try {
			/*
			 * Etape 2 : création d'un parseur
			 */
			final DocumentBuilder builder = factory.newDocumentBuilder();

			/*
			 * Etape 3 : création d'un Document
			 */
			final Document document = builder.parse(new File("panneau.xml"));

			// Affiche du prologue
			System.out.println("*************PROLOGUE************");
			System.out.println("version : " + document.getXmlVersion());
			System.out.println("encodage : " + document.getXmlEncoding());

			/*
			 * Etape 4 : récupération de l'Element racine
			 */
			final Element racine = document.getDocumentElement();

			// Affichage de l'élément racine : panneau
			System.out.println("\n*************RACINE************");
			System.out.println(racine.getNodeName());

			/*
			 * Etape 5 : récupération des panneau
			 */
			final NodeList racineNoeuds = racine.getChildNodes();
			final int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) {
				if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element panneau = (Element) racineNoeuds.item(i);

					// Affichage d'un onglet
					System.out.println("\n*************ONGLET************");
					System.out.println("onglet : "+ panneau.getAttribute("type"));
					//System.out.println("mat : "+ panneau.getAttribute("bouton"));
					Panneau p =new Panneau(panneau.getAttribute("type"));
					Materiaux.AddOnglet(panneau.getAttribute("type"), p);
					/*
					 * Etape 6 : récupération des boutons radio
					 */
					final NodeList bouton = panneau
							.getElementsByTagName("bouton");
					final int nbBoutonsElements = bouton.getLength();
					
					for (int j = 0; j < nbBoutonsElements; j++) {
						final Element matiere = (Element) bouton.item(j);

						// Affichage du bouton
						System.out.println(matiere.getTextContent());
						p.AddBoutonPanneau("baroque","img/mat/icon/sol1.jpg","http://cdn4.micasa.ch/medias/sys_master/zoom/c/5/6/7/id_9526227402782_zoom.jpg", nbBoutonsElements);
						
						 //Affichage du téléphone
                        //System.out.println(telephone.getAttribute("type") + " : " + telephone.getTextContent());
		    
					}
				}
			}
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}