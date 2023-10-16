package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {

		@Override
		public Node execute(Node root, String goal) {
			PriorityQueue<Node> frontier = new PriorityQueue<Node>();
			ArrayList<Node> explored = new ArrayList<Node>();
			frontier.add(root);

			while (!frontier.isEmpty()) {
				Node current = frontier.poll();
				explored.add(current);
				if (current.getLabel().equals(goal)) {
					return current;
				}
				List<Edge> children = current.getChildren();
				Collections.sort(children);
				for (Edge child : children) {
					Node node = child.getEnd();
					double newPathCost = current.getPathCost() + child.getWeight();
					if (!explored.contains(node) && !frontier.contains(node)) {
						node.setPathCost(newPathCost);
						node.setParent(current);

						frontier.add(node);
					}
					if (frontier.contains(node)) {
						if (node.getPathCost() >= newPathCost) {
							node.setParent(current);
							node.setPathCost(newPathCost);
						}
					}
				}
			}
			return null;

		}

		@Override
		public Node execute(Node root, String start, String goal) {
			PriorityQueue<Node> frontier = new PriorityQueue<Node>();
			ArrayList<Node> explored = new ArrayList<Node>();
			frontier.add(root);
			boolean started = false;
			while (!frontier.isEmpty()) {
				Node current = frontier.poll();
				explored.add(current);
				if (current.getLabel().equals(start)) {
					started = true;
					frontier.clear();
					current.setParent(null);
					current.setPathCost(0);
				}
				if (current.getLabel().equals(goal) && started) {
						return current;
				}
				List<Edge> children = current.getChildren();
				Collections.sort(children);
				for (Edge child : children) {
					Node node = child.getEnd();
					double newPathCost = current.getPathCost() + child.getWeight();
					if (!explored.contains(node) && !frontier.contains(node)) {
						node.setPathCost(newPathCost);
						node.setParent(current);

						frontier.add(node);
					}
					if (frontier.contains(node)) {
						if (node.getPathCost() >= newPathCost) {
							node.setParent(current);
							node.setPathCost(newPathCost);
						}
					}
				}
			}
			return null;
		}

}
