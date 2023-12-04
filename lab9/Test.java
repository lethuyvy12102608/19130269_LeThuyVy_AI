package lab9;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {

		Node node = new Node();
		Integer[] data = { 5, 2 };
		node.addAll(Arrays.asList(data));
		MinimaxAlgo m = new MinimaxAlgo();
		int v = m.minValue(node);
//		int v = m.maxValue(node);
		System.out.println(node.getBestMove());
	}

}
