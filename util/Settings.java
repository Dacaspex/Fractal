package util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class Settings {
	
	private static String file;
	private static String defaultFile = "src/resource/settings.xml";
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
	
	public static void loadDefaultSettignsFile() {

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
	
	public static String getDefaultFractal() {
		
		return doc.getElementsByTagName("defaultFractal").item(0).getTextContent().trim();
		
	}
	
	public static String[] getFractalNameList() {
		
		NodeList fractalNodeList = doc.getElementsByTagName("fractal");
		int length = fractalNodeList.getLength();
		String[] fractalNameList = new String[length];
		
		for (int i = 0; i < length; i++) {
			
			Element fractalNode = (Element) fractalNodeList.item(i);
			String name = fractalNode.getElementsByTagName("name").item(0).getTextContent();
			fractalNameList[i] = name;
			
		}
		
		return fractalNameList;
		
	}

}
