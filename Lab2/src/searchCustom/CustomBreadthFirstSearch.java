package searchCustom;

import java.util.Random;

public class CustomBreadthFirstSearch  extends CustomGraphSearch{

	public   CustomBreadthFirstSearch(int maxDepth){
		super(new Random().nextBoolean()); // Temporary random choice, you need to pick true or false!
		System.out.println("Change line above in \"CustomBreadthFirstSearch.java\"!");
	}
};
