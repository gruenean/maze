# SimEx03.py
# TurtleRobot

from ch.aplu.jgamegrid import *
from ch.aplu.nxtsim import * 


robot = TurtleRobot()

for i in range(4):
  robot.forward(100)
  robot.left(90)

robot.exit();