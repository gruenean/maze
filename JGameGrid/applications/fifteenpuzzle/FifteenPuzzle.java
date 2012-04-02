// FifteenPuzzle.java
// Sliding block puzzle

import ch.aplu.jgamegrid.*;

public class FifteenPuzzle extends GameGrid
{
  public FifteenPuzzle()
  {
    super(4, 4, 60, java.awt.Color.red, false);
    setSimulationPeriod(100);
    NumberBlock[] stones = new NumberBlock[15];
    for (int i = 0; i < 15; i++)
    {
      stones[i] = new NumberBlock(i);
      addMouseListener(stones[i], GGMouse.lPress | GGMouse.lDrag | GGMouse.lRelease);
      addActor(stones[i], getRandomEmptyLocation());
    }
    
    doRun();
    show();
  }

  public static void main(String[] args)
  {
    new FifteenPuzzle();
  }
}
