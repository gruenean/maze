// InfoBoard.java

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.text.*;

import ch.aplu.jgamegrid.GGBackground;
import ch.aplu.jgamegrid.GameGrid;

public class InfoBoard extends GameGrid
{
  private GameGrid gg;
  private SkiLift skiLift;
  private GGBackground bg;
  private int nbSlowSkiers, nbFastSkiers, crashCount;
  private int waitingTimeSlow, waitingTimeFast;
  private int skiingTimeSlow, skiingTimeFast;
  private int skiingDistanceSlow, skiingDistanceFast;
  private String NaN = new DecimalFormatSymbols().getNaN();

  public InfoBoard(SkiResort gg, SkiLift skiLift, int nbSkiers)
  {
    super(300, 400, 1, null, null, false, true);
    this.gg = gg;
    this.skiLift = skiLift;
    this.nbSlowSkiers = nbSkiers / 2;		//immer abgerundet
    this.nbFastSkiers = Math.round((float)nbSkiers / 2); //aufgerundet, wenn ungerade Anzahl skiers
    bg = getBg();
    setBgColor(Color.LIGHT_GRAY);
    setSimulationPeriod(200);
    setTitle("Skiresort Infoboard!");
    bg.setFont(new Font("Serif", Font.PLAIN, 15));
    doRun();
  }

  public void act()
  {
    reWrite();
  }

  /** renews the InfoBoard: */
  private void reWrite()
  {
    bg.clear();

    int slowSkiersInQueue = skiLift.getSlowSkiersInQueue();

    // for calculating time in seconds instead of periods:
    float speedFactor = (float)gg.getSimulationPeriod() / 1000;
    DecimalFormat dcm = new DecimalFormat("0.00");

    //average waiting Time:
    float averageWaitTimeSlow = (float)((waitingTimeSlow * speedFactor) / nbSlowSkiers);
    float averageWaitTimeFast = (float)((waitingTimeFast * speedFactor) / nbFastSkiers);

    // percentage of time on track:
    String onTrackPercentageSlow = dcm.format((float)skiingTimeSlow / (gg.getNbCycles() * nbSlowSkiers) * 100);
    if (onTrackPercentageSlow.equals(NaN))
      onTrackPercentageSlow = "0.00";
    String onTrackPercentageFast = dcm.format((float)skiingTimeFast / (gg.getNbCycles() * nbFastSkiers) * 100);
    if (onTrackPercentageFast.equals(NaN))
      onTrackPercentageFast = "0.00";

    // percentage of time in line:
    String inLinePercentageSlow = dcm.format((float)waitingTimeSlow / (gg.getNbCycles() * nbSlowSkiers) * 100);
    if (inLinePercentageSlow.equals(NaN))
      inLinePercentageSlow = "0.00";
    String inLinePercentageFast = dcm.format((float)waitingTimeFast / (gg.getNbCycles() * nbFastSkiers) * 100);
    if (inLinePercentageFast.equals(NaN))
      inLinePercentageFast = "0.00";
    
    bg.setPaintColor(Color.black);
    bg.drawText("Skiers waiting in line: " + skiLift.getQueueSize(), new Point(5, 20));
    bg.drawText("Average time spent in line:", new Point(5, 80));
    bg.drawText("Percentage of time spent on track:", new Point(5, 140));
    bg.drawText("Distance (\"steps\") made on track: ", new Point(5, 200));
    bg.drawText("Percentage of time spent in line:", new Point(5, 260));

    //--------- slow skiers ----------
    bg.setPaintColor(Color.blue);
    bg.drawText("    Slow Skiers: " + slowSkiersInQueue, new Point(5, 40));
    bg.drawText("    Slow skiers: " + dcm.format(averageWaitTimeSlow), new Point(5, 100));
    bg.drawText("    Slow skiers: " + onTrackPercentageSlow + "%", new Point(5, 160));
    bg.drawText("    Slow skiers: " + skiingDistanceSlow, new Point(5, 220));
    bg.drawText("    Slow skiers: " + inLinePercentageSlow + "%", new Point(5, 280));

    //--------- fast skiers -----------
    bg.setPaintColor(Color.red);
    bg.drawText("    Fast Skiers: " + (skiLift.getQueueSize() - slowSkiersInQueue), new Point(
      5, 60));
    bg.drawText("    Fast skiers: " + dcm.format(averageWaitTimeFast), new Point(5, 120));
    bg.drawText("    Fast skiers: " + onTrackPercentageFast + "%", new Point(5, 180));
    bg.drawText("    Fast skiers: " + skiingDistanceFast, new Point(5, 240));
    bg.drawText("    Fast skiers: " + inLinePercentageFast + "%", new Point(5, 300));

  }

  public void incWaitingTime(boolean slow)
  {
    if (slow)
      waitingTimeSlow++;
    else
      waitingTimeFast++;
  }

  public void incSkiingTime(boolean slow)
  {
    if (slow)
      skiingTimeSlow++;
    else
      skiingTimeFast++;
  }

  public void incSkiingDistance(boolean slow)
  {
    if (slow)
      skiingDistanceSlow++;
    else
      skiingDistanceFast++;
  }
  
  public void incCrashCount()
  {
    crashCount++;
  }

  public void purgeInfoBoard()
  {
    crashCount = 0;
    skiingDistanceSlow = 0;
    skiingDistanceFast = 0;
    waitingTimeSlow = 0;
    waitingTimeFast = 0;
    skiingTimeSlow = 0;
    skiingTimeFast = 0;
  }
}
