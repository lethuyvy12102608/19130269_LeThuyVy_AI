package lab2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public interface ISearchAlgo {
	public Node execute(Node root, String goal);// find the path from root node to the goal node


	public Node execute(Node root, String start, String goal); // find the path from start node to the goal node
}
