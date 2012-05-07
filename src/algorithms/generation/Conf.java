package algorithms.generation;

import java.io.IOException;
import java.util.logging.Level;

import logging.MyLogger;
import logging.UseLogger;

public class Conf {
	public final static int LEFT_WALL = 0;
	public final static int RIGHT_WALL = 1;
	public final static int TOP_WALL = 2;
	public final static int BOTTOM_WALL = 3;
	public static int STEPS = 0;
	private MyLogger _logger;
	
	
	public  static void increseSteps() {
		STEPS = STEPS + 1;
	}

	public Conf() {
		
		setUpLogger();
	
	}
	
	
	public void setUpLogger(){
		
		_logger = new MyLogger();
		try {
			_logger.setup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UseLogger.LOGGER.setLevel(Level.SEVERE);
	}
	
	
	public static String getWallName(int i) {

	if (i==0) return "LEFT";
	if (i==1) return "RIGHT";
	if (i==2) return "TOP";
	if (i==3) return "BOTTOM";
	else return "NOT A CORRECT WALL DIRECTION!!!!";
	}
	
}
