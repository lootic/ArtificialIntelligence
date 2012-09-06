

package priorityQueue;

/**
 * Interface for objects stored in a PriorityQueue.
 */
public interface Prioritized {
    /**
     * Get the priority of the object.
     */
    public abstract double getPriority ();
    
    public abstract boolean isEqualTo(Prioritized o);
}
