package Cell_OLDOLDOLD;

import java.util.Random;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import algorithms.generation.create.CreatingAlgorithms;
import algorithms.generation.create.ownCreatingAlgo;
import algorithms.generation.solve.OwnSolvingAlgo_NEW;
import algorithms.generation.solve.SolvingAlgorithms;

public class Test {

	private CreatingAlgorithms[] _possiblesCreatingAlgos = null;
	private SolvingAlgorithms[] _possiblesSolvingAlgos = null;
	private int _rows;
	private int _cols;
	private Maze _maze;
	private CreatingAlgorithms _mycreatingAlgo;
	private SolvingAlgorithms _mysolvingAlgo;
	private Cell _startCell;
	private Cell _endCell;

	public Test() {

		_rows = 5;
		_cols = 5;
		_maze = new Maze(_rows, _cols);
		
		/**
		 * set _rows, _cols and _endCell new
		 */
		_rows = _maze.getLastPosition()[0];
		_cols = _maze.getLastPosition()[1];
		
		
		_startCell = _maze.getCellOnPosition(1, 1);
		
		
		//_endCell = _maze.getCellOnPosition(_maze.getLastPosition()[0], _maze.getLastPosition()[1]);
		
		_endCell = _maze.getCellOnPosition(_cols-2, _rows-2);
		
		
		System.out.println("Position Ende = " + _maze.getLastPosition()[0] + _maze.getLastPosition()[1]);
		
		System.out.println("Position EndCell = " + _maze.getPositionOfCell(_endCell)[0] + _maze.getPositionOfCell(_endCell)[1]);
		
		_possiblesCreatingAlgos = new CreatingAlgorithms[] { new ownCreatingAlgo(
				_maze) };
		_possiblesSolvingAlgos = new SolvingAlgorithms[] { new OwnSolvingAlgo_NEW(
				_maze, _startCell, _endCell) };
		_mycreatingAlgo = chooseOneRandomCreatingAlgo();
		_mysolvingAlgo = chooseOneRandomSolvingAlgo();
	}

	public void createMaze() {

		/**
		 * creates all possibles Algorithms
		 */
		_mycreatingAlgo.createMaze();

		System.out.println("\n Das Labyrinth wurde mit dem "
				+ _mycreatingAlgo.getName() + " erstellt... \n\n\n");

		
	}

	public void solveMaze() {
		// TODO this is in TESING MODE
		_mysolvingAlgo.resolveMaze();

	}

	/**
	 * 
	 * @return gives a Random Creating Algorithms (off all possibles)
	 */
	public CreatingAlgorithms chooseOneRandomCreatingAlgo() {
		return _possiblesCreatingAlgos[new Random()
				.nextInt(_possiblesCreatingAlgos.length)];

	}

	/**
	 * 
	 * @return gives a Random Solving Algorithms (off all possibles)
	 */
	public SolvingAlgorithms chooseOneRandomSolvingAlgo() {
		return _possiblesSolvingAlgos[new Random()
				.nextInt(_possiblesSolvingAlgos.length)];

	}

}
