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

import fractals.FractalManager;
import fractals.Scale;
import timers.ResizeDelayTimer;

public class FractalPanel extends JPanel implements MouseListener, ComponentListener {

	private static final long serialVersionUID = 11274784860690473L;

	private static FractalPanel instance;

	private BufferedImage image;
	private ResizeDelayTimer resizeDelayTimer;
	private SettingsPanel settingsPanel;
	private FractalManager fractalManager;

	public FractalPanel(FractalManager fractalManager, SettingsPanel settingsPanel) {

		FractalPanel.instance = this;

		resizeDelayTimer = new ResizeDelayTimer(this);
		this.settingsPanel = settingsPanel;
		this.fractalManager = fractalManager;

		fractalManager.setFractalPanel(this);

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

		fractalManager.requestImage(getWidth(), getHeight());

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
	public void zoomIn(Point centerPoint) {

		double zoomFactor = 3.0;

		// Get the fractal and window (panel dimension) scale
		Scale fractalScale = fractalManager.getSelectedFractal().getScale();
		Scale windowScale = Scale.createFromWindow(getWidth(), getHeight());

		// Calculate where the point in the window maps to in the fractal scale
		Point2D.Double point = new Point2D.Double(centerPoint.getX(), centerPoint.getY());
		Point2D.Double pointInScale = windowScale.getPointInScale(fractalScale, point);

		// Zoom at the point in the fractal scale
		fractalScale.zoomAtPoint(pointInScale, zoomFactor);

		// Update information
		settingsPanel.getInformationPanel().updateInformation();
		requestUpdate();

	}

	/**
	 * Zooms out the current fractal scale at a point on the screen.
	 * 
	 * @param centerPoint
	 *            The point on the screen
	 */
	public void zoomOut(Point centerPoint) {

		double zoomFactor = 0.3;

		// Get the fractal and window (panel dimension) scale
		Scale fractalScale = fractalManager.getSelectedFractal().getScale();
		Scale windowScale = Scale.createFromWindow(getWidth(), getHeight());

		// Calculate where the point in the window maps to in the fractal scale
		Point2D.Double point = new Point2D.Double(centerPoint.getX(), centerPoint.getY());
		Point2D.Double pointInScale = windowScale.getPointInScale(fractalScale, point);

		// Zoom out at the point
		fractalScale.zoomAtPoint(pointInScale, zoomFactor);

		// Update information
		settingsPanel.getInformationPanel().updateInformation();
		requestUpdate();

	}

	/**
	 * Translates the current fractal scale at a point on the screen.
	 * 
	 * @param centerPoint
	 *            The point on the screen
	 */
	public void translate(Point targetPoint) {

		// Get the fractal and window (panel dimension) scale
		Scale fractalScale = fractalManager.getSelectedFractal().getScale();
		Scale windowScale = Scale.createFromWindow(getWidth(), getHeight());

		// Calculate where the point in the window maps to in the fractal scale
		Point2D.Double point = new Point2D.Double(targetPoint.getX(), targetPoint.getY());
		Point2D.Double pointInScale = windowScale.getPointInScale(fractalScale, point);

		// Zoom out at the point
		fractalScale.translateCenterToPoint(pointInScale);

		// Update information
		settingsPanel.getInformationPanel().updateInformation();
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
