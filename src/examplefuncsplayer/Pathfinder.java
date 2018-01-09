package examplefuncsplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import battlecode.common.*;

public class Pathfinder {
	
	private RobotController rc;
	private Node one;
	private List<Node> frontier = new ArrayList<Node>(); 
	private List<Node> visited = new ArrayList<Node>();
	private List<Node> frontierBuffer = new ArrayList<Node>();
	private Map<Node, Node> cameFrom;
	
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
	
	private void runPathfind() throws GameActionException {
		Node start = Node.currentNode();
		Node goal = Node.getClosestNode(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]);
		
		initPathfind(start);
		initBuildFrontier();
		iterateFrontier(goal);
		ArrayList<Node> route = this.path(start, goal, this.cameFrom);
		moveToGoal(route);
		
	}
	
	private void moveToGoal(ArrayList<Node> path) throws GameActionException {
		Collections.reverse(path);
		
		for(Node node: path) {
			Move.move(node);
		}
		
	}
	
	private ArrayList<Node> path (Node start, Node goal, Map<Node, Node> cameFrom) {
		System.out.println("I am at {" + Node.currentNode().getX() + ", " + Node.currentNode().getY() + "}");
		Node pathNode = goal;
		System.out.println("I am going to {" + goal.getX() + ", " + goal.getY() + "}");
		ArrayList<Node> path = new ArrayList<Node>();
		
		while(pathNode != start) {
			System.out.println("pathNode is {" + pathNode.getX() + ", " + pathNode.getY() + "}");
			path.add(cameFrom.get(pathNode));
			pathNode = cameFrom.get(pathNode);
		}
		
		System.out.println("************");
		System.out.println("Path array:");
		
		for(Node node : path) {
			System.out.println("{" + node.getX() + ", " + node.getY() + "}");
		}
		
		return path;
	}
	
	private void initPathfind(Node start) {
		System.out.println("Running initPathfind()");
		System.out.println("Initializing cameFrom");
		cameFrom = new HashMap<Node, Node>();
		cameFrom.put(start, Node.currentNode());
		for(Node node: cameFrom.keySet()) {
			System.out.println("{" + node.getX() + ", " + node.getY() + "} : {" + cameFrom.get(node).getX() + ", " + cameFrom.get(node).getY() + "}");
		}
		System.out.println("Clearing frontier...");
		frontier.clear();
		System.out.println("...frontier clear!");
		frontier.add(Node.currentNode());
		System.out.println("Added currentNode to frontier: " + "{" + Node.currentNode().getX() + ", " + Node.currentNode().getY() + "}");
		System.out.println("frontier contains:");
		for(Node node : frontier) {
			System.out.println("{" + node.getX() + ", " + node.getY() + "}");
		}
		visited.add(Node.currentNode());
		System.out.println("Added currentNode to visited: " + "{" + Node.currentNode().getX() + ", " + Node.currentNode().getY() + "}");
		System.out.println("visited contains:");
		for(Node node : visited) {
			System.out.println("{" + node.getX() + ", " + node.getY() + "}");
		}
	}
	
	private void  initBuildFrontier() {
		System.out.println("Running buildFrontier()");
		for(Node node : Node.getSurroundingNodes()) {
			System.out.println("Building a frontier node at: {" + node.getX() + ", " + node.getY() + "}");
			frontier.add(node);
			cameFrom.put(node, Node.currentNode());
			System.out.println("cameFrom:");
			for(Node cameFromNode: cameFrom.keySet()) {
				System.out.println("{" + cameFromNode.getX() + ", " + cameFromNode.getY() + "} : {" + cameFrom.get(cameFromNode).getX() + ", " + cameFrom.get(cameFromNode).getY() + "}");
			}
			System.out.println("frontier contains:");
			for(Node frontierNode : frontier) {
				System.out.println("{" + frontierNode.getX() + ", " + frontierNode.getY() + "}");
			}
			rc.setIndicatorDot(node.getClosestMapLocation(), 100, 0, 0);
		}
		System.out.println("*********All INITIAL frontiers built*********");
		for(Node node : frontier) {
			System.out.println("{" + node.getX() + ", " + node.getY() + "}");
		}
		System.out.println("*********************************************");
	}
	
	private void buildFrontier(Node frontierNode) {
		System.out.println("...frontierNode passed: {" + frontierNode.getX() + ", " + frontierNode.getY() + "}");
		for(Node node : Node.getSurroundingNodes(frontierNode)) {
			System.out.println("Checking surrounding node {" + node.getX() + ", " + node.getY() + "}");
			if(!isInFrontier(node) && !isInFrontierBuffer(node)) {
				System.out.println("Frontier does not contain this node!");
				System.out.println("Adding {" + node.getX() + ", " + node.getY() + "} to the frontierBuffer");
				frontierBuffer.add(node);
				cameFrom.put(node, frontierNode);
				System.out.println("cameFrom:");
				for(Node cameFromNode: cameFrom.keySet()) {
					System.out.println("{" + cameFromNode.getX() + ", " + cameFromNode.getY() + "} : {" + cameFrom.get(cameFromNode).getX() + ", " + cameFrom.get(cameFromNode).getY() + "}");
				}
				System.out.println("frontierBuffer contains:");
				for(Node bufferNode : frontierBuffer) {
					System.out.println("{" + bufferNode.getX() + ", " + bufferNode.getY() + "}");
				}
				rc.setIndicatorDot(node.getClosestMapLocation(), 0, 0, 200);
			} else {
				System.out.println("Frontier contains this node!");
			}
		}
	}
	
	private boolean hasBeenVisited(Node nodeToCheck) {
		for(Node node: visited) {
			if(nodeToCheck.equals(node)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isInFrontier(Node nodeToCheck) {
		for(Node node: frontier) {
			if(nodeToCheck.equals(node)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isInFrontierBuffer(Node nodeToCheck) {
		for(Node node: frontierBuffer) {
			if(nodeToCheck.equals(node)) {
				return true;
			}
		}
		return false;
	}
	
	private void iterateFrontier(Node goal) {
		System.out.println("Running iterateFrontier()");
		Node breakNode = Node.currentNode();
		outerloop:
		for(Node node : frontier) {
			System.out.println("Currently iterating node: {" + node.getX() + ", " + node.getY() + "}");
			if(!hasBeenVisited(node)) {
				System.out.println("This node has not been visited.");
				System.out.println("Running BuildFrontier(node)...");
				buildFrontier(node);
				visited.add(node);
				System.out.println("Added {" + node.getX() + ", " + node.getY() + "}");
				System.out.println("visited contains:");
				for(Node visitedNode : visited) {
					System.out.println("{" + visitedNode.getX() + ", " + visitedNode.getY() + "}");
				}
			}
			
			if(node.equals(goal)) {
				System.out.println("*>*>*>*>*>*>*I found the goal node!");
				breakNode = node;
					break outerloop;
				} else {
				System.out.println("__________Visited contains this node!");
			}
		}
		
		int count = frontier.size();
		System.out.println("iterateFrontier() for loop (ln 79) complete, count = " + count);
		refreshBuffer();
		System.out.println("Checking count vs. frontier.size...");
		if (frontier.size() > count || !breakNode.equals(goal)) {
			System.out.println("-----Continue to iterate!-----");
			iterateFrontier(goal);
			} else {
			System.out.println("Frontier is complete!");
		}
	}
	
	private void refreshBuffer() {
		System.out.print("Running refreshBuffer()");
		System.out.print("refreshBuffer currently contains:");
		for(Node node: frontierBuffer) {
			System.out.println("{" + node.getX() + ", " + node.getY() + "}");
		}
		frontier.addAll(frontierBuffer);
		frontierBuffer.clear();
		System.out.println("frontierBuffer cleared!");
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


