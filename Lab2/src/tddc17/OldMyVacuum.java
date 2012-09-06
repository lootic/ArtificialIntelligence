/*
	    // State update based on the percept value and the last action
	    if (state.agent_last_action==state.ACTION_MOVE_FORWARD)
	    {
	    	if (!bump)
	    	{
	    		state.agent_x_position++;
	    	} else
	    	{
	    		state.updateWorld(state.agent_x_position+1,state.agent_y_position,state.WALL);
	    	}
	    }
	    if (dirt)
	    	state.updateWorld(state.agent_x_position,state.agent_y_position,state.DIRT);
	    else
	    	state.updateWorld(state.agent_x_position,state.agent_y_position,state.CLEAR);
	    
	    state.printWorldDebug();
	    
	    
	    // Next action selection based on the percept value
	    if (dirt)
	    {
	    	System.out.println("DIRT -> choosing SUCK action!");
	    	state.agent_last_action=state.ACTION_SUCK;
	    	return LIUVacuumEnvironment.ACTION_SUCK;
	    } 
	    else
	    {
	    	if (bump)
	    	{
	    		state.agent_last_action=state.ACTION_NONE;
		    	return NoOpAction.NO_OP;
	    	}
	    	else
	    	{
	    		state.agent_last_action=state.ACTION_MOVE_FORWARD;
	    		return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
	    	}
	    }*/
