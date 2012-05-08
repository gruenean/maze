package GUI;

import java.awt.event.KeyEvent;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GGKeyListener;

class LittleBug extends Actor implements GGKeyListener {
	public LittleBug() {
		super(true, "sprites/smallbug.gif");
	}

	public boolean keyPressed(KeyEvent evt)
	  {
	    switch (evt.getKeyCode())
	    {
	      case KeyEvent.VK_UP:
	        setDirection(270);
	        break;
	      case KeyEvent.VK_RIGHT:
	        setDirection(0);
	        break;
	      case KeyEvent.VK_LEFT:
	        setDirection(180);
	        break;
	      case KeyEvent.VK_DOWN:
	        setDirection(90);  
	        break;
	    }
	    canIgoOn();
	  //  act();
	    
	    return true;
	  }



	public boolean keyReleased(KeyEvent evt)
	  {
	    return true;
	  }

	public void canIgoOn()
	  {

	    Actor actor = gameGrid.getOneActorAt(getNextMoveLocation(), Cell.class);
	  
	    System.out.println("aktor = " + actor);
	    if (actor == null)
	    {
	    	 move();
	    	
	    	
	    	//turn(180);
	    }
	    else
	    	setHorzMirror(!isHorzMirror());
	  }


	public void reset() {
		setHorzMirror(false);
	}
}