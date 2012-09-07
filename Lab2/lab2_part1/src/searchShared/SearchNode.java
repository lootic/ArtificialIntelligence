package searchShared;

import java.util.ArrayList;
import java.util.LinkedList;

import world.GridPos;

public class SearchNode{
	SearchNode parent;
	GridPos state;
	int depth; 

	public SearchNode(GridPos gridPos){
		parent =  null;
		state = gridPos;
		depth=1;//0 ?
	}

	/**
	 * Use this to create a new search node
	 */
	public SearchNode(GridPos aState, SearchNode aNode){
		parent =  aNode;
		state = aState;
		depth =  aNode.getDepth() + 1;
	}

	public boolean  isRootNode(){
		if (parent == null){
			  return true;
		}
		else{
			return false;
		}
	}
	public SearchNode getParent() {
		return parent;
	}

	public void setParent(SearchNode parent) {
		this.parent = parent;
	}

	public GridPos getState() {
		return state;
	}

	public void setState(GridPos state) {
		this.state = state;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public ArrayList<SearchNode> getPathFromRoot(){
		LinkedList<SearchNode> ll = new LinkedList<SearchNode>();
		SearchNode current = this;
		while(!(current.isRootNode())){
			ll.addFirst(current);
			current = current.getParent();
		}
		// Note: Does not include the root node itself as the agent is already there!
		return new ArrayList<SearchNode>(ll);
	}


    // To make hash sets work
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

    // To make hash sets work
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchNode other = (SearchNode) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
}
