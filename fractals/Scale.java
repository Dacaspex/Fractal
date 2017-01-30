package fractals;

import java.awt.Point;

public class Scale {
	
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;

	public Scale(double xScaleMin, double xScaleMax, double yScaleMin, double yScaleMax) {

		this.xMin = xScaleMin;
		this.xMax = xScaleMax;
		this.yMin = yScaleMin;
		this.yMax = yScaleMax;

	}

	public double getxMin() {

		return xMin;

	}

	public void setxMin(double xScaleMin) {

		this.xMin = xScaleMin;

	}

	public double getxMax() {

		return xMax;

	}

	public void setxMax(double xScaleMax) {

		this.xMax = xScaleMax;

	}

	public double getyMin() {

		return yMin;

	}

	public void setyMin(double yScaleMin) {

		this.yMin = yScaleMin;

	}

	public double getyMax() {

		return yMax;

	}

	public void setyMax(double yScaleMax) {

		this.yMax = yScaleMax;

	}

	public double getxDifference() {

		return xMax - xMin;

	}

	public double getyDifference() {

		return yMax - yMin;

	}
	
	// TODO change name
	public Point getPointInScaling(Scale source, Scale target, Point point) {
		
		// TODO create method body
		return new Point();
		
	}

}
