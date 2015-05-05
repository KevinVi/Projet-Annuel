package pluging;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modeles.Panneau;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import vue.Materiaux;

/**
 * Estimate maker java application with GUI. This class read the .xml file for
 * create the materials tabs Info: JDOM (Java Document Object Model)
 *
 * @author Mohammad Saman, Vivor Kevin
 * @version 1.0
 */
public class JDOM {

	int j = 0;

	/**
	 * Default Constructor of the class JDOM Read the xml file and send the
	 * information to the class Panneau To create the materials radio buttons
	 **/
	public JDOM() {

		// récupération d'une instance de la classe "DocumentBuilderFactory"
		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();

		try {
			// création d'un parseur
			final DocumentBuilder builder = factory.newDocumentBuilder();
			// création d'un Document
			final Document document = builder.parse(new File(
					"fichiers/panneau.xml"));

			// récupération de l'Element racine
			final Element racine = document.getDocumentElement();

			// récupération des panneaus comportant les différents boutons radio
			final NodeList racineNoeuds = racine.getChildNodes();
			final int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) {
				if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element panneau = (Element) racineNoeuds.item(i);

					Panneau p = new Panneau(panneau.getAttribute("type"));
					Materiaux.AddOnglet(panneau.getAttribute("type"), p);

					// récupération des boutons radio
					final NodeList bouton = panneau
							.getElementsByTagName("bouton");
					final int nbBoutonsElements = bouton.getLength();

					for (int j = 0; j < nbBoutonsElements; j++, this.j++) {
						NodeList nodeListMat = document
								.getElementsByTagName("matiere");
						Node nodeMatiere = nodeListMat.item(this.j);

						NodeList nodeListIcon = document
								.getElementsByTagName("imgChemin");
						Node nodeIcon = nodeListIcon.item(this.j);

						NodeList nodeListImg = document
								.getElementsByTagName("img");
						Node nodeImg = nodeListImg.item(this.j);

						NodeList nodeListPrix = document
								.getElementsByTagName("prix");
						Node nodePrix = nodeListPrix.item(this.j);

						NodeList nodeListColor = document
								.getElementsByTagName("color");
						Node nodeColor = nodeListColor.item(this.j);

						p.AddBoutonPanneau(nodeMatiere.getFirstChild()
								.getNodeValue(), nodeIcon.getFirstChild()
								.getNodeValue(), nodeImg.getFirstChild()
								.getNodeValue(), nbBoutonsElements, Integer
								.parseInt(nodePrix.getFirstChild()
										.getNodeValue()), nodeColor
								.getFirstChild().getNodeValue());
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