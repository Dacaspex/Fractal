package util.math;

public class Scale {

	/*
	 * Defines the center of the scale
	 */
	private Point center;

	/*
	 * Defines the zoom level
	 */
	private double zoomLevel;

	/*
	 * Zoom factor to define the amount of "zoom" when zooming in or out
	 */
	private double zoomFactor;

	/*
	 * This value determines how many pixels in the x direction fit into 1 scale
	 * unit at a zoom level of 1. (1 / amount of pixels)
	 */
	private double densityX;

	/*
	 * This value determines how many pixels in the y direction fit into 1 scale
	 * unit at a zoom level of 1. (1 / amount of pixels)
	 */
	private double densityY;

	public Scale(Point center) {

		this.center = center;
		this.zoomLevel = 1;
		this.zoomFactor = 2;
		this.densityX = 0.002;
		this.densityY = 0.002;

	}

	public Scale(Point center, double zoomLevel) {

		this(center);
		this.zoomLevel = zoomLevel;

	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public double getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(double zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public double getDensityX() {
		return densityX;
	}

	public void setDensityX(double densityX) {
		this.densityX = densityX;
	}

	public double getDensityY() {
		return densityY;
	}

	public void setDensityY(double densityY) {
		this.densityY = densityY;
	}

	/**
	 * Zooms the scale (in) by a factor of the zoom factor
	 */
	public void zoomIn() {
		zoomLevel *= zoomFactor;
	}

	/**
	 * Zooms the scale (out) by a factor of the zoom factor
	 */
	public void zoomOut() {
		zoomLevel /= zoomFactor;
	}

	/**
	 * This method calculates maps the point on the screen to a point in this
	 * scale. The returned point shows on the same position on the screen.
	 * 
	 * @param width
	 *            Screen width
	 * @param height
	 *            Screen height
	 * @param screenPoint
	 *            Point on the screen
	 * @return Point in the scale on the same position on the screen
	 */
	public Point getPointInScale(Point screenPoint, int width, int height) {

		// Calculate x point
		double xScreen = screenPoint.x - (width / 2);
		double x = center.x + densityX * (1 / zoomLevel) * xScreen;

		// Calculate x point
		double yScreen = screenPoint.y - (height / 2);
		double y = center.y - densityY * (1 / zoomLevel) * yScreen;

		return new Point(x, y);

	}

	/**
	 * Calculates for each pixel on the screen the corresponding point in the
	 * scale that maps to the same position on the screen
	 * 
	 * @param width
	 *            Screen width
	 * @param height
	 *            Screen height
	 * @return A 2-dimensional mapped point list with point in the scale
	 */
	public Point[][] getPointsOnScreen(int width, int height) {

		// Create point list
		Point[][] points = new Point[width][height];

		// Loop through each pixel on the screen
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				// Map x to [-0.5width, 0.5width], vice versa for y
				int _x = x - (width / 2);
				int _y = y - (height / 2);

				// Calculate point
				Point point = new Point(center.x + densityX * (1 / zoomLevel) * (double) _x,
						center.y - densityY * (1 / zoomLevel) * (double) _y);
				points[x][y] = point;

			}
		}

		return points;

	}

}
