package examplefuncsplayer;

import battlecode.common.*;

public class Move extends Global {
		
	static void move(Node node) throws GameActionException {
		float x = (float)node.getX();
		float y = (float)node.getY();
		MapLocation point = new MapLocation(x,y);
		System.out.println(Clock.getBytecodesLeft());
		rc.setIndicatorDot(point, 100, 0, 0);
		rc.move(point);
	}
	
	static void moveByNodes(MapLocation location) throws GameActionException {
		Direction direction = rc.getLocation().directionTo(location);
		NodeVector vector = NodeVector.getClosestVector(direction);
		Node nextNode = Node.add(vector);
		Move.move(nextNode);
	}
}
