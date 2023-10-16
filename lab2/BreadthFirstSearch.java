package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			explored.add(currentNode);
			System.out.println(currentNode.getLabel() + " ");
			if (currentNode.getLabel().equals(goal)) {
				return currentNode;
			}
			List<Node> children = currentNode.getChildrenNodes();
			for (Node child : children) {
				if (!frontier.contains(child) && !explored.contains(child)) {
					frontier.add(child);
					child.setParent(currentNode);
				}

			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		boolean started = false;

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			System.out.println(current.getLabel() + "\t");
			if (current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				explored.clear();
			} else if (current.getLabel().equals(goal) && started) {
				return current;
			}
			List<Node> children = current.getChildrenNodes();
			for (int i = 0; i < children.size(); i++) {
				Node n = children.get(i);
				if(explored.contains(n) && frontier.contains(n)) {
					n.setParent(current);
					n.setPathCost(current.getPathCost());
					frontier.add(n);
				}
			}

		}

		return null;
	}

}