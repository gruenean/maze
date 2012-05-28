package algorithms.generation.solve;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import labyrinth.Cell;
import labyrinth.Maze;
import main.Conf;

public class WallFollowerSolvingAlgorithm extends ASolvingAlgorithms {
	private int[] currentCellPos;
	private Direction direction;

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
		_configs.get_output().printLine("\n###########################\nstarting with wall follower\n###########################\n\n");
		
		currentCellPos = _maze.getPositionOfCell(_startCell);
		while(! Arrays.equals(currentCellPos, _maze.getPositionOfCell(_endCell))) {
			_configs.get_output().printLine("My current Position is: " + Arrays.toString(currentCellPos) + " and my direction is " + direction);
			
			if (isValidCellPosition(getRighterCell(currentCellPos))) { _configs.get_output().printLine("turning right"); turnRight(); 
			} else if (isValidCellPosition(getCellAhead(currentCellPos))) { _configs.get_output().printLine("doing nothing");
			} else if (isValidCellPosition(getLefterCell(currentCellPos))) { _configs.get_output().printLine("turning left"); turnLeft(); 
			} else {_configs.get_output().printLine("turning around"); turnAround(); }
			//moveForward();
		}
	}

	private boolean isValidCellPosition(int[] cellPos) {
		System.out.println("testing cell on position "+ cellPos[0] + " " + cellPos[1]);
		if (_maze.getCellOnPosition(cellPos[0], cellPos[1]).getState() != "B" && _maze.getCellOnPosition(cellPos[0], cellPos[1]).getState() != "U")  {
			currentCellPos[0]= cellPos[0];
			currentCellPos[1]= cellPos[1];	
			return true;
		}
		return false;
	}
	
	private int[] getCellAhead(int[] tempCellPos) {
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
	
	private int[] getRighterCell(int[] tempCellPos) {
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
	
	private int[] getLefterCell(int[] tempCellPos) {
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

	private void moveForward() {
		switch(direction) {
			case NORTH:
				//if (currentCellPos[0]-- > _maze.getRows() && currentCellPos[0]-- < 0) 
				currentCellPos[0]--;
				break;
			case EAST:
				//if (currentCellPos[1]++ > _maze.getCols() && currentCellPos[1]++ < 0) 
				currentCellPos[1]++;
				break;
			case SOUTH:
				//if (currentCellPos[0]++ > _maze.getRows() && currentCellPos[0]++ < 0)
				currentCellPos[0]++;
				break;
			case WEST:
				//if (currentCellPos[1]-- > _maze.getCols() && currentCellPos[1]-- < 0)
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
		_configs.get_output().printLine("[turnRight] my current position is now: " + Arrays.toString(currentCellPos) + "and my direction is " + direction);
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
		_configs.get_output().printLine("[turnLeft] my current position is now: " + Arrays.toString(currentCellPos) + "and my direction is " + direction);
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
		_configs.get_output().printLine("[turnAround] my current position is now: " + Arrays.toString(currentCellPos) + "and my direction is " + direction);
	}
	@Override
	public void defineStartandEndCell() {
		// TODO Auto-generated method stub

	}

}
