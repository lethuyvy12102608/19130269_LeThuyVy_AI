package lab6;

public class HillClimbingWithRamdonRestartAlgo implements IHillClimbingAlgo {

	int randomRestarts = 0;

	@Override
	public Node execute(Node initialState) {
		// TODO Auto-generated method stub
		HillClimbingSearchAlgo algo = new HillClimbingSearchAlgo();
		Node state = algo.execute(initialState);

		while (state.getH() != 0) {
			state = new Node();
			state.generateBoard();
			randomRestarts++;
			algo.stepClimbebAfterRandomRestart = 0;
			state = algo.execute(state);
		}
		System.out.println("Step climbeb: " + algo.stepClimbed);
		System.out.println("Number restart: " + randomRestarts);
		System.out.println("Step climbeb after random restart: " + algo.stepClimbebAfterRandomRestart);

		return state;
	}

}
