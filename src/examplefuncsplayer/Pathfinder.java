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

	protected void pathfind() throws GameActionException {		
		initPathFind();
		nodeRecursion();
	}
	
	private void initPathFind() {
		count = 0;
		one = Node.currentNode();
		frontier.clear();
		visited.clear();	
		frontier.add(one);
	}
	
	private void nodeRecursion() {
		for(int i = 0; i < frontier.size(); i++) {
			System.out.println(i);
			Node nextNode  = frontier.get(i);
			if(!visited.contains(nextNode)) {
				buildFrontier(nextNode);
				visited.add(nextNode);
				count++;
			}		
		}	
	}
	
	private void buildFrontier(Node node) {
		for (float direction : directions) {
			MapLocation newNode = node.add(direction, stride);
			if (!frontier.contains(newNode)) {
				frontier.add(newNode);
				System.out.println("Frontier size is" + frontier.size());
				rc.setIndicatorDot(newNode, 100, 0, 0);
			}
		}
		Clock.yield();
	}
}


