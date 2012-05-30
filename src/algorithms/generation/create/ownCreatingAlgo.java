package algorithms.generation.create;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import main.Conf;

/**
 * this algorithm is a creating algotihm with a own created algorithm, which is
 * near to the kruscal algorithm
 * 
 * @author micha
 * 
 */
public class ownCreatingAlgo extends ACreatingAlgorithms {

	public ownCreatingAlgo(Maze maze, Conf globalConf) {
		super(maze);
		_globalConf = globalConf;
		setName("<<eigener CreatingAlgo>>");
	}

	// private void defineStartandEndCell() {
	// _maze.createEntranceAndExit();
	// }

	public void createMaze() {
		Cell randomCell;
		Cell NeighbourCell;
		while (_maze.hasMultipleRoots()) {
			randomCell = _maze.getRandomCell();
			NeighbourCell = _maze.getRandomNeighbour(randomCell);

			if (!randomCell.getRoot().equals(NeighbourCell.getRoot())) {
				_maze.breakWallBetweenCells(randomCell, NeighbourCell);

				if (_globalConf.isStepModus()) {
					try {
						System.out.println("jetzt dauert es an...");
						Thread.sleep(main.Conf.TIME);
					} catch (InterruptedException e) {
						UseLogger.LOGGER
								.warning("Warning: I can't get no sleep");
						e.printStackTrace();
					}
				}
				_globalConf.getGUI().removeWall(
						_maze.getWallBetweenCells(randomCell, NeighbourCell));

				// TODO: comment out the following four lines as these are
				// testing only
				// System.out.println("asdfasdf " + _configs);
				_maze.printAsciiMaze();
				_globalConf.get_output().printLine(" ");
			} else {
				_globalConf.get_output().printLine("impossibru");
			}
		}
		// defineStartandEndCell();
//		_maze.createEntranceAndExit();
//		_globalConf.getGUI().removeWall(_maze.getPositionOfCell(_maze.getStartCell()));
//		_globalConf.getGUI().removeWall(_maze.getPositionOfCell(_maze.getEndCell()));
		_globalConf.getGUI().configLitteBug(_maze.getPositionOfCell(_maze.getStartCell()));
		_maze.printAsciiMaze();
	}
}
