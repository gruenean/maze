// SimpleGraphics.java

import ch.aplu.jgamegrid.*;

public class SimpleGraphics extends GameGrid
{
  public SimpleGraphics()
  {
    super(600, 600, 1, false);
    show();
    GGPanel p = getPanel(0, 40, 0, 40);
    int i = 5;
    while (i < 36)
    {
      p.line(i, 5, 35, i);
      i++;
      delay(100);
    }
  }
  
  public static void main(String[] args)
  {
    new SimpleGraphics();
  }
}
