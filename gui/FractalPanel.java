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
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fractals.FractalManager;
import render.Renderer;
import util.timers.ResizeDelayTimer;

public class FractalPanel extends JPanel implements MouseListener, ComponentListener {

	private static final long serialVersionUID = 11274784860690473L;

	private static FractalPanel instance;

	private BufferedImage image;
	private ResizeDelayTimer resizeDelayTimer;
	private FractalManager fractalManager;

	private Renderer renderer;

	public FractalPanel(FractalManager fractalManager, SettingsPanel settingsPanel) {

		FractalPanel.instance = this;

		this.resizeDelayTimer = new ResizeDelayTimer(this);
		this.fractalManager = fractalManager;
		this.renderer = new Renderer();

		setPreferredSize(new Dimension());
		addMouseListener(this);
		addComponentListener(this);

	}

	/**
	 * @return An instance of itself (singleton)
	 */
	public static FractalPanel getFractalPanel() {

		return FractalPanel.instance;

	}

	/**
	 * Request to render a new fractal image
	 */
	public void requestUpdate() {
		
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		for (StackTraceElement element : trace) {
			System.out.println(element);
		}
		
		// fractalManager.requestImage(getWidth(), getHeight());
		BufferedImage image = renderer.render(fractalManager.getSelectedFractal(), getWidth(), getHeight());
		if (image != null) {
			showImage(image);
		}
		
		System.out.println("----------------------");
		
	}

	/**
	 * Sets the image that should be painted on the panel
	 * 
	 * @param image
	 *            The image to be drawn
	 */
	public void showImage(BufferedImage image) {

		this.image = image;
		repaint();

	}

	/**
	 * Zooms the current fractal scale at a point on the screen.
	 * 
	 * @param centerPoint
	 *            The point on the screen
	 */
	public void zoomIn(Point targetPoint) {

		// Set center point and zoom in
		util.math.Point screenPoint = new util.math.Point(targetPoint.x, targetPoint.y);
		util.math.Point point = fractalManager.getSelectedFractal().getScale().getPointInScale(screenPoint, getWidth(),
				getHeight());
		fractalManager.getSelectedFractal().getScale().setCenter(point);
		fractalManager.getSelectedFractal().getScale().zoomIn();

		// Update information
		requestUpdate();

	}

	/**
	 * Zooms out the current fractal scale at a point on the screen.
	 * 
	 * @param centerPoint
	 *            The point on the screen
	 */
	public void zoomOut(Point targetPoint) {

		// Set center point and zoom out
		util.math.Point screenPoint = new util.math.Point(targetPoint.x, targetPoint.y);
		util.math.Point point = fractalManager.getSelectedFractal().getScale().getPointInScale(screenPoint, getWidth(),
				getHeight());
		fractalManager.getSelectedFractal().getScale().setCenter(point);
		fractalManager.getSelectedFractal().getScale().zoomOut();

		// Update information
		requestUpdate();

	}

	/**
	 * Translates the current fractal scale at a point on the screen.
	 * 
	 * @param centerPoint
	 *            The point on the screen
	 */
	public void translate(Point targetPoint) {

		// Set center point
		util.math.Point screenPoint = new util.math.Point(targetPoint.x, targetPoint.y);
		util.math.Point point = fractalManager.getSelectedFractal().getScale().getPointInScale(screenPoint, getWidth(),
				getHeight());
		fractalManager.getSelectedFractal().getScale().setCenter(point);

		// Update information
		requestUpdate();

	}

	@Override
	public void paint(Graphics g) {

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		super.paint(g);

		((Graphics2D) g).drawImage(image, null, null);

	}

	@Override
	public void mouseClicked(MouseEvent event) {

		switch (event.getButton()) {

		case MouseEvent.BUTTON1:
			zoomIn(event.getPoint());
			break;

		case MouseEvent.BUTTON2:
			translate(event.getPoint());
			break;

		case MouseEvent.BUTTON3:
			zoomOut(event.getPoint());
			break;

		default:
			break;

		}

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
