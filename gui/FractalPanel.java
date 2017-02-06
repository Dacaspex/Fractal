package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fractals.AbstractFractal;
import fractals.JuliaFractal;
import fractals.MandleBrotFractal;
import fractals.Scale;
import fractals.TestJuliaFractal;
import timers.ResizeDelayTimer;

public class FractalPanel extends JPanel implements MouseListener, ComponentListener {

	private static final long serialVersionUID = 11274784860690473L;

	private Scale scale;
	private BufferedImage image;
	private AbstractFractal fractal;
	private ResizeDelayTimer resizeDelayTimer;
	
	// debug
	private TestJuliaFractal test;

	public FractalPanel() {

		resizeDelayTimer = new ResizeDelayTimer(this);
		fractal = new JuliaFractal();
//		fractal = new MandleBrotFractal();
		scale = new Scale(-1, 1, -1, 1);
//		scale = new Scale(-1.5, 0.5, -1, 1);
		
		// debug
		test = new TestJuliaFractal();

		setPreferredSize(new Dimension());
		addMouseListener(this);
		addComponentListener(this);
		
		debug();

	}

	public void setScaling(Scale scaling) {

		this.scale = scaling;

	}
	
	public void debug() {
		
		System.out.println("-- Starting timer --");
		
		System.out.println("Old complex number system: ");
		long time = System.currentTimeMillis();
		test.getImage(scale, 1000, 1000);
		System.out.println("time 1: " + (System.currentTimeMillis() - time));
		
		System.out.println();
		
		System.out.println("New complex number system: ");
		time = System.currentTimeMillis();
		fractal.getImage(scale, 1000, 1000);
		System.out.println("time 2: " + (System.currentTimeMillis() - time));
		
	}

	public void draw() {
		
//		image = fractal.getImage(scale, getWidth(), getHeight());
//		repaint();

	}

	public void zoom(Point centerPoint) {

		double zoomFactor = 2.0;
		Scale windowScale = Scale.createFromWindow(getWidth(), getHeight());
		Point2D.Double point = new Point2D.Double(centerPoint.getX(), centerPoint.getY());
		Point2D.Double pointInScale = windowScale.getPointInScale(scale, point);
		scale.zoomAtPoint(pointInScale, zoomFactor);
		draw();

	}

	@Override
	public void paint(Graphics g) {

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		super.paint(g);

		((Graphics2D) g).drawImage(image, null, null);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		zoom(e.getPoint());

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentResized(ComponentEvent e) {

		resizeDelayTimer.restart();

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

}
