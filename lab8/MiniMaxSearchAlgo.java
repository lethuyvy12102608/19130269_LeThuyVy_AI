package lab8;

import java.util.Collections;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		node.setValue(maxValue(node));
		node.setValue(minValue(node));
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		if (node.isTerminal())
			return node.getValue();
		int max = Integer.MIN_VALUE;
		for (Node succsesor : node.getChildren()) {
			int valueSuccessor = minValue(succsesor);
			if (valueSuccessor > max) {
				max = valueSuccessor;
			}

		}
		node.setValue(max);
		return max;
	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		if (node.isTerminal())
			return node.getValue();
		int min = Integer.MAX_VALUE;
		for (Node successor : node.getChildren()) {
			int valueSuccessor = maxValue(successor);
			if (valueSuccessor < min) {
				min = valueSuccessor;
			}
		}
		node.setValue(min);
		return min;
	}
}
