/******************************************************
 *
 * Project: javaRL
 * File: Agent.java
 * @version 1.0
 * Date: 04.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description:
 * 
 *  
 ******************************************************/
package main;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import searchCustom.CustomBreadthFirstSearch;
import searchCustom.CustomDepthFirstSearch;
import searchHidden.AStarSearch;
import searchHidden.BreadthFirstSearch;
import searchHidden.DepthFirstSearch;
import searchHidden.GraphSearch;
import searchHidden.GridHeuristicOrdering;
import searchHidden.IterativeDepthFirstSearch;
import searchShared.SearchNode;
import searchShared.SearchObject;

import world.GridPos;
import world.Dirt;
import world.World;

public class Agent {
	// The maximal search depth
    public static final int MAX_DEPTH = (int) 1e6;

    public static final int BREADTH_FIRST_SEARCH = 0;
    public static final int DEPTH_FIRST_SEARCH = 1;
    public static final int ITERATIVE_DEEPENING_SEARCH = 2;
    public static final int ASTAR_SEARCH = 3;
    public static final int CUSTOM_BREADTH_FIRST_SEARCH = 4;
    public static final int CUSTOM_DEPTH_FIRST_SEARCH = 5;
    public static final int NUM_SEARCH = 6;

    // This class represents a GRID planning problem 
    private class GridSearchProblem extends searchShared.Problem {
        private World m_World;
        private int m_NumExpandedNodes;
        public GridSearchProblem(World world, GridPos initialState, GridPos goalState) {
            m_World = world;
            m_GoalState = goalState;
            m_InitialState = initialState;
            m_NumExpandedNodes = 0;
        }
        public ArrayList<GridPos> getReachableStatesFrom(GridPos aState) {
            GridPos state = (GridPos) aState;
            ArrayList l = m_World.getReachableStatesFrom(state);
            m_NumExpandedNodes += l.size();
            return l;
        }
        public boolean isGoalState(GridPos aState) {
            if (m_GoalState.equals((GridPos) aState)) {
                return true;
            }
            return false;
        }
        public GridPos getInitialState() {
            return (GridPos) m_InitialState;
        }
        public void resetNumExpNodes() {
            m_NumExpandedNodes = 0;
        }
        public void setInitialState(GridPos initialState) {
            this.m_InitialState = (GridPos) initialState;
        }
        public int getNumExpandedNodes() {
            return m_NumExpandedNodes;
        }
    }

    public World m_World;
    private int m_SearchMethod = ASTAR_SEARCH;
    private GridPos m_Target = null;
    private ArrayList<SearchNode> m_currentPath = new ArrayList<SearchNode>();
    private int m_currentPathIdx = 0;

    
    public Agent(World world) {
        m_World = world;
        m_Target = (Dirt) world.m_Dirt.toArray()[0];
        m_Target = null;
    }

    public void setSearchMethod(int method) {
        if (method < 0 || method+1 > NUM_SEARCH) {
            System.err.println("Agent: Bad search method selected!");
            m_SearchMethod = BREADTH_FIRST_SEARCH;
        }
        else
            m_SearchMethod = method;
        System.out.println("Agent: Changed Search Method to " + method);
    }

    public int getSearchMethod() {
        return m_SearchMethod;
    }

    public void step(World realWorld) {
        if (realWorld.isTerminated()) {
            System.out.println("There are no more targets in this world!");
            return;
        }
        byte action = World.NO_OPERATION;

        // 1. Set Senses, i.e. to update the internal WM (m_World)
        m_World.setSenses(realWorld.getSenses());
        // 2. Cleverly select an action
        action = chooseAction();
        // 3. Execute Action in the real world
        act(action, realWorld);
    }

    public byte chooseAction() {
    	byte action;
    	
        // Are we at the dirt's location?
        if (m_Target != null && m_World.m_Agent.atPos(m_Target)) {
        	Dirt dirt = m_World.getDirtAtPos(m_Target);
        	if (dirt == null) {
        		// User manually removed it
            	m_Target = null;	
        		return World.NO_OPERATION;
        	} else {
            	// Suck dirt
            	m_Target = null;	
        		return World.SUCK_DIRT;
        	}
        }

        // Do we need to select a new target dirt?
    	GridPos target = selectTarget(m_Target);
    	if (target == null) {
    		System.out.println("Agent: No more dirt, the maze is shining clean!");
    		return World.NO_OPERATION;
    	} else {
    		// New target needs new plan 
    		if (target != m_Target) {
    			m_Target = target;
    			resetPlan();
    		}
    	}

    	if (m_currentPath.size() == 0) {
			ArrayList<SearchNode> path = planToTarget(m_Target, m_World);
			m_currentPath = path;
			m_currentPathIdx = 0;
    	}
    		
    	// If we still have no path, the target must be unreachable
		if (m_currentPath.size() == 0) {
			System.out.println("Agent: Target unreachable!.");
			return World.NO_OPERATION;
		} else {
			// Follow the plan
			action = stepToTarget(m_currentPath, m_World);
		}
		return action;
    }
    
