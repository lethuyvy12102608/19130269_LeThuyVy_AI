package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Heuristic {
	public boolean isAdmissibleH(Node root, String goal) {
		if (heuristic(root, goal) < 0 || heuristic(new Node(goal), goal) != 0) {
			return false;
		}
		List<Node> explored = new ArrayList<Node>();

		PriorityQueue<Node> frontier = new PriorityQueue<>(
				Comparator.comparingDouble(node -> node.getH() + heuristic(node, goal)));
		explored.add(root);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);

			if (current.getState().equals(goal)) {
				if (current.getH() > heuristic(current, goal)) {
					return false;
				}
			}

			for (Node nodeChild : current.getChildrenNodes()) {
				if (!explored.contains(nodeChild)) {
					frontier.add(nodeChild);
				}
			}
		}
		return true;
	}

	public double heuristic(Node node, String goal) {
		int h = 0;
		for (int i = 0; i < node.getState().length(); i++) {
			char c1 = node.getState().charAt(i);
			char c2 = goal.charAt(i);
			h += Math.abs(c1 - c2);
		}

		return h;
	}

	public static void main(String[] args) {
		Node a = new Node("A", 22);
		Node b = new Node("B", 25);
		Node c = new Node("C", 20);
		Node d = new Node("D", 10);
		Node e = new Node("E", 6);
		Node f = new Node("F", 8);
		Node g1 = new Node("G1", 0);
		Node g2 = new Node("G2", 0);
		Node h = new Node("H", 16);
		Node k = new Node("K", 26);
		Node s = new Node("S", 30);

		a.addEdge(b, 8);
		a.addEdge(d, 15);
		b.addEdge(a, 7);
		b.addEdge(c, 11);
		c.addEdge(e, 12);
		d.addEdge(g2, 9);
		e.addEdge(g1, 7);
		f.addEdge(g1, 10);
		h.addEdge(f, 7);
		h.addEdge(s, 9);
		k.addEdge(a, 13);
		k.addEdge(h, 10);
		s.addEdge(k, 6);
		s.addEdge(b, 8);
		s.addEdge(c, 20);

	}
}