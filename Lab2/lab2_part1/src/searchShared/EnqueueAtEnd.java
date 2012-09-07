
package searchShared;

import java.util.ArrayList;


public class EnqueueAtEnd extends OrderingFunction{
     public  void  addNodeToQueue(SearchNode node, NodeQueue q){
         q.addNodeToBack(node);
     }

}
