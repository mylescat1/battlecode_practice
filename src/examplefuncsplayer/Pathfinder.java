package examplefuncsplayer;

import java.util.ArrayList;
import java.util.List;

import battlecode.common.*;

public class Pathfinder {
	
	private RobotController rc;
	private MapLocation one;
	private List<MapLocation> frontier = new ArrayList<MapLocation>(); 
	private List<MapLocation> visited = new ArrayList<MapLocation>();
	private float stride;
	private float pi = (float)Math.PI;
	private float[]directions = {2*pi,(7*pi)/4,(3*pi)/2,(5*pi)/4,pi,(3*pi)/4, pi/2, pi/4};
	
	protected Pathfinder(RobotController rc) {
		super();
		this.rc = rc;
		this.stride = rc.getType().strideRadius;
	}

	protected void pathfind() throws GameActionException {		
		initPathFind();
		buildFrontier();
	}
	
	private void initPathFind() {
		one = rc.getLocation();
		frontier.clear();
		visited.clear();	
		frontier.add(one);		
		visited.add(one);
	}
	
	private void buildFrontier() {
		
		for (float direction : directions) {
			MapLocation nextNode = one.add(direction, stride);
			if (!visited.contains(nextNode)) {
				frontier.add(nextNode);
			}
		}		
		
		for (MapLocation node : frontier) {
			rc.setIndicatorDot(node, 100, 0, 0);
		}
	}
}
