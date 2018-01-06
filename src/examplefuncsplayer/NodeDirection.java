package examplefuncsplayer;

public strictfp enum NodeDirection {
	
	N ( (float) Math.PI/2, GameConstants.cardinal ),
	NE ( (float) (Math.PI/4), GameConstants.diag ),
	E ( (float) (Math.PI*2), GameConstants.cardinal ),
	SE ( (float) ((7*Math.PI)/4), GameConstants.diag ),
	S ( (float) ((3*Math.PI)/2), GameConstants.cardinal ),
	SW ( (float) ((5*Math.PI)/4), GameConstants.diag ),
	W ( (float) Math.PI, GameConstants.cardinal ),
	NW ( (float) ((3*Math.PI)/4), GameConstants.diag );
	
	private float direction;
	private float edgeLength;
	private NodeDirection(float direction, float edgeLength) {
		this.direction = direction;
		this.edgeLength = edgeLength;
	}
	
	public float getRadians() {
		return this.direction;
	}
	
	public float getEdgeLength() {
		return this.edgeLength;
	}
	
}
