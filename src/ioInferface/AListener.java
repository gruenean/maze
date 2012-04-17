package ioInferface;

import java.util.Scanner;

public abstract class AListener {

	protected String inputString = " ";
	protected Scanner _in = null;
	protected boolean goingon;
	

	public AListener() {
		_in = new Scanner(System.in);
		goingon = true;
	}
	
	
	
	
	protected void quit(){
		goingon = false;
		
	}
	
}
