package tddc17;


import aima.core.environment.liuvacuum.*;
import aima.core.agent.Action;
import aima.core.agent.AgentProgram;
import aima.core.agent.Percept;
import aima.core.agent.impl.*;

import java.util.Random;

public class RandomVacuumAgent extends AbstractAgent {
    public RandomVacuumAgent() {
	super(new AgentProgram() {
		public int iterationCounter = 100;
		public Action execute(Percept percept) {
		    Random r = new Random();

		    iterationCounter--;

		    if (iterationCounter==0)
			return NoOpAction.NO_OP;

		    DynamicPercept p = (DynamicPercept) percept;
		    Boolean bump = (Boolean)p.getAttribute("bump");
		    Boolean dirt = (Boolean)p.getAttribute("dirt");
		    Boolean home = (Boolean)p.getAttribute("home");
		    System.out.println("percept: " + p);
		    if (dirt.booleanValue())
			return LIUVacuumEnvironment.ACTION_SUCK;
		    else {
			int action = r.nextInt(8);
			if(action==0)
			    return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			else if (action==1)
			    return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			else if (action>=2)
			    return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
		    }
		    return NoOpAction.NO_OP;
		}
	    });
    }
}
