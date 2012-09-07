/******************************************************
 *
 * Project: searchDemo
 * File: ValueFunction.java
 * @version 1.0
 * Date: 03.05.2004
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description:
 * 
 *  
 ******************************************************/
package viewer;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedList;

import world.GridPos;
import world.World;

public class ValueFunction implements ViewableFunction {
	private World m_World = null; 
    /** 
     * 
     **/
    public ValueFunction(World world) {
        super();
        m_World = world;
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see viewer.ViewableFunction#getValues()
     */
    public Collection getValues() {
        System.err.println("\"Value Function\" has not been implemented yet!");
        
		int h = m_World.getHeight();
		int w = m_World.getWidth();

		LinkedList l = new LinkedList();
        
		for (int x=0; x<w; x++)
			for (int y=0; y<h; y++) {
				GridPos pos = m_World.m_Maze.getGridObject(x,y);
//				double value = ((double)x/(double)h) + ((double)y/(double)w);
				double value = ((double)x/(double)w) * 255.0 + ((double)y/(double)h) * 255.0;
				value = value/2.0;
				
				Color c = new Color(0,(int)value,0);
				l.add(pos);
				l.add(c);
				l.add(new Double(value));				
			}
		// TODO Auto-generated constructor stub
        return l;
    }
}
