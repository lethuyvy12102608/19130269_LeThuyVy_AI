package lab9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimaxAlgo {
	public void execute(Node node) {
		int v = minValue(node);
		System.out.println(v);
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	private List<Node> explored = new ArrayList<Node>();

	public int maxValue(Node node) {
		if (node.isTerminal())
			return 0;
		List<Node> childs = node.getSuccessors();
//		Collections.sort(childs);
		int v = Integer.MIN_VALUE;
		explored.add(node);
		for (Node n : childs) {

			int min = minValue(n);
			if (v < min) {
				v = min;
				node.setBestMove(n);
			}
		}
		return v;
	}

	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v
	public int minValue(Node node) {
		if (node.isTerminal())
			return 1;
		List<Node> childs = node.getSuccessors();
		int v = Integer.MAX_VALUE;
		explored.add(node);
		for (Node n : childs) {
			int max = maxValue(n);
			if (v > max) {
				v = max;
				node.setBestMove(n);
			}
		}
		return v;
	}
}
