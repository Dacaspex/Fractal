package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class VideoTest {

	public VideoTest() {

		run();

	}

	public void run() {

		runScript("C:\\Users\\Casper\\Desktop\\testBat.bat");

	}

	public void runScript(String exe) {

		Process process = null;

		try {

			process = new ProcessBuilder(exe).start();

		} catch (IOException e) {

			e.printStackTrace();

		}

		InputStream inputStream = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String line;

		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
