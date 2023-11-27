package lab8;

import java.util.Collections;

public class AlphaBetaRightToLeftSearchAlgo implements ISearchAlgo {

	@Override
	public void execute(Node node) {
		int v = maxValue(node, Integer.MIN_VALUE, Integer.MIN_VALUE);
		System.out.println("Value: " + v);
	}

	private int maxValue(Node node, int alpha, int beta) {
		if (node.isTerminal()) {
			return node.getValue();
		}
		int maxUtility = Integer.MIN_VALUE;

		Collections.sort(node.getChildren(), Collections.reverseOrder(Node.LabelComparator));

		for (Node child : node.getChildren()) {
			int childUtility = minValue(child, alpha, beta);
			maxUtility = Math.max(maxUtility, childUtility);

			alpha = Math.max(alpha, maxUtility);

			if (beta <= alpha) {
				break;
			}
		}

		return maxUtility;
	}

	private int minValue(Node node, int alpha, int beta) {
		if (node.isTerminal()) {
			return node.getValue();
		}

		int minUtility = Integer.MAX_VALUE;

		Collections.sort(node.getChildren(), Collections.reverseOrder(Node.LabelComparator));

		for (Node child : node.getChildren()) {
			int childUtility = maxValue(child, alpha, beta);
			minUtility = Math.min(minUtility, childUtility);

			beta = Math.min(beta, minUtility);

			if (beta <= alpha) {
				break;
			}
		}

		return minUtility;
	}
}
