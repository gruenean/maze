package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import algorithms.generation.Conf;

public class OwnSolvingAlgoCalcing {
	Cell _endCell;
	Cell _cell;
	Maze _maze;
	boolean[] _isWallAllowed;

	public OwnSolvingAlgoCalcing(Maze maze, Cell cell, Cell endCell,
			int notThisWall) {
		_cell = cell;
		_endCell = endCell;
		_maze = maze;
		whichWalls(notThisWall);
		// checkIfEndCell();
		// calc();
	}

	public void whichWalls(int notThisWall) {
		/**
		 * create an array with 3 walls. This makes shure that there is no loop
		 * and the algorihm can not go back to a cell which was solved before.
		 * 
		 */

		// UseLogger.LOGGER.severe("notThisWall = " + notThisWall);

		/**
		 * if the solving algo confs from the right, then do not go back left
		 */
		if (notThisWall == Conf.RIGHT_WALL)
			_isWallAllowed = new boolean[] { false, true, true, true };

		/**
		 * if the solving algo confs from the left, then do not go back right
		 */
		if (notThisWall == Conf.LEFT_WALL)
			_isWallAllowed = new boolean[] { true, false, true, true };

		/**
		 * if the solving algo confs from the top, then do not go back bottom
		 */
		if (notThisWall == Conf.TOP_WALL)
			_isWallAllowed = new boolean[] { true, true, true, false };
		/**
		 * if the solving algo confs from the bottom, then do not go top
		 */
		if (notThisWall == Conf.LEFT_WALL)
			_isWallAllowed = new boolean[] { true, true, false, true };

		/**
		 * this is just used for the first Cell (startCell)
		 */
		if (notThisWall >= 3)
			_isWallAllowed = new boolean[] { false, true, false, true };

	}

	public boolean calc() {
		if (_cell == _endCell) {
			System.out.println("Position aktuelle Zelle = ["
					+ _maze.getPositionOfCell(_cell)[0]
					+ _maze.getPositionOfCell(_cell)[1] + "]");

			System.out.println("Position letzte Zelle = ["
					+ _maze.getPositionOfCell(_endCell)[0]
					+ _maze.getPositionOfCell(_endCell)[1] + "]");

			System.out.println("ich bin bei der letzten Zelle ;-)");
			return true;
		}

		Cell newCell = null;

		Conf.increseSteps();
		UseLogger.LOGGER.severe("Bin bei der " + Conf.STEPS
				+ ". Zelle. \nBerechnen wurde aufgerufen. \nAnzahl Steps = "
				+ Conf.STEPS + "\nBin jetzt bei Zelle an Position: " + "["
				+ _maze.getPositionOfCell(_cell)[0]
				+ _maze.getPositionOfCell(_cell)[1] + "]");

		for (int i = 0; i <= _isWallAllowed.length - 1; i++) {

			/**
			 * if the Cell doesn't have a Wall and wallisAllowed then...
			 */
			System.out.println("Ist hier eine Wand? = " + _cell.isWallHere(i)
					+ "     Erlaubt? " + _isWallAllowed[i]);

			if (!_cell.isWallHere(i) && _isWallAllowed[i]) {

				newCell = _maze.getNeigbourofCell(_cell, i);
				UseLogger.LOGGER.severe("Neue Zelle ist an Position: " + "["
						+ _maze.getPositionOfCell(newCell)[0]
						+ _maze.getPositionOfCell(newCell)[1] + "]");
				UseLogger.LOGGER.severe("Mšchte zur Zelle " + i
						+ "  Position ist " + Conf.getWallName(i));
				OwnSolvingAlgoCalcing newOne = new OwnSolvingAlgoCalcing(_maze,
						newCell, _endCell, i);
				if (newOne.calc())
					return true;

			}
		}

		return false;
	}
}
