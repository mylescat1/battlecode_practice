package examplefuncsplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import battlecode.common.*;

public class Pathfinder {
	
	private RobotController rc;
	private MapLocation one;
	private List<MapLocation> frontier = new ArrayList<MapLocation>(); 
	private List<MapLocation> visited = new ArrayList<MapLocation>();
	ListIterator<MapLocation> iterFrontier = frontier.listIterator();
	ListIterator<MapLocation> iterVisited = visited.listIterator();
	private float stride, senseRange, frontierCount;
	private float pi = (float)Math.PI;
	private float[]directions = {2*pi,(7*pi)/4,(3*pi)/2,(5*pi)/4,pi,(3*pi)/4, pi/2, pi/4};
	
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
		
		while(iterFrontier.hasNext()){
			MapLocation nextNode = iterFrontier.next();
			System.out.println("I am here");
			buildFrontier(nextNode);
			//iterVisited.add(iterFrontier.next());
		}
	}
	
	private void buildFrontier(MapLocation node) {
		System.out.println("HERE NOW");
		for (float direction : directions) {
			MapLocation nextNode = node.add(direction, stride);
			//if (!visited.contains(nextNode) && !frontier.contains(nextNode)) {
				iterFrontier.add(nextNode);
				rc.setIndicatorDot(nextNode, 100, 0, 0);
			//}
		}		
	}
}


