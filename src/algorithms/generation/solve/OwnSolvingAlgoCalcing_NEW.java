package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import algorithms.generation.Conf;

public class OwnSolvingAlgoCalcing_NEW {
	private Cell _endCell;
	private Maze _maze;
	// private boolean[] _isWallAllowed;
	private Cell _myCell = null;
	private int[] possibleWays;
	private Cell[] _alreadyChecked;
	private Cell _nextCell = null;
	private boolean[] _directionPossible;
	private String logString = null;

	public OwnSolvingAlgoCalcing_NEW(Maze maze, Cell startCell, Cell endCell,
			int notthisWall) {
		_endCell = endCell;
		_maze = maze;
		_myCell = startCell;
		_nextCell = null;
		_directionPossible = new boolean[] { true, true, true, true };

		if (notthisWall == 0) {
			_directionPossible[1] = false;
		}
		if (notthisWall == 1) {
			_directionPossible[0] = false;
		}
		if (notthisWall == 2) {
			_directionPossible[3] = false;
		}
		if (notthisWall == 3) {

			_directionPossible[2] = false;
		}
		logString = " Ich bin Zelle " + _maze.getPositionOfCell(_myCell)[0]
				+ _maze.getPositionOfCell(_myCell)[1] + " und mein Status = "
				+ _myCell.getState();

//		System.out.println("my logger = " + UseLogger.LOGGER);
		UseLogger.LOGGER.info(logString);

	}

	/**
	 * 
	 * @param i
	 *            defines which wall (left=0 / right = 1 / top = 2 / bottom = 3)
	 * @return true if the Neigbour is a " " / false if the Neigbour is a U or B
	 */
	private boolean getPossibleNeigbour(int i) {

		_nextCell = _maze.getNeigbourofCell(_myCell, i);

		/**
		 * if the Cell is not a empty one, return null. Then it is a U or B Cell
		 */
		if (_nextCell == null) {
			// System.out.println("not possible: Cell = null");
			// UseLogger.LOGGER.info("not possible: Cell = null");
			return false;
		}
		if (_nextCell.getState() == "B" || _nextCell.getState() == "U") {
			logString = "Ist nicht mšglich. Zelle hat getState: "
					+ _nextCell.getState();
			// System.out.println(logString);

			UseLogger.LOGGER.info(logString);
			return false;
		}

		return true;

	}

	public boolean reachedTheEndCell() {
		String logString = "current Cell = "
				+ _maze.getPositionOfCell(_myCell)[0]
				+ _maze.getPositionOfCell(_myCell)[1] + "     EndCell = "
				+ _maze.getPositionOfCell(_endCell)[0]
				+ _maze.getPositionOfCell(_endCell)[1];
		UseLogger.LOGGER.info(logString);

		if (_myCell == _endCell)
			return true;
		return false;

	}

	public boolean calc() {
		UseLogger.LOGGER.info("Calc von Zelle "
				+ _maze.getPositionOfCell(_myCell)[0]
				+ _maze.getPositionOfCell(_myCell)[1] + "aufgerufen...");

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
				+ _maze.getPositionOfCell(_myCell)[0]
				+ _maze.getPositionOfCell(_myCell)[1] + " "
				+ _directionPossible[0] + "  " + _directionPossible[1] + "  "
				+ _directionPossible[2] + "  " + _directionPossible[3]);
		System.out.println("Mein Status: " + _myCell.getState());

		if (!_directionPossible[0] && !_directionPossible[1]
				&& !_directionPossible[2] && !_directionPossible[3]) {
			logString = "Kein Druchgang mšglich. Sorry.";
			UseLogger.LOGGER.info(logString);
			return false;
		}

		for (int counter = 0; counter < 4; counter++) {

			if (_directionPossible[counter]) {
				UseLogger.LOGGER.info(" " + _maze.getPositionOfCell(_myCell)[0]
						+ _maze.getPositionOfCell(_myCell)[1] + " will nach "
						+ Conf.getWallName(counter) + " gehen. Dies ist "
						+ _directionPossible[counter] + ".");
				logString = "\n Suche Nachbar " + Conf.getWallName(counter)
						+ " von Zelle an Position: \n "
						+ _maze.getPositionOfCell(_myCell)[0]
						+ _maze.getPositionOfCell(_myCell)[1]
						+ "     possible ? = " + getPossibleNeigbour(counter);
				_nextCell = _maze.getNeigbourofCell(_myCell, counter);
				UseLogger.LOGGER.info(logString);

				// SolvingAlgorithms._solvingCounter =
				// SolvingAlgorithms._solvingCounter++;
				// _myCell = _nextCell;

				String _currentCellPosition = ""
						+ _maze.getPositionOfCell(_myCell)[0]
						+ _maze.getPositionOfCell(_myCell)[1];

				UseLogger.LOGGER.info(" ");
				logString = "Rekursiver Aufruf:  Aktuelle Zelle = "
						+ _currentCellPosition + "\n";
				UseLogger.LOGGER.info(logString);

				boolean retbool = new OwnSolvingAlgoCalcing_NEW(_maze,
						_nextCell, _endCell, counter).calc();

				if (retbool)
					return retbool;

				if (!retbool) {
					System.out.println();

					logString = "Ich bin die Zelle "
							+ _maze.getPositionOfCell(_myCell)[0]
							+ _maze.getPositionOfCell(_myCell)[1]
							+ " und habe keinen Weg in die Richtung "
							+ Conf.getWallName(counter) + " gefunden.";
					UseLogger.LOGGER.info(logString);
					logString = "current Cell = "
							+ _maze.getPositionOfCell(_myCell)[0]
							+ _maze.getPositionOfCell(_myCell)[1] + " "
							+ _directionPossible[0] + " "
							+ _directionPossible[1] + "  "
							+ _directionPossible[2] + " "
							+ _directionPossible[3];
					UseLogger.LOGGER.info(logString);
					_directionPossible[counter] = false;
					logString = "current Cell = "
							+ _maze.getPositionOfCell(_myCell)[0]
							+ _maze.getPositionOfCell(_myCell)[1] + " "
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
