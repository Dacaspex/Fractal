package util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Settings {
	
	private static String file;
	private static String defaultFile = "settings.xml";
	private static Document doc;
	
	public static void setFile(String file) {
		
		Settings.file = file;
		
	}

	public static void loadSettigns() {

		try {

			File xmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	public static void loadDefaultSettigns() {

		try {

			File xmlFile = new File(defaultFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	public static String getProgramName() {
		
		return doc.getElementsByTagName("programName").item(0).getTextContent().trim();
		
	}
	
	public static String getVersion() {
		
		return doc.getElementsByTagName("version").item(0).getTextContent().trim();
		
	}

}
