package util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * Settings utility class. Via this class the settings of the program can be
 * read.
 * 
 * @author Casper
 *
 */
public abstract class Settings {

	private static String defaultFile = "src/resource/settings.xml";
	private static Document doc;

	/**
	 * Loads the default settings.xml file into the program. Also, it normalizes
	 * the data.
	 */
	public static void loadSettignsFile() {

		try {

			File xmlFile = new File(defaultFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

		} catch (Exception e) {
			return;
		}

	}

	/**
	 * Reads the program name from the settings.xml file
	 * 
	 * @return The name of the program
	 */
	public static String getProgramName() {

		if (doc != null) {
			return doc.getElementsByTagName("programName").item(0).getTextContent().trim();
		} else {
			return "Fractal: Could not load settings.xml file";
		}

	}

	/**
	 * Reads the version of the program in the following format: "x.y.z" where
	 * x, y and z represent integers.
	 * 
	 * @return The version of the program
	 */
	public static String getVersion() {

		if (doc != null) {
			return doc.getElementsByTagName("version").item(0).getTextContent().trim();
		} else {
			return "Version unkown";
		}

	}

}
