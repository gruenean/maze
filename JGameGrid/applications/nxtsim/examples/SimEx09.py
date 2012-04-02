# SimEx09.py
# One sound sensor, one touch sensor, event driven

from ch.aplu.jgamegrid import *
from ch.aplu.nxtsim import * 

NxtContext.useObstacle("sprites/bar0.gif", 250, 50);

def pressed(port):
  gear.backward()

def loud(port, level):
  if gear.isMoving():
      gear.stop()
  else:
      gear.forward()

robot = NxtRobot()
gear = Gear()
robot.addPart(gear)
ss = SoundSensor(SensorPort.S1, loud = loud)
ts = TouchSensor(SensorPort.S3, pressed = pressed) 
robot.addPart(ss)
robot.addPart(ts)
