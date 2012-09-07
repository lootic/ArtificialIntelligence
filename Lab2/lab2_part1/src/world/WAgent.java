package world;
/******************************************************
 *
 * Project: javaRL
 * File: Agent.java
 * @version 1.0
 * Date: 03.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description:
 * 
 *  
 ******************************************************/
public class WAgent extends GridPos {
    final static int NO_LOAD = -1;
    // The agents ID
    private int m_ID;
    // The loaded passenger
    private int m_Load = NO_LOAD;

    public WAgent(int x, int y) {
        super(x, y);
    }
	public WAgent(GridPos p) {
		super(p);
	}
	public WAgent(WAgent a) {
		super(a);
		m_ID = a.m_ID;
		m_Load = a.m_Load;
	}

    /** getM_ID
     *  @return the agents ID**/
    public int getID() {
        return m_ID;
    }

    /** getLoad
     *  @return The loaded passenger. -1 means no load**/
    public int getLoad() {
        return m_Load;
    }
    
    /** setLoad
     *  @param load - the passenger to be loaded**/
    public void setLoad(int load) {
        m_Load = load;
    }
    
    /** hasLoad
     *  @return TRUE if a passenger is loaded**/
    public boolean hasLoad() {
        if (m_Load != NO_LOAD)
            return true;
        else
            return false;
    }

	/** unLoad
     *  Unload a passenger **/
    public void unLoad() {
		m_Load = NO_LOAD;
	}
}
