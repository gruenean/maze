package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import main.Conf;

public class OwnSolvingAlgoCalcing {
	private Cell _endCell;
	private Maze _maze;
	private Cell _myCell = null;
	private Cell _nextCell = null;
	private boolean[] _directionPossible;
	private String logString = null;
	private Conf _globalConf;

	public OwnSolvingAlgoCalcing(Maze maze, Cell startCell, Cell endCell,
			int notthisWall, Conf conf) {
		_globalConf = conf;
		_endCell = endCell;
		_maze = maze;
		_myCell = startCell;
		_nextCell = null;
		_directionPossible = new boolean[] { true, true, true, true };

		/**
		 * if the Solver choosed from the last Cell to go Left, it is now not
		 * allowed to go back to right.
		 */
		if (notthisWall == 0) {
			_directionPossible[1] = false;
		}
		/**
		 * if the Solver choosed from the last Cell to go right, it is now not
		 * allowed to go back to left.
		 */
		if (notthisWall == 1) {
			_directionPossible[0] = false;
		}
		/**
		 * if the Solver choosed from the last Cell to go top, it is now not
		 * allowed to go back to bottom.
		 */
		if (notthisWall == 2) {
			_directionPossible[3] = false;
		}
		/**
		 * if the Solver choosed from the last Cell to go bottom, it is now not
		 * allowed to go back to top.
		 */
		if (notthisWall == 3) {

			_directionPossible[2] = false;
		}
		
		/**
		 * if the Solver choosed from the last Cell to go Left, it is now not
		 * allowed to go back to right.
		 */
		if (notthisWall == 99) {
			_directionPossible[0] = false;
		}
	}

	/**
	 * Checks whether the neighbour in the given direction is a valid one
	 * 
	 * @param 	direction (left=0 / right = 1 / top = 2 / bottom = 3)
	 * @return true if the Neigbour is a " " / false if the Neigbour is a U or B
	 */
	private boolean getPossibleNeigbour(int i) {

		_nextCell = _maze.getNeigbourofCell(_myCell, i);

		/**
		 * if the Cell is not a empty one, return null. Then it is a U or B Cell
		 */
		if (_nextCell == null) {
			return false;
		}
		if (_nextCell.getState() == "B" || _nextCell.getState() == "U") {
			logString = "Ist nicht m�glich. Zelle hat getState: "
					+ _nextCell.getState();
			UseLogger.LOGGER.info(logString);
			return false;
		}
		return true;
	}

	/**
	 * @return true if the solver reached the endCell, otherwise return false
	 */
	private boolean reachedTheEndCell() {
		String logString = "current Cell = "
				+ _maze.getPositionOfCell(_myCell)[0]
				+ _maze.getPositionOfCell(_myCell)[1] + "     EndCell = "
				+ _maze.getPositionOfCell(_endCell)[0]
				+ _maze.getPositionOfCell(_endCell)[1];
		UseLogger.LOGGER.info(logString);

		if (_myCell == _endCell) {
			//note: rows and columns are switched here!!!!
			_globalConf.getGUI().setDot(_maze.getPositionOfCell(_myCell)[1], _maze.getPositionOfCell(_myCell)[0]);
			return true;
		}
		return false;
	}

	/**
	 * @return true if the solver reached the endCell, otherwise return false
	 */
	protected boolean calc() {
		UseLogger.LOGGER.info("Calc von Zelle "
				+ _maze.getPositionOfCell(_myCell)[0]
				+ _maze.getPositionOfCell(_myCell)[1] + "aufgerufen...");

		if (reachedTheEndCell()) {
			UseLogger.LOGGER.info("ENDE ERREICHT!!!!");
			_globalConf.get_output().printLine("ENDE ERREICHT!!!!");
			return true;
		}

		for (int i = 0; i < 4; i++) {
			if (!getPossibleNeigbour(i))
				_directionPossible[i] = false;
		}
		return calcnow();
	}

	/**
	 * @return
	 */
	private boolean calcnow() {
		UseLogger.LOGGER.info("Moegliche Durchgang fuer Zelle "
				+ _maze.getPositionOfCell(_myCell)[0]
				+ _maze.getPositionOfCell(_myCell)[1] + " "
				+ _directionPossible[0] + "  " + _directionPossible[1] + "  "
				+ _directionPossible[2] + "  " + _directionPossible[3]);
		if (!_directionPossible[0] && !_directionPossible[1]
				&& !_directionPossible[2] && !_directionPossible[3]) {
			logString = "Kein Druchgang m�glich. Sorry.";
			UseLogger.LOGGER.info(logString);
			//note: rows and columns are switched here!!!!
			_globalConf.getGUI().setDot(_maze.getPositionOfCell(_myCell)[1], _maze.getPositionOfCell(_myCell)[0],"blue");
			return false;
		}

		for (int counter = 0; counter < 4; counter++) {

			if (_globalConf.isStepModus()) {
				try {
					System.out.println("jetzt dauert es an...");
					Thread.sleep(main.Conf.TIME);
				} catch (InterruptedException e) {
					UseLogger.LOGGER
							.warning("Warning: I can't get no sleep");
					e.printStackTrace();
				}	
			}
			//note: rows and columns are switched here!!!!
			_globalConf.getGUI().setDot(_maze.getPositionOfCell(_myCell)[1], _maze.getPositionOfCell(_myCell)[0]);
	
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

				String _currentCellPosition = ""
						+ _maze.getPositionOfCell(_myCell)[0]
						+ _maze.getPositionOfCell(_myCell)[1];

				UseLogger.LOGGER.info(" ");
				logString = "Rekursiver Aufruf:  Aktuelle Zelle = "
						+ _currentCellPosition + "\n";
				UseLogger.LOGGER.info(logString);

				
				boolean retbool = new OwnSolvingAlgoCalcing(_maze, _nextCell,
						_endCell, counter, _globalConf).calc();

				if (retbool)				
					return retbool;

				if (!retbool) {
					_globalConf.get_output().printLine(" ");

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
		//note: rows and columns are switched here!!!!
		_globalConf.getGUI().setDot(_maze.getPositionOfCell(_myCell)[1], _maze.getPositionOfCell(_myCell)[0],"blue");
		return false;
	}
}
