package lab1;

import java.util.Random;

import lab1.Environment.LocationState;

public class TestTask2 {
	public static void main(String[] args) {
		Environment e = new Environment();
		LocationState[][] environment = new LocationState[4][1];

		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			environment[i][0] = random.nextBoolean() ? LocationState.CLEAN : LocationState.DIRTY;
		}

		int steps = 10;

	}

}
