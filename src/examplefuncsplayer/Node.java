package examplefuncsplayer;

import java.math.BigDecimal;

import battlecode.common.*;

public class Node extends Global {
	
	private int x, y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private static Node closestNode(MapLocation mapLocation) {
		
		int x = round(mapLocation.x, 1 );
		int y = round(mapLocation.y, 1 );
		
		return new Node(x, y);
	}
	
    private static int round(float value, int numberOfDigitsAfterDecimalPoint) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(numberOfDigitsAfterDecimalPoint,
                BigDecimal.ROUND_DOWN);
        return bigDecimal.intValue();
    }
    
    private static MapLocation nodeToMapLocation(Node node) {
    	
    	float x = (float) node.x;
    	float y = (float) node.y;
    	
    	return new MapLocation(x,y);
    }
    
    private static Node mapLocationToNode(MapLocation mapLocation) {
    	return closestNode(mapLocation);
    }
    
    public static boolean onNode(Node node) {
    	 	
    	if(rc.getLocation().distanceTo(nodeToMapLocation(node)) < 0.1) {
    		return true;
    	}
    	
    	return false;
    }
    
    public static Node currentNode() {
    	return closestNode(rc.getLocation());
    }
    
    public static Node getClosestNode(MapLocation mapLocation) {    	
    	return mapLocationToNode(mapLocation);
    }
    
    public static MapLocation getClosestMapLocation(Node node) {
    	return nodeToMapLocation(node);
    }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean equals(Node node) {
		
		if(this.x == node.x && this.y == node.y) {
			return true;
		}
		
		return false;
	}
   
}
