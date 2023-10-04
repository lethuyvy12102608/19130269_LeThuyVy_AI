package lab1;

import lab1.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		//TODO
		if(p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		} else if(p.getAgentLocation().equals(Environment.LOCATION_A)) {
			return Environment.MOVE_RIGHT;
		} else if(p.getAgentLocation().equals(Environment.LOCATION_B)) {
			return Environment.MOVE_RIGHT;
		}
		return NoOpAction.NO_OP;
	}
	
}