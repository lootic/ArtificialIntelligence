package searchShared;

import java.util.ArrayList;

import world.GridPos;

public abstract class Problem {

    protected GridPos m_InitialState;
    protected GridPos m_GoalState;

    public abstract ArrayList<GridPos> getReachableStatesFrom(GridPos aState);

    public abstract boolean isGoalState(GridPos aState);

	public GridPos getInitialState() {
		return m_InitialState;
	}

	public void setInitialState(GridPos initialState) {
		this.m_InitialState = initialState;
	}

	public GridPos getGoalState() {
		return m_GoalState;
	}

	public void setGoalState(GridPos finalState) {
		this.m_GoalState = finalState;
	}

}
