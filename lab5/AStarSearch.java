package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearch implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
		List<Node> explore = new ArrayList<Node>();
		frontier.add(model.getInitialState());
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			System.out.println(current);
			if(current.equals(model.getGoalState())) return current;
			if(!frontier.contains(current) && !explore.contains(current)) {
				explore.add(current);
				List<Node> child = model.getSuccessors(current);
				for (Node node : child) {
					int pathCost= current.getG() + 1;
					if(!frontier.contains(node) && !explore.contains(node)) {
						frontier.add(node);
						node.setG(pathCost);
					}
				}
			}
		}
		return null;
	}

}
