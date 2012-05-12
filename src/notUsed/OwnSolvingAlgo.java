package notUsed;

import algorithms.generation.solve.ISolvingAlgorithms;
import algorithms.generation.solve.ASolvingAlgorithms;
import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;

/**
 * @deprecated this Class will not be used / 11.05.2012
 * @author micha
 */
public class OwnSolvingAlgo extends ASolvingAlgorithms implements
		ISolvingAlgorithms {

	OwnSolvingAlgoCalcing _calc;
	

	public OwnSolvingAlgo(Maze maze, Cell startCell, Cell endCell) {
		super(maze, startCell, endCell);
		setName("<<eigener CreatingAlgo>>");

		_currentCell = startCell;
		UseLogger.LOGGER.finest("Startcell = " + _startCell);
		System.out.println("Startcell = " + _startCell);
		// randomCell = null;
		// NeighbourCell = null;
	}

	@Override
	public void resolveMaze() {
		System.out.println("Position letzte Zelle = ["
				+ _maze.getPositionOfCell(_endCell)[0]
				+ _maze.getPositionOfCell(_endCell)[1] + "]");
		_calc = new OwnSolvingAlgoCalcing(_maze, _startCell, _endCell, 3);

		if (_calc.calc()) System.out.println("Lšsung yes...");
		else System.out.println("keine Lšsung...");
		
		
	}


	@Override
	public void defineStartandEndCell() {
		// TODO Auto-generated method stub

	}

}
