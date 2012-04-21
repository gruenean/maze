package Cell;

import java.util.Random;

import labyrinth.Cell;
import labyrinth.Maze;
import algorithms.generation.create.CreatingAlgorithms;
import algorithms.generation.create.ownCreatingAlgo;
import algorithms.generation.solve.OwnSolvingAlgo;
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

		_rows = 7;
		_cols = 7;
		Maze _maze = new Maze(_rows, _cols);
		_startCell = _maze.getCellOnPosition(0, 0);
		_endCell = _maze.getCellOnPosition(_rows - 1, _cols - 1);

		_possiblesCreatingAlgos = new CreatingAlgorithms[] { new ownCreatingAlgo(
				_maze) };
		_possiblesSolvingAlgos = new SolvingAlgorithms[] { new OwnSolvingAlgo(
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
