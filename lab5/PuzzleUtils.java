package lab5;

import java.util.Comparator;

public class PuzzleUtils {

	public static int manhattanDistance(int[] current, int[] target) {
		return Math.abs(target[0] - current[0]) + Math.abs(target[1] - current[1]);
	}
	public static Comparator<Node> HeuristicComparatorByH = new Comparator<Node>() {

		@Override
		public int compare(Node a, Node b) {
			return a.getH() - b.getH();
		}
	};
	public static Comparator<Node> HeuristicComparatorByF = new Comparator<Node>() {

		@Override
		public int compare(Node a, Node b) {
			return a.getF() - b.getF();
		}
	};
}
