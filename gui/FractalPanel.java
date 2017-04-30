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

	public static FractalPanel getFractalPanel() {

		return FractalPanel.instance;

	}

	public void requestUpdate() {

		fractalManager.requestImage(getWidth(), getHeight());

	}

	public void showImage(BufferedImage image) {

		this.image = image;
		repaint();

	}

	public void zoomIn(Point centerPoint) {

		double zoomFactor = 3.0;

		Scale fractalScale = fractalManager.getSelectedFractal().getScale();
		Scale windowScale = Scale.createFromWindow(getWidth(), getHeight());

		Point2D.Double point = new Point2D.Double(centerPoint.getX(), centerPoint.getY());
		Point2D.Double pointInScale = windowScale.getPointInScale(fractalScale, point);

		fractalScale.zoomAtPoint(pointInScale, zoomFactor);
		settingsPanel.getInformationPanel().updateInformation();
		requestUpdate();

	}

	public void zoomOut(Point centerPoint) {

		double zoomFactor = 0.3;

		Scale fractalScale = fractalManager.getSelectedFractal().getScale();
		Scale windowScale = Scale.createFromWindow(getWidth(), getHeight());

		Point2D.Double point = new Point2D.Double(centerPoint.getX(), centerPoint.getY());
		Point2D.Double pointInScale = windowScale.getPointInScale(fractalScale, point);

		fractalScale.zoomAtPoint(pointInScale, zoomFactor);
		settingsPanel.getInformationPanel().updateInformation();
		requestUpdate();

	}

	public void translate(Point targetPoint) {

		Scale fractalScale = fractalManager.getSelectedFractal().getScale();
		Scale windowScale = Scale.createFromWindow(getWidth(), getHeight());

		Point2D.Double point = new Point2D.Double(targetPoint.getX(), targetPoint.getY());
		Point2D.Double pointInScale = windowScale.getPointInScale(fractalScale, point);

		fractalScale.translateCenterToPoint(pointInScale);
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
