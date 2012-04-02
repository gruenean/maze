
// SkiResort.java

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import ch.aplu.jgamegrid.*;

public class SkiResort extends GameGrid implements GGWindowStateListener
{
  private final int platterNumber = 20;
  // Number of animation periods between platters
  private final int platterPeriod = 35;
  private final int nbSkiers = 21;
  private Skier[] skiers = new Skier[nbSkiers];
  private InfoBoard infoBoard;
  private SkiLift skiLift;

  public SkiResort()
  {
    super(800, 600, 1, null, "sprites/ski_background.png", true);
    setSimulationPeriod(40);
    addWindowStateListener(this);
    skiLift = new SkiLift(platterNumber, platterPeriod, this);
    infoBoard = new InfoBoard(this, skiLift, nbSkiers);
    
    Arrays.fill(skiers, null);
    // the first half of the skiers are slow:
    for (int i = 0; i < nbSkiers / 2; i++)
    {
      skiers[i] = new Skier(skiLift, infoBoard, true);
    }
    // The other half is fast:
    for (int i = nbSkiers / 2; i < nbSkiers; i++)
    {
      skiers[i] = new Skier(skiLift, infoBoard, false);

    }  
    // Set collision listeners:
    for (Skier skier : skiers)
    {
      skier.setCollisionSpot(new Point(10, 0));
      for (Skier skierb : skiers)
      {
        if (skier != skierb)
          skier.addCollisionActor(skierb);
      }
    }
    
    reset();
    show();
    infoBoard.show();
    setTitle("Have a nice skiing day!");
    infoBoard.setPosition(getPosition().x + 820, getPosition().y);
  }

  public void reset()
  {
	removeAllActors();
	
	addActor(skiLift, new Location(135, 297));
	skiLift.show();
	skiLift.purgeQueue();
    infoBoard.purgeInfoBoard();

    // Mixe schnelle + langsame Skifahrer durcheinander
    Collections.shuffle(Arrays.asList(skiers));

    for (int i = 0; i < skiers.length; i++)
    {
      addActor(skiers[i], new Location(-50, -50)); //out of sight
      skiers[i].setState(Skier.State.inQueue);
      skiLift.put(skiers[i]);
    }
    setPaintOrder(SkiLift.class, Skier.class, EmptyPlatter.class);
  }

  public void windowMoved(int x, int y)
  {
    infoBoard.setPosition(x + 820, y);
  }

  public void windowIconified()
  {
    infoBoard.hide();
  }

  public void windowDeiconified()
  {
    infoBoard.show();
  }

  public void windowActivated()
  {
  }

  public void windowDeactivated()
  {
  }

  public static void main(String[] args)
  {
    new SkiResort();

  }
}
