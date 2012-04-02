# SimEx01.py
# Motors

from ch.aplu.jgamegrid import *
from ch.aplu.nxtsim import * 
   
robot = NxtRobot()
motA = Motor(MotorPort.A)
motB = Motor(MotorPort.B)
robot.addPart(motA)
robot.addPart(motB)

motA.forward()
motB.forward()
Tools.delay(2000)

motA.stop()
Tools.delay(2000)

motB.stop()
Tools.delay(2000)

motA.backward()
motB.forward()
Tools.delay(2000)

motB.backward()
Tools.delay(2000)

robot.exit()