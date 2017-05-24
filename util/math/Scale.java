package util.math;

public class Scale {

	private Point center;
	private double zoomLevel;
	private double density;

	public Scale(Point center) {

		this.center = center;
		this.zoomLevel = 1;
		this.density = 0.002;

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

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public void zoomIn() {
		zoomLevel *= 2;
	}

	public void zoomOut() {
		zoomLevel /= 2;
	}

	/**
	 * This method calculates the point in the scale for a given point on the
	 * screen
	 * 
	 * @param width Screen width
	 * @param height Scree height
	 * @param screenPoint
	 *            Point on the screen
	 * @return Point in the scale on the same position on the screen
	 */
	public Point getPointInScale(Point screenPoint, int width, int height) {
		
		// Calculate x point
		double xScreen = screenPoint.x - (width / 2);
		double x = center.x + density * (1 / zoomLevel) * xScreen;

		// Calculate x point
		double yScreen = screenPoint.y - (height / 2);
		double y = center.y - density * (1 / zoomLevel) * yScreen;	
		
		return new Point(x, y);
		
	}

	/**
	 * 
	 * @param width
	 *            Screen width
	 * @param height
	 *            Screen height
	 * @return
	 */
	public Point[][] getPointsOnScreen(int width, int height) {

		Point[][] points = new Point[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				int _x = x - (width / 2);
				int _y = y - (height / 2);

				Point point = new Point(center.x + density * (1 / zoomLevel) * (double) _x,
						center.y - density * (1 / zoomLevel) * (double) _y);
				points[x][y] = point;

			}
		}

		return points;

	}

}
