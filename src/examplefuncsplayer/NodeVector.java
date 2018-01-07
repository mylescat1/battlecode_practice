package examplefuncsplayer;

import battlecode.common.Direction;

public strictfp enum NodeVector {
	
	N ( (float) Math.PI/2, BotConstants.CARDINAL ),
	NE ( (float) (Math.PI/4), BotConstants.DIAG ),
	E ( (float) (Math.PI*2), BotConstants.CARDINAL ),
	SE ( (float) ((7*Math.PI)/4), BotConstants.DIAG ),
	S ( (float) ((3*Math.PI)/2), BotConstants.CARDINAL ),
	SW ( (float) ((5*Math.PI)/4), BotConstants.DIAG ),
	W ( (float) Math.PI, BotConstants.CARDINAL ),
	NW ( (float) ((3*Math.PI)/4), BotConstants.DIAG );
	
	private float direction;
	private float edgeLength;
	
	private NodeVector(float direction, float edgeLength) {
		this.direction = direction;
		this.edgeLength = edgeLength;
	}
	
	private static boolean isBetween(float x, float lower, float upper) {
		return lower <= x && x < upper;
	}
	
	public float getRadians() {
		return this.direction;
	}
	
	public float getEdgeLength() {
		return this.edgeLength;
	}
	
	public NodeVector getClosestVector(Direction direction) {
		
		direction.
		
	}
	

	
}
