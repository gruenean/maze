// GameOfLife1.java
// Game of life

/*
Population rules:
For a cell that is 'populated':
- each cell with one or no neighbours dies, as if by loneliness.
- each cell with four or more neighbours dies, as if by overpopulation.
- each cell with two or three neighbours survives

For a cell that is 'unpopulated':
 - each cell with three neighbours becomes populated
 */

/*
Implementation:
All cell contains actors of the same class Creature. For a populated cell
the actor is visible, for an unpopulated cell, the actor is invisible
*/

import ch.aplu.jgamegrid.*;
import java.awt.Color;

public class GameOfLife1 extends GameGrid
{
  private static final int nb = 20;  // Number of cells in each direction
  private static final int nbCreatures = nb * nb;
  private static final int popSize = 200; // Size of population at start
  private boolean[][] pop = new boolean[nb][nb];
  private Creature[] creatures = new Creature[nbCreatures];

  public GameOfLife1()
  {
    super(nb, nb, 25, Color.red, "sprites/snowwindow.gif");
    int k = 0;
    // Create creature in every cell
    for (int x = 0; x < nb; x++)
    {
      for (int y = 0; y < nb; y++)
      {
        creatures[k] = new Creature();
        Location loc = new Location(x, y);
        addActor(creatures[k], loc);
        creatures[k].hide();
        k++;
      }
    }
    reset();
    show();
  }

  public void reset()
  {
    // All actors are dead
    for (int i = 0; i < nbCreatures; i++)
      creatures[i].isAlive = false;
    // Create the living population randomly
    for (int i = 0; i < popSize; i++)
    {
      Creature creature = (Creature)getOneActorAt(getRandomLocation());
      creature.isAlive = true;
    }
    act();
  }

  public void act()
  {
    // Show the population
    for (int i = 0; i < nbCreatures; i++)
    {
      if (creatures[i].isAlive)
        creatures[i].show();
      else
        creatures[i].hide();
    }
  }

  public static void main(String[] args)
  {
    new GameOfLife1();
  }
}
