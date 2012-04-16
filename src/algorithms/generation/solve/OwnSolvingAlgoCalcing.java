package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import algorithms.generation.Conf;

public class OwnSolvingAlgoCalcing {
	Cell _endCell;
	Cell _cell;
	Maze _maze;
	int[] walls;

	public OwnSolvingAlgoCalcing(Maze maze, Cell cell, Cell endCell,
			int notThisWall) {
		_cell = cell;
		_endCell = endCell;
		_maze = maze;

		whichWalls(notThisWall);
		calc();
	}

	public void whichWalls(int notThisWall) {
		/**
		 * create an arary with 3 walls. This makes shure that there is no loop
		 * and the algorihm can not go back to a cell which was solved before.
		 * 
		 */

		UseLogger.LOGGER.severe("notThisWall = " + notThisWall);

		if (notThisWall == Conf.LEFT_WALL)
			walls = new int[] { 0, 1, 0, 0 }; // you are allowed for all walls
												// excepted the right one
		if (notThisWall == Conf.RIGHT_WALL)
			walls = new int[] { 1, 0, 0, 0 }; // you are allowed for all walls
												// the right one
		if (notThisWall == Conf.BOTTOM_WALL)
			walls = new int[] { 0, 0, 1, 0 }; // you are allowed for all walls
												// the bottom one
		if (notThisWall == Conf.TOP_WALL)
			walls = new int[] { 0, 0, 0, 1 }; // you are allowed for all walls
												// the top one
		if (notThisWall > 3)
			walls = new int[] { Conf.TOP_WALL, Conf.BOTTOM_WALL }; // this could
																	// just be
																	// if it is
																	// the start
																	// Cell
		UseLogger.LOGGER.info("Anzahl WŠnde zum checken = " + walls.length);

	}

	public boolean calc() {
		Conf.increseSteps();
		UseLogger.LOGGER.severe("Berechnen wurde aufgerufen. \nAnzahl Steps = "
				+ Conf.STEPS + "\nBin jetzt bei Zelle an Position: " + "["
				+ _maze.getPositionOfCell(_cell)[0]
				+ _maze.getPositionOfCell(_cell)[1] + "]");

		// while (Conf.STEPS < 1000) {

		for (int i = 0; i <= walls.length; i++) {
			if (_cell.isWallHere(i)) {
				UseLogger.LOGGER.info("Hier IST eine Wand");
				return false; // because there is a Wall there is no way
								// here
			} else {
				// System.out.println("Rekursiver Aufruf mit " + i);
				// new OwnSolvingAlgoCalcing(_cell, _endCell, i);
				UseLogger.LOGGER
						.info("Hier ist keine Wand. NŠchster Aufruf des Nachbarn...");
				for (int j = 0; j < walls.length; j++) {
					System.out.println("mšgliche Wand: " + j);
				}

				Cell newCell = _maze.getNeigbourofCell(_cell, walls[i]);

				new OwnSolvingAlgoCalcing(_maze, newCell, _endCell, i);

			}
		}

		// }
		return true;// because there is NOT a Wall there is no way here
	}
}
