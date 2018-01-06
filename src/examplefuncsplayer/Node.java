package examplefuncsplayer;

import java.math.BigDecimal;

import battlecode.common.*;

public class Node extends Global {
	
	private int x, y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	static Node closestNode(MapLocation mapLocation) {
		
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
    
    public static boolean onNode(Node node) {
    	 	
    	if(rc.getLocation().distanceTo(nodeToMapLocation(node)) < rc.getType().bodyRadius) {
    		return true;
    	}
    	
    	return false;
    }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
   
}
