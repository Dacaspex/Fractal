package util.math;

import util.PointList;

public class Scale {

	private Point center;
	private double zoomFactor;
	private double density;

	public Scale(Point center) {

		this.center = center;
		this.zoomFactor = 1;
		this.density = 0.1;

	}

	public PointList getPointsOnScreen(int width, int height) {

		// Translate screen coordinate system
		// width -> [-0.5width, 0.5width]

		PointList pointList = new PointList(width, height);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				int _x = x - width / 2;
				int _y = y - height / 2;

				Point point = new Point(center.x + density * zoomFactor * (double) _x,
						center.y + density * zoomFactor * (double) _y);
				System.out.println(point.x + ", " + point.y);
				pointList.put(point, x, y);

			}
		}

		return null;

	}

}
