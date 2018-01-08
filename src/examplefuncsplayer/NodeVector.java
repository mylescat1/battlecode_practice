package examplefuncsplayer;

import battlecode.common.Direction;

public strictfp class NodeVector {
	
	private float direction;
	private float edgeLength;
	private int index;
	public static final NodeVector N = getN();
	public static final NodeVector NE = getNE();
	public static final NodeVector E= getE();
	public static final NodeVector SE = getSE();
	public static final NodeVector S = getS();
	public static final NodeVector SW = getSW();
	public static final NodeVector W = getW();
	public static final NodeVector NW = getNW();
	
	private NodeVector(float direction, float edgeLength, int index) {
		this.direction = direction;
		this.edgeLength = edgeLength;
		this.index = index;
	}

	private static NodeVector getN() {
		return new NodeVector((float) Math.PI/2, BotConstants.CARDINAL, 0);
	}

	private static NodeVector getNE() {
		return new NodeVector((float) (Math.PI/4), BotConstants.DIAG, 1 );
	}

	private static NodeVector getE() {
		return new NodeVector((float) (Math.PI*2), BotConstants.CARDINAL, 2 );
	}

	private static NodeVector getSE() {
		return new NodeVector((float) ((7*Math.PI)/4), BotConstants.DIAG, 3 );
	}

	private static NodeVector getS() {
		return new NodeVector((float) ((3*Math.PI)/2), BotConstants.CARDINAL, 4 );
	}

	private static NodeVector getSW() {
		return new NodeVector((float) ((5*Math.PI)/4), BotConstants.DIAG, 5 );
	}

	private static NodeVector getW() {
		return new NodeVector((float) Math.PI, BotConstants.CARDINAL, 6 );
	}

	private static NodeVector getNW() {
		return new NodeVector((float) ((3*Math.PI)/4), BotConstants.DIAG, 7);
	}
	
	private static boolean isBetween(float x, float lower, float upper) {
		return lower <= x && x < upper;
	}	
	
	private static int calculateIndexClockwise(NodeVector nodeVector, int index) {
		
		if(nodeVector.getIndex() + index > 7) {
			return (nodeVector.getIndex() + index) - 8;
		}
		return nodeVector.getIndex() + index;
	}
	
	private static int calculateIndexCounterClockwise(NodeVector nodeVector, int index) {
		
		if(nodeVector.getIndex() - index < 0) {
			return (nodeVector.getIndex() - index) + 8;
		}
		return nodeVector.getIndex() - index;	
	}

	public float getRadians() {
		return this.direction;
	}
	
	public float getEdgeLength() {
		return this.edgeLength;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public static NodeVector[] getAllVectors() {
		NodeVector[] allVectors = {NodeVector.N, NodeVector.NE, NodeVector.E, NodeVector.SE, NodeVector.S, NodeVector.SW, NodeVector.W, NodeVector.NW};	
		return allVectors;
	}
	
	public static NodeVector getClosestVector(Direction direction) {
		
		float radians = direction.radians;
		
		if(isBetween(radians, BotConstants.EIGHT, BotConstants.ONE)) {
			return E;
		}
		else if(isBetween(radians, BotConstants.ONE, BotConstants.TWO)) {
			return NE;
		}
		else if(isBetween(radians, BotConstants.TWO, BotConstants.THREE)) {
			return N;
		}
		else if(isBetween(radians, BotConstants.THREE, BotConstants.FOUR)) {
			return NW;
		}
		else if(isBetween(radians, BotConstants.FOUR, BotConstants.FIVE)) {
			return W;
		}
		else if(isBetween(radians, BotConstants.FIVE, BotConstants.SIX)) {
			return SW;
		}
		else if(isBetween(radians, BotConstants.SIX, BotConstants.SEVEN)) {
			return S;
		}
		else if(isBetween(radians, BotConstants.SEVEN, BotConstants.EIGHT)) {
			return SE;
		}		
		else {
			throw new ArithmeticException("These radians don't fit in range.");
		}
	}
	
	public static NodeVector adjustVectorClockwise(NodeVector currentVector, int steps) {
		
		int newIndex = calculateIndexClockwise(currentVector, steps%8);
		return getAllVectors()[newIndex];
	}
	
	public static NodeVector adjustVectorCounterClockwise(NodeVector currentVector, int steps) {
		
		int newIndex = calculateIndexCounterClockwise(currentVector, steps%8);
		return getAllVectors()[newIndex];
	}

}
