// EmptyPlatter.java

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;

/** Idee:
 * die Klasse emptyPlotter von Skier ableiten?
 * @author Stefan
 *
 */
public class EmptyPlatter extends Actor
{
  private static double liftSpeed;
  private static double[] liftDirection;
  private double posX, posY;

  public EmptyPlatter(double[] liftStart)
  {
    super("sprites/ski_buegel.png");
    posX = liftStart[0];
    posY = liftStart[1];
    liftSpeed = Skier.getLiftSpeed();
    liftDirection = Skier.getLiftDirection();
  }

  public void act()
  {
    // gleiches Verhalten wie Skier auf Lift:
    setPosition(posX + liftDirection[0] * liftSpeed, posY
      + liftDirection[1] * liftSpeed);
    setLocation(posToLoc(posX, posY));

    if (posY < 50)
      removeSelf();
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
}
