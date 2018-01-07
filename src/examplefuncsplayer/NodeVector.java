package examplefuncsplayer;

public strictfp enum NodeVector {
	
	N ( (float) Math.PI/2, BotConstants.CARDINAL, 0 ),
	NE ( (float) (Math.PI/4), BotConstants.DIAG, 1 ),
	E ( (float) (Math.PI*2), BotConstants.CARDINAL, 2 ),
	SE ( (float) ((7*Math.PI)/4), BotConstants.DIAG, 3 ),
	S ( (float) ((3*Math.PI)/2), BotConstants.CARDINAL, 4 ),
	SW ( (float) ((5*Math.PI)/4), BotConstants.DIAG, 5 ),
	W ( (float) Math.PI, BotConstants.CARDINAL, 6 ),
	NW ( (float) ((3*Math.PI)/4), BotConstants.DIAG, 7 );
	
	private float direction;
	private float edgeLength;
	private int index;

	private NodeVector(float direction, float edgeLength, int index) {
		this.direction = direction;
		this.edgeLength = edgeLength;
		this.index = index;
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
    
}
