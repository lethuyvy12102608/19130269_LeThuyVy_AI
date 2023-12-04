package lab9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();
	private List<Node> children = new ArrayList<Node>();
	private int value;

	Node bestMove;

	public Node getBestMove() {
		return bestMove;
	}

	public void setBestMove(Node bestMove) {
		this.bestMove = bestMove;
	}

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}
	

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		// Enter your code here
		if (children == null) {
			children = new ArrayList<Node>();
			for (Integer d : data) {
				for (int i = 1; i <= d / 2; i++) {
					if (i != d - i) {
						Node temp = new Node();
						temp.add(i);
						temp.add(d - i);
						temp.addAll(data);
						temp.data.remove((Integer) d);
						if (!children.contains(temp))
							children.add(temp);
					}
				}
			}
		}
		return children;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		// Enter your code here
//		return getSuccessors().size() == 0;
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.get(0) <= 2;

	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

}
