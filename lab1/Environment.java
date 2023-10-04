package lab1;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public static final int SUCK_REWARD = 500;
	public static final int CANT_MOVE_PENALTY = -100;
	public static final int OTHER_ACTION_PENALTY = -10;

	public enum Location {
		A, B, C, D
	}
	
    public enum Action1 {
        SUCK_, LEFT_, RIGHT_, UP_, DOWN_;
    }

	private int score;

	public enum LocationState {
		CLEAN, DIRTY
	}

	private Location currentLocation;

	public Environment() {
		// Initialize the agent at a random location
		Random random = new Random();
		currentLocation = Location.values()[random.nextInt(4)];
		score = 0;
	}

	public int getScore() {
		return score;
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		// TODO
		if (action == SUCK_DIRT) {
			String agentLocation = envState.getAgentLocation();
			envState.setLocationState(agentLocation, LocationState.CLEAN);
		} else if (action == MOVE_LEFT) {
			String agentLocation = envState.getAgentLocation();
			if (agentLocation.equals(LOCATION_B)) {
				envState.setAgentLocation(LOCATION_A);
			}

		} else if (action == MOVE_RIGHT) {
			String agentLocation = envState.getAgentLocation();
			if (agentLocation.equals(LOCATION_A)) {
				envState.setAgentLocation(LOCATION_B);
			}
		}
		return envState;
	}

	public EnvironmentState excuteAction4Move(LocationState[] environment) {
		  LocationState currentState = environment[currentLocation.ordinal()];

		if (currentState == LocationState.DIRTY) {
			environment[currentLocation.ordinal()] = LocationState.CLEAN;
			score += SUCK_REWARD;
		} else {
			Action1 action = getRandomAction();

			switch (action) {
			case LEFT_:
				if (currentLocation != Location.A) {
					currentLocation = Location.values()[currentLocation.ordinal() - 1];
				} else {
					score += CANT_MOVE_PENALTY;
				}
				break;
			case RIGHT_:
				if (currentLocation != Location.D) {
					currentLocation = Location.values()[currentLocation.ordinal() + 1];
				} else {
					score += CANT_MOVE_PENALTY;
				}
				break;
			case UP_:
				if (currentLocation == Location.C || currentLocation == Location.D) {
					currentLocation = Location.values()[currentLocation.ordinal() - 2];
				} else {
					score += CANT_MOVE_PENALTY;
				}
				break;
			case DOWN_:
				if (currentLocation == Location.A || currentLocation == Location.B) {
					currentLocation = Location.values()[currentLocation.ordinal() + 2];
				} else {
					score += CANT_MOVE_PENALTY;
				}
				break;
			default:
				score += OTHER_ACTION_PENALTY;
				break;
			}
		}
		score += OTHER_ACTION_PENALTY;
		return envState;
	}

	private Action1 getRandomAction() {
		Random random = new Random();
		return Action1.values()[random.nextInt(5)];
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		String agentLocation = envState.getAgentLocation();
		LocationState state = envState.getLocationState(agentLocation);
		return new Percept(agentLocation, state);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
