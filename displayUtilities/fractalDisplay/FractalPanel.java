package displayUtilities.fractalDisplay;

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

import fractals.AbstractFractal;
import fractals.JuliaFractal;
import fractals.Scale;
import timers.ResizeDelayTimer;

public class FractalPanel extends JPanel implements MouseListener, ComponentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11274784860690473L;
	
	private Scale scaling;
	private BufferedImage image;
	private AbstractFractal fractal;
	private ResizeDelayTimer resizeDelayTimer;

	public FractalPanel() {
		
		resizeDelayTimer = new ResizeDelayTimer(this);
		fractal = new JuliaFractal();
		scaling = new Scale(-1, 1, -1, 1);
		
		setPreferredSize(new Dimension());
		addMouseListener(this);
		addComponentListener(this);
		
		// Video encoding
		// http://jcodec.org/
		// FFMPEG <- works in command prompt

	}
	
	public void setScaling(Scale scaling) {
		
		this.scaling = scaling;
		
	}

	public void draw() {

		image = fractal.getImage(scaling, getWidth(), getHeight());
		repaint();

	}
	
	public void zoom(Point centerPoint) {
		
		double zoomFactor = 3.0;
		
		double xScaleMin = scaling.getxMin();
		double xScaleMax = scaling.getxMax();
		double yScaleMin = scaling.getyMin();
		double yScaleMax = scaling.getyMax();
		
		// Translation
		double normalizedX = centerPoint.getX() / (double) getWidth();
		double normalizedY = centerPoint.getY() / (double) getHeight();
		
		double xPoint = xScaleMin + normalizedX * (xScaleMax - xScaleMin);
		double yPoint = yScaleMin + normalizedY * (yScaleMax - yScaleMin);
		
		double xMiddle = (xScaleMax + xScaleMin) / 2.0;
		double yMiddle = (yScaleMax + yScaleMin) / 2.0;
		
		double xTranslation = xPoint - xMiddle;
		double yTranslation = yPoint - yMiddle;
		
		xScaleMax += xTranslation;
		xScaleMin += xTranslation;
		yScaleMax += yTranslation;
		yScaleMin += yTranslation;
		
		// Scaling
		double xDelta = (xScaleMax - xScaleMin) / zoomFactor;
		double yDelta = (yScaleMax - yScaleMin) / zoomFactor;
		
		xMiddle = (xScaleMax + xScaleMin) / 2.0;
		yMiddle = (yScaleMax + yScaleMin) / 2.0;
		
		xScaleMax = xMiddle + xDelta;
		xScaleMin = xMiddle - xDelta;
		yScaleMax = yMiddle + yDelta;
		yScaleMin = yMiddle - yDelta;
		
		Scale scaling = new Scale(xScaleMin, xScaleMax, yScaleMin, yScaleMax);
		
		setScaling(scaling);
		
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
