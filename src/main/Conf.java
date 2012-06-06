package main;

import ioInferface.gui.MyGameGrid;

import java.io.IOException;
import java.util.logging.Level;

import Interfaces.IOutput;

import logging.MyLogger;
import logging.UseLogger;

/**
 * General variables and methods are stored in this central configuration class
 *
 */
public class Conf {
	public final static int LEFT_WALL = 0;
	public final static int RIGHT_WALL = 1;
	public final static int TOP_WALL = 2;
	public final static int BOTTOM_WALL = 3;
	public static int TIME = 1000;
	private boolean _isStepModus;
	private IOutput _output;
	private MyGameGrid _myGui;
	private MazeHandler _mazeHandler;

	/**
	 * Sets a reference to the given GUI to make it available to other classes. 
	 * 
	 * @param myGUI GUI
	 */
	public void setGUI(MyGameGrid myGUI) {
		_myGui = myGUI;
	}

	/**
	 * set time between two steps (only useful if step modus is switched on)
	 * 
	 * @param time time in milli seconds
	 */
	public void setTime(int time){	
		TIME = time;
	}
	
	/**
	 * returns the reference to the graphical user interface
	 * 
	 * @return reference to gui
	 */
	public MyGameGrid getGUI() {
		return _myGui;
	}

	public Conf() {
		setUpLogger();
	}

	public IOutput get_output() {
		return _output;
	}

	public void set_output(IOutput output) {
		this._output = output;
	}
	
	public static String LOGSTRING = null;

	/**
	 * check if step modus is switched on or off
	 * 
	 * @return true if on, false if off
	 */
	public boolean isStepModus() {
		return _isStepModus;
	}

	/**
	 * switch step modus on or off
	 * 
	 * @param isStepModus true to switch on, false to switch off
	 */
	public void setStepModus(boolean isStepModus) {
		this._isStepModus = isStepModus;
	}

	/**
	 * initially sets up the logger.
	 */
	private void setUpLogger() {

		//_logger = new MyLogger();
		try {
			MyLogger.setup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UseLogger.LOGGER.setLevel(Level.ALL);
	}

	/**
	 * Returns the name of a given wall (used by own solving algorithm.)
	 * This will probably be moved to the algorithm one day.
	 *  
	 * @param id of the wall
	 * @return name of the wall 
	 */
	public static final String getWallName(int i) {
		if (i == 0)
			return "LEFT";
		if (i == 1)
			return "RIGHT";
		if (i == 2)
			return "TOP";
		if (i == 3)
			return "BOTTOM";
		else
			return "NOT A CORRECT WALL DIRECTION!!!!";
	}

	/**
	 * Method returns the reference to the maze handler
	 * 
	 * @return
	 */
	public MazeHandler getMazeHandler() {
		return _mazeHandler;
	}

	/**
	 * Sets the reference to the maze handler.
	 * 
	 * @param mazeHandler
	 */
	public void setMazeHandler(MazeHandler mazeHandler) {
		this._mazeHandler = mazeHandler;
	}
}
