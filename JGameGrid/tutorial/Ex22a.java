// Ex22a.java

import ch.aplu.jgamegrid.*;
import java.awt.*;

public class Ex22a extends GameGrid implements GGMouseTouchListener
{
  private Point hotSpot;
  private Location startLocation;

  public Ex22a()
  {
    super(5, 5, 100, Color.red, false);
    setTitle("Drag Nemo!");
    for (int i = 0; i < 4; i++)
    {
      Actor nemo = new Actor("sprites/nemo.gif");
      addActor(nemo, getRandomEmptyLocation());
      nemo.addMouseTouchListener(this, 
        GGMouse.lPress | GGMouse.lDrag | GGMouse.lRelease, true);
    }
    show();
  }

  public void mouseTouched(Actor actor, GGMouse mouse, Point spot)
  {
    switch (mouse.getEvent())
    {
      case GGMouse.lPress:
        hotSpot = spot;
        startLocation = toLocation(mouse.getX(), mouse.getY());
        actor.setOnTop();
        break;
      case GGMouse.lDrag:
        Point imageCenter =
          new Point(mouse.getX() - hotSpot.x, mouse.getY() - hotSpot.y);
        actor.setPixelLocation(imageCenter);
        refresh();
        break;
      case GGMouse.lRelease:
        if (spot.x == -1)  // Cursor is outside image
          actor.setLocation(startLocation);
        else
          actor.setPixelLocation(new Point(mouse.getX(), mouse.getY()));
        actor.setLocationOffset(new Point(0, 0));
        hotSpot = null;
        refresh();
        break;
    }
  }

  public static void main(String[] args)
  {
    new Ex22a();
  }
}
