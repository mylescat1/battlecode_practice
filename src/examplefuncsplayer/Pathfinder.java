package examplefuncsplayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import battlecode.common.*;

public class Pathfinder {
	
	private RobotController rc;
	private Node one;
	private List<Node> frontier = new ArrayList<Node>(); 
	private List<Node> visited = new ArrayList<Node>();
	private float stride, senseRange, frontierCount;
	private float pi = (float)Math.PI;
	private float[]directions = {2*pi,(7*pi)/4,(3*pi)/2,(5*pi)/4, pi, 3*pi/4, pi/2, pi/4};
	private int count;
	
	protected Pathfinder(RobotController rc) {
		this.rc = rc;
		this.stride = rc.getType().strideRadius;
		this.senseRange = rc.getType().sensorRadius;
	}
	
	protected void initPathfind() throws GameActionException {
		
		if(!Node.onNode()) {
			System.out.println("Not on a node!");
			Move.move(Node.getClosestNode());
		}
		else {
			for(Node node : Node.getSurroundingNodes() ) {
				System.out.println(Clock.getBytecodesLeft());
				System.out.println(node.getX() + ", " + node.getY());
			}
		}
	}
}


