package lab6;

public class Test {
	public static void main(String[] args) {
		// hillclimbingsearch
		Node initialState = new Node();
		initialState.generateBoard();
		HillClimbingSearchAlgo algo = new HillClimbingSearchAlgo();

		Node goalState = algo.execute(initialState);
		goalState.displayBoard();
		System.out.println("Heuristic: " + goalState.getH());

		
		//HillClimbingWithRandomRestart
		Node initialState1 = new Node();
		initialState1.generateBoard();
		HillClimbingWithRamdonRestartAlgo algo1 = new HillClimbingWithRamdonRestartAlgo();

		Node goalState1 = algo1.execute(initialState1);
		goalState1.displayBoard();
		System.out.println("Heuristic: " + goalState1.getH());
		
		//QA
		Node currentState2 = new Node();
		currentState2.generateBoard();
		SA_NQueenAlgo algo2 = new SA_NQueenAlgo();

		Node goalState2 = algo2.execute(currentState2);
		goalState2.displayBoard();
		System.out.println("Heuristic: " + goalState2.getH());
	}

}
