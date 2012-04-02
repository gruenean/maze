// SoundDetector.java

import ch.aplu.jgamegrid.*;
import ch.aplu.kinect.*;
import ch.aplu.ggkinect.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.JOptionPane;

public class SoundDetector extends GameGrid
  implements KinectCloseListener, GGButtonListener, SoundLevelListener
{
  private GGKinect kinect;
  private String dllPath = 
    Kinect.is64bit()? "KinectHandler64" : "KinectHandler";
  private final String title = "Kinect Video Frame";
  private static final int captionHeight = 19;
  private final int ulx = 0; // Upper left x of native window
  private final int uly = 20; // Upper left y of nativewindow
  private static int width = 640;  // Width of native window
  private static int height = 480 + captionHeight; // Height of native window
  private GGButton grabBtn = new GGButton("sprites/grab.png");

  public SoundDetector()
  {
    super(640, 520, 1, null, false);
    setSimulationPeriod(20);
    setPosition(ulx + width, uly);
    GGBackground bg = getBg();
    bg.setPaintColor(Color.black);
    bg.clear(new Color(128, 255, 128));
    bg.drawRectangle(new Point(0, 0), new Point(640, 470));
    addActor(grabBtn, new Location(40, 500));
    grabBtn.addButtonListener(this);

    if (Kinect.is64bit())
    {
      JOptionPane.showMessageDialog(null, "Must use 32-bit JVM.");
      System.exit(0);
    }
    
    kinect = new GGKinect(dllPath,
      title, // Window title
      ulx, uly, // Window position
      width, height, // Window size
      GGKinect.DecorationStyle.STANDARD, // Decoration style
      1000);  // Sound buffer size
    if (!kinect.isInitialized())
    {
      kinect.setVisible(false);
      JOptionPane.showMessageDialog(null, "Initializing of Kinect failed.");
      System.exit(0);
    }
    kinect.addCloseListener(this);
    kinect.addSoundLevelListener(this);
    show();
    doRun();
  }

  public int soundLevel(int level)
  {
    setTitle("Sound Level = " + level);
    if (level > 10)
    {
      Fish fish = new Fish();
      addActor(fish, new Location(10, 450 - 4 * level));
      return 200;
    }
    return 0;
  }

  public void notifyClose()
  {
    System.exit(0);
  }

  public void buttonClicked(GGButton button)
  {
  }

  public void buttonReleased(GGButton button)
  {
  }

  public void buttonPressed(GGButton button)
  {
    BufferedImage bi = kinect.getImage();
    getBg().drawImage(bi);
  }

  public static void main(String args[])
  {
    new SoundDetector();
  }
}