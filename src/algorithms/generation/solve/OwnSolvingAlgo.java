package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import main.Conf;

public class OwnSolvingAlgo extends ASolvingAlgorithms {

	OwnSolvingAlgoCalcing _calc;

	public OwnSolvingAlgo(Maze maze, Cell startCell, Cell endCell, Conf configs) {
		super(maze, startCell, endCell, configs);

		setName("<<eigener CreatingAlgo>>");

		_currentCell = startCell;
		UseLogger.LOGGER.info("startCell = " + _startCell);

	}

	@Override
	public void resolveMaze() {

		UseLogger.LOGGER.info("Position letzte Zelle = ["
				+ _maze.getPositionOfCell(_endCell)[0]
				+ _maze.getPositionOfCell(_endCell)[1] + "]");

		UseLogger.LOGGER.info("First time calcing...");
		_calc = new OwnSolvingAlgoCalcing(_maze, _startCell, _endCell, 99,
				_configs);
		boolean _soltutionfound = _calc.calc();

		if (_soltutionfound) {
			_configs.get_output().printLine("L�sung yes...");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			_configs.get_output().printLine("keine L�sung gefunden ;-( ...");
			_configs.get_output()
					.printLine(
							"Erstellungs- und L�sungsalgorithmus passen nicht zusammen...");
		}
	}

	@Override
	public void defineStartandEndCell() {
		// TODO Auto-generated method stub

	}

}
