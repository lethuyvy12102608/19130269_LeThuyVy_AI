package lab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Puzzle {
	public static final int MAX_ROW = 3;// 3x3: Dimension of the puzzle map
	public static final int MAX_COL = 3;
	public static final char[] operators = { 'l', 'r', 'u', 'd' };

	private Node initialState;
	private Node goalState;

	public Puzzle() {
		this.initialState = new Node(MAX_ROW, MAX_COL);
		this.goalState = new Node(MAX_ROW, MAX_COL);
	}
	public void readInput(String INITIAL_STATE_MAP_PATH, String GOAL_STATE_MAP_PATH) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(INITIAL_STATE_MAP_PATH));
			String line = null;
			int row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					initialState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

			// 2 - Import goal state
			bufferedReader = new BufferedReader(new FileReader(GOAL_STATE_MAP_PATH));

			line = null;
			row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					goalState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// The total number of misplaced tiles
	public int computeH1(Node currentState) {
		int output = 0;
		/* Enter your code here */
		for (int i = 0; i < goalState.getRow(); i++) {
			for (int j = 0; j < goalState.getColumn(); j++) {
				 if(currentState.getTile(i, j) != goalState.getTile(i, j)) {
					 output++;
				 }
			}
		}
		return output;
	}

	public int computeH2(Node currentState) {
		int result = 0;
		/* Enter your code here */
		PuzzleUtils calculate = new PuzzleUtils();
		int sumNumber = (MAX_COL * MAX_ROW) ;
		for (int i = 1; i < sumNumber; i++) {
		result += calculate.manhattanDistance(currentState.getLocation(i), goalState.getLocation(i));
		}
		return result;
	}


	public Node moveWhiteTile(Node currentState, char operator) {
		Node result = new Node(currentState);
		int[] whiteTile = currentState.getLocation(0);//get white tile
		if (operator == 'u') {// Case-1: Move tile UP
			// New postion of tile if move UP
			int row = whiteTile[0] - 1;
			int col = whiteTile[1];
			if (row >= 0) {// Tile stands inside the map
				int tmp = currentState.getTile(row, col);
				result.updateTile(row, col, 0);
				result.updateTile(whiteTile[0], whiteTile[1], tmp);
				result.setH(computeH2(result));
				return result;
			}
		}

		else if (operator == 'd') {
			/* Enter your code here */
			int row = whiteTile[0] + 1;
			int col = whiteTile[1];
			if (row < currentState.getRow()) {
				int tmp = currentState.getTile(row, col);
				result.updateTile(row, col, 0);
				result.updateTile(whiteTile[0], whiteTile[1], tmp);
				result.setH(computeH2(result));
				return result;
		}}

		else if (operator == 'l') {
			/* Enter your code here */
			int row = whiteTile[0];
			int col = whiteTile[1] -1;
			if (col >= 0) {// Tile stands inside the map
				int tmp = currentState.getTile(row, col);
				result.updateTile(row, col, 0);
				result.updateTile(whiteTile[0], whiteTile[1], tmp);
				result.setH(computeH2(result));
				return result;
		}
		}
		else if (operator == 'r') {
			/* Enter your code here */
			int row = whiteTile[0];
			int col = whiteTile[1] + 1;
			if (col < currentState.getColumn()) {// Tile stands inside the map
//				System.out.println(currentState.getRow() + " so hang");
//				System.out.println(currentState.getColumn() + " so cot");
				int tmp = currentState.getTile(row, col);
				result.updateTile(row, col, 0);
				result.updateTile(whiteTile[0], whiteTile[1], tmp);
				result.setH(computeH2(result));
				return result;
			}
		}
		return null;
	}

	public List<Node> getSuccessors(Node currentState) {
		ArrayList<Node> result = new ArrayList<Node>();

		for (char operator : operators) {
			Node tmp = moveWhiteTile(currentState, operator);
			if (tmp != null) {
				result.add(tmp);
			}
		}

		return result;
	}

	public Node getInitialState() {
		return initialState;
	}

	public Node getGoalState() {
		return goalState;
	}
}
