
package searchShared;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;



    public class NodeQueue {
        LinkedList<SearchNode> l;
        public NodeQueue() {
            l = new LinkedList<SearchNode>() ;
        }

        /**
         * Adds a node to the front of the queue
         */
        public void addNodeToFront(SearchNode node){
        	l.addFirst(node);
        }

        /**
         * Adds a node to the back of the queue
         */
        public void addNodeToBack(SearchNode node){
        	l.addLast(node);
        }
        
        /**
         * Returns true if the queue contains the given node
         */
        public boolean contains(SearchNode node) {
        	return l.contains(node);
        }

        /**
         * Removes the first node
         */
        public SearchNode removeFirst(){
            return (l.removeFirst());
        }

        /**
         * Removes the last node
         */
        public SearchNode  removeLast(){
            return (l.removeLast());
        }

        /**
         * Returns first node without removing
         */
        public SearchNode peekAtFront(){
            return (l.getFirst()) ;
        }

        /**
         * Returns last node without removing
         */
        public SearchNode peekAtBack(){
            return (l.getLast());
        }

        /**
         * Returns true if the queue is empty
         */
        public boolean isEmpty(){
            return l.isEmpty();
        }
        
        /**
         * Returns the size of the queue 
         */
        public int size(){
            return l.size();
        }
        
        public void  sort(Comparator c){
            Collections.sort(l,c);

        }
        
        public ArrayList<SearchNode> toList() {
        	return new ArrayList<SearchNode>(l);
        }

    }
