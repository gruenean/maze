// PrettyGraphics.java
// Select in ctor which graphics you want to show

import ch.aplu.jgamegrid.*;
import java.awt.Color;

public class PrettyGraphics extends GameGrid
{
  public PrettyGraphics()
  {
    super(600, 600, 1, false);
    show();
    colorGraduation();
  }

  void colorLines()
  {
    GGPanel p = getPanel(-20, 20, -20, 20);
    for (int i = -20; i <= 20; i++)
    {
      delay(100);
      if (i < 0)
        p.color(Color.RED);
      else
        p.color(Color.GREEN);
      p.line(i, -20, 0, 20);
    }
  }

  void shapes()
  {
    GGPanel p = getPanel(-10, 10, -10, 10);
    p.color(Color.YELLOW);
    p.rectangle(10, 10, true);
    p.color(Color.RED);
    p.circle(4, true);
    p.color(Color.BLUE);
    p.triangle(0, 4, -3.5, -2, 3.5, -2, true);
  }

  private void circles()
  {
    GGPanel p = getPanel(-25, 25, -25, 25);

    for (int i = 0; i < 6; i++)
    {
      double angle = i * Math.PI / 3.0;
      double a = 10 * Math.cos(angle);
      double b = 10 * Math.sin(angle);
      p.move(a, b);
      Color c = new Color(255, 40 * i, 255 - 40 * i);
      p.color(c);
      p.circle(5, true);
    }
  }

  private void colorGraduation()
  {
    GGPanel p = getPanel(0, 255, 0, 255);
    int breite = 255;
    int hoehe = 256;
    for (int i = 0; i <= breite; i++)
    {
      int b = i * 255 / breite;
      int r = 255 - b;
      int g = (500 * Math.abs(i - breite / 2)) / breite;
      Color c = new Color(r, g, b);
      p.color(c);
      p.move(i, 127);
      p.rectangle(1, hoehe, true);
    }
  }

  private void chessBoard()
  {
    GGPanel p = getPanel();

    p.window(0, 8, 0, 8);
    for (int i = 0; i < 8; i++)
      for (int j = 0; j < 8; j++)
        if ((i + j) % 2 == 0)
          p.rectangle(i, j, i + 1, j + 1, true);
    p.line(0, 8, 8, 8);
  }

  void pyramide()
  {
    GGPanel p = getPanel(0, 30, 0, 30);

    for (int i = 1; i < 30; i++)
    {
      if (i < 10)
        p.color(Color.RED);
      else
        if (i < 20)
          p.color(Color.YELLOW);
        else
          p.color(Color.GREEN);

      p.move(15, i);
      p.rectangle (30-i, 0.7, true);
    }
  }

  void  colorTiles()
  {
    getBg().clear(Color.WHITE);
    GGPanel p = getPanel(0, 10, 0, 10);

    for (int j = 1; j < 10; j++)
    {
      for (int i = 1; i < 10; i++)
      {
        switch (i)
        {
          case 1:
            p.color(Color.RED);
            break;
          case 2:
            p.color(Color.YELLOW);
            break;
          case 3:
            p.color(Color.GREEN);
            break;
          case 4:
            p.color(Color.BLUE);
            break;
          case 5:
            p.color(Color.MAGENTA);
            break;
          case 6:
            p.color(Color.CYAN);
            break;
          case 7:
            p.color(Color.GRAY);
            break;
          case 8:
            p.color(Color.GRAY);
            break;
          default:
            p.color(Color.DARK_GRAY);
            break;
        }
        p.move(i, j);
        p.rectangle(0.9, 0.9, true);
      }
    }
  }
  
  public static void main(String[] args)
  {
    new PrettyGraphics();
  }
}

