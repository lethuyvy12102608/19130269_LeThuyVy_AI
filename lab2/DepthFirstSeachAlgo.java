package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthFirstSeachAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<>();

		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			explored.add(current);
			System.out.println(current.getLabel() + " ");
			if (current.getLabel().equals(goal)) {
				return current;
			}
			List<Node> children = current.getChildrenNodes();
			for (int i = 0; i < children.size(); i++) {
				Node n = children.get(i);
				if (explored.contains(n) && frontier.contains(n)) {
					frontier.push(n);
					n.setParent(current);
				}
			}

		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		boolean started = false;

		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
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
				if (explored.contains(n) && frontier.contains(n)) {
					n.setParent(current);
					n.setPathCost(current.getPathCost());
					frontier.add(n);
				}
			}
		}
		return null;
	}

}
