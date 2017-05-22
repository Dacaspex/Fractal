package util.math;

import java.awt.geom.Point2D;

public class Point extends Point2D.Double {

	private static final long serialVersionUID = 1214155619618871037L;
	public static final Point Null = null;

	public Point(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public static Point getCenterPoint(double width, double height) {

		return new Point(width / 2, height / 2);

	}

}