    public void act(byte Action, World realWorld) {
        switch (Action) {
            case World.SUCK_DIRT :
            	System.out.println("Agent: Vacuuming Dirt");
            	realWorld.suck();
                break;
            case World.GO_NORTH :
                realWorld.moveAgent(World.GO_NORTH);
                break;
            case World.GO_SOUTH :
                realWorld.moveAgent(World.GO_SOUTH);
                break;
            case World.GO_EAST :
                realWorld.moveAgent(World.GO_EAST);
                break;
            case World.GO_WEST :
                realWorld.moveAgent(World.GO_WEST);
                break;
            case World.NO_OPERATION :
                System.out.println("Agent: NO-OP Action: " + Action);
                //m_World.update();
                break;
            default :
                System.out.println("Agent: Unknown Action: " + Action);
        }
		m_World.setSenses(realWorld.getSenses());
    }

    /*******************************************************************************
     *******************************************************************************
     **  selectTarget
     **	 This function selects the closest dirt 
     **  @return the selected Target
     **
     *******************************************************************************
     *******************************************************************************/
    public GridPos selectTarget(GridPos lastTarget) {
        int numTargets = m_World.m_Dirt.toArray().length;
        GridPos target = null;
        boolean DEBUG = false;

        // No target left?
        if (numTargets == 0)
        	return null;

        if (lastTarget == null) {
        	// else select closest dirt (air distance) 
        	double bestDist = Double.MAX_VALUE;
        	for (int i = 0; i < numTargets; i++) {
        		Dirt dirt = (Dirt) m_World.m_Dirt.toArray()[i];
        		double dist = m_World.m_Agent.getDistanceEuclidian(dirt);
        		if (dist < bestDist) {
        			bestDist = dist;
        			target = dirt;
        		}
        	}
        }
        else {
        	target = lastTarget;
        }

        GridPos pos = m_World.m_Maze.getGridObject(target);
        return pos;
    }

    /*******************************************************************************
     *******************************************************************************
     **  stepToTarget
     **  Makes one step in the world towards the destination
     **	 The agent plans within its own world (m_World) but executes in the real world
     **  @param path - path to the destination
     **  @param world - the world
     **  @return 
     **
     *******************************************************************************
     *******************************************************************************/
    public byte stepToTarget(ArrayList<SearchNode> path, World world) {

        // Execute the plan. 
        if (path == null || path.size() == 0) {
        	System.err.println("Agent: NO PATH FOUND!");
        } else {

        	SearchNode n = path.get(m_currentPathIdx);
        	m_currentPathIdx++;

        	GridPos nextPos = (GridPos) n.getState();

        	int dx = nextPos.m_x - world.m_Agent.m_x;
        	int dy = nextPos.m_y - world.m_Agent.m_y;

        	// Note that we know which action to take due to known grid adjacency.
        	if (Math.abs(dx) > Math.abs(dy)) {
        		if (dx > 0) {
        			return World.GO_EAST;
        		}
        		else if (dx < 0) {
        			return World.GO_WEST;
        		}
        		else
        			return World.NO_OPERATION;
        	}
        	else {
        		if (dy < 0) {
        			return World.GO_NORTH;
        		}
        		else if (dy > 0) {
        			return World.GO_SOUTH;
        		}
        		else
        			return World.NO_OPERATION;
        	}

        }
        return World.NO_OPERATION;
    }

    public ArrayList<SearchNode> planToTarget(GridPos dest, World world) {
        GridPos start = (GridPos) world.m_Agent;
        GridPos goal = (GridPos) dest;

        if (start == null || goal == null)
            return null;

        System.out.println("Agent: planning from " + start + " to " + goal);

        SearchObject searchRun = searchPlan(world, start, goal, m_SearchMethod, true);

        ArrayList<SearchNode> path = searchRun.getPath();
    
        // Update path graphics in world
        if (path != null) {
        	ArrayList<GridPos> pList = new ArrayList<GridPos>();
        	for (SearchNode n : path) {
                // Extract state
        		pList.add(n.getState());
        	}
        	world.markCurrentPath(pList);
        }

        // Update explored state graphics in world
    	ArrayList<GridPos> eList = new ArrayList<GridPos>();
    	for (SearchNode n : searchRun.getAllExpandedNodes()) {
    		// Extract state
    		eList.add(n.getState());
    	}
        world.markExpandedNodes(eList);
        
        return path;
    } 
    
    public void resetPlan() {
    	// Resets the plan, forcing the agent to re-plan.
		m_currentPath = new ArrayList<SearchNode>();
		m_currentPathIdx = 0;
    }

