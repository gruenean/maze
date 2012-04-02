// Ex02.java
// Use a custom JFrame to embed GameGrid playground

import ch.aplu.jgamegrid.*;
import javax.swing.*;
import java.awt.*;

public class Ex02 extends JFrame
{
  public Ex02()
  {
    GameGrid gg = new GameGrid();
    gg.setCellSize(40);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    getContentPane().add(gg, BorderLayout.NORTH);
    JTextField f = new JTextField("Hello, I am Nemo in a custom window");
    getContentPane().add(f, BorderLayout.SOUTH);
    pack();  // Must be called before actors are added!
    Clownfish fish = new Clownfish();
    gg.addActor(fish, new Location(2, 4));
    gg.doRun();
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        new Ex02().setVisible(true);
      }
    });
  }
}
