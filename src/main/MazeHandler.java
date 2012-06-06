package main;

import ioInferface.gui.MyGameGrid;

import java.util.List;
import java.util.Random;

import labyrinth.Cell;
import labyrinth.Maze;
import algorithms.generation.create.ACreatingAlgorithms;
import algorithms.generation.create.ICreatingAlgorithms;
import algorithms.generation.create.ownCreatingAlgo;
import algorithms.generation.solve.ASolvingAlgorithms;
import algorithms.generation.solve.ISolvingAlgorithms;
import algorithms.generation.solve.OwnSolvingAlgo;
import algorithms.generation.solve.WallFollowerSolvingAlgorithm;

public class MazeHandler {

	private ACreatingAlgorithms[] _possiblesCreatingAlgos = null;
	private ASolvingAlgorithms[] _possiblesSolvingAlgos = null;
	private int _rows;
	private int _cols;
	private Maze _maze;
	private Conf _globalConf;

	public MazeHandler(Conf globalConf) {
		_globalConf = globalConf;
		_globalConf.setMazeHandler(this);
		_rows = 10;
		_cols = 10;
		_maze = new Maze(_rows, _cols, _globalConf);

		/**
		 * set _rows, _cols and _endCell new
		 */
		_rows = _maze.getLastPosition()[0];
		_cols = _maze.getLastPosition()[1];

		createNewGui();
		createAlgos();
	}

	private void createAlgos() {
		_possiblesCreatingAlgos = new ACreatingAlgorithms[] { new ownCreatingAlgo(
				_maze, _globalConf) };
		_possiblesSolvingAlgos = new ASolvingAlgorithms[] {
				new OwnSolvingAlgo(_maze, _maze.getStartCell(),
						_maze.getEndCell(), _globalConf),
				new WallFollowerSolvingAlgorithm(_maze, _maze.getStartCell(),
						_maze.getEndCell(), _globalConf) };
	}

	public void createNewGui() {

		if (_globalConf.getGUI() != null)
			_globalConf.getGUI().hide();
		_globalConf.setGUI(new MyGameGrid(_maze.getRows(), _maze.getCols(),
				_globalConf));
		showWalls();
		_globalConf.getGUI().configLitteBug(
				_maze.getPositionOfCell(_maze.getStartCell()));

	}

	public void createMaze(String creatingAlgoName) {
		for (ICreatingAlgorithms creatingAlgo : _possiblesCreatingAlgos) {
			if (creatingAlgo.getName().equals(creatingAlgoName)) {
				System.out.println(creatingAlgo.getName());
				creatingAlgo.createMaze();
				
				//Set and draw Entrance / Exit
				_maze.createEntranceAndExit();
				_globalConf.getGUI().removeWall(_maze.getPositionOfCell(_maze.getStartCell()));
				_globalConf.getGUI().removeWall(_maze.getPositionOfCell(_maze.getEndCell()));
				
				_globalConf.get_output().printLine(" ");
				_maze.printAsciiMaze();
				_globalConf.get_output().printLine("\n Das Labyrinth wurde mit dem " + creatingAlgo.getName()
						+ " erstellt... \n\n\n");
			}
		}
	}

	public Maze getMaze() {
		return _maze;
	}

	/**
	 * @param solvingAlgoName
	 */
	public void solveMaze(String solvingAlgoName) {
		for (ISolvingAlgorithms solvingAlgo : _possiblesSolvingAlgos) {
			if (solvingAlgo.getName().equals(solvingAlgoName)) {
				System.out.println(solvingAlgo.getName());
				solvingAlgo.resolveMaze();
			}
		}
	}

	/**
	 * 
	 * @return gives a Random Creating Algorithms (off all possibles)
	 */
	public ACreatingAlgorithms chooseOneRandomCreatingAlgo() {
		return _possiblesCreatingAlgos[new Random()
				.nextInt(_possiblesCreatingAlgos.length)];

	}

	/**
	 * 
	 * @return gives a Random Solving Algorithms (off all possibles)
	 */
	public ASolvingAlgorithms chooseOneRandomSolvingAlgo() {
		return _possiblesSolvingAlgos[new Random()
				.nextInt(_possiblesSolvingAlgos.length)];

	}

	/**
	 * displays all walls on the maze
	 */
	private void showWalls() {
		List<Cell> allWalls = _maze.getAllWalls();
		for (int i = 0; i < allWalls.size(); i++) {

			_globalConf.getGUI().setWall(
					_maze.getPositionOfCell(allWalls.get(i))[0],
					_maze.getPositionOfCell(allWalls.get(i))[1]);
		}
	}
}