    /*******************************************************************************
     *******************************************************************************
     **  searchPlan
     **  This function returns a plan from the start node to the goal node as an arry list.
     **  The method for planning can be defined by method.
     **  @param world - the world to plan on
     **  @param start - the start node
     **  @param goal - the goal node
     **  @param method - the method for planning
     **  @param debug - a debug value 
     **  @return 
     **
     *******************************************************************************
     *******************************************************************************/
    public SearchObject searchPlan(World world, GridPos start, GridPos goal, int method, boolean printResult) {
        long time;
        GridSearchProblem myProblem = null;

        switch (method) {
            case BREADTH_FIRST_SEARCH :
                {
                    if (printResult)
                        System.out.println("Agent: starting Breath First Search Method (BFS)");
                    BreadthFirstSearch breadthSearchRun = new BreadthFirstSearch(MAX_DEPTH);
                    time = (new Date()).getTime();
                    myProblem = new GridSearchProblem(world, start, goal);
                    ArrayList breadthPlan = breadthSearchRun.search(myProblem);
                    time -= (new Date()).getTime();
                    if (printResult)
                        System.out.println(
                            "\tNeeded "
                                + (-time)
                                + " msec, PathLength:  "
                                + breadthPlan.size()
                                + ", NumExpNodes: "
                                + myProblem.getNumExpandedNodes());
                    return breadthSearchRun;
                }
            case DEPTH_FIRST_SEARCH :
                {
                    if (printResult)
                        System.out.println("Agent: starting Depth First Search Method (DFS)");
                    DepthFirstSearch depthSearchRun = new DepthFirstSearch(MAX_DEPTH);
                    time = (new Date()).getTime();
                    myProblem = new GridSearchProblem(world, start, goal);
                    ArrayList depthPlan = depthSearchRun.search(myProblem);
                    time -= (new Date()).getTime();
                    if (printResult)
                        System.out.println("\tNeeded " + (-time) + " msec, PathLength: " + depthPlan.size());
                    return depthSearchRun;
                }
            case ITERATIVE_DEEPENING_SEARCH :
                {
                    if (printResult)
                        System.out.println("Agent: starting Iterative Deepening Search Method (IDS)");
                    IterativeDepthFirstSearch iterativeDeepeningRun = new IterativeDepthFirstSearch(MAX_DEPTH);
                    time = (new Date()).getTime();
                    myProblem = new GridSearchProblem(world, start, goal);
                    ArrayList deepeningPlan = iterativeDeepeningRun.search(myProblem);
                    time -= (new Date()).getTime();
                    if (printResult)
                        System.out.println("\tNeeded " + (-time) + " msec, PathLength:  " + deepeningPlan.size());
                    return iterativeDeepeningRun;
                }
            case ASTAR_SEARCH :
                {
                    if (printResult)
                        System.out.println("Agent: starting AStar Search Method");
                    GridHeuristicOrdering heuristic = new GridHeuristicOrdering();
                    heuristic.setGoalState(goal);
                    AStarSearch aStarSearchRun = new AStarSearch(MAX_DEPTH, heuristic);
                    time = (new Date()).getTime();
                    myProblem = new GridSearchProblem(world, start, goal);
                    ArrayList astarPlan = aStarSearchRun.search(myProblem);
                    time -= (new Date()).getTime();
                    if (printResult)
                        System.out.println(
                            "\tNeeded "
                                + (-time)
                                + " msec , PathLength: "
                                + astarPlan.size()
                                + ", NumExpNodes: "
                                + myProblem.getNumExpandedNodes());
                    return aStarSearchRun;
                }
            case CUSTOM_BREADTH_FIRST_SEARCH :
            {
            	if (printResult)
            		System.out.println("Agent: starting Breath First Search Method (BFS)");
            	CustomBreadthFirstSearch cBreadthSearchRun = new CustomBreadthFirstSearch(MAX_DEPTH);
            	time = (new Date()).getTime();
            	myProblem = new GridSearchProblem(world, start, goal);
            	ArrayList breadthPlan = cBreadthSearchRun.search(myProblem);
            	time -= (new Date()).getTime();
            	if (printResult)
            		System.out.println(
            				"\tNeeded "
            						+ (-time)
            						+ " msec, PathLength:  "
            						+ breadthPlan.size()
            						+ ", NumExpNodes: "
            						+ myProblem.getNumExpandedNodes());
            	return cBreadthSearchRun;
            }
            case CUSTOM_DEPTH_FIRST_SEARCH :
            {
            	if (printResult)
            		System.out.println("Agent: starting Depth First Search Method (DFS)");
            	CustomDepthFirstSearch cDepthSearchRun = new CustomDepthFirstSearch(MAX_DEPTH);
            	time = (new Date()).getTime();
            	myProblem = new GridSearchProblem(world, start, goal);
            	ArrayList depthPlan = cDepthSearchRun.search(myProblem);
            	time -= (new Date()).getTime();
            	if (printResult)
            		System.out.println("\tNeeded " + (-time) + " msec, PathLength: " + depthPlan.size());
            	return cDepthSearchRun;
            }

        }
        System.err.println("Agent: Unknown Search Method selected!");
        return null;
    }
}
