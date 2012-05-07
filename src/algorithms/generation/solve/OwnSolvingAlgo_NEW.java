package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;

public class OwnSolvingAlgo_NEW extends SolvingAlgorithms implements
		ISolvingAlgorithms {

	OwnSolvingAlgoCalcing_NEW _calc;

	public OwnSolvingAlgo_NEW(Maze maze, Cell startCell, Cell endCell) {
		super(maze, startCell, endCell);
		setName("<<eigener CreatingAlgo>>");

		_currentCell = startCell;
		UseLogger.LOGGER.info("startCell = " + _startCell);
		//System.out.println("Startcell = " + _startCell);

	
	}

	@Override
	public void resolveMaze() {
		UseLogger.LOGGER.info("Position letzte Zelle = ["
				+ _maze.getPositionOfCell(_endCell)[0]
				+ _maze.getPositionOfCell(_endCell)[1] + "]");
		
		UseLogger.LOGGER.info("First time calcing...");
		_calc = new OwnSolvingAlgoCalcing_NEW(_maze, _startCell, _endCell, 99);
boolean _soltutionfound = _calc.calc();

		if (_soltutionfound)
			System.out.println("Lšsung yes...");
		else
			System.out.println("keine Lšsung...");

	}

	@Override
	public void defineStartandEndCell() {
		// TODO Auto-generated method stub

	}

}
