package fractals.postImageProcessing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import fractals.Scale;

public class Axis {

	private Color axisColor;
	private Color textColor;

	private int axisWidth;

	private boolean showY;
	private boolean showX;

	private Scale scale;

	public Axis(Scale scale) {

		axisColor = new Color(25, 25, 50, 100);
		textColor = Color.WHITE;
		axisWidth = 2;
		showY = true;
		showX = true;
		this.scale = scale;

	}

	public BufferedImage drawAxis(BufferedImage image) {

		if (showX && (scale.getxMin() < 0 && scale.getxMax() > 0)) {

			Scale windowScale = Scale.createFromWindow(image.getWidth(), image.getHeight());
			Point2D.Double xPoint1 = scale.getPointInScale(windowScale, new Point2D.Double(0, scale.getyMax()));
			Point2D.Double xPoint2 = scale.getPointInScale(windowScale, new Point2D.Double(0, scale.getyMin()));
			Graphics2D g = (Graphics2D) image.getGraphics();

			g.setColor(axisColor);
			g.setStroke(new BasicStroke(axisWidth));
			g.drawLine((int) xPoint1.getX(), (int) xPoint1.getY(), (int) xPoint2.getX(), (int) xPoint2.getY());
			g.dispose();

		}

		if (showY && (scale.getyMin() < 0 && scale.getyMax() > 0)) {

			Scale windowScale = Scale.createFromWindow(image.getWidth(), image.getHeight());
			Point2D.Double yPoint1 = scale.getPointInScale(windowScale, new Point2D.Double(scale.getxMin(), 0));
			Point2D.Double yPoint2 = scale.getPointInScale(windowScale, new Point2D.Double(scale.getxMax(), 0));
			Graphics2D g = (Graphics2D) image.getGraphics();

			g.setColor(axisColor);
			g.setStroke(new BasicStroke(axisWidth));
			g.drawLine((int) yPoint1.getX(), (int) yPoint1.getY(), (int) yPoint2.getX(), (int) yPoint2.getY());
			g.dispose();

		}

		return image;

	}

}
