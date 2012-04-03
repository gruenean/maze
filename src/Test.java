import labyrinth.Maze;
import algorithms.generation.CreatingAlgorithms;
import algorithms.generation.ownAlgo;

public class Test {

	private static CreatingAlgorithms[] possiblesAlgos = null;
	private static int rows;
	private static int cols;
	private static Maze maze;
	private static ownAlgo mycreatingAlgo;

	public static void main(String[] args) {
		rows = 5;
		cols = 5;
		Maze maze = new Maze(10, 10);

		//maze.printMap();

		/**
		 * creates all possibles Algorithms
		 */
		possiblesAlgos = new CreatingAlgorithms[] { new ownAlgo(maze) };
		possiblesAlgos[0].createMaze();

		// for (int i = 0; i < possiblesAlgos.length; i++) {
		// System.out.println(possiblesAlgos[i].getInstance(null));}

	}

	// lab.create_One_Solution();

	// lab.printMap();
	// System.out.println("-----");
	// lab.getRandomCell().setValue("X");
	// lab.printMap();

}
