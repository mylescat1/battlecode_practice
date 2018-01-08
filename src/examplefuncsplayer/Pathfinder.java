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
	private List<Node> frontierBuffer = new ArrayList<Node>();
	
	protected Pathfinder(RobotController rc) {
		this.rc = rc;
	}
	
	private boolean assertOnNode() throws GameActionException {
		if(!Node.onNode()) {
			System.out.println("Not on a node!");
			Move.move(Node.getClosestNode());
			return false;
		}
		return true;
	}
	
	private void runPathfind() {
		initPathfind();
		buildFrontier();
		iterateFrontier();
	}
	
	private void initPathfind() {
		frontier.clear();
		frontier.add(Node.currentNode());
		visited.add(Node.currentNode());
	}
	
	private void buildFrontier() {
		for(Node node : Node.getSurroundingNodes()) {
			frontier.add(node);
			rc.setIndicatorDot(node.getClosestMapLocation(), 100, 0, 0);
		}
	}
	
	private void buildFrontier(Node frontierNode) {
		for(Node node : Node.getSurroundingNodes(frontierNode)) {
			if(!frontier.contains(node)) {
				frontierBuffer.add(node);
				rc.setIndicatorDot(node.getClosestMapLocation(), 0, 0, 200);
			}
		}
	}
	
	private void iterateFrontier() {
		for(Node node : frontier) {
			if(!visited.contains(node)) {
				buildFrontier(node);
				visited.add(node);
			}
		}
		int count = frontier.size();
		refreshBuffer();
		if (frontier.size() > count) {
			iterateFrontier();
		}
	}
	
	private void refreshBuffer() {
		frontier.addAll(frontierBuffer);
		frontierBuffer.clear();
	}
	
	protected void pathfind() throws GameActionException {
		
		if(assertOnNode()) {			
			runPathfind();
		}
		else {
			Clock.yield();
		}
	}
}


