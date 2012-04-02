# SimEx06.py
# Two touch sensors, complex track


from ch.aplu.jgamegrid import *
from ch.aplu.nxtsim import * 

NxtContext.setLocation(10, 10)
NxtContext.setStartDirection(5)
NxtContext.setStartPosition(100, 240)
NxtContext.useObstacle(NxtContext.channel)
   
robot = NxtRobot()
gear = Gear()
robot.addPart(gear)
ts1 = TouchSensor(SensorPort.S1) # right sensor
ts2 = TouchSensor(SensorPort.S2) # left sensor
robot.addPart(ts1)
robot.addPart(ts2)
gear.forward()
while (True):
  t1 = ts1.isPressed()
  t2 = ts2.isPressed()
  if (t1 and t2):
    gear.backward(500)
    gear.left(400)
    gear.forward()
  else:
    if t1:
      gear.backward(500)
      gear.left(400)
      gear.forward()
    else:
      if t2:
        gear.backward(500)
        gear.right(100)
        gear.forward()
  Tools.delay(20)
