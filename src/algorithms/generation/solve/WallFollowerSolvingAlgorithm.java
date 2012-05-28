package algorithms.generation.solve;

import java.util.Arrays;

import labyrinth.Cell;
import labyrinth.Maze;
import main.Conf;

/**
 * NOT IMPLEMENTED YET
 * 
 * @author micha
 * 
 */
public class WallFollowerSolvingAlgorithm extends ASolvingAlgorithms {
	int[] currentCellPos;
	Direction direction;

	private enum Direction {
		NORTH, EAST, SOUTH, WEST;
	}

	public WallFollowerSolvingAlgorithm(Maze maze, Cell startCell, Cell endCell, Conf configs) {
		super(maze, startCell, endCell, configs);
		setName("<<WallFollowerAlgorithm>>");
		direction = Direction.EAST;
	}

	@Override
	public void resolveMaze() {

		currentCellPos = _maze.getPositionOfCell(_startCell);
		while (!Arrays
				.equals(currentCellPos, _maze.getPositionOfCell(_endCell))) {
			_configs.get_output().printLine(
					"My current Position is: "
							+ Arrays.toString(currentCellPos));

			if (isValidCellPosition(getRighterCell())) {
				_configs.get_output().printLine("turning right");
				turnRight();
			} else if (isValidCellPosition(getCellAhead())) {
				_configs.get_output().printLine("doing nothing");
			} else if (isValidCellPosition(getLefterCell())) {
				_configs.get_output().printLine("turning left");
				turnLeft();
			} else {
				_configs.get_output().printLine("turning around");
				turnAround();
			}
			moveForward();
		}
	}

	private boolean isValidCellPosition(int[] cellPos) {
		return (_maze.getCellOnPosition(cellPos[0], cellPos[1]).getState() != "B" && _maze
				.getCellOnPosition(cellPos[0], cellPos[1]).getState() != "U");
	}

	private int[] getCellAhead() {
		switch (direction) {
		case NORTH:
			return new int[] { currentCellPos[0]--, currentCellPos[1] };
		case EAST:
			return new int[] { currentCellPos[0], currentCellPos[1]++ };
		case SOUTH:
			return new int[] { currentCellPos[0]++, currentCellPos[1] };
		case WEST:
			return new int[] { currentCellPos[0], currentCellPos[1]-- };
		}
		return new int[] { 0, 0 };
	}

	private int[] getRighterCell() {
		switch (direction) {
		case NORTH:
			return new int[] { currentCellPos[0], currentCellPos[1]++ };
		case EAST:
			return new int[] { currentCellPos[0]++, currentCellPos[1] };
		case SOUTH:
			return new int[] { currentCellPos[0], currentCellPos[1]-- };
		case WEST:
			return new int[] { currentCellPos[0]--, currentCellPos[1] };
		}
		return new int[] { 0, 0 };
	}

	private int[] getLefterCell() {
		switch (direction) {
		case NORTH:
			return new int[] { currentCellPos[0], currentCellPos[1]-- };
		case EAST:
			return new int[] { currentCellPos[0]--, currentCellPos[1] };
		case SOUTH:
			return new int[] { currentCellPos[0], currentCellPos[1]++ };
		case WEST:
			return new int[] { currentCellPos[0]++, currentCellPos[1] };
		}
		return new int[] { 0, 0 };
	}

	private void moveForward() {
		switch (direction) {
		case NORTH:
			if (currentCellPos[0]-- > _maze.getRows()
					&& currentCellPos[0]-- < 0)
				currentCellPos[0]--;
			break;
		case EAST:
			if (currentCellPos[1]++ > _maze.getCols()
					&& currentCellPos[1]++ < 0)
				currentCellPos[1]++;
			break;
		case SOUTH:
			if (currentCellPos[0]++ > _maze.getRows()
					&& currentCellPos[0]++ < 0)
				currentCellPos[0]++;
			break;
		case WEST:
			if (currentCellPos[1]-- > _maze.getCols()
					&& currentCellPos[1]-- < 0)
				currentCellPos[1]--;
			break;
		}
	}

	private void turnRight() {
		switch (direction) {
		case NORTH:
			direction = Direction.EAST;
			break;
		case EAST:
			direction = Direction.SOUTH;
			break;
		case SOUTH:
			direction = Direction.WEST;
			break;
		case WEST:
			direction = Direction.NORTH;
			break;
		}
	}

	private void turnLeft() {
		switch (direction) {
		case NORTH:
			direction = Direction.WEST;
			break;
		case EAST:
			direction = Direction.NORTH;
			break;
		case SOUTH:
			direction = Direction.EAST;
			break;
		case WEST:
			direction = Direction.SOUTH;
			break;
		}
	}

	private void turnAround() {
		switch (direction) {
		case NORTH:
			direction = Direction.SOUTH;
			break;
		case EAST:
			direction = Direction.WEST;
			break;
		case SOUTH:
			direction = Direction.NORTH;
			break;
		case WEST:
			direction = Direction.EAST;
			break;
		}
	}

	@Override
	public void defineStartandEndCell() {
		// TODO Auto-generated method stub

	}

}
