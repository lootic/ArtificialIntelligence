/******************************************************
 *
 * Project: javaRL
 * File: World.java
 * @version 1.0
 * Date: 02.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 *
 * Description: This class moddels a simple grid world 
 * 				for the taxi domain 
 * 
 ******************************************************/
package world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;

public class World extends Observable {

    public static final int NO_DIRT = -1;

    // Possible Actions
    public static final byte GO_NORTH = 1;
    public static final byte GO_SOUTH = 2;
    public static final byte GO_WEST = 3;
    public static final byte GO_EAST = 4;
	public static final byte SUCK_DIRT = 5;
	//public static final byte UNLOAD_PASSENGER = 6;
    public static final byte NO_OPERATION = 7;

    public Maze m_Maze;
    public WAgent m_Agent;
    public Collection m_Dirt;
    public Collection m_Blockades;
    public int m_initialDirt;
    public int m_initialSize;
    public int m_initialBlock;
    public int m_initialType;
    private GridPos startPos;
    private GridPos goalPos;
    public boolean m_fullyObservable = false;

    /** @param size - the size of the world in x and y
     *  @param numDirt - number of dirt to be created 
     **/
    public World(int size, int numDirt, int numBlock, int type, boolean fullyObservable) {
        m_initialDirt = numDirt;
        m_initialBlock = numBlock;        
        m_initialSize = size;
        m_initialType = type;
        m_fullyObservable = fullyObservable;
        reset();
    }

    public World(World world) {
        m_initialDirt = world.m_initialDirt;
        m_initialBlock = world.m_initialBlock;
        m_initialSize = world.m_initialSize;
        m_initialType = world.m_initialType;
		m_fullyObservable = world.m_fullyObservable;

        m_Maze = new Maze(world.m_Maze);
        m_Agent = new WAgent(world.m_Agent);

        // Copy Dirt
        m_Dirt = new ArrayList();
        Iterator it = world.m_Dirt.iterator();
        while (it.hasNext()) {
            Dirt p = new Dirt((Dirt) it.next());
            m_Dirt.add(p);
        }
        // Copy Blockades
        m_Blockades = new ArrayList();
        Iterator it2 = world.m_Blockades.iterator();
        while (it2.hasNext()) {
            GridPos p = new GridPos((GridPos) it2.next());
            m_Blockades.add(p);
        }

        /** Notify registerd observers **/
        setChanged();
        notifyObservers();
    }

    public void reset() {
        int size = m_initialSize;
        startPos = new GridPos(1, 1);
        goalPos = new GridPos(size - 1, size - 1);

        /** Create Maze **/
        m_Maze = new Maze(size, m_initialType, this);

        /** Create agent**/
        //m_Agent = new WAgent(m_Maze.getRandomFreePos());
        m_Agent = new WAgent(new GridPos(1, 1));
        m_Maze.updateAgent(m_Agent);

        /** Create dirt and destinations**/
        m_Dirt = new ArrayList();
        for (int i = 0; i < m_initialDirt; i++) {
            Dirt p = new Dirt(i, m_Maze.getRandomFreePos());
            p.setDestination(m_Maze.getRandomFreePos());
            m_Dirt.add(p);
        }
        m_Maze.updateDirt(m_Dirt);

        /** Create Bloackades **/
        m_Blockades = new ArrayList();
        for (int i = 0; i < m_initialBlock; i++) {
            GridPos p = new GridPos(m_Maze.getRandomFreePos());
            m_Blockades.add(p);
        }
        m_Maze.updateBlockades(m_Blockades);

        /** Notify registerd observers **/
        setChanged();
        notifyObservers();
    }

    public int getNumDirt() {
        return m_Dirt.size();
    }

    public boolean dirtAt(GridPos pos) {
        Iterator it = m_Dirt.iterator();

        while (it.hasNext()) {
            Dirt p = (Dirt) it.next();
            if (p.equals(pos))
                return true;
        }
        return false;
    }

    private boolean agentAt(GridPos pos) {
        return m_Agent.equals(pos);
    }

    private boolean dirtAtDest(int ID) {
        Iterator it = m_Dirt.iterator();
        while (it.hasNext()) {
            Dirt p = (Dirt) it.next();
            if (p.getID() == ID)
                return p.atDestination();
        }
        return false;
    }

//    private boolean passengerDestAt(int ID, GridPos pos) {
//        Iterator it = m_Dirt.iterator();
//        while (it.hasNext()) {
//            Dirt p = (Dirt) it.next();
//            if (p.getID() == ID)
//                return p.equals(pos);
//        }
//        return false;
//    }

