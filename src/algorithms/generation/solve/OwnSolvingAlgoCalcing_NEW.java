package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import algorithms.generation.Conf;

public class OwnSolvingAlgoCalcing_NEW {
	Cell _endCell;
	// Cell _cell;
	Maze _maze;
	boolean[] _isWallAllowed;
	Cell _currentCell;
	int[] possibleWays;
	// Cell[] _thisIsTheWay;
	Cell[] _alreadyChecked;
	Cell _tempCell;
	boolean[] _directionPossible;
	String logString;
	String _whereDoIComeFrom;

	public OwnSolvingAlgoCalcing_NEW(Maze maze, Cell startCell, Cell endCell,
			int notthisWall) {
		_endCell = endCell;
		_maze = maze;
		_currentCell = startCell;
		_tempCell = null;
		_directionPossible = new boolean[] { true, true, true, true };
		

		if (notthisWall == 0) {
			_directionPossible[1] = false;
			_whereDoIComeFrom = Conf.getWallName(1);
		}
		if (notthisWall == 1) {
			_directionPossible[0] = false;
			_whereDoIComeFrom = Conf.getWallName(0);
		}
		if (notthisWall == 2) {
			_directionPossible[3] = false;
			_whereDoIComeFrom = Conf.getWallName(3);
		}
		if (notthisWall == 3) {

			_directionPossible[2] = false;
			_whereDoIComeFrom = Conf.getWallName(2);
		}

		logString = " Ich bin Zelle "
				+ _maze.getPositionOfCell(_currentCell)[0]
				+ _maze.getPositionOfCell(_currentCell)[1]
				+ " und mein Status = " + _currentCell.getState();

		UseLogger.LOGGER.info(logString);

	}

	/**
	 * 
	 * @param i
	 *            defines which wall (left=0 / right = 1 / top = 2 / bottom = 3)
	 * @return true if the Neigbour is a " " / false if the Neigbour is a U or B
	 */
	private boolean getPossibleNeigbour(int i) {

		_tempCell = _maze.getNeigbourofCell(_currentCell, i);

		/**
		 * if the Cell is not a empty one, return null. Then it is a U or B Cell
		 */
		if (_tempCell == null) {
			// System.out.println("not possible: Cell = null");
			// UseLogger.LOGGER.info("not possible: Cell = null");
			return false;
		}
		if (_tempCell.getState() == "B" || _tempCell.getState() == "U") {
			logString = "Ist nicht mšglich. Zelle hat getState: "
					+ _tempCell.getState();
			// System.out.println(logString);

			UseLogger.LOGGER.info(logString);
			return false;
		}

		return true;

	}

	public boolean reachedTheEndCell() {
		String logString = "current Cell = "
				+ _maze.getPositionOfCell(_currentCell)[0]
				+ _maze.getPositionOfCell(_currentCell)[1] + "     EndCell = "
				+ _maze.getPositionOfCell(_endCell)[0]
				+ _maze.getPositionOfCell(_endCell)[1];
		UseLogger.LOGGER.info(logString);

		if (_currentCell == _endCell)
			return true;
		return false;

	}

	public boolean calc() {
		UseLogger.LOGGER.info("Calc von Zelle "
				+ _maze.getPositionOfCell(_currentCell)[0]
				+ _maze.getPositionOfCell(_currentCell)[1] + "aufgerufen...");

		if (reachedTheEndCell()) {
			UseLogger.LOGGER.info("ENDE ERREICHT!!!!");
			System.out.println("ENDE ERREICHT!!!!");
			return true;
		}

		for (int i = 0; i < 4; i++) {
			if (!getPossibleNeigbour(i))
				_directionPossible[i] = false;
		}

		return calcnow();

	}

	public boolean calcnow() {

		UseLogger.LOGGER.info("Moegliche Durchgang fuer Zelle "
				+ _maze.getPositionOfCell(_currentCell)[0]
				+ _maze.getPositionOfCell(_currentCell)[1] + " "
				+ _directionPossible[0] + "  " + _directionPossible[1] + "  "
				+ _directionPossible[2] + "  " + _directionPossible[3]);
		System.out.println("Mein Status: " + _currentCell.getState());

		if (!_directionPossible[0] && !_directionPossible[1]
				&& !_directionPossible[2] && !_directionPossible[3]) {
			logString = "Kein Druchgang mšglich. Sorry.";
			UseLogger.LOGGER.info(logString);
			return false;
		}

		for (int counter = 0; counter < 4; counter++) {

			if (_directionPossible[counter]) {
				UseLogger.LOGGER.info(" "
						+ _maze.getPositionOfCell(_currentCell)[0]
						+ _maze.getPositionOfCell(_currentCell)[1]
						+ " will nach " + Conf.getWallName(counter)
						+ " gehen. Dies ist " + _directionPossible[counter]
						+ ".");
				logString = "\n Suche Nachbar " + Conf.getWallName(counter)
						+ " von Zelle an Position: \n "
						+ _maze.getPositionOfCell(_currentCell)[0]
						+ _maze.getPositionOfCell(_currentCell)[1]
						+ "     possible ? = " + getPossibleNeigbour(counter);
				_tempCell = _maze.getNeigbourofCell(_currentCell, counter);
				UseLogger.LOGGER.info(logString);

				// SolvingAlgorithms._solvingCounter =
				// SolvingAlgorithms._solvingCounter++;
				_currentCell = _tempCell;

				String _currentCellPosition = ""
						+ _maze.getPositionOfCell(_currentCell)[0]
						+ _maze.getPositionOfCell(_currentCell)[1];

				UseLogger.LOGGER.info(" ");
				logString = "Rekursiver Aufruf:  Aktuelle Zelle = "
						+ _currentCellPosition + "\n";
				UseLogger.LOGGER.info(logString);

				boolean retbool = new OwnSolvingAlgoCalcing_NEW(_maze,
						_currentCell, _endCell, counter).calc();

				if (retbool)
					return retbool;

				if (!retbool) {
					System.out.println();

					logString = "Ich bin die Zelle "
							+ _maze.getPositionOfCell(_currentCell)[0]
							+ _maze.getPositionOfCell(_currentCell)[1]
							+ " und habe keinen Weg in die Richtung "
							+ Conf.getWallName(counter) + " gefunden.";
					UseLogger.LOGGER.info(logString);
					logString = "current Cell = "
							+ _maze.getPositionOfCell(_currentCell)[0]
							+ _maze.getPositionOfCell(_currentCell)[1] + " "
							+ _directionPossible[0] + " "
							+ _directionPossible[1] + "  "
							+ _directionPossible[2] + " "
							+ _directionPossible[3];
					UseLogger.LOGGER.info(logString);
					_directionPossible[counter] = false;
					logString = "current Cell = "
							+ _maze.getPositionOfCell(_currentCell)[0]
							+ _maze.getPositionOfCell(_currentCell)[1] + " "
							+ _directionPossible[0] + " "
							+ _directionPossible[1] + "  "
							+ _directionPossible[2] + " "
							+ _directionPossible[3];
					UseLogger.LOGGER.info(logString);

				}
			}
		}

		return false;
		// logString = "Bin bei der Zelle "
		// + _maze.getPositionOfCell(_currentCell)[0]
		// + _maze.getPositionOfCell(_currentCell)[1]
		// + ". Es geht hier nicht mehr weiter... ;-( \n";
		// UseLogger.LOGGER.info(logString);

	}
}
