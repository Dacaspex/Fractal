package fractals;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;

import complex.Complex;
import fractals.colorSchemes.JuliaColorScheme;
import fractals.threads.Worker;
import util.Settings;

public class JuliaFractal extends AbstractFractal {

	private Complex constant;
	private int maxIterations;
	private double escapeValue;
	private JuliaColorScheme juliaColoring;

	private Complex lastEscapeComplexValue;

	public JuliaFractal() {

		name = "Julia Set";
		juliaColoring = new JuliaColorScheme(true, 512);
		lastEscapeComplexValue = new Complex();

		loadDefaultSettings();

	}

	public int getMaxIterations() {

		return maxIterations;

	}

	public void setMaxIterations(int maxIterations) {

		this.maxIterations = maxIterations;

	}

	public double getEscapeValue() {

		return escapeValue;

	}

	public void setEscapeValue(double escapeValue) {

		this.escapeValue = escapeValue;

	}

	public JuliaColorScheme getJuliaColoring() {

		return juliaColoring;

	}

	public void setJuliaColoring(JuliaColorScheme juliaColoring) {

		this.juliaColoring = juliaColoring;

	}

	@Override
	public BufferedImage getImage(int width, int height) {

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Worker worker1 = new Worker(
				new Scale(scale.getxMin(), scale.getxDifference() / 2, scale.getyMin(), scale.getyDifference() / 2),
				width / 2, height / 2, image, this);
		Worker worker2 = new Worker(
				new Scale(scale.getxDifference() / 2, scale.getxMax(), scale.getyMin(), scale.getyDifference() / 2),
				width / 2, height / 2, image, this);
		Worker worker3 = new Worker(
				new Scale(scale.getxMin(), scale.getxDifference() / 2, scale.getyDifference() / 2, scale.getyMax()),
				width / 2, height / 2, image, this);
		Worker worker4 = new Worker(
				new Scale(scale.getxDifference() / 2, scale.getxMax(), scale.getyDifference() / 2, scale.getyMax()),
				width / 2, height / 2, image, this);
		
		worker1.start();
		worker2.start();
		worker3.start();
		worker4.start();
		
		worker1.run();
		worker2.run();
		worker3.run();
		worker4.run();

		return image;

	}

	public int getEscapeNumber(Complex complex) {

		Complex currentValue = complex;
		int i;

		for (i = 0; i < maxIterations - 1 && currentValue.getModulus() < escapeValue; i++) {

			currentValue = currentValue.power(2).add(constant);

		}

		lastEscapeComplexValue = currentValue;

		return i;

	}

	public int getRGBValue(double continuousIndex) {

		return juliaColoring.getRGBValue(continuousIndex);

	}

	@Override
	public void loadDefaultSettings() {

		super.loadDefaultSettings();

		Element defaultSettingsElement = Settings.getFractalSettingsDOM(name);
		Element constantNode = (Element) defaultSettingsElement.getElementsByTagName("constant").item(0);
		Element complexNode = (Element) constantNode.getElementsByTagName("complex").item(0);

		Complex constant = Settings.getComplexFromElement(complexNode);
		int maxIterations = Integer
				.parseInt(defaultSettingsElement.getElementsByTagName("maxIterations").item(0).getTextContent());
		double escapeValue = Double
				.parseDouble(defaultSettingsElement.getElementsByTagName("escapeValue").item(0).getTextContent());

		this.constant = constant;
		this.maxIterations = maxIterations;
		this.escapeValue = escapeValue;

	}

}
