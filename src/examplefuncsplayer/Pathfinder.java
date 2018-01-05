package examplefuncsplayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import battlecode.common.*;

public class Pathfinder {
	
	private RobotController rc;
	private MapLocation one;
	private List<MapLocation> frontier = new ArrayList<MapLocation>(); 
	private List<MapLocation> visited = new ArrayList<MapLocation>();
	private float stride, senseRange, frontierCount;
	private float pi = (float)Math.PI;
	private float[]directions = {2*pi,(7*pi)/4,(3*pi)/2,(5*pi)/4, pi,-((7*pi)/4),-((3*pi)/2),-((5*pi)/4)};
	
	protected Pathfinder(RobotController rc) {
		super();
		this.rc = rc;
		this.stride = rc.getType().strideRadius;
		this.senseRange = rc.getType().sensorRadius;
	}

	protected void pathfind() throws GameActionException {		
		initPathFind();
		nodeRecursion();
	}
	
	private void initPathFind() {
		one = rc.getLocation();
		frontier.clear();
		visited.clear();	
		frontier.add(one);
	}
	
	private void nodeRecursion() {
		for(int i = 0; i < frontier.size(); i++) {
			System.out.println(i);
			MapLocation nextNode  = frontier.get(i);
			if(!visited.contains(nextNode) && (nextNode.distanceTo(one) < (stride * 3))) {
				buildFrontier(nextNode);
				visited.add(nextNode);
			}		
		}
	
	}
	
	private void buildFrontier(MapLocation node) {
		for (float direction : directions) {
			MapLocation newNode = node.add(direction, stride);
			if (!frontier.contains(newNode)) {
				frontier.add(newNode);
				System.out.println("added");
				rc.setIndicatorDot(newNode, 100, 0, 0);
			}
		}		
	}
}


