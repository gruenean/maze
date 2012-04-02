// Farn.java

import ch.aplu.jgamegrid.*;
import ch.aplu.util.*;
import java.util.*;
import java.awt.Color;

public class Farn extends GameGrid
{
  private GGPanel p;

  public Farn()
  {
    super(600, 600, 1, false);
    show();

    p = getPanel();
    int n = new GGInputInt("Point Density", "Enter number of points", 20000).show();
    farn(n);
  }

  private void farn(int nbIterations)
  {
    p.window(-3.5, 3.5, 0, 10);
    Complex z = new Complex(0, 0);
    int it = 0;
    Random rnd = new Random();

    while (it <= nbIterations)
    {
      if (it % 1000 == 0)
      {
        p.color(Color.WHITE);
      }
      double r = rnd.nextDouble();
      Color color = Color.BLACK;

      if (r < 0.01)
      {
        color = Color.YELLOW;
        z = f(z, 0, 0, 0, 0.16, 0, 0); // Stiel
      }
      else
      {
        if (r < 0.86)
        {
          color = Color.GREEN;
          z = f(z, 0.85, 0.04, -0.04, 0.85, 0, 1.60); // symmetry
        }
        else
        {
          if (r > 0.86 && r < 0.93)
          {
            color = Color.RED;
            z = f(z, 0.20, -0.26, 0.23, 0.22, 0, 1.60); // left leaves
          }
          else
          {
            if (r > 0.93)
            {
              color = Color.BLUE;
              z = f(z, -0.15, 0.28, 0.26, 0.24, 0, 1.44); // right leaves
            }
          }
        }
      }
      p.color(color);
      p.point(z.real, z.img);
      delay(10);
      it++;
    }
    setTitle("Creating Farm...Done.");
  }

  private Complex f(Complex z,
    double a, double b, double c, double d, double e, double f)
  {
    double re = a * z.real + b * z.img + e;
    double im = c * z.real + d * z.img + f;
    return new Complex(re, im);
  }

  public static void main(String[] args)
  {
    new Farn();
  }
}
