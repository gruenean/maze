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
	boolean[] _directionPossible = new boolean[] { true, true, true, true };
	String logString;
	String _whereDoIComeFrom;

	public OwnSolvingAlgoCalcing_NEW(Maze maze, Cell startCell, Cell endCell,
			int notthisWall) {
		_endCell = endCell;
		_maze = maze;
		_currentCell = startCell;
		_tempCell = null;

		if (notthisWall == 0) {
			_directionPossible[1] = false;
			_whereDoIComeFrom = Conf.getWallName(1);
		} else if (notthisWall == 1) {
			_directionPossible[0] = false;
			_whereDoIComeFrom = Conf.getWallName(0);
		} else if (notthisWall == 2) {
			_directionPossible[3] = false;
			_whereDoIComeFrom = Conf.getWallName(3);
		} else if (notthisWall == 3) {
			_directionPossible[2] = false;
			_whereDoIComeFrom = Conf.getWallName(2);
		}
		
		logString = " Ich bin Zelle " + _maze.getPositionOfCell(_currentCell)[0]
				+ _maze.getPositionOfCell(_currentCell)[1] + " und mein Status = " + _currentCell.getState();
						
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
			//System.out.println("not possible: Cell = null");
			//UseLogger.LOGGER.info("not possible: Cell = null");
			return false;
		}
		if (_tempCell.getState() == "B" || _tempCell.getState() == "U") {
			logString = "Ist nicht mšglich. Zelle hat getState: "
					+ _tempCell.getState();
			//System.out.println(logString);

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

		if (reachedTheEndCell()) {
			UseLogger.LOGGER.info("ENDE ERREICHT!!!!");
			System.out.println("ENDE ERREICHT!!!!");
			return true;
		}

		// String poss = "";
		
		for (int i = 0; i < 4; i++) {
			if (!getPossibleNeigbour(i)) _directionPossible[i]  = false;
		}
		
		UseLogger.LOGGER.info("Moegliche Waende = "
				+ _directionPossible[0] + "  " + _directionPossible[1]
						+ "  " + _directionPossible[2] + "  "
						+ _directionPossible[3]);
		System.out.println("Mein Status: " + _currentCell.getState());
		
		for (int i = 0; i < 4; i++) {
			
			UseLogger.LOGGER.info(_maze.getPositionOfCell(_currentCell)[0]
					+ _maze.getPositionOfCell(_currentCell)[1] + " will nach " + Conf.getWallName(i)
					+ " gehen. Dies ist " + _directionPossible[i] + ".");
			
			if (_directionPossible[i]) {
				logString = "\n Suche Nachbar " + Conf.getWallName(i)
						+ " von Zelle an Position: \n "
						+ _maze.getPositionOfCell(_currentCell)[0]
						+ _maze.getPositionOfCell(_currentCell)[1]
						+ "     possible ? = " + getPossibleNeigbour(i);
				_tempCell = _maze.getNeigbourofCell(_currentCell, i);
				UseLogger.LOGGER.info(logString);

			//	SolvingAlgorithms._solvingCounter = SolvingAlgorithms._solvingCounter++;
				_currentCell = _tempCell;

				String _currentCellPosition = ""
						+ _maze.getPositionOfCell(_currentCell)[0]
						+ _maze.getPositionOfCell(_currentCell)[1];

				UseLogger.LOGGER.info(" ");
				logString = "Rekursiver Aufruf:  Aktuelle Zelle = "
						+ _currentCellPosition + "\n";
				UseLogger.LOGGER.info(logString);
				
				boolean retbool = new OwnSolvingAlgoCalcing_NEW(_maze,
						_currentCell, _endCell, i).calc();
				
				if (retbool)
					return retbool;

				if (!retbool) {
					System.out.println();
					_directionPossible[i] = false;
					logString = "Ich bin die Zelle "
							+ _maze.getPositionOfCell(_currentCell)[0]
							+ _maze.getPositionOfCell(_currentCell)[1]
							+ " und habe keinen Weg in die Richtung "
							+ Conf.getWallName(i) + " gefunden.";
					UseLogger.LOGGER.info(logString);
					String logString = "current Cell = "
							+ _directionPossible[0] + " "
							+ _directionPossible[1] + "  "  
							+ _directionPossible[2] + " "
							+ _directionPossible[3];
					UseLogger.LOGGER.info(logString);
					
					
					
					// System.out.println(_directionPossible[i]);
					
				}

//				else {
//					UseLogger.LOGGER.info("FALSE, FALSE !!!!");
//				}

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
