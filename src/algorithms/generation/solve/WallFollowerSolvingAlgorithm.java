package algorithms.generation.solve;

import java.util.Arrays;

import labyrinth.Cell;
import labyrinth.Maze;
import main.Conf;

public class WallFollowerSolvingAlgorithm extends ASolvingAlgorithms {
	private int[] currentCellPos;
	private Direction direction;

	private enum Direction {
		NORTH, EAST, SOUTH, WEST;
	}

	/**
	 * @param maze
	 * @param startCell
	 * @param endCell
	 * @param configs
	 */
	public WallFollowerSolvingAlgorithm(Maze maze, Cell startCell, Cell endCell, Conf configs) {
		super(maze, startCell, endCell, configs);
		setName("<<WallFollowerAlgorithm>>");
		direction = Direction.EAST;
	}

	@Override
	public void resolveMaze() {	
		_configs.get_output().printLine("\n###########################\nstarting with wall follower\n###########################\n\n");
		
		currentCellPos = _maze.getPositionOfCell(_startCell);
		while(! Arrays.equals(currentCellPos, _maze.getPositionOfCell(_endCell))) {
			_configs.get_output().printLine("My current Position is: " + Arrays.toString(currentCellPos) + " and my direction is " + direction);
			
			if (isValidCellPosition(getRighterCell(currentCellPos))) { _configs.get_output().printLine("turning right"); turnRight(); 
			} else if (isValidCellPosition(getCellAhead(currentCellPos))) { _configs.get_output().printLine("doing nothing");
			} else if (isValidCellPosition(getLefterCell(currentCellPos))) { _configs.get_output().printLine("turning left"); turnLeft(); 
			} else {_configs.get_output().printLine("turning around"); turnAround(); }
		}
	}

	/**
	 * This method checks whether a cell is a valid room.
	 * $
	 * @param Position of a cell which needs to be checked
	 * @return true if given cell is neither a breakable wall nor a unbreakable wall otherwise false.
	 */
	private boolean isValidCellPosition(int[] cellPos) {
		_configs.get_output().printLine("testing cell on position "+ cellPos[0] + " " + cellPos[1]);
		if (_maze.getCellOnPosition(cellPos[0], cellPos[1]).getState() != "B" && _maze.getCellOnPosition(cellPos[0], cellPos[1]).getState() != "U")  {
			currentCellPos[0]= cellPos[0];
			currentCellPos[1]= cellPos[1];	
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns the position of the cell in front of a given cell.
	 * 
	 * @param Cell of which the cell ahead should be returned 
	 * @return	position of the cell ahead.
	 */
	private int[] getCellAhead(int[] currentCellPos) {
		int[] tempCellPos = new int[] {currentCellPos[0], currentCellPos[1]};
		System.out.println("getting cell ahead of " + Arrays.toString(tempCellPos));
		switch(direction) {
			case NORTH:
				tempCellPos[0]--;
				break;
			case EAST:
				tempCellPos[1]++;
				break;
			case SOUTH:
				tempCellPos[0]++;
				break;
			case WEST:
				tempCellPos[1]--;
				break;
		}
		_configs.get_output().printLine("cell ahead is " + Arrays.toString(tempCellPos));
		return tempCellPos;
	}
	
	/**
	 * This method returns the position of the cell on the right side of a given cell.
	 * 
	 * @param Cell of which the cell on the ride side should be returned 
	 * @return	position of the cell on the right side.
	 */
	private int[] getRighterCell(int[] currentCellPos) {
		int[] tempCellPos = new int[] {currentCellPos[0], currentCellPos[1]};
		_configs.get_output().printLine("getting righter cell of " + Arrays.toString(tempCellPos));
		switch (direction) {	
			case NORTH:
				tempCellPos[1]++;
				break;
			case EAST:
				tempCellPos[0]++;
				break;
			case SOUTH:
				tempCellPos[1]--;
				break;
			case WEST:
				tempCellPos[0]--;
				break;
		}
		_configs.get_output().printLine("righter cell is " + Arrays.toString(tempCellPos));
		return tempCellPos;
	}
	
	/**
	 * This method returns the position of the cell on the left side of a given cell.
	 * 
	 * @param Cell of which the cell on the ride side should be returned 
	 * @return	position of the cell on the left side.
	 */
	private int[] getLefterCell(int[] currentCellPos) {
		int[] tempCellPos = new int[] {currentCellPos[0], currentCellPos[1]};
		System.out.println("getting lefter cell of " + Arrays.toString(tempCellPos));
		switch (direction) {
			case NORTH:
				tempCellPos[1]--;
				break;
			case EAST:
				tempCellPos[0]--;
				break;
			case SOUTH:
				tempCellPos[1]++;
				break;
			case WEST:
				tempCellPos[0]++;
				break;
		}
		_configs.get_output().printLine("lefter cell is " + Arrays.toString(tempCellPos));
		return tempCellPos;
	}

	
	/**
	 * Changes the direction of the follower to right
	 */
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
		_configs.get_output().printLine("[turnRight] my current position is now: " + Arrays.toString(currentCellPos) + " and my direction is " + direction);
	}

	/**
	 * Changes the direction of the follower to left
	 */
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
		_configs.get_output().printLine("[turnLeft] my current position is now: " + Arrays.toString(currentCellPos) + " and my direction is " + direction);
	}

	/**
	 * Turns around the direction of the follower
	 */
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
		_configs.get_output().printLine("[turnAround] my current position is now: " + Arrays.toString(currentCellPos) + " and my direction is " + direction);
	}
	@Override
	public void defineStartandEndCell() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getCommand() {
		return "WALLFOLLOWER";
	}

}
