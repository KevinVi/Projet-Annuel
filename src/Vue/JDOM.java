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

/**
 * Estimate maker java application with GUI. 
 * This class read the .xml file for create the materials tabs
 * Info: JDOM (Java Document Object Model)
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class JDOM {
	
	int j = 0;
	
	/**
	 * Default Constructor of the class JDOM
	 **/
	public JDOM() {
		
		//Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory" 
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			//Etape 2 : création d'un parseur 
			final DocumentBuilder builder = factory.newDocumentBuilder();
			//Etape 3 : création d'un Document
			final Document document = builder.parse(new File("fichiers/panneau.xml"));

			/* Affiche du prologue
			System.out.println("*************PROLOGUE************");
			System.out.println("version : " + document.getXmlVersion());
			System.out.println("encodage : " + document.getXmlEncoding());*/

			
			 //Etape 4 : récupération de l'Element racine
			final Element racine = document.getDocumentElement();

			/* Affichage de l'élément racine : panneau
			System.out.println("\n*************RACINE************");
			System.out.println(racine.getNodeName());*/

			//Etape 5 : récupération des panneaus comportant les différents boutons radio
			final NodeList racineNoeuds = racine.getChildNodes();
			final int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) 
			{
				if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) 
				{
					final Element panneau = (Element) racineNoeuds.item(i);

					/* Affichage d'un onglet
					System.out.println("\n*************ONGLET************");
					System.out.println("onglet : "+ panneau.getAttribute("type"));
					System.out.println("mat : "+
					panneau.getAttribute("bouton"));*/
					Panneau p = new Panneau(panneau.getAttribute("type"));
					Materiaux.AddOnglet(panneau.getAttribute("type"), p);
					
					//Etape 6 : récupération des boutons radio
					final NodeList bouton = panneau.getElementsByTagName("bouton");
					final int nbBoutonsElements = bouton.getLength();

					for (int j = 0; j < nbBoutonsElements; j++, this.j++) 
					{

						NodeList nodeListMat = document.getElementsByTagName("matiere");
						Node nodeMatiere = nodeListMat.item(this.j);
						//System.out.println("matiere"+ nodeMatiere.getFirstChild().getNodeValue());

						NodeList nodeListIcon = document.getElementsByTagName("imgChemin");
						Node nodeIcon = nodeListIcon.item(this.j);
						//System.out.println("icon"+ nodeIcon.getFirstChild().getNodeValue());

						NodeList nodeListImg = document.getElementsByTagName("img");
						Node nodeImg = nodeListImg.item(this.j);
						//System.out.println("image"+ nodeImg.getFirstChild().getNodeValue());
						
						NodeList nodeListPrix = document.getElementsByTagName("prix");
						Node nodePrix = nodeListPrix.item(this.j);
						//System.out.println("prix"+ Integer.parseInt(nodeImg.getFirstChild().getNodeValue()));

						// System.out.println(matiere.getTextContent());
						p.AddBoutonPanneau(nodeMatiere.getFirstChild()
								.getNodeValue(), nodeIcon.getFirstChild()
								.getNodeValue(), nodeImg.getFirstChild()
								.getNodeValue(), nbBoutonsElements,Integer.parseInt(nodePrix.getFirstChild().getNodeValue()));
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