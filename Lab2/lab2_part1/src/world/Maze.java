/******************************************************
 *
 * Project: javaRL
 * File: Maze.java
 * @version 1.0
 * Date: 03.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description: Describes a maze
 * 
 *  
 ******************************************************/
package world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Maze {
    public static final int NONE = 0;
    public static final int WALL = 1;
    public static final int AGENT = 2;
    public static final int LOADED_AGENT = 4;
    public static final int PASSENGER = 8;
    public static final int DESTINATION = 16;
    public static final int BLOCKADE = 32;
    public static final int PATH1 = 64;
    public static final int PATH2 = 128;
    public static final int FREE = 256;

    public static final byte MAZE_ALTERNATIVES = 100;
    public static final byte MAZE_NO_ALTERNATIVES = 101;
    public static final byte MAZE_SOME_WALLS = 102;
    public static final byte MAZE_ONLY_BORDER = 103;
    public static final byte MAZE_OFFICE = 104;
    public static final byte MAZE_QCUT_1 = 105;
    public static final byte MAZE_QCUT_2 = 106;
    public static final byte MAZE_QCUT_3 = 107;
	public static final byte MAZE_TAXI_DOMAIN = 108;
	public static final byte MAZE_MAX_DOMAIN = 108;

    public static final double ALTERNATIVE_PROB = 0.2;
    public int MAX_DIST = 1;
    private int m_array[][];
    public HashMap m_GridObjects;
    public ArrayList m_Roads;
    private int m_size;
    private int m_rows;
    private int m_columns;

    private Random rand;
    private boolean rawPrint = false;
    private World m_World;

    /**  Constructor
     * @param size - The size of the maze in X and Y
     **/
    public Maze(int size, int type, World world) {
        m_World = world;
        if (size % 2 == 0)
            size++;

        MAX_DIST = (int) dist(new GridPos(0, 0), new GridPos(size, size));
        m_array = new int[size][size];
        m_GridObjects = new HashMap(size * size);
        m_Roads = new ArrayList();
        m_size = size;
        m_rows = m_size;
        m_columns = m_size;
        rand = new Random();

        switch (type) {
            case MAZE_ALTERNATIVES :
                makeRandomMaze(true);
                break;
            case MAZE_NO_ALTERNATIVES :
                makeRandomMaze(false);
                break;
            case MAZE_SOME_WALLS :
                makeEmptyMaze(true);
                break;
            case MAZE_ONLY_BORDER :
                makeEmptyMaze(false);
                break;
            case MAZE_OFFICE :
                makeOfficeMaze(10);
                break;
            case MAZE_QCUT_1 :
                makeQCutMaze(1);
                break;
            case MAZE_QCUT_2 :
                makeQCutMaze(2);
                break;
            case MAZE_QCUT_3 :
                makeQCutMaze(3);
                break;
            case MAZE_TAXI_DOMAIN :
                makeTaxiDomain();
                break;

            default :
                makeEmptyMaze(false);
                break;
        }
        /** Init Grid Objects **/
        for (int i = 0; i < m_size; i++)
            for (int j = 0; j < m_size; j++)
                m_GridObjects.put(new Point(i, j), new GridPos(i, j));

        /** Update roads data structure **/
        for (int i = 0; i < m_size; i++)
            for (int j = 0; j < m_size; j++)
                if (!wallAt(i, j))
                    m_Roads.add(m_GridObjects.get(new Point(i, j)));

    }

    /**  Constructor
     * @param m - The maze to be copied from
     **/
    public Maze(Maze maze) {
        MAX_DIST = maze.MAX_DIST;
        m_size = maze.m_size;
        m_rows = maze.m_size;
        m_columns = maze.m_size;
        m_World = maze.m_World;

        m_array = new int[m_size][m_size];
        m_GridObjects = new HashMap(maze.m_GridObjects);
        m_Roads = new ArrayList(maze.m_Roads);
        rand = new Random();

        // Copy maze
        for (int i = 0; i < m_size; i++)
            for (int j = 0; j < m_size; j++)
                m_array[i][j] = maze.m_array[i][j];
    }

    /** makeEmptyMaze
     * Creates basically the walls
     * 
     *  @param obstacles - If TRUE some obstacles will be created **/
    public void makeEmptyMaze(boolean obstacles) {
        /** Create walls **/
        for (int i = 0; i < m_size; i++)
            for (int j = 0; j < m_size; j++) {
                m_array[i][j] = 0;
                if (i == 0 || j == 0 || (i == m_size - 1) || (j == m_size - 1))
                    setObjectTo(WALL, i, j);
            }

        /** Create some obstacles **/
        if (obstacles) {
            for (int i = 0; i < m_size / 2; i++)
                setObjectTo(WALL, m_size / 2, i);
            for (int i = 0; i < m_size / 3; i++)
                setObjectTo(WALL, i, m_size - m_size / 3);
            for (int i = 0; i < m_size / 5; i++)
                setObjectTo(WALL, m_size - m_size / 3, i);
        }
    }

    public void makeQCutMaze(int num) {
        /** Create walls **/
        for (int i = 0; i < m_size; i++)
            for (int j = 0; j < m_size; j++) {
                m_array[i][j] = 0;
                if (i == 0 || j == 0 || (i == m_size - 1) || (j == m_size - 1))
                    setObjectTo(WALL, i, j);
            }

        if (num == 1) {
            int x1 = m_size / 2;
            int x2 = m_size / 2 + 1;
            int x3 = m_size / 2 - 1;
            int y = 0;

            // Do vertical seperation
            for (y = 0; y < m_size; y++) {
                m_array[x1][y] = 1;
                m_array[x2][y] = 1;
                m_array[x3][y] = 1;
            }
            // Create one bottleneck
            y = m_size / 2;
            m_array[x1][y] = 0;
            m_array[x2][y] = 0;
            m_array[x3][y] = 0;
        }

        if (num == 2) {
            int x1 = m_size / 2;
            int x2 = m_size / 2 + 1;
            int x3 = m_size / 2 - 1;
            int y = 0;

            // Do vertical seperation
            for (y = 0; y < m_size; y++) {
                m_array[x1][y] = 1;
                m_array[x2][y] = 1;
                m_array[x3][y] = 1;
            }

            // Create two bottlenecks
            y = m_size / 4;
            m_array[x1][y] = 0;
            m_array[x2][y] = 0;
            m_array[x3][y] = 0;
            y = 3 * m_size / 4;
            m_array[x1][y] = 0;
            m_array[x2][y] = 0;
            m_array[x3][y] = 0;
        }

        //Create 6 rooms
        if (num == 3) {
            int x1 = m_size / 3;
            int x2 = 2 * m_size / 3;
            int y = 0;

            // Do vertical seperation
            for (y = 0; y < m_size; y++) {
                m_array[x1][y] = 1;
                m_array[x2][y] = 1;
            }

            // Do horizontal seperation
            int x;
            y = m_size / 2;
            for (x = 0; x < m_size; x++) {
                m_array[x][y] = 1;
            }

            // Do 2 horizontal bottelnecks
            x1 = (int) ((1.0 / 6.0) * (double) m_size);
            x2 = (int) ((5.0 / 6.0) * (double) m_size);
            y = (int) (m_size / 2.0);
            m_array[x1][y] = 0;
            m_array[x1 + 1][y] = 0;
            m_array[x2][y] = 0;
            m_array[x2 + 1][y] = 0;

            // Do 4 vertical bottelnecks
            x1 = (int) ((1.0 / 3.0) * (double) m_size);
            x2 = (int) ((2.0 / 3.0) * (double) m_size);
            int y1 = (int) ((1.0 / 4.0) * (double) m_size);
            int y2 = (int) ((3.0 / 4.0) * (double) m_size);
            m_array[x1][y1] = 0;
            m_array[x1][y1 + 1] = 0;
            m_array[x2][y1] = 0;
            m_array[x2][y1 + 1] = 0;
            m_array[x1][y2] = 0;
            m_array[x1][y2 + 1] = 0;
            m_array[x2][y2] = 0;
            m_array[x2][y2 + 1] = 0;
        }
    }

    public void makeTaxiDomain() {
        System.out.println("Maze: TaxiDomein not implemented yet");
    }

    /** makeMazeRandom
     *
     * Create a random maze.  The strategy is to start with
     * a grid of disconnnected "rooms" separated by walls.
     * then look at each of the separating walls, in a random
     * order.  If tearing down a wall would not create a loop
     * in the maze, then tear it down.  Otherwise, leave it in place.
     */
    public void makeRandomMaze(boolean alternatives) {
        int i, j;
        int emptyCt = 0; // number of rooms
        int wallCt = 0; // number of walls

        int[] wallrow = new int[(m_rows * m_columns) / 2]; // position of walls between rooms
        int[] wallcol = new int[(m_rows * m_columns) / 2];

        // start with everything being a wall
        for (i = 0; i < m_rows; i++)
            for (j = 0; j < m_columns; j++)
                m_array[i][j] = WALL;

        // make a grid of empty rooms
        for (i = 1; i < m_rows - 1; i += 2)
            for (j = 1; j < m_columns - 1; j += 2) {
                emptyCt++;
                m_array[i][j] = -emptyCt; // each room is represented by a different negative number
                if (i < m_rows - 2) { // record info about wall below this room
                    wallrow[wallCt] = i + 1;
                    wallcol[wallCt] = j;
                    wallCt++;
                }
                if (j < m_columns - 2) { // record info about wall to right of this room
                    wallrow[wallCt] = i;
                    wallcol[wallCt] = j + 1;
                    wallCt++;
                }
            }

        // choose a wall randomly and maybe tear it down
        for (i = wallCt - 1; i > 0; i--) {
            int r = (int) (Math.random() * i);
            tearDown(wallrow[r], wallcol[r]);
            wallrow[r] = wallrow[i];
            wallcol[r] = wallcol[i];
        }

        //replace negative values in m_array[][] with emptyCode
        for (i = 1; i < m_rows - 1; i++)
            for (j = 1; j < m_columns - 1; j++)
                if (m_array[i][j] < 0)
                    m_array[i][j] = NONE;

        // Buld Alternatives            
        if (alternatives) {
            for (i = 1; i < m_rows - 1; i++)
                for (j = 1; j < m_columns - 1; j++)
                    if (m_array[i][j] == WALL) {
                        if ((m_array[i - 1][j] == NONE) && (m_array[i + 1][j] == NONE))
                            //|| (m_array[i][j - 1] == NONE && m_array[i][j + 1] == NONE))
                            if (rand.nextDouble() < ALTERNATIVE_PROB)
                                m_array[i][j] = NONE;
                    }
        }
    }

    /** tearDown
    * Tear down a wall, unless doing so will form a loop.  Tearing down a wall
    * joins two "rooms" into one "room".  (Rooms begin to look like corridors
    * as they grow.)  When a wall is torn down, the room codes on one side are
    * converted to match those on the other side, so all the cells in a room
    * have the same code.   Note that if the room codes on both sides of a
    * wall already have the same code, then tearing down that wall would 
    * create a loop, so the wall is left in place.
    *  @param row
    *  @param col **/
    private void tearDown(int row, int col) {
        // row is odd; wall separates rooms horizontally
        if (row % 2 == 1 && m_array[row][col - 1] != m_array[row][col + 1]) {
            fill(row, col - 1, m_array[row][col - 1], m_array[row][col + 1]);
            m_array[row][col] = m_array[row][col + 1];
        }
        // row is even; wall separates rooms vertically
        else if (row % 2 == 0 && m_array[row - 1][col] != m_array[row + 1][col]) {
            fill(row - 1, col, m_array[row - 1][col], m_array[row + 1][col]);
            m_array[row][col] = m_array[row + 1][col];
        }
    }

    /** fill
     * called by tearDown() to change "room codes".
     *  @param row - The row position
     *  @param col - The col position
     *  @param replace - Symbol to be repaced
     *  @param replaceWith - Replacing Symbol 
     ***/
    private void fill(int row, int col, int replace, int replaceWith) {
        if ((row < 0) || (col < 0))
            return;
        if ((row >= m_rows) || (col >= m_columns))
            return;

        if (m_array[row][col] == replace) {
            m_array[row][col] = replaceWith;
            fill(row + 1, col, replace, replaceWith);
            fill(row - 1, col, replace, replaceWith);
            fill(row, col + 1, replace, replaceWith);
            fill(row, col - 1, replace, replaceWith);
        }
    }

    public void makeOfficeMaze(int width) {
        int i, j;
        int emptyCt = 0; // number of rooms
        int wallCt = 0; // number of walls

        int[] wallrow = new int[(m_rows * m_columns) / 2]; // position of walls between rooms
        int[] wallcol = new int[(m_rows * m_columns) / 2];

        // start with everything being free
        for (i = 0; i < m_rows; i++)
            for (j = 0; j < m_columns; j++)
                m_array[i][j] = NONE;

        boolean drawY = false;
        boolean drawX = false;
        boolean passageY, passageX;
        for (int y = 0; y < m_rows; y++) {
            passageY = false;

            if (((y - 1) % (width + 3)) == 0)
                passageY = true;

            if (((y - 2) % (width + 3)) == 0)
                drawY = true;
            else if ((y % (width + 3)) == 0)
                drawY = true;
            else
                drawY = false;
            for (int x = 0; x < m_columns; x++) {
                passageX = false;
                boolean door = false;

                if (((x - width / 2) % (width + 3)) == 0)
                    door = true;

                if (((x - 1) % (width + 3)) == 0)
                    passageX = true;

                if ((x % (width + 3)) == 0)
                    drawX = true;
                else if (((x - 2) % (width + 3)) == 0)
                    drawX = true;
                else
                    drawX = false;

                if (y == 0 || y == (m_rows - 1) || x == 0 || x == (m_columns - 1))
                    m_array[y][x] = WALL;
                else if (drawX && !passageY && !door)
                    m_array[y][x] = WALL;
                else if (drawY && !passageX && !door)
                    m_array[y][x] = WALL;
            }

        }

        //replace negative values in m_array[][] with emptyCode
        for (i = 1; i < m_rows - 1; i++)
            for (j = 1; j < m_columns - 1; j++)
                if (m_array[i][j] < 0)
                    m_array[i][j] = NONE;

        // HACK:
        int gpX = m_rows - 2;
        int gpY = m_columns - 2;
        if (m_array[gpX - 1][gpY] == WALL && m_array[gpX][gpY - 1] == WALL)
            m_array[gpX][gpY - 1] = NONE;

    }

    /** dist
                                 *  @param p1 - Pos1 on the Grid
                                 *  @param p2 - Pos2 on the Grid
                                 *  @return The distance between both positions**/
    public double dist(GridPos p1, GridPos p2) {
        return p1.getDistanceEuclidian(p2);
    } /** wallAt
                                                      *  @param x - x on the map
                                                      *  @param y - y on the map
                                                      *  @return TRUE if there is no wall**/
    public boolean wallAt(int x, int y) {
        if ((m_array[x][y] & WALL) == WALL)
            return true;
        return false;
    } /** wallAt
                                                          *  @param pos - a psoition on the map
                                                          *  @return TRUE if there is no wall**/
    public boolean wallAt(GridPos pos) {
        return wallAt(pos.m_x, pos.m_y);
    }

    public int getSize() {
        return m_size;
    }

    public boolean agentAt(GridPos pos) {
        if ((m_array[pos.m_x][pos.m_y] & AGENT) == AGENT)
            return true;
        return false;
    }

    public boolean blockadeAt(GridPos pos) {
        if ((m_array[pos.m_x][pos.m_y] & BLOCKADE) == BLOCKADE)
            return true;
        return false;
    }
    public boolean blockadeAt(int x, int y) {
        if ((m_array[x][y] & BLOCKADE) == BLOCKADE)
            return true;
        return false;
    }

    public boolean markedPath1At(GridPos pos) {
        if ((m_array[pos.m_x][pos.m_y] & PATH1) == PATH1)
            return true;
        return false;
    }

    public boolean markedPath2At(GridPos pos) {
        if ((m_array[pos.m_x][pos.m_y] & PATH2) == PATH2)
            return true;
        return false;
    }

    public boolean markedAsFree(GridPos pos) {
        if ((m_array[pos.m_x][pos.m_y] & FREE) == FREE)
            return true;
        return false;
    }

    public boolean loadedAgentAt(GridPos pos) {
        if ((m_array[pos.m_x][pos.m_y] & LOADED_AGENT) == LOADED_AGENT)
            return true;
        return false;
    }

    public boolean dirtAt(GridPos pos) {
        if ((m_array[pos.m_x][pos.m_y] & PASSENGER) == PASSENGER)
            return true;
        return false;
    }
    public boolean destinationAt(GridPos pos) {
        if ((m_array[pos.m_x][pos.m_y] & DESTINATION) == DESTINATION)
            return true;
        return false;
    }

    public void setObjectTo(int object, GridPos pos) {
        int x = pos.m_x;
        int y = pos.m_y;
        setObjectTo(object, x, y);
    }

    public void setObjectTo(int object, int x, int y) {
        m_array[x][y] |= object;
    }

    public void eraseObjectAt(int object, GridPos pos) {
        int x = pos.m_x;
        int y = pos.m_y;
        eraseObjectAt(object, x, y);
    }

    public void eraseObjectAt(int object, int x, int y) {
        m_array[x][y] &= ~object;
    }

    public void moveObjectFromTo(byte object, GridPos f, GridPos t) {
        if (object == AGENT && loadedAgentAt(f))
            object = LOADED_AGENT;
        eraseObjectAt(object, f);
        setObjectTo(object, t);
    }

    public void toggleAgent(GridPos pos) {
        if (agentAt(pos)) {
            eraseObjectAt(AGENT, pos);
            setObjectTo(LOADED_AGENT, pos);
        }
        else if (loadedAgentAt(pos)) {
            eraseObjectAt(LOADED_AGENT, pos);
            setObjectTo(AGENT, pos);
        }
    } /** getRandomFreePos
                                                  *  @return A wall-free position on the map**/
    public GridPos getRandomFreePos() {
        int x, y;
        do {
            x = Math.abs(rand.nextInt()) % (m_size - 1);
            y = Math.abs(rand.nextInt()) % (m_size - 1);
        }
        while (wallAt(x, y) || blockadeAt(x, y));
        return new GridPos(x, y);
    } /** print
                                                      *  Prints the whole map to stdout
                                                      *   **/
    public void print() {
        if (m_size <= 0)
            return;
        int x, y;
        System.out.print("   "); // Header
        for (x = 0; x < m_size; x++) {
            if (x < 10)
                System.out.print("0");
            System.out.print(x + " ");
        }
        for (y = 0; y < m_size; y++) {
            System.out.println(" ");
            if (y < 10)
                System.out.print("0");
            System.out.print(y + " ");
            for (x = 0; x < m_size; x++) {
                GridPos pos = new GridPos(x, y);
                if (rawPrint) {
                    System.out.print(m_array[x][y]);
                    System.out.print("  ");
                }
                else {
                    if (loadedAgentAt(pos))
                        System.out.print("a");
                    else if (agentAt(pos))
                        System.out.print("A");
                    else if (dirtAt(pos))
                        System.out.print("P");
                    else if (destinationAt(pos))
                        System.out.print("D");
                    else if (wallAt(pos))
                        System.out.print("#");
                    else if (wallAt(pos))
                        System.out.print("x");
                    else
                        System.out.print(".");
                    System.out.print("  ");
                }
            }
        }
        System.out.println(" ");
    }

    public int getRows() {
        return m_rows;
    }
    public int getColumns() {
        return m_columns;
    } /*   public void removeDynamicObjects() {
                                                            // Copy maze
                                                            for (int i = 0; i < m_size; i++)
                                                                for (int j = 0; j < m_size; j++)
                                                                    switch (m_array[i][j]) {
                                                                    	case BLOCKADE:
                                                                    	 m_array[i][j] = NONE;
                                                                    	 break;
                                                                    }
                                                        }
                                                     */

    public void updateAgent(WAgent agent) { // Erase all agents
        for (int i = 0; i < m_rows; i++)
            for (int j = 0; j < m_columns; j++) {
                GridPos p = new GridPos(i, j);
                if (agentAt(p))
                    eraseObjectAt(AGENT, p);
            } // Set new Agent
        setObjectTo(AGENT, agent);
    }

    public void updateDirt(Collection dirt) { // Erase all passengers
        for (int i = 0; i < m_rows; i++)
            for (int j = 0; j < m_columns; j++) {
                GridPos p = new GridPos(i, j);
                if (dirtAt(p))
                    eraseObjectAt(PASSENGER, p);
                else if (destinationAt(p))
                    eraseObjectAt(DESTINATION, p);
            } // Set new passengers
        Iterator it = dirt.iterator();
        while (it.hasNext()) {
            Dirt p = (Dirt) it.next();
            setObjectTo(PASSENGER, p);
            setObjectTo(DESTINATION, p.getDestination());
        }
    }

//    public void updateAgent(WAgent agent) { // Erase all agents
//        for (int i = 0; i < m_rows; i++)
//            for (int j = 0; j < m_columns; j++) {
//                GridPos p = new GridPos(i, j);
//                if (agentAt(p))
//                    eraseObjectAt(AGENT, p);
//                if (loadedAgentAt(p))
//                    eraseObjectAt(LOADED_AGENT, p);
//            } // Set new Agent
//        if (agent.getLoad() != World.NO_PASSENGER)
//            setObjectTo(AGENT, agent);
//        else
//            setObjectTo(LOADED_AGENT, agent);
//    }
//
//    public void updatePassengers(Collection passengers) { // Erase all passengers
//        for (int i = 0; i < m_rows; i++)
//            for (int j = 0; j < m_columns; j++) {
//                GridPos p = new GridPos(i, j);
//                if (passengerAt(p))
//                    eraseObjectAt(PASSENGER, p);
//                else if (destinationAt(p))
//                    eraseObjectAt(DESTINATION, p);
//            } // Set new passengers
//        Iterator it = passengers.iterator();
//        while (it.hasNext()) {
//            Passenger p = (Passenger) it.next();
//            setObjectTo(PASSENGER, p);
//            setObjectTo(DESTINATION, p.getDestination());
//        }
//    }

    public void updatePath(Collection path) { //Erase old Path
        for (int i = 0; i < m_rows; i++)
            for (int j = 0; j < m_columns; j++) {
                GridPos p = new GridPos(i, j);
                if (markedPath1At(p))
                    eraseObjectAt(PATH1, p);
            }

        Iterator it = path.iterator();
        while (it.hasNext()) {
            GridPos pos = (GridPos) it.next();
            setObjectTo(Maze.PATH1, pos);
        }
    }

    public void updateExpandedNodes(Collection path) { //Erase old Path
        for (int i = 0; i < m_rows; i++)
            for (int j = 0; j < m_columns; j++) {
                GridPos p = new GridPos(i, j);
                if (markedPath2At(p))
                    eraseObjectAt(PATH2, p);
            }

        Iterator it = path.iterator();
        while (it.hasNext()) {
            GridPos pos = (GridPos) it.next();
            setObjectTo(Maze.PATH2, pos);
        }
    }

    public void updateBlockades(Collection blockades) { // Erase all blockades
        for (int i = 0; i < m_rows; i++)
            for (int j = 0; j < m_columns; j++) {
                GridPos p = new GridPos(i, j);
                if (blockadeAt(p))
                    eraseObjectAt(BLOCKADE, p);
            } // Set new blockades
        Iterator it = blockades.iterator();
        while (it.hasNext()) {
            GridPos b = (GridPos) it.next();
            setObjectTo(BLOCKADE, b);
        }
    }

    public GridPos getGridObject(int x, int y) {
        return (GridPos) m_GridObjects.get(new Point(x, y));
    }
    public GridPos getGridObject(GridPos pos) {
        return (GridPos) m_GridObjects.get(new Point(pos.m_x, pos.m_y));
    }
}