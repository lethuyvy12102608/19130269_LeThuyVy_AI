package lab4;

import java.util.Comparator;

public class NodeComparatorToGn implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		double node1 = o1.getG() + o1.getH();
		double node2 = o2.getG() + o2.getH();
		double result = node1 - node2;
		if (result == 0) {
			return o1.getLabel().compareTo(o2.getLabel());
		}
		if (result > 0)
			return 1;
		return -1;
	}

}
