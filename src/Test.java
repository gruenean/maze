import java.io.Console;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.MyLogger;
import logging.UseLogger;
import algorithms.generation.create.CreatingAlgorithms;
import algorithms.generation.create.ownCreatingAlgo;
import algorithms.generation.solve.OwnSolvingAlgo;
import algorithms.generation.solve.SolvingAlgorithms;

public class Test{

	private static CreatingAlgorithms[] _possiblesCreatingAlgos = null;
	private static SolvingAlgorithms[] _possiblesSolvingAlgos = null;
	private static int _rows;
	private static int _cols;
	private static Maze _maze;
	private static CreatingAlgorithms _mycreatingAlgo;
	private static SolvingAlgorithms _mysolvingAlgo;
	private static Cell _startCell;
	private static Cell _endCell;
	private static MyLogger _logger;
	private static Console _console;

	public static void main(String[] args) {
		_logger = new MyLogger();
		try {
			_logger.setup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UseLogger.LOGGER.setLevel(Level.SEVERE);

		_rows = 5;
		_cols = 5;
		Maze _maze = new Maze(_rows, _cols);
		_startCell = _maze.getCellOnPosition(0, 0);
		_endCell = _maze.getCellOnPosition(_rows-1, _cols-1);

		/**
		 * creates all possibles Algorithms
		 */
		_possiblesCreatingAlgos = new CreatingAlgorithms[] { new ownCreatingAlgo(
				_maze) };
		_possiblesSolvingAlgos = new SolvingAlgorithms[] { new OwnSolvingAlgo(
				_maze, _startCell, _endCell) };

		_mycreatingAlgo = chooseOneRandomCreatingAlgo();
		_mycreatingAlgo.createMaze();

		_mysolvingAlgo = chooseOneRandomSolvingAlgo();

		System.out.println("\n Das Labyrinth wurde mit dem "
				+ _mycreatingAlgo.getName() + " erstellt... \n\n\n");

		// TODO this is in TESING MODE
		_mysolvingAlgo.resolveMaze();

	}

	/**
	 * 
	 * @return gives a Random Creating Algorithms (off all possibles)
	 */
	public static CreatingAlgorithms chooseOneRandomCreatingAlgo() {
		return _possiblesCreatingAlgos[new Random()
				.nextInt(_possiblesCreatingAlgos.length)];

	}

	/**
	 * 
	 * @return gives a Random Solving Algorithms (off all possibles)
	 */
	public static SolvingAlgorithms chooseOneRandomSolvingAlgo() {
		return _possiblesSolvingAlgos[new Random()
				.nextInt(_possiblesSolvingAlgos.length)];

	}

	// lab.create_One_Solution();

	// lab.printMap();
	// System.out.println("-----");
	// lab.getRandomCell().setValue("X");
	// lab.printMap();

}
