package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;

public class OwnSolvingAlgo extends SolvingAlgorithms implements
		ISolvingAlgorithms {

	OwnSolvingAlgoCalcing _calc;

	// Cell randomCell;
	// Cell NeighbourCell;

	public OwnSolvingAlgo(Maze maze, Cell startCell, Cell endCell) {
		super(maze, startCell, endCell);
		setName("<<eigener CreatingAlgo>>");

		_currentCell = startCell;
		UseLogger.LOGGER.finest("Startcelle = " + _startCell);
		// randomCell = null;
		// NeighbourCell = null;
	}

	@Override
	public void resolveMaze() {
		_calc = new OwnSolvingAlgoCalcing(_maze, _startCell, _endCell, 5);
		
		
	int notMoreThan = 1000;
	int currentSteps = 0;
	while(	_calc.calc() && notMoreThan > currentSteps){
		
		
		currentSteps++;
	}

	}

	// private boolean isWallHere(){
	// return false;
	//
	//
	// }

	@Override
	public void defineStartandEndCell() {
		// TODO Auto-generated method stub

	}

}
