package examplefuncsplayer;

import battlecode.common.*;

public class Global {

	static RobotController rc;
	
	public static void init (RobotController theRc) throws GameActionException {
		
		rc = theRc;
		
	}
	
	public static void loop_common() throws GameActionException { //code that every robot will execute every turn.
		
		Move.tryMove(Move.randomDirection());
		
	}
	
}
