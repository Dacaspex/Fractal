package util.timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Application;

/**
 * <p>
 * Timer to handle the updates of the explorer panel when the display is
 * resized. It waits a certain amount of time before requesting the explorer
 * panel to update. The exact amount of time is given by {@link #delay}, which
 * has a default value of 200.
 * </p>
 * <p>
 * This makes sure that the program isn't overloaded with updates when the
 * screen is resized. Whenever the timer fires, the explorer panel is requested
 * to update.
 * </p>
 * 
 * @see gui.explorer.FractalPanel
 * 
 * @author Casper
 *
 */
public class ResizeDelayTimer implements ActionListener {

	/*
	 * The amount of delay in milliseconds the program has to wait from the
	 * moment the screen has been resized until the explorer panel should
	 * update.
	 */
	private final int delay;

	/*
	 * The actual timer
	 */
	private Timer timer;

	public ResizeDelayTimer() {

		delay = 200;
		timer = new Timer(delay, this);

	}

	/**
	 * Restarts the timer. This should be called when the screen is resized. It
	 * acts as a start method as well.
	 */
	public void restart() {
		timer.restart();
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		timer.stop();
		// Request the explorer panel to update.
		// TODO: Create more abstractive use
		Application.getApplication().getDisplay().getExplorerPanel().update();

	}

}
