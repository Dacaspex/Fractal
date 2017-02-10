package util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fractals.AbstractFractal;
import fractals.FractalManager;
import fractals.Scale;

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
	
	public static AbstractFractal getDefaultFractal(FractalManager fractalManager) {
		
		// TODO should throw an error when the parsing throws an error
		
		Element defaultFractalNode = (Element) doc.getElementsByTagName("defaultFractal").item(0);
		Element scaleNode = (Element) defaultFractalNode.getElementsByTagName("scale").item(0);
		
		String name = defaultFractalNode.getElementsByTagName("name").item(0).getTextContent();
		
		double xMin = Double.parseDouble(scaleNode.getElementsByTagName("xMin").item(0).getTextContent());
		double xMax = Double.parseDouble(scaleNode.getElementsByTagName("xMax").item(0).getTextContent());
		double yMin = Double.parseDouble(scaleNode.getElementsByTagName("yMin").item(0).getTextContent());
		double yMax = Double.parseDouble(scaleNode.getElementsByTagName("yMax").item(0).getTextContent());
		
		Scale scale = new Scale(xMin, xMax, yMin, yMax);
		
		AbstractFractal fractal = fractalManager.getFractalByName(name);
		fractal.setScale(scale);
		
		return fractal;
		
	}
	
	public static Element getFractalSettingsDOM(String name) {

		NodeList fractalNodeList = doc.getElementsByTagName("fractal");
		
		for (int i = 0; i < fractalNodeList.getLength(); i++) {
			
			Element fractalElement = (Element) fractalNodeList.item(i);
			String currentName = fractalElement.getElementsByTagName("name").item(0).getTextContent();
			
			if (name.equals(currentName)) {
				
				Element defaultSettingsELement = (Element) fractalElement.getElementsByTagName("defaultSettings").item(0);
				return defaultSettingsELement;
				
			}
			
		}
		
		return null;
		
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
