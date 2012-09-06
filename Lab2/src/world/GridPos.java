package world;


/******************************************************
 *
 * Project: javaRL
 * File: GridPos.java
 * @version 1.0
 * Date: 02.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description: Implements a grid position
 * 
 *  
 ******************************************************/

public class GridPos {
    public int m_x, m_y;

    public GridPos(int x, int y) {
        m_x = x;
        m_y = y;
    }
    public GridPos(GridPos p) {
        m_x = p.m_x;
        m_y = p.m_y;
    }
    public GridPos() {
        m_x = 0;
        m_y = 0;
    }

    /** getX
     *  @return - the X-Position on the grid**/
    public int getX() {
        return m_x;
    }

    /** getY
     *  @return - the Y-Position on the grid**/
    public int getY() {
        return m_y;
    }

    /** setPos
     *  @param p the new position**/
    public void setPos(GridPos p) {
        m_x = p.m_x;
        m_y = p.m_y;
    }
    /** setPos
     *  @param x 
     *  @param y the new position**/
    public void setPos(int x, int y) {
        m_x = x;
        m_y = y;
    }
    /** atPos
     *  @param p
     *  @return TRUE if the agent is at p**/
    public boolean atPos(GridPos p) {
        return this.equals(p);
    }

    public boolean connected(GridPos o) {
        GridPos p = (GridPos) o;
        if (p == null || this.equals(o))
            return false;

        if ((m_x == p.m_x - 1 && m_y == p.m_y)
            || (m_x == p.m_x + 1 && m_y == p.m_y)
            || (m_x == p.m_x && m_y == p.m_y - 1)
            || (m_x == p.m_x && m_y == p.m_y + 1))
            return true;
        else
            return false;
    }

    /** adjacentPos
     *  @param p - Pos to be checked
     *  @return TRUE if pos is adjacent p**/
    public boolean adjacent(GridPos o) {
        GridPos p = (GridPos) o;
        if (p == null)
            return false;

        if ((m_x == p.m_x - 1 && m_y == p.m_y - 1)
            || (m_x == p.m_x - 1 && m_y == p.m_y + 1)
            || (m_x == p.m_x - 1 && m_y == p.m_y)
            || (m_x == p.m_x + 1 && m_y == p.m_y - 1)
            || (m_x == p.m_x + 1 && m_y == p.m_y + 1)
            || (m_x == p.m_x + 1 && m_y == p.m_y)
            || (m_x == p.m_x && m_y == p.m_y - 1)
            || (m_x == p.m_x && m_y == p.m_y + 1)
            || (m_x == p.m_x && m_y == p.m_y))
            return true;
        else
            return false;
    }

    /** distance
      *  @param p
      *  @return The eucledian distance between this and p**/
    public double getDistanceEuclidian(GridPos p) {
        return Math.sqrt((m_x - p.m_x) * (m_x - p.m_x) + (m_y - p.m_y) * (m_y - p.m_y));
    }

    public double getDistanceManhattan(GridPos p) {
        return Math.abs(m_x - p.m_x)  + Math.abs(m_y - p.m_y);
    }

    
    public String toString() {

        String result = "(";
        if (m_x < 10)
            result += "0" + m_x;
        else
            result += m_x;
        result += ",";
        if (m_y < 10)
            result += "0" + m_y;
        else
            result += m_y;
        result += ")";
        return result;
    }

      /** equals
     *  @param p
     *  @return TRUE if p=this**/
    public boolean equals(GridPos o) {
        GridPos p = (GridPos) o;
        if (p == null)
            return false;
        if (m_x == p.m_x && m_y == p.m_y)
            return true;
        else
            return false;
    }

    // To make hash sets work...
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m_x;
		result = prime * result + m_y;
		return result;
	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		GridPos other = (GridPos) obj;
//		if (m_x != other.m_x)
//			return false;
//		if (m_y != other.m_y)
//			return false;
//		return true;
//	}
};
