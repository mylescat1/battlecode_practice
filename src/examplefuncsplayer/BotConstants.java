package examplefuncsplayer;

import java.util.EnumSet;

public interface BotConstants {
	
	public final int DEFAULT_EDGE_LENGTH = 5;
	public final float CARDINAL = DEFAULT_EDGE_LENGTH;
	public final float DIAG = (float) Math.sqrt(Math.pow(CARDINAL, 2) * 2);
	public final double ON_NODE_PRECISION = 0.1;
	public final EnumSet<NodeVector> ALL_VECTORS = EnumSet.allOf(NodeVector.class);
	
	/*
	 * Direction boundary constants
	 */
	
	public final float ONE = (float) Math.PI/6;
	public final float TWO = (float) Math.PI/3;
	public final float THREE = (float) (2*Math.PI)/3;
	public final float FOUR = (float) (5*Math.PI)/6;
	public final float FIVE = -(FOUR);
	public final float SIX = -(THREE);
	public final float SEVEN = -(TWO);
	public final float EIGHT = -(ONE);
	
	/*
	 * End direction boundary constants
	 */

}
