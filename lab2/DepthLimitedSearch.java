package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthLimitedSearch {
	public Node execute(Node root, String goal, int limitedPath) {
		Stack<Node> frontier = new Stack<Node>();
		ArrayList<Node> explored = new ArrayList<Node>();

		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			explored.add(current);
			if (current.getLabel().equals(goal)) {
				return current;
			}
			if (current.getDepth() < limitedPath) {
				List<Edge> children = current.getChildren();
//				Collections.sort(children);
				for (Edge e : children) {
					Node child = e.getEnd();
					if (!explored.contains(child) && !frontier.contains(child)) {
						child.setParent(current);
						child.setDepth(current.getDepth() + 1);
						child.setPathCost(current.getPathCost() + e.getWeight());
						frontier.add(child);
					}
				}
			}
			System.out.println("- Frontier limited DFS: " + frontier);
		}
		return null;
	}
}
