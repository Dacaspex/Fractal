package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import displayUtilities.fractalDisplay.FractalPanel;

public class ResizeDelayTimer implements ActionListener {

	private final int delay;

	private Timer timer;

	private FractalPanel fractalDrawManager;

	public ResizeDelayTimer(FractalPanel fractalDrawManager) {

		delay = 200;
		timer = new Timer(delay, this);
		this.fractalDrawManager = fractalDrawManager;

	}

	public void restart() {

		timer.restart();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		timer.stop();
		fractalDrawManager.draw();

	}

}
