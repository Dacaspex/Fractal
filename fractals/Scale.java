package fractals;

import java.awt.Point;

public class Scale {
	
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;

	public Scale(double xMin, double xMax, double yMin, double yMax) {

		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;

	}

	public double getxMin() {

		return xMin;

	}

	public void setxMin(double xMin) {

		this.xMin = xMin;

	}

	public double getxMax() {

		return xMax;

	}

	public void setxMax(double xMax) {

		this.xMax = xMax;

	}

	public double getyMin() {

		return yMin;

	}

	public void setyMin(double yMin) {

		this.yMin = yMin;

	}

	public double getyMax() {

		return yMax;

	}

	public void setyMax(double yMax) {

		this.yMax = yMax;

	}

	/**
	 * Calculates the difference between the xMax and the xMin
	 * @return Returns the result of xMax - xMin
	 */
	public double getxDifference() {

		return xMax - xMin;

	}

	/**
	 * Calculates the difference between the yMax and the yMin
	 * @return Returns the result of yMax - yMin
	 */
	public double getyDifference() {

		return yMax - yMin;

	}
	
	// TODO change name
	public Point getPointInScaling(Scale source, Scale target, Point point) {
		
		// TODO create method body
		return new Point();
		
	}

}
