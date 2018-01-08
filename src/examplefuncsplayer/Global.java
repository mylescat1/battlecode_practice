package examplefuncsplayer;

import battlecode.common.*;

public class Global {

	static RobotController rc;
	static Pathfinder pathfinder;
	
	public static void init (RobotController theRc) throws GameActionException {
		
		rc = theRc;
		pathfinder = new Pathfinder(theRc);
	}
	
	public static void loop_common() throws GameActionException { //code that every robot will execute every turn.
		
		MapLocation enemy = rc.getInitialArchonLocations(rc.getTeam().opponent())[0];
		System.out.println(enemy.x + ", " + enemy.y);
		Move.moveByNodes(enemy);
		
		//pathfinder.initPathfind();
		
	}
}
