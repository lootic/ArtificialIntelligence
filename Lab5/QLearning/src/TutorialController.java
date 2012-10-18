public class TutorialController extends Controller {

    public SpringObject object;

    ComposedSpringObject cso;

    /* These are the agents senses (inputs) */
	DoubleFeature x; /* Positions */
	DoubleFeature y;
	DoubleFeature vx; /* Velocities */
	DoubleFeature vy;
	DoubleFeature angle; /* Angle */

    /* Example:
     * x.getValue() returns the vertical position of the rocket 
     */

	/* These are the agents actuators (outputs)*/
	RocketEngine leftRocket;
	RocketEngine middleRocket;
	RocketEngine rightRocket;

    /* Example:
     * leftRocket.setBursting(true) turns on the left rocket 
     */
	
	public void init() {
		cso = (ComposedSpringObject) object;
		x = (DoubleFeature) cso.getObjectById("x");
		y = (DoubleFeature) cso.getObjectById("y");
		vx = (DoubleFeature) cso.getObjectById("vx");
		vy = (DoubleFeature) cso.getObjectById("vy");
		angle = (DoubleFeature) cso.getObjectById("angle");

		leftRocket = (RocketEngine) cso.getObjectById("rocket_engine_left");
		rightRocket = (RocketEngine) cso.getObjectById("rocket_engine_right");
		middleRocket = (RocketEngine) cso.getObjectById("rocket_engine_middle");
		
		
	}

    public void tick(int currentTime) {
    	System.out.println(" vx: " + vx.getValue()+ " vy: " + vy.getValue()+" angle: " + angle.getValue()) ;
    	leftRocket.setBursting(false);
    	rightRocket.setBursting(false);
    	middleRocket.setBursting(false);

    	if (Math.abs(angle.getValue())==0.01){
    		
    	} else if (vx.getValue()>0.1){
    		rightRocket.setBursting(true);
    		middleRocket.setBursting(false);
    	} else if (vx.getValue()<-0.1){
    		leftRocket.setBursting(true);
    		middleRocket.setBursting(false);
    	} else if (angle.getValue()<-0.05){
    		leftRocket.setBursting(true);
    	} else if (angle.getValue()>+0.05){
    		rightRocket.setBursting(true);
    	}
    	if (vy.getValue()>0){
    		middleRocket.setBursting(true);
    	}

    }

}
