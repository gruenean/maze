// Ex06.java
// Virtual switches

import ch.aplu.jgamegrid.*;
import java.awt.Color;

public class Ex06 extends GameGrid
{
  public Ex06()
  {
    super(7, 4, 40, false);
    setBgColor(new Color(210, 210, 210));
    Bulb[] bulbs = new Bulb[3];
    Switch[] switches = new Switch[3];
    for (int i = 0; i < 3; i++)
    {
      bulbs[i] = new Bulb(i);
      addActor(bulbs[i], new Location(2*i + 1, 1));
      switches[i] = new Switch(bulbs[i]);
      addActor(switches[i], new Location(2*i + 1, 3));
      addMouseListener(switches[i], GGMouse.lPress);
    }
    show();
  }

  public static void main(String[] args)
  {
    new Ex06();
  }
}
