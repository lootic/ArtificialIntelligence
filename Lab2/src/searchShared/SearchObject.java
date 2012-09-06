/*
 * Created by IntelliJ IDEA.
 * User: rrmohan
 * Date: Feb 5, 2003
 * Time: 2:56:15 PM
 * To change template for new interface use 
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package searchShared;

import java.util.ArrayList;

import world.GridPos;

public interface SearchObject {

    public ArrayList<SearchNode> search(Problem p);
    
    public ArrayList<SearchNode> getPath();
    public ArrayList<SearchNode> getAllExpandedNodes();
    public ArrayList<SearchNode> getExploredNodes();
    public ArrayList<SearchNode> getFrontierNodes();    
}
