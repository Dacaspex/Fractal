package main;

import displayUtilities.fractalDisplay.FractalDisplay;
import test.VideoTest;
import video.VideoEncoder;

public class Main {
    
    public void run() {
    	
    	new FractalDisplay();
    	//new VideoTest();
    	new VideoEncoder().render();
                
    }

    public static void main(String[] args) {
        
        (new Main()).run();

    }

}
