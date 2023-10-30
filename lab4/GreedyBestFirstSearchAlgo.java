package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node> explore = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(goal)) return current;
			if(!frontier.contains(current) && !explore.contains(current)) {
				explore.add(current);
				List<Edge> child = current.getChildren();
				for (Edge edge : child) {
					Node nodeChild = edge.getEnd();
					if(!frontier.contains(nodeChild) && !explore.contains(nodeChild)) {
						nodeChild.setParent(current);
						nodeChild.setG(current.getG() + edge.getWeight());
						frontier.add(nodeChild);
					}
				}
			}
		}
		return null;
	}
	
	public Node findStar(Node root, String start) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node> explore = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(start)) return current;
			if(!frontier.contains(current) && !explore.contains(current)) {
				explore.add(current);
				List<Edge> child = current.getChildren();
				for (Edge edge : child) {
					Node nodeChild = edge.getEnd();
					if(!frontier.contains(nodeChild) && !explore.contains(nodeChild)) {
						frontier.add(nodeChild);
						nodeChild.setParent(null);
						nodeChild.setG(0);
					}
				}
			}
			
			
		}
		return null;
	}
	
	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Node nodeStart = findStar(root, start);
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node> explore = new ArrayList<Node>();
		frontier.add(nodeStart);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(goal)) return current;
			if(!frontier.contains(current) && !explore.contains(current)) {
				explore.add(current);
				List<Edge> child = current.getChildren();
				for (Edge edge : child) {
					Node nodeChild = edge.getEnd();
					if(!frontier.contains(nodeChild) && !explore.contains(nodeChild)) {
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
