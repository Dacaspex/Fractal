package util.math;

public class Scale {

	private Point center;
	private double zoomFactor;
	private double density;

	public Scale(Point center) {

		this.center = center;
		this.zoomFactor = 1;
		this.density = 0.1;

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

				int _x = x - width / 2;
				int _y = y - height / 2;

				Point point = new Point(center.x + density * zoomFactor * (double) _x,
						center.y + density * zoomFactor * (double) _y);
				points[x][y] = point;

			}
		}

		return points;

	}

}
