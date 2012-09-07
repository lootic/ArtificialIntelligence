/******************************************************
 *
 * Project: javaRL
 * File: Passenger.java
 * @version 1.0
 * Date: 02.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description: Datastructire of a passenger
 * 
 ******************************************************/
package world;
import java.util.Random;

public class Dirt extends GridPos{
    public static final int MAX_TIME = 100;
    private GridPos m_destination;
    private int m_age;
    private int m_ID;

    /** 
     * Constructor
     **/
    public Dirt(int ID, int x, int y) {
    	super(x,y);
		m_ID = ID;
        m_destination = new GridPos();
 
        /// Generate random time of passenger
		Random r = new Random();
        m_age = Math.abs(r.nextInt()) % MAX_TIME;
    }
	public Dirt(int ID, GridPos pos) {
		super(pos);
		m_ID = ID;
		m_destination = new GridPos();
 
		/// Generate random time of passenger
		Random r = new Random();
		m_age = Math.abs(r.nextInt()) % MAX_TIME;
	}

	public Dirt(Dirt p) {
		super(p);	
		m_ID = p.m_ID;
		m_age = p.m_age;
		m_destination = new GridPos(p.m_destination);
	}
	
    public void setDestination(GridPos dest) {
    	m_destination.setPos(dest);
    }
	public void setDestination(int x, int y) {
		m_destination.setPos(x,y);
	}
    public GridPos getDestination() {
        return m_destination;
    }

	public int getID() {
		return m_ID;
	}
    public void growOld() {
        if (m_age > 0)
            m_age--;
    }
    public int age() {
        return m_age;
    }
    public boolean atDestination() {
        return this.equals(m_destination);
    }
	public String toString() {
		return "ID: "+ m_ID +
			   " Age: "+ m_age + 
			   " Position: " + super.toString() + 
			   " Destination: " + m_destination;
	}
}
