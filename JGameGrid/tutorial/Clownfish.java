// Clownfish.java
// Used in Ex01.java, Ex02.java

import ch.aplu.jgamegrid.*;

public class Clownfish extends Actor
{
  public Clownfish()
  {
    super("sprites/nemo.gif");
  }

  public void act()
  {
    move();
    if (getX() == 0 || getX() == 9)
    {
      turn(180);
      setHorzMirror(isHorzMirror() ? false : true) ;
    }
  }
}
