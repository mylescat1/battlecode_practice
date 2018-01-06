package examplefuncsplayer;

import battlecode.common.*;

public class Move extends Global {

	static void move(Node node) throws GameActionException {
		float x = (float)node.getX();
		float y = (float)node.getY();
		MapLocation point = new MapLocation(x,y);
		rc.move(point);
	}
	
}
