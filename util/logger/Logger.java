package util.logger;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Logger utility class to provide simple logging functionality. Log messages
 * can be created and shown in the console. Logging can be disabled as well.
 * 
 * @author Casper
 *
 */
public class Logger {

	/**
	 * Stores all log messages
	 */
	private static ArrayList<String> logMessages = new ArrayList<String>();

	/**
	 * Boolean to indicate if log messages should be send to the console
	 */
	private static boolean logging = false;

	public static void setLogging(boolean logging) {
		Logger.logging = logging;
	}

	/**
	 * Create a new default log message. The type will automatically be set to
	 * default. If logging is set to true, the message will appear in the
	 * console.
	 * 
	 * @param source
	 *            Source of the message
	 * @param message
	 *            The message itself
	 */
	public static void log(Object source, String message) {
		log(source, message, Type.LOG_DEFAULT);
	}

	/**
	 * Create a new default log message with the specified type. If logging is
	 * set to true, the message will appear in the console.
	 * 
	 * @param source
	 *            The source of the message
	 * @param message
	 *            The message itself
	 * @param type
	 *            The type of the message
	 */
	public static void log(Object source, String message, Type type) {

		// Get time
		LocalDateTime time = LocalDateTime.now();
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();

		// Format time
		String formattedHour = String.format("%02d", hour);
		String formattedMinute = String.format("%02d", minute);
		String formattedSecond = String.format("%02d", second);

		// Build message
		String logMessage = formattedHour + ":" + formattedMinute + ":" + formattedSecond + " - ["
				+ source.getClass().getSimpleName() + "] " + type.name + message;

		logMessages.add(logMessage);

		if (logging) {
			System.out.println(logMessage);
		}

	}

	/**
	 * Different types of log messages
	 */
	public enum Type {

		LOG_DEFAULT(""), LOG_SUCCESS("Success: "), LOG_WARNING("Warning: "), LOG_ERROR("Error: ");

		private final String name;

		private Type(String string) {
			name = string;
		}

	}

}
