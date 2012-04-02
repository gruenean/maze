# SimEx07.py
# One light sensor, event driven

from ch.aplu.jgamegrid import *
from ch.aplu.nxtsim import * 
from java.awt import *

 
def _init(gg):
  bg = gg.getBg();
  bg.setPaintColor(Color.black);
  bg.fillArc(Point(250, 250), 50, 0, 360);
  bg.fillArc(Point(250, 350), 100, 0, 360);

def bright(port, level):
  gear.rightArc(0.15)

def dark(port, level):
  gear.leftArc(0.15)

robot = NxtRobot()
_init(robot.getGameGrid())
gear = Gear()
robot.addPart(gear)
ls = LightSensor(SensorPort.S3, bright = bright, dark = dark)
robot.addPart(ls)
gear.setSpeed(30)
gear.forward()

