package main;

import java.util.ArrayList;

import util.PointList;
import util.math.Point;
import util.math.Scale;

public class Main {

	public void run() {

		Scale scale = new Scale(new Point(0, 0));
		PointList pointList = scale.getPointsOnScreen(10, 10);
		
		for (ArrayList<Point> row : pointList.getRows()) {
			for (Point point : row) {
			
				System.out.println(point.x + ", " + point.y);
				
			}
		}
		
//		new Display();

		// try {
		// new VideoEncoder().render();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public static void main(String[] args) {

		(new Main()).run();

	}

}
