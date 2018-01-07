package examplefuncsplayer;

import java.math.BigDecimal;

import battlecode.common.*;

public class Node extends Global {
	
	/*
	 * Class used to build nodes for edge node graph.
	 */
	
	private int x, y;
	
	Node(int x, int y) { //Use to instantiate a node object.
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Private methods, do not worry about these. ------------------------------------
	 */
	
	private static Node closestNode(MapLocation mapLocation) {
		
		int x = closestInteger(mapLocation.x);
		int y = closestInteger(mapLocation.y);
		
		return new Node(x, y);
	}
	
	private static int closestInteger(float x) {
		
		int xInt = round(x,1);
	    int c1 = xInt - (xInt % BotConstants.DEFAULT_EDGE_LENGTH);
	    int c2 = (xInt + BotConstants.DEFAULT_EDGE_LENGTH) - (xInt % BotConstants.DEFAULT_EDGE_LENGTH);
	    if (xInt - c1 > c2 - xInt) {
	        return c2;
	    } else {
	        return c1;
	    }
	}
	
    @SuppressWarnings("deprecation")
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
    
    /*
     * End private methods. -------------------------------------------------------------
     */
    
    
    /*
     * Static methods - these should be called as a class method i.e. Node.onNode(node) or Node.currentNode().
     */
    
    public static boolean onNode() { // returns true if a bot is on a node.
    	
    	Node closestNode = closestNode(rc.getLocation());
    	if(rc.getLocation().distanceTo(nodeToMapLocation(closestNode)) < BotConstants.ON_NODE_PRECISION) {
    		return true;
    	}
    	
    	return false;
    }
    
    public static Node currentNode() {
    	if(!onNode()) {
    		throw new ArithmeticException("Bot not currently on node.");
    	}
		return closestNode(rc.getLocation());
    }
    
    public static Node getClosestNode() {
    	return mapLocationToNode(rc.getLocation());
    }
    
    public static Node getClosestNode(MapLocation mapLocation) {    	
		return mapLocationToNode(mapLocation);
    }
    
    public static MapLocation getClosestMapLocation(Node node) {
		return nodeToMapLocation(node);
    }
    
	public static Node add(NodeVector direction) {
    	float dir = direction.getRadians();
    	float distance = direction.getEdgeLength();
    	System.out.println(distance + "d");
    	MapLocation mapLoc = rc.getLocation().add(dir, distance);
    	
    	return getClosestNode(mapLoc);
	}
	
    public static Node add(int edges, NodeVector direction) { //will add to current locations node of calling bot.
    	
    	float dir = direction.getRadians();
    	float distance = direction.getEdgeLength();
    	MapLocation mapLoc = rc.getLocation().add(dir, distance*edges);
    	
    	return getClosestNode(mapLoc);
    }
    
    public static Node add(Node node, int edges, NodeVector direction) { //will add to the location of the node specified.
	    
    	float dir = direction.getRadians();
    	float distance = direction.getEdgeLength();
    	MapLocation nodeMapLoc = getClosestMapLocation(node);
    	MapLocation mapLoc = nodeMapLoc.add(dir, distance*edges);
    	
    	return getClosestNode(mapLoc);
    }
    
    public static Node[] getSurroundingNodes(Node node) { // Gets the surrounding nodes of a specified node.
    		
		Node[] surroundingNodes = new Node[8];
		int i = 0;
		for(NodeVector vector : BotConstants.ALL_VECTORS) {
			surroundingNodes[i] = Node.add(node, BotConstants.DEFAULT_EDGE_LENGTH, vector);
			i++;
		}
		return surroundingNodes;
    }
    
    public static Node[] getSurroundingNodes() { // Gets the surrounding nodes of your current node.
		
		Node currentNode = Node.currentNode();
		Node[] surroundingNodes = new Node[8];
		int i = 0;
		for(NodeVector vector : BotConstants.ALL_VECTORS) {
			surroundingNodes[i] = Node.add(currentNode, BotConstants.DEFAULT_EDGE_LENGTH, vector);
			i++;
		}
		return surroundingNodes;
    }
    
    /*
     * End static methods.
     */
    
    /*
     * Start non-static methods. These should be called as an instance method i.e. node.getX() or node.onCurrent()
     */

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean amIOnThisNode() {
    	if(rc.getLocation().distanceTo(nodeToMapLocation(this)) < BotConstants.ON_NODE_PRECISION) {
    		return true;
    	}
    	
    	return false;
	}
	
	public MapLocation getClosestMapLocation() {
		return nodeToMapLocation(this);
	}
	
	public boolean equals(Node node) {
		
		if(this.x == node.x && this.y == node.y) {
			return true;
		}
		
		return false;
	}
   
	/*
	 * End non-static methods.
	 */
	
	/*
	 * End class.
	 */
}
