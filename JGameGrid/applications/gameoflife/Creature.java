// Creature.java
// Used for GameOfLife

import ch.aplu.jgamegrid.*;
import java.util.ArrayList;

public class Creature extends Actor
{
  protected boolean isAlive = false;

  public Creature()
  {
    super("sprites/creature.gif");
  }

  // Every actor applies the population rules to himself
  public void act()
  {
    // Get number of (living) neighbours
    ArrayList<Actor> neighbours = getNeighbours(1);
    int nbNeighbours = 0;
    for (Actor neighbour : neighbours)
      if (neighbour.isVisible())
        nbNeighbours++;

    // Generation rule:
    if (isVisible())  // alive
    {
      if (!(nbNeighbours == 2 || nbNeighbours == 3))
        isAlive = false; // dying
    }
    else // dead
    {
      if (nbNeighbours == 3)
        isAlive = true;  // become alive
    }
  }
}
