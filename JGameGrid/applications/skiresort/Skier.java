// Skier.java

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;

/**
 * Idee: ganze onLift-Bewegung und dazugehörigen
 * Konstanten in SkiLift.class regeln.
 * @author muzi
 *
 */
public class Skier extends Actor
{
  public enum State
  {
    onTrack, onLift, inQueue
  };
  private static int lastId = 0;
  private State state;
  private int id, angle, angle2;
  private InfoBoard infoBoard;
  // variables concerning the skilift:
  private SkiLift skiLift;
  private static final double liftSpeed = 0.6;
  private static final double[] liftDirection =
  {
    .95, -3
  };
  private static final double[] liftDirectionTop =
  {
    3.3, -0.31
  };
  private int liftTopBorder = 1001;
  private double posX, posY;
  private boolean slow;

  public Skier(SkiLift skiLift, InfoBoard infoBoard, boolean slow)
  {
    super(true, "sprites/skierMini.png", 6);
    this.infoBoard = infoBoard;
    this.skiLift = skiLift;
    this.slow = slow;
    id = ++lastId;
    setActorCollisionEnabled(false);
    infoBoard.incCrashCount();
  }

  public int collide(Actor actor1, Actor actor2)
  {
    // gameGrid.playSound(this, GGSound.BOING);
    System.out.println(actor1 + " crashed into " + actor2);
    return 50;
  }

  public static double getLiftSpeed()
  {
    return liftSpeed;
  }

  public static double[] getLiftDirection()
  {
    return liftDirection;
  }

  private void getDownFast2()
  {
    // wenn zu weit rechts
    if (getX() > 700)
      angle = 2;
    // wenn zu weit links
    else if (getX() < 330)
      angle = -2;
    else if (angle2 > 20)
      angle = -2;
    else if (angle2 < -20)
      angle = 2;

    angle2 = angle2 + angle;
    infoBoard.incSkiingDistance(slow);
    setDirection(angle2 + 90);
    move();
  }

  private void getDownSlowly2()
  {
    // wenn zu weit rechts
    if (getX() > 700)
      angle = 2;
    // wenn zu weit links
    else if (getX() < 330)
      angle = -2;
    else if (angle2 > 70)
      angle = -2;
    else if (angle2 < -70)
      angle = 2;

    // Verlangsamung: Jede 5. Periode kein Schritt
    if (nbCycles % 2 != 0)
    {
      infoBoard.incSkiingDistance(slow);
      angle2 = angle2 + angle;
      setDirection(angle2 + 90);
      move();
    }
  }

  public void act()
  {
    if (this.getState() == Skier.State.onTrack)
    {
//			skiLift.incSkiingTime(slow);
      infoBoard.incSkiingTime(slow);

      if (isSlow())
        getDownSlowly2();
      else
        getDownFast2();

      if (getY() > 575)
      {
        setState(Skier.State.inQueue);
        setActorCollisionEnabled(false); // Kollision aus!
        skiLift.put(this);
      }
    }
    if (this.getState() == Skier.State.onLift)
    {
      // Oben ankommen:
      if (getY() < 50 || getX() > liftTopBorder)
      {
        liftTopBorder = 1001; // reset für die nächste Liftfahrt
        setState(Skier.State.onTrack);
        setLocation(posToLoc(posX, posY));
        setActorCollisionEnabled(true); // it's a wild ride!
        angle2 = (int)(Math.random() * 120 - 60);
        // change sprite to "on Track"
        setHorzMirror(false);
        if (isSlow())
          show(0);
        else
          show(1);

        if (angle2 < 0)
          angle = 2;
        else
          angle = -2;

      }
      // Gehe nach rechts am Ende des Lifts:
      // TODO: mit enum-state regeln.
      else if (getY() < 70)
      {
        // Beim ersten mal: ändere sprite und bestimme, wie lange
        // nach rechts gelaufen wird.
        if (liftTopBorder > 1000)
        {
          liftTopBorder = (int)(250 + Math.random() * 250);
          setDirection(3);
          setHorzMirror(true);
          if (isSlow())
            show(2);
          else
            show(3);
        }
        setPosition(posX + liftDirectionTop[0] * liftSpeed, posY
          + liftDirectionTop[1] * liftSpeed);
        setLocation(posToLoc(posX, posY));

      }
      else
        useLift();
    }
    if (this.getState() == Skier.State.inQueue)
    {
      //this.lookBored
//			skiLift.incWaitingTime(slow);
      infoBoard.incWaitingTime(slow);
    }
  }

  private void useLift()
  {
    setPosition(posX + liftDirection[0] * liftSpeed, posY
      + liftDirection[1] * liftSpeed);
    setLocation(posToLoc(posX, posY));
  }

  public void setState(State state)
  {
    this.state = state;
  }

  public State getState()
  {
    return state;
  }

  public void setPosition(double x, double y)
  {
    this.posX = x;
    this.posY = y;
  }

  public Location posToLoc(double n, double m)
  {
    Location loc;
    loc = new Location((int)n, (int)m);
    return loc;
  }

  public String toString()
  {
    return "Skier: " + getId();
  }

  public int getId()
  {
    return id;
  }

  public boolean isSlow()
  {
    return slow;
  }
}
