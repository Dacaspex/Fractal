package main;

import java.io.IOException;

import video.VideoEncoder;

public class Main {

	public void run() {

//		 new FractalDisplay();
		// new VideoTest();
		 try {
			new VideoEncoder().render();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		(new Main()).run();

	}

}
