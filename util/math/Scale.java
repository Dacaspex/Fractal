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
		zoomLevel += 1;
	}

	public void zoomOut() {
		zoomLevel -= 1;
	}

	/**
	 * This method calculates the point in the scale for a given point on the
	 * screen
	 * 
	 * @param point
	 *            Point on the screen
	 * @return Point in the scale on the same position on the screen
	 */
	public Point getPointInScale(Point point) {

		return null;

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

				Point point = new Point(center.x + density * (1 / Math.pow(zoomLevel, 2)) * (double) _x,
						center.y - density * (1 / Math.pow(zoomLevel, 2)) * (double) _y);
				points[x][y] = point;

			}
		}

		return points;

	}

}
