package util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import complex.Complex;
import fractals.AbstractFractal;
import fractals.FractalManager;

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
		String name = defaultFractalNode.getElementsByTagName("name").item(0).getTextContent();
		AbstractFractal fractal = fractalManager.getFractalByName(name);
		
		return fractal;
		
	}
	
	public static Element getFractalSettingsDOM(String name) {
		
		if (name == null) {
			
			return null;
			
		}

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
	
	public static Complex getComplexFromElement(Element complexNode) {
		
		Element realNode = (Element) complexNode.getElementsByTagName("real").item(0);
		Element imaginaryNode = (Element) complexNode.getElementsByTagName("imaginary").item(0);
		
		if (!(realNode == null && imaginaryNode == null)) {
			
			double real = Double.parseDouble(realNode.getTextContent());
			double imaginary = Double.parseDouble(imaginaryNode.getTextContent());
			
			return new Complex(real, imaginary);
			
		}
		
		// TODO throw error for unaccessible nodes
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
