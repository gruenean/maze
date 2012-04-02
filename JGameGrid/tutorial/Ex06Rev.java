// Ex06Rev.java
// Use of GGMouseTouchListener

import ch.aplu.jgamegrid.*;
import java.awt.*;

public class Ex06Rev extends GameGrid
{
  public Ex06Rev()
  {
    super(7, 4, 40, false);
    setBgColor(new Color(210, 210, 210));
    Bulb[] bulbs = new Bulb[3];
    SwitchRev[] switches = new SwitchRev[3];
    for (int i = 0; i < 3; i++)
    {
      bulbs[i] = new Bulb(i);
      addActor(bulbs[i], new Location(2*i + 1, 1));
      switches[i] = new SwitchRev(bulbs[i]);
      addActor(switches[i], new Location(2*i + 1, 3));
      switches[i].addMouseTouchListener(switches[i], GGMouse.lPress);
      switches[i].setMouseTouchRectangle(new Point(-10, 10), 30, 20);
      // Only switch knob active. Used for both sprite IDs
    }
    show();
  }

  public static void main(String[] args)
  {
    new Ex06Rev();
  }
}