    public Dirt getDirt(int ID) {
        Iterator it = m_Dirt.iterator();
        while (it.hasNext()) {
            Dirt p = (Dirt) it.next();
            if (p.getID() == ID)
                return p;
        }
        return null;
    }

    public int getDirtID(GridPos pos) {
        Iterator it = m_Dirt.iterator();
        while (it.hasNext()) {
            Dirt p = (Dirt) it.next();
            if (((GridPos) p).equals(pos))
                return p.getID();
        }
        return NO_DIRT;
    }

	public Dirt getDirtAtPos(GridPos pos) {
		int id = getDirtID(pos);
		return getDirt(id);
	}


    private void delDirt(int ID) {
        Iterator it = m_Dirt.iterator();
        while (it.hasNext()) {
            Dirt p = (Dirt) it.next();
            if (p.getID() == ID) {
                m_Dirt.remove(p);
                return;
            }
        }
    }

    private void delBlockadeAt(int x, int y) {
        GridPos search = new GridPos(x, y);
        Iterator it = m_Blockades.iterator();
        while (it.hasNext()) {
            GridPos p = (GridPos) it.next();
            if (p.equals(search)) {
                m_Blockades.remove(p);
                return;
            }
        }
    }

    public void printDirt() {
        if (m_Dirt.isEmpty())
            System.out.println("Empty!");
        Iterator it = m_Dirt.iterator();
        while (it.hasNext()) {
            Dirt p = (Dirt) it.next();
            System.out.println(p);
        }
    }

    public void print() {
        m_Maze.print();
    }

    /** moveAgent
     *  @param dir - the direction to move to 
     *  @return TRUE if there was no wall**/
    public double moveAgent(byte dir) {
        double reward = -1.0;
        int _x = m_Agent.getX();
        int _y = m_Agent.getY();
        if (dir == NO_OPERATION)
            return -10;

        GridPos newPos = checkMove((GridPos) m_Agent, dir);
        if (newPos == null)
            return -10;

        /** Move agent **/
        m_Agent.setPos(newPos);
        m_Maze.updateAgent(m_Agent);

//        /** If load, move as well **/
//        if (m_Agent.hasLoad()) {
//            Dirt p = getDirt(m_Agent.getLoad());
//            p.setPos(newPos);
//            m_Maze.updateDirt(m_Dirt);
//        }

        /** Notify registerd observers **/
        setChanged();
        notifyObservers();

        return reward;
    }

    public GridPos checkMove(GridPos p, int direction) {
        int _x = p.getX();
        int _y = p.getY();

        switch (direction) {
            case GO_NORTH :
                _y--;
                break;
            case GO_SOUTH :
                _y++;
                break;
            case GO_WEST :
                _x--;
                break;
            case GO_EAST :
                _x++;
                break;
            default :
                System.out.println("World: Unknown Direction " + direction);
                break;
        }
        if (m_Maze.wallAt(_x, _y) || m_Maze.blockadeAt(_x, _y))
            return null;
        return m_Maze.getGridObject(_x, _y);
    }

    public void suck() {

        if (!dirtAt((GridPos) m_Agent)) {
            System.out.println("World: No dirt at " + m_Agent);
            return;
        }
        int ID = getDirtID((GridPos) m_Agent);
        delDirt(ID);
        System.out.println("World: Dirt " + ID + " vacuumed at " + m_Agent);
        m_Agent.setLoad(ID);
        m_Maze.updateAgent(m_Agent);

        /** Notify registered observers **/
        setChanged();
        notifyObservers();

        return;
    }

    
//    /** loadPassenger
//     *  @return TRUE if loaded succesfully**/
//    public double loadPassenger() {
//        double reward = -1.0;
//
//        if (!passengerAt((GridPos) m_Agent)) {
//            System.out.println("World: No passenger at " + m_Agent);
//            return -10.0;
//        }
//        if (m_Agent.hasLoad()) {
//            System.out.println("World: Already someone loaded!");
//            return -10.0;
//        }
//        int ID = getPassengerID((GridPos) m_Agent);
//        System.out.println("World: loaded Passenger " + ID + " at " + m_Agent);
//        m_Agent.setLoad(ID);
//        m_Maze.updateAgent(m_Agent);
//
//        /** Notify registerd observers **/
//        setChanged();
//        notifyObservers();
//
//        return reward;
//    }
//
//    /** unloadPassenger
//     *  @return TRUE if unloaded succesfully**/
//    public double unloadPassenger() {
//        double reward = -1.0;
//        if (!m_Agent.hasLoad()) {
//            System.out.println("World: Agent has no passenger loaded!");
//            return -10.0;
//        }
//
//        if (passengerAtDest(m_Agent.getLoad())) {
//            System.out.println("World: Passenger arrived! DELETE " + m_Agent.getLoad());
//            delPassenger(m_Agent.getLoad());
//            m_Maze.updatePassengers(m_Passengers);
//            reward = 100;
//        }
//        else {
//            Passenger p = getPassenger(m_Agent.getLoad());
//            System.out.println("World: Passenger not at destination! LOADED " + m_Agent.getLoad());
//            System.out.println("World: Destination is: " + p.getDestination());
//            System.out.println("World: Position is: " + (GridPos) p);
//            reward = -1.0;
//        }
//        System.out.println("World: unloaded Passenger " + m_Agent.getLoad());
//        m_Agent.unLoad();
//        m_Maze.updateAgent(m_Agent);
//
//        /** Notify registerd observers **/
//        setChanged();
//        notifyObservers();
//
//        return reward;
//    }

