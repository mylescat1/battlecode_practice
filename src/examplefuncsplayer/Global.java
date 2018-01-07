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
		
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 1).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 2).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 3).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 4).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 5).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 6).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 7).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 8).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 9).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 10).toString());
		System.out.println(Move.adjustVectorClockwise(NodeVector.N, 11).toString());
		
		MapLocation enemy = rc.getInitialArchonLocations(rc.getTeam().opponent())[0];
		System.out.println(enemy.x + ", " + enemy.y);
		Move.moveByNodes(enemy);
		
		//pathfinder.initPathfind();
		
	}
}
