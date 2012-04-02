# SimEx04.py
# One touch sensor, polling

from ch.aplu.jgamegrid import *
from ch.aplu.nxtsim import * 

NxtContext.showNavigationBar();
NxtContext.useObstacle("sprites/bar0.gif", 250, 200);
NxtContext.useObstacle("sprites/bar1.gif", 400, 250);
NxtContext.useObstacle("sprites/bar2.gif", 250, 400);
NxtContext.useObstacle("sprites/bar3.gif", 100, 250);

robot = NxtRobot()
gear = Gear()
ts = TouchSensor(SensorPort.S3)
robot.addPart(gear)
robot.addPart(ts)
gear.setSpeed(30)
gear.forward()
while (True):
  if ts.isPressed():
    gear.backward(1200);
    gear.left(750);
    gear.forward();