    public boolean isTerminated() {
        if (m_Dirt.isEmpty())
            return true;
        return false;
    }
    public int getWidth() {
        return m_Maze.getColumns();
    }
    public int getHeight() {
        return m_Maze.getRows();
    }
	public int getSize() {
		return m_Maze.getSize();
	}
	public int getNumActions() {
		return 4;
	}

    public World getWorld() {
        World w = new World(this);
        if (!m_fullyObservable)
            w.removeDynamicObjects();
        return w;
    }

    public void removeDynamicObjects() {
        m_Blockades.clear();
        m_Maze.updateBlockades(m_Blockades);

        /** Notify registered observers **/
        setChanged();
        notifyObservers();
    }

    /** getSenses
     *  @return **/
    public Collection getSenses() {
        Collection senses = new ArrayList();

        // Add Agent pos
        senses.add(m_Agent);

        // Add blockades if in sight
        Iterator b = m_Blockades.iterator();
        while (b.hasNext()) {
            GridPos block = (GridPos) b.next();
            if (m_Agent.adjacent(block))
                senses.add(block);
        }

        // Add dirt if in sight
        Iterator p = m_Dirt.iterator();
        while (p.hasNext()) {
            Dirt d = (Dirt) p.next();
            //if (m_Agent.adjacentPos(pass))
            senses.add(d);
        }

        return senses;
    }

