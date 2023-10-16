package lab2;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		Node nodeS = new Node("S");
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		Node nodeG = new Node("G");
		Node nodeH = new Node("H");

		nodeS.addEdge(nodeA, 5);
		nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4);
		nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4);
		nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2);
		nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6);
		nodeF.addEdge(nodeG, 1);
		
		// BFS
		ISearchAlgo algo1 = new BreadthFirstSearch();
		Node result1 = algo1.execute(nodeS, "G");
		List<String> path = NodeUtils.printPath(result1);
		System.out.println("Result of BFS: " + path);
		
		Node result2 = algo1.execute(nodeS, "B", "G");
		List<String> path1 = NodeUtils.printPath(result2);
		System.out.println("Result of BFS with any starting node: ");
		System.out.print(path1);
		
		// DFS
		ISearchAlgo algo2 = new DepthFirstSeachAlgo();
		Node result3 = algo2.execute(nodeS, "G");
		List<String> path3 = NodeUtils.printPath(result3);
		System.out.println("Result of DFS: ");
		System.out.println(path3 + " = " + result3.getPathCost());

		Node result4 = algo2.execute(nodeS, "C", "G");
		List<String> path4 = NodeUtils.printPath(result4);
		System.out.println("Result of DFS with any starting node: ");
		System.out.println(path4 + " = " + result4.getPathCost());
		
		//UCS
		ISearchAlgo algo3 = new UniformCostSearchAlgo();
		Node result5 = algo3.execute(nodeS, "G");
		List<String> path5 = NodeUtils.printPath(result5);
		System.out.println("- Ket qua duyet UCS: ");
		System.out.println(path5 + " = " + result5.getPathCost());

		Node result6 = algo3.execute(nodeS, "A", "G");
		List<String> path6 = NodeUtils.printPath(result6);
		System.out.println("- Ket qua duyet UCS: ");
		System.out.println("\t" + path4 + " = " + result6.getPathCost());

//		DFS limited
		DepthLimitedSearch algo4 = new DepthLimitedSearch();
		Node result7 = algo4.execute(nodeS, "G", 3);
		List<String> path7 = NodeUtils.printPath(result7);
		System.out.println("- Duyet DFS co gioi han: ");
		System.out.println(path7 + " = " + result7.getPathCost());
	}
}
