package lab6;

public class SA {
	public Node excute(Node initialState) {
		int T = 1000;
		Node current = initialState;
		Node next = null;
		while (current.getH() != 0) {
			next = current.selectNextRandomCandidate();
			int deltaE = next.getH() - current.getH();
			if (deltaE < 0) {
				current = next;
			} else {
				if (Math.exp(deltaE / T) > Math.random()) {
					current = next;
					T--;
				}
			}
		}
		return null;
	}

}
