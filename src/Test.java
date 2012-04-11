import java.util.Random;

import labyrinth.Cell;
import labyrinth.Maze;
import algorithms.generation.create.CreatingAlgorithms;
import algorithms.generation.create.ownCreatingAlgo;
import algorithms.generation.solve.OwnSolvingAlgo;
import algorithms.generation.solve.SolvingAlgorithms;

public class Test {

	private static CreatingAlgorithms[] _possiblesCreatingAlgos = null;
	private static SolvingAlgorithms[] _possiblesSolvingAlgos = null;
	private static int _rows;
	private static int _cols;
	private static Maze _maze;
	private static CreatingAlgorithms _mycreatingAlgo;
	private static SolvingAlgorithms _mysolvingAlgo;
//	private static ownSolvingAlgo _possiblesSolvingAlgos;
	private static Cell _startCell;
	private static Cell _endCell;
	
	

	public static void main(String[] args) {
		_rows = 5;
		_cols = 5;
		Maze _maze = new Maze(10, 10);
		_startCell = _maze.getCellOnPosition(1,1);
		_endCell = _maze.getCellOnPosition(_rows,_cols);
		// maze.printMap();

		/**
		 * creates all possibles Algorithms
		 */
		_possiblesCreatingAlgos = new CreatingAlgorithms[] { new ownCreatingAlgo(_maze)};
		_possiblesSolvingAlgos = new SolvingAlgorithms[] { new OwnSolvingAlgo(_startCell, _endCell) };
		
		_mycreatingAlgo = chooseOneRandomCreatingAlgo();
		_mycreatingAlgo.createMaze();
		
		_mysolvingAlgo = chooseOneRandomSolvingAlgo();
		
	
		_mysolvingAlgo.resolveMaze();
		
		
		System.out.println("\n Das Labyrinth wurde mit dem " + _mycreatingAlgo.getName() + " erstellt... \n\n\n");

		
		
	
		
		
		
		// for (int i = 0; i < possiblesAlgos.length; i++) {
		// System.out.println(possiblesAlgos[i].getInstance(null));}

	}
	/**
	 * 
	 * @return gives a Random Creating Algorithms (off all possibles)
	 */
	public static CreatingAlgorithms chooseOneRandomCreatingAlgo (){
		return _possiblesCreatingAlgos[new Random().nextInt(_possiblesCreatingAlgos.length)];
		
	}
	
	/**
	 * 
	 * @return gives a Random Solving Algorithms (off all possibles)
	 */
	public static SolvingAlgorithms chooseOneRandomSolvingAlgo (){
		return _possiblesSolvingAlgos[new Random().nextInt(_possiblesSolvingAlgos.length)];
		
	}
	
	
	// lab.create_One_Solution();

	// lab.printMap();
	// System.out.println("-----");
	// lab.getRandomCell().setValue("X");
	// lab.printMap();

}
