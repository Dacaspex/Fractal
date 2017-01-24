package main;

import video.VideoEncoder;

public class Main {

	public void run() {

//		 new FractalDisplay();
		// new VideoTest();
		 new VideoEncoder().render();
	}

	public static void main(String[] args) {

		(new Main()).run();

	}

}
