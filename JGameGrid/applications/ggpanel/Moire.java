// Moire.java

import ch.aplu.jgamegrid.*;

public class Moire extends GameGrid
{
  public Moire()
  {
    super(600, 600, 1, false);
    show();
    GGPanel p = getPanel();
    p.window(0, 10, 0, 10);
    int i, k;
    for (i = 0; i <= 10; i++)
    {
      for (k = 0; k <= 10; k++)
      {
        p.line(i, 0, k, 10);
        delay(50);
      }
    }

    for (i = 0; i <= 10; i++)
    {
      for (k = 0; k <= 10; k++)
      {
        p.line(0, i, 10, k);
        delay(50);
      }
    }
  }
  
  public static void main(String[] args)
  {
    new Moire();
  }
}