    /** setSenses
     *  @param list **/
    public void setSenses(Collection list) {
        if (list == null)
            return;

        /** Set last Position as known **/
        m_Maze.setObjectTo(Maze.FREE, m_Agent);

        ArrayList newDirt = new ArrayList();
        ArrayList newBlock = new ArrayList();

        m_Dirt.clear();

        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o.getClass() == WAgent.class) {
                m_Agent = new WAgent((WAgent) o);
            }
            else if (o.getClass() == GridPos.class) {
                newBlock.add(o);
                m_Blockades.add((GridPos) o);

            }
            else if (o.getClass() == Dirt.class) {
                newDirt.add(o);
                m_Dirt.add((Dirt) o);
            }
        }

        /** Check for inconsistency **/
        /*Iterator itPass = m_Passengers.iterator();
        while (itPass.hasNext()) {
            Passenger pass = (Passenger) itPass.next();
            if (m_Agent.adjacentPos(pass)) {
                boolean found = false;
                Iterator newPassIt = newPass.iterator();
                while (newPassIt.hasNext()) {
                    Passenger p = (Passenger) newPassIt.next();
                    if (p.equals(pass)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Found inconsistent Passenger: " + pass);
                    //System.out.println("Memmory: " + m_Passengers);
                    //System.out.println("New senses: " + newPass);
                    delPassenger(pass.getID());
                }
            }
        }
        */

        m_Maze.updateAgent(m_Agent);
        m_Maze.updateBlockades(m_Blockades);
        m_Maze.updateDirt(m_Dirt);

        /** Notify registerd observers **/
        setChanged();
        notifyObservers();
    }

    // Interface for Planning
    public ArrayList getReachableStatesFrom(Object aState) {
        ArrayList actions = new ArrayList();
        GridPos p = (GridPos) aState;
        GridPos temp;

        if ((temp = checkMove(p, GO_NORTH)) != null)
            actions.add(temp);
        if ((temp = checkMove(p, GO_SOUTH)) != null)
            actions.add(temp);
        if ((temp = checkMove(p, GO_WEST)) != null)
            actions.add(temp);
        if ((temp = checkMove(p, GO_EAST)) != null)
            actions.add(temp);

        return actions;
    }

    public int getNumReachableStates(Object aState) {
        int num = 0;
        GridPos p = (GridPos) aState;

        if (checkMove(p, GO_NORTH) != null)
            num++;
        if (checkMove(p, GO_SOUTH) != null)
            num++;
        if (checkMove(p, GO_WEST) != null)
            num++;
        if (checkMove(p, GO_EAST) != null)
            num++;

        return num;
    }


    public double getProbability(GridPos s1, int action, GridPos s2) {
        int a = getActionNum(s1, s2);
        /*System.out.println("FROM " + s1 + " TO " + s2 
        + " ShouldBe: " + getActionName(s1, s2) + " " +getActionNum(s1,s2)  
        +  " is: "+ action);*/
        if (a == action) {
            //System.out.println(action + " is right");
            return 1.0;
        }
        //System.out.println(action + " is false");
        return 0.0;
    }

    public String getActionName(GridPos gp1, GridPos gp2) {
        if ((gp2.m_y - gp1.m_y) == -1 && checkMove(gp1,GO_NORTH)!=null)        		
            return new String("GO_NORTH");
        if ((gp2.m_y - gp1.m_y) == 1 && checkMove(gp1,GO_SOUTH)!=null)
            return new String("GO_SOUTH");
        if ((gp2.m_x - gp1.m_x) == -1 && checkMove(gp1,GO_WEST)!=null)
            return new String("GO_WEST");
        if ((gp2.m_x - gp1.m_x) == 1 && checkMove(gp1,GO_EAST)!=null)
            return new String("GO_EAST");
        else {
            System.out.println("World.getAction: The following states are not connected: "+gp1+";"+gp2);
			return new String("NO_ACTION");
		}

    }

    public int getActionNum(GridPos gp1, GridPos gp2) {
        if ((gp2.m_y - gp1.m_y) == -1 && checkMove(gp1,GO_NORTH)!=null)
            return GO_NORTH;
        if ((gp2.m_y - gp1.m_y) == 1 && checkMove(gp1,GO_SOUTH)!=null)
            return GO_SOUTH;
        if ((gp2.m_x - gp1.m_x) == 1 && checkMove(gp1,GO_WEST)!=null)
            return GO_WEST;
        if ((gp2.m_x - gp1.m_x) == -1 && checkMove(gp1,GO_EAST)!=null)
            return GO_EAST;
        else {
			return -1;
        }
    }

    public Collection getReachableStatesFrom2(Object aState, int depth) {
        GridPos p = (GridPos) aState;

        //System.out.println("Checking state " + p);
        ArrayList l = new ArrayList();

        if (checkMove(p, GO_NORTH) != null) {
            GridPos n = checkMove(p, GO_NORTH);
            l.add(n);
            if (depth > 0)
                l.addAll(getReachableStatesFrom2(n, depth - 1));
        }
        if (checkMove(p, GO_SOUTH) != null) {
            GridPos n = checkMove(p, GO_SOUTH);
            l.add(n);
            if (depth > 0)
                l.addAll(getReachableStatesFrom2(n, depth - 1));
        }
        if (checkMove(p, GO_WEST) != null) {
            GridPos n = checkMove(p, GO_WEST);
            l.add(n);
            if (depth > 0)
                l.addAll(getReachableStatesFrom2(n, depth - 1));
        }
        if (checkMove(p, GO_EAST) != null) {
            GridPos n = checkMove(p, GO_EAST);
            l.add(n);
            if (depth > 0)
                l.addAll(getReachableStatesFrom2(n, depth - 1));
        }

        return l;
    }

    public boolean isReachable(Object state1, Object state2) {
        GridPos s1 = (GridPos) state1;
        GridPos s2 = (GridPos) state2;

        if (m_Maze.wallAt(s2) || m_Maze.blockadeAt(s2) || m_Maze.wallAt(s1) || m_Maze.blockadeAt(s1))
            return false;

        if (!s1.connected(s2))
            return false;
        return true;
    }

    public boolean isValidState(Object state) {
        GridPos s = (GridPos) state;
        if (m_Maze.wallAt(s) || m_Maze.blockadeAt(s))
            return false;
        return true;
    }

	// Marks the yellow thing (path)
    public void markCurrentPath(ArrayList l) {
        m_Maze.updatePath(l);

        /** Notify registerd observers **/
        setChanged();
        notifyObservers();
    }

	// Marks the magenta thing (expanded)
    public void markExpandedNodes(ArrayList l) {
        m_Maze.updateExpandedNodes(l);

        /** Notify registerd observers **/
        setChanged();
        notifyObservers();
    }

    public boolean setBlockadeAt(int x, int y) {
        if (!m_Maze.wallAt(x, y)) {
            if (m_Maze.blockadeAt(x, y)) {
                m_Maze.eraseObjectAt(Maze.BLOCKADE, x, y);
                delBlockadeAt(x, y);
                return false;
            }
            else {
                m_Maze.setObjectTo(Maze.BLOCKADE, x, y);
                m_Blockades.add(new GridPos(x, y));
                return true;
            }
        }
        return true;
    }
}
