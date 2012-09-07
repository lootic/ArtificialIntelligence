package priorityQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Priority queue.  Objects stored in a priority queue must implement 
 * the Prioritized interface.
 * 
 */
public class PriorityQueue {

    private LinkedList q; // the queue of elements

    /**
     * Make an empty PriorityQueue.
     */
    public PriorityQueue() {
        q = new LinkedList();
    }

    /**
     * Put an object on the queue.  
     * Returns FALSE if object already there
     * 
     * @param x object to put on the queue 
     */
    public synchronized boolean put(Prioritized x) {

        if (x == null)
            return false;

        if (isEmpty()) {
            q.add(x);
            return true;
        }

        Iterator it = q.listIterator();
        int index = 0;
        while (it.hasNext()) {
            Prioritized p = (Prioritized) it.next();
            if (x.isEqualTo(p))
                return false;

            if (x.getPriority() < p.getPriority()) {
                q.add(index, x);
                return true;
            }
            /*if (x.getPriority() == p.getPriority()) {
                Random r = new Random();
                if (r.nextBoolean()) {
                    q.add(index, x);
                    return true;
                }
            }*/
            index++;
        }
        q.add(index, x);
        return true;
    }

    /**
     * Get object with lowest priority from queue.
     * @return object with lowest priority, or null if queue is empty
     */
    public synchronized Object getMin() {
        return !isEmpty() ? q.getFirst() : null;
    }

    /**
     * Get and delete the object with lowest priority.
     * @return object with lowest priority, or null if queue is empty
     */
    public synchronized Object deleteMin() {
        if (isEmpty())
            return null;
        Object obj = q.getFirst();
        q.removeFirst();
        return obj;
    }

    /**
     * Remove all objects from queue.
     */
    public synchronized void clear() {
        q.clear();
    }

    /**
     * Get number of objects in queue.
     * @return number of objects
     */
    public synchronized int size() {
        return q.size();
    }

    /**
     * Test whether queue is empty.
     * @return true iff queue is empty.
     */
    public synchronized boolean isEmpty() {
        return q.isEmpty();
    }

    /**
     * Rebuild priority queue in case the priorities of its elements 
     * have changed since they were inserted.  If the priority of
     * any element changes, this method must be called to update
     * the priority queue.
     */
    /*    public synchronized void update() {
        	
        	Iterator it = q.listIterator();
        	
            for (int i = (q.size() / 2) - 1; i >= 0; --i)
                heapify(i);
        }
    */
    /*******************************************************************************
     *******************************************************************************
     **  Deletes an object from the queue
     *   
     **  @param p - The object to be deleted
     *	 @param all - If true, the whole list is searched for double occurence
     **  @return true if found
     **
     *******************************************************************************
     *******************************************************************************/
    public boolean delete(Prioritized p, boolean all) {
        boolean found = false;
        Iterator it = q.listIterator();
        while (it.hasNext()) {
            Prioritized p2 = (Prioritized) it.next();
            if (p2.isEqualTo(p)) {
                it.remove();
                it = q.listIterator();
                if (!all)
                    return true;
                found = true;
            }
        }
        return found;
    }

    public Collection delete(Prioritized p) {
        ArrayList ret = new ArrayList();
        Iterator it = q.listIterator();
        while (it.hasNext()) {
            Prioritized p2 = (Prioritized) it.next();
            if (p2.isEqualTo(p)) {
                it.remove();
                ret.add(p2);
            }
        }
        return ret;
    }

    /* Return the priority of the element at position i.  For convenience,
       positions beyond the end of the table have infinite priority.
    */
    public double getPriority(int i) {
        return ((Prioritized) q.get(i)).getPriority();
    }

    public String toString() {
        String str = new String("PriorityQueue: ");
        Iterator it = q.listIterator();
        int index = 0;
        if (isEmpty())
            str += "Empty!";
        str += "\n";
        while (it.hasNext())
            str += "elements()[" + (index++ +1) + "] = " + ((Prioritized) it.next()) + "\n";
        return str;
    }

    public static void dump(PriorityQueue p) {
        System.out.println(p.toString());
    }
}
