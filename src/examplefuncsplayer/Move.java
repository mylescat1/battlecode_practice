package examplefuncsplayer;

import battlecode.common.*;

public class Move extends Global {

	private static boolean isBetween(float x, float lower, float upper) {
		return lower <= x && x < upper;
	}
		
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
		NodeVector vector = getClosestVector(direction);
		System.out.println(vector.name());
		Node nextNode = Node.add(vector);
		Move.move(nextNode);
	}
	
	private static NodeVector getClosestVector(Direction direction) {
		
		float radians = direction.radians;
		
		if(isBetween(radians, BotConstants.EIGHT, BotConstants.ONE)) {
			return (NodeVector) BotConstants.ALL_VECTORS.toArray()[2];
		}
		else if(isBetween(radians, BotConstants.ONE, BotConstants.TWO)) {
			return (NodeVector) BotConstants.ALL_VECTORS.toArray()[1];
		}
		else if(isBetween(radians, BotConstants.TWO, BotConstants.THREE)) {
			return (NodeVector) BotConstants.ALL_VECTORS.toArray()[0];
		}
		else if(isBetween(radians, BotConstants.THREE, BotConstants.FOUR)) {
			return (NodeVector) BotConstants.ALL_VECTORS.toArray()[7];
		}
		else if(isBetween(radians, BotConstants.FOUR, BotConstants.FIVE)) {
			return (NodeVector) BotConstants.ALL_VECTORS.toArray()[6];
		}
		else if(isBetween(radians, BotConstants.FIVE, BotConstants.SIX)) {
			return (NodeVector) BotConstants.ALL_VECTORS.toArray()[5];
		}
		else if(isBetween(radians, BotConstants.SIX, BotConstants.SEVEN)) {
			return (NodeVector) BotConstants.ALL_VECTORS.toArray()[4];
		}
		else if(isBetween(radians, BotConstants.SEVEN, BotConstants.EIGHT)) {
			return (NodeVector) BotConstants.ALL_VECTORS.toArray()[3];
		}		
		else {
			throw new ArithmeticException("These radians don't fit in range.");
		}
	}
	
}
