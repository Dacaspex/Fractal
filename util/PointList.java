package util;

import java.util.ArrayList;

import util.math.Point;

public class PointList {

	private ArrayList<ArrayList<Point>> points;
	private int dimensionX;
	private int dimensionY;

	public PointList(int dimensionX, int dimensionY) {

		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
		this.points = new ArrayList<ArrayList<Point>>();

		init();

	}

	private void init() {

		for (int i = 0; i < dimensionY; i++) {
			ArrayList<Point> row = new ArrayList<Point>();
			for (int j = 0; j < dimensionX; j++) {
				row.add(Point.Null);
			}
			points.add(row);
		}

	}

	public void put(Point point, int x, int y) {

		ArrayList<Point> row = points.get(y);
		row.add(x, point);
		points.set(y, row);

	}

	public ArrayList<ArrayList<Point>> getRows() {
		return points;
	}

}
