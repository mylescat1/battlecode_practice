package examplefuncsplayer;

public strictfp enum NodeVector {
	
	N ( (float) Math.PI/2, GameConstants.CARDINAL ),
	NE ( (float) (Math.PI/4), GameConstants.DIAG ),
	E ( (float) (Math.PI*2), GameConstants.CARDINAL ),
	SE ( (float) ((7*Math.PI)/4), GameConstants.DIAG ),
	S ( (float) ((3*Math.PI)/2), GameConstants.CARDINAL ),
	SW ( (float) ((5*Math.PI)/4), GameConstants.DIAG ),
	W ( (float) Math.PI, GameConstants.CARDINAL ),
	NW ( (float) ((3*Math.PI)/4), GameConstants.DIAG );
	
	private float direction;
	private float edgeLength;
	
	private NodeVector(float direction, float edgeLength) {
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
