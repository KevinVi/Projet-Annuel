package Vue;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
	int j =0;
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
					
					for (int j=0; j < nbBoutonsElements; j++,this.j++) {
						final Element matiere = (Element) bouton.item(j);
						
						NodeList nodeListMat = document.getElementsByTagName("matiere");
						Node nodeMatiere = nodeListMat.item(this.j);
						System.out.println("matiere"+nodeMatiere.getFirstChild().getNodeValue());
						
						NodeList nodeListIcon = document.getElementsByTagName("imgChemin");
						Node nodeIcon = nodeListIcon.item(this.j);
						System.out.println("icon"+nodeIcon.getFirstChild().getNodeValue());
						
						NodeList nodeListImg = document.getElementsByTagName("img");
						Node nodeImg = nodeListImg.item(this.j);
						System.out.println("image>"+nodeImg.getFirstChild().getNodeValue());
						
						// Affichage du bouton
						//System.out.println(matiere.getTextContent());
						p.AddBoutonPanneau(nodeMatiere.getFirstChild().getNodeValue(),nodeIcon.getFirstChild().getNodeValue(),nodeImg.getFirstChild().getNodeValue(), nbBoutonsElements);
						
						 //Affichage du téléphone
                        //System.out.println(telephone.getAttribute("type") + " : " + telephone.getTextContent());
		    
					}
				}
			}
			
			//---------
			/*NodeList nodeList = document.getElementsByTagName("matiere");
		    for (int i = 0; i < nodeList.getLength(); i++) {
		        Node node = nodeList.item(i);
		        if (node.hasChildNodes()) {

		            System.out.println("<tr><td>Mat</td>" + "<td>"+node.getFirstChild().getNodeValue()+"</td></tr>");

		        }
		    }*/
		        //---------
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}