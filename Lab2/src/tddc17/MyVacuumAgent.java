package tddc17;

import aima.core.environment.liuvacuum.*;
import aima.core.agent.Action;
import aima.core.agent.AgentProgram;
import aima.core.agent.Percept;
import aima.core.agent.impl.*;
import java.util.concurrent.LinkedBlockingQueue;

class MyAgentState
{
	public int[][] world = new int[20][20];
	public int initialized = 0;
	final int UNKNOWN 	= 0;
	final int WALL 		= 1;
	final int CLEAR 	= 2;
	final int DIRT		= 3;
	final int ACTION_NONE 		= 0;
	final int ACTION_MOVE_FORWARD 	= 1;
	final int ACTION_TURN_RIGHT 	= 2;
	final int ACTION_TURN_LEFT 		= 3;
	final int ACTION_SUCK	 		= 4;
	
	public int agent_x_position = 1;
	public int agent_y_position = 1;
	public int agent_last_action = ACTION_NONE;
	
	MyAgentState()
	{
		for (int i=0; i < world.length; i++)
			for (int j=0; j < world[i].length ; j++)
				world[i][j] = UNKNOWN;
		world[1][1] = CLEAR;
		agent_last_action = ACTION_NONE;
	}
	
	public void updateWorld(int x_position, int y_position, int info)
	{
		world[x_position][y_position] = info;
	}
	
	public void printWorldDebug()
	{
		for (int i=0; i < world.length; i++)
		{
			for (int j=0; j < world[i].length ; j++)
			{
				if (world[j][i]==UNKNOWN)
					System.out.print(" ? ");
				if (world[j][i]==WALL)
					System.out.print(" # ");
				if (world[j][i]==CLEAR)
					System.out.print(" . ");
				if (world[j][i]==DIRT)
					System.out.print(" D ");
			}
			System.out.println("");
		}
	}
}

class MyAgentProgram implements AgentProgram {

	// Here you can define your variables!
	public int iterationCounter = 200;
	public MyAgentState state = new MyAgentState();
	boolean bump;
	boolean dirt;
	boolean home;
	int virtualX; //statevar that will be used for pathFinding
	int virtualY; //statevar that will be used for pathFinding
	int homeX;
	int homeY;
	private short turningDirection = 2;
	public final short WEST = 0;
	public final short NORTH = 1;
	public final short EAST = 2;
	public final short SOUTH = 3;
	public LinkedBlockingQueue<Action> actionQueue = 
		new LinkedBlockingQueue<Action>();
	
	/**
	* DONT TOUCH, this function has been abstracted into makeDecision so that
	* we can place a lot of decisions after each other.
	*/
	@Override 
	public Action execute(Percept percept) {

	    DynamicPercept p = (DynamicPercept) percept;
	    bump = (boolean)p.getAttribute("bump");
	    dirt = (boolean)p.getAttribute("dirt");
	    home = (boolean)p.getAttribute("home");

		state.updateWorld(state.agent_x_position, state.agent_y_position, 
							state.CLEAR);

		//always suck if dirt, dont move if iterationCounter is 0
		if (dirt) {
			return LIUVacuumEnvironment.ACTION_SUCK;
		}
		
		//do everything in actionQueue
		if (!actionQueue.isEmpty()) {
			return actionQueue.poll();
		}

		//remember home if there for first time, else stop if finished
		if(home) {
			homeX = currentX();
			homeY = currentY();
		}
		makeDecision();

		//nice comment that should explain why this recursive call is needed,
		//but for now, just trust me
		return execute(percept);
	}

	void makeDecision() {
		circleAlgorithm();
	}

	/**
	* kinda like nextY()
	*/
	int nextX() {
		return state.agent_x_position + ((turningDirection - 1) % 2);
	}

	/**
	* -1 so that we get the correct index in the array for the room, then we do 
	* turningDirection -2 so that we get -1 or +1. Modulus 2 so that west and 
	* east becomes zero.
	*/
	int nextY() {
		return state.agent_y_position + ((turningDirection - 2) % 2);
	}

	int currentX() {
		return state.agent_x_position;
	}

