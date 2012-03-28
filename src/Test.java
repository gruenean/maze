import labyrinth.Maze;
import algorithms.generation.Algorithms;
import algorithms.generation.Kruskal;
import algorithms.generation.ownAlgo;

public class Test {

	private static Algorithms[] possiblesAlgos = null;
	private static int rows;
	private static int cols;
	private static Maze mymaze;
	private static ownAlgo mycreatingAlgo;

	public static void main(String[] args) {
		rows = 10;
		cols = 10;
		Maze lab = new Maze(10, 10);

		/**
		 * creates all possibles Algorithms
		 */
		possiblesAlgos = new Algorithms[] { new ownAlgo(true), new Kruskal(true), new ownAlgo(true)};


//		 for (int i = 0; i < possiblesAlgos.length; i++) {
//		 System.out.println(possiblesAlgos[i].getInstance(null));}

	}

	// lab.create_One_Solution();

	// lab.printMap();
	// System.out.println("-----");
	// lab.getRandomCell().setValue("X");
	// lab.printMap();

}

