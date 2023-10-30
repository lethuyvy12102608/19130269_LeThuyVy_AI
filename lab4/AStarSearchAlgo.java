package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorToGn());
		List<Node> explore = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			if (!frontier.contains(current) && !explore.contains(current)) {
				explore.add(current);
				List<Edge> child = current.getChildren();
				for (Edge edge : child) {
					Node nodeChild = edge.getEnd();
					if (!frontier.contains(nodeChild) && !explore.contains(nodeChild)) {
						nodeChild.setParent(current);
						nodeChild.setG(current.getG() + edge.getWeight());
						frontier.add(nodeChild);
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Node nodeStart = new GreedyBestFirstSearchAlgo().findStar(root, start);
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorToGn());
		List<Node> explore = new ArrayList<Node>();
		frontier.add(nodeStart);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			if (!frontier.contains(current) && !explore.contains(current)) {
				explore.add(current);
				List<Edge> child = current.getChildren();
				for (Edge edge : child) {
					Node nodeChild = edge.getEnd();
					if (!frontier.contains(nodeChild) && !explore.contains(nodeChild)) {
						nodeChild.setParent(current);
						nodeChild.setG(current.getG() + edge.getWeight());
						frontier.add(nodeChild);
					}
				}
			}
		}
		return null;
	}

}
