package lab6;

public class HillClimbingSearchAlgo implements IHillClimbingAlgo {
	int stepClimbed = 0;
	int stepClimbebAfterRandomRestart = 0;

	@Override
	public Node execute(Node initialState) {
		// TODO Auto-generated method stub
		Node current = new Node(initialState);
		while (true) {
			Node neighbor = current.getBestCandidate();
			if (neighbor.getH() < current.getH()) {
				current = neighbor;
				stepClimbed++;
				stepClimbebAfterRandomRestart++;
			} else
				return current;
		}
	}
}
