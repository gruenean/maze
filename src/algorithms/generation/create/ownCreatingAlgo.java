package algorithms.generation.create;

import labyrinth.Cell;
import labyrinth.Maze;

/**
 * this algorithm is a creating algotihm with a own created algorithm, which is near to the kruscal algorithm
 * 
 * @author micha
 * 
 */
public class ownCreatingAlgo extends ACreatingAlgorithms {

	Cell randomCell;
	Cell NeighbourCell;

	public ownCreatingAlgo(Maze maze) {
		super(maze);
		setName("<<eigener CreatingAlgo>>");
	}

	private void defineStartandEndCell() {
		_maze.createEntranceAndExit();
	}

	public void createMaze() {
		while (_maze.hasMultipleRoots()) {
			randomCell = _maze.getRandomCell();
			NeighbourCell = _maze.getRandomNeighbour(randomCell);

			if (!randomCell.getRoot().equals(NeighbourCell.getRoot())) {
				_maze.breakWallBetweenCells(randomCell, NeighbourCell);
				// TODO: comment out the following four lines as these are
				// testing only
				_maze.printAsciiMaze();
				System.out.println();
			} else {
				System.out.println("impossibru");
			}
		}
		defineStartandEndCell();
		_maze.printAsciiMaze();
	}

}
