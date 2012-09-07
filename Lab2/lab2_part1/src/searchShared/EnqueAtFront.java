
package searchShared;



public class EnqueAtFront extends OrderingFunction {
    public  void  addNodeToQueue(SearchNode node, NodeQueue q){
        q.addNodeToFront(node);
    }
}
