package fractals;

import java.awt.geom.Point2D;

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
	 * 
	 * @return Returns the result of xMax - xMin
	 */
	public double getxDifference() {

		return xMax - xMin;

	}

	/**
	 * Calculates the difference between the yMax and the yMin
	 * 
	 * @return Returns the result of yMax - yMin
	 */
	public double getyDifference() {

		return yMax - yMin;

	}
	
	/**
	 * Zooms the current scale with a certain factor with respect to the middle. 
	 * @param factor The factor to zoom by
	 */
	public void zoomAtCenter(double factor) {
		
		double xMiddle = (xMax + xMin) / 2.0;
		double yMiddle = (yMax + yMin) / 2.0;
		
		double distanceToMiddleX = xMax - xMiddle;
		double distanceToMiddleY = yMax - yMiddle;
		
		double scaledDistanceX = distanceToMiddleX / factor;
		double scaledDistanceY = distanceToMiddleY / factor;

		xMax = xMiddle + scaledDistanceX;
		xMin = xMiddle - scaledDistanceX;
		yMax = yMiddle + scaledDistanceY;
		yMin = yMiddle - scaledDistanceY;
		
	}

	/**
	 * TODO Change name This method converts a Point2D.Double from the standard
	 * Java library to a new point in the target scale. This means that the
	 * relative position is preserved.
	 * 
	 * @param target
	 *            The target scale which the point should be mapped to
	 * @param point
	 *            The point in source scale
	 * @return Returns a point that has the same relative position from the
	 *         source scale to the target scale
	 */
	public Point2D.Double getPointInScale(Scale target, Point2D.Double point) {

		double normalizedX = (point.getX() - xMin) / getxDifference();
		double normalizedY = (point.getY() - yMin) / getyDifference();

		double xPoint = target.getxMin() + normalizedX * target.getxDifference();
		double yPoint = target.getyMin() + normalizedY * target.getyDifference();

		return new Point2D.Double(xPoint, yPoint);

	}

	/**
	 * Creates a new scale based on the window with and height. It is simply a
	 * quicker and cleaner way. It creates a scale (0, 0, windowWith,
	 * windowHeight).
	 * 
	 * @param windowWith
	 *            The window width
	 * @param windowHeight
	 *            The windows height
	 * @return Returns a new scale based on the window width and height
	 */
	public static Scale createFromWindow(int windowWith, int windowHeight) {

		return new Scale(0, 0, windowWith, windowHeight);

	}

}