	int currentY() {
		return state.agent_y_position;
	}

	void turnLeft() {
		try {
			--turningDirection;
			turningDirection %= 4;
			actionQueue.put(LIUVacuumEnvironment.ACTION_TURN_LEFT);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
		
	void turnRight() {
		try {
			++turningDirection;
			turningDirection %= 4;
			actionQueue.put(LIUVacuumEnvironment.ACTION_TURN_RIGHT);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	void goForward() {
		try{
			switch(turningDirection) {
				case WEST:
					--state.agent_x_position;break;
				case NORTH:
					--state.agent_y_position;break;
				case EAST:
					++state.agent_x_position;break;
				case SOUTH:					
					++state.agent_y_position;break;
			}
			actionQueue.put(LIUVacuumEnvironment.ACTION_MOVE_FORWARD);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	void stop() {		
		try{
			actionQueue.put(NoOpAction.NO_OP);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	void goWest() {
		switch(turningDirection) {
				case WEST:
					goForward();
					break;
				case NORTH:
					turnLeft();
					goForward();
					break;
				case EAST:
					turnLeft();
					turnLeft();
					goForward();
					break;
				case SOUTH:	
					turnRight();
					goForward();
			}
	}

	
	void goEast() {
		switch(turningDirection) {
				case EAST:
					goForward();
					break;
				case SOUTH:
					turnLeft();
					goForward();
					break;
				case WEST:
					turnLeft();
					turnLeft();
					goForward();
					break;
				case NORTH:	
					turnRight();
					goForward();
			}
	}

	
	void goNorth() {
		switch(turningDirection) {
				case NORTH:
					goForward();
					break;
				case EAST:
					turnLeft();
					goForward();
					break;
				case SOUTH:
					turnLeft();
					turnLeft();
					goForward();
					break;
				case WEST:	
					turnRight();
					goForward();
			}
	}

	void goSouth() {
		switch(turningDirection) {
				case SOUTH:
					goForward();
					break;
				case WEST:
					turnLeft();
					goForward();
					break;
				case NORTH:
					turnLeft();
					turnLeft();
					goForward();
					break;
				case EAST:	
					turnRight();
					goForward();
			}
	}
	
	void clear() {		
		try{
			actionQueue.clear();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	boolean beenVisited(int x, int y) {
		return state.world[x][y] != 0;
	}

	boolean surroundedByVisited(int x, int y) {
		return beenVisited(x-1,y) && beenVisited(x+1,y) && beenVisited(x,y-1) &&
			beenVisited(x,y+1);
	}

	boolean isBumping() {
		if(bump){
			switch(turningDirection) {
				case WEST:
					++state.agent_x_position;break;
				case NORTH:
					++state.agent_y_position;break;
				case EAST:
					--state.agent_x_position;break;
				case SOUTH:					
					--state.agent_y_position;
			}
		state.updateWorld(state.agent_x_position, state.agent_y_position, 
							state.WALL);
		}
		return bump;
	}

	void findPathTo(int x, int y) {
		//TO-DO change so that we can handle a path to all quadrants
		for(int i=currentX(); i > x; --i){
			goWest();
		}
		
		for(int i=currentY(); i > y; --i){
			goNorth();
		}
	}
	
	void circleAlgorithm() {
		if (isBumping()) {
			turnRight();
		} else if(surroundedByVisited(currentX(), currentY())) { 
			findPathTo(homeX, homeY);
			stop();
		} else if (beenVisited(nextX(), nextY())) {			
			turnRight();
		} else {
			goForward();
		}
	}
	
	/**
	* Go to closest unknown that cost the least. /care now
	*/
	void greedyAlgorithm() {
		if(state.world[nextX()][nextY()] == state.UNKNOWN){
		//	if(turningDirection == WEST) {
		//	} else if(turningDirection == EAST) {
		//	} else if(turningDirection == WEST){}
			goForward();
		} else {
			++turningDirection;
		}
	}
	
}

public class MyVacuumAgent extends AbstractAgent {
    public MyVacuumAgent() {
    	super(new MyAgentProgram());
	}
}
