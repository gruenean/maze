// SkiLift.java

import java.util.LinkedList;
import java.util.NoSuchElementException;
import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;

public class SkiLift extends Actor
{
  private final static double[] liftStart =
  {
    44, 575
  };
  private int platterPeriod;
  private Skier[] skiers;
  private SkiResort skiGG;
  private static LinkedList<Skier> queue = new LinkedList<Skier>();
  private final static Location queueStartingPoint = new Location(50, 573);
  private final static int queueWaitingDistance = 30;

  public SkiLift(int platterNumber, int platterPeriod, SkiResort skiGG)
  {
    super("sprites/ski_skilift.png");
    this.platterPeriod = platterPeriod;
    this.skiGG = skiGG;
    skiers = new Skier[platterNumber];
    for (int i = 0; i < platterNumber; i++)
      skiers[i] = null;
  }

  public void act()
  {
    Skier skier;
    // Alle [platterPeriod] Zyklen kommt ein Teller vorbei.
    if ((this.getNbCycles() % platterPeriod == 0))
    {
      if (queue.isEmpty())
      {
        // erzeuge einen leeren Teller, falls niemand in Warteschlange:
        skiGG.addActor(new EmptyPlatter(liftStart),
          new Location(-50, -50));
        //damit er im Hintergrund des Skilifts gezeichnet wird:
        skiGG.setPaintOrder(SkiLift.class, Skier.class,
          EmptyPlatter.class);
      }
      else
      {
        // take first skier of queue and place him on lift
        skier = get();
        skier.setState(Skier.State.onLift);
        // Setze an Position des Liftanfangs
        skier.setPosition(liftStart[0], liftStart[1]);
        // Nachrücken der Schlange
        for (int i = 0; i < queue.size(); i++)
        {
          int oldX = queue.get(i).getX();
          queue.get(i).setX(oldX - queueWaitingDistance);
        }
      }
    }

  }

  /**
   * Ordnet den übergebenen Skifahrer in die Warteschlange und setzt ihn an
   * die richtige Position.
   *
   * @param skier
   */
  public synchronized void put(Skier skier)
  {
    if (!queue.isEmpty())
    {
      int posXOfLastSkierInQueue = queue.getLast().getX();
      skier.setLocation(new Location(posXOfLastSkierInQueue
        + queueWaitingDistance, queueStartingPoint.y));
      // Setze skier an letzte Position der Schlange
      // skier.setOnTop();
    }
    else
      // Schlange ist leer -> gehe an erste Stelle
      skier.setLocation(queueStartingPoint);
    queue.add(skier);
    // set sprite to "wait"-sprite
    if (skier.isSlow())
      skier.show(2);
    else
      skier.show(3);
    skier.setDirection(0);
  }

  /** returns the skier first in line or null if the queue is empty **/
  public synchronized Skier get()
  {
    Skier skier = null;
    try
    {
      skier = queue.removeFirst();
      // set sprite to "onLift"-sprite
      if (skier.isSlow())
        skier.show(4);
      else
        skier.show(5);
    }
    catch (NoSuchElementException ex)
    {
    }
    return skier;
  }

  /** Returns the size of queue **/
  public int getQueueSize()
  {
    return queue.size();
  }

  public int getSlowSkiersInQueue()
  {
    int slowSkiers = 0;
    for (Skier skier : queue)
      if (skier.isSlow())
        slowSkiers++;
    return slowSkiers;
  }

  /** Deletes all entries of queue for reseting purposes **/
  public void purgeQueue()
  {
    queue.clear();
  }
}
