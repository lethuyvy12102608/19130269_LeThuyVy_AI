package lab6;

public class SA_NQueenAlgo implements IHillClimbingAlgo {

	@Override
	public Node execute(Node initialState) {
		// TODO Auto-generated method stub
		double T = 1000.0;
		double coolingRate = 0.995;
		Node current = new Node(initialState);

		while (current.getH() != 0) {
			Node next = current.selectNextRandomCandidate();
			int deltaE = next.getH() - current.getH();
			if (deltaE < 0) {
				current = new Node(next);
			} else if (Math.exp(deltaE / T) > Math.random()) {
				current = new Node(next);

			}
			T *= coolingRate;
		}
		System.out.println("T: " + T);
		return current;
	}
}
