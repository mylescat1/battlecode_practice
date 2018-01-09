package examplefuncsplayer;

public interface BotConstants {
	
	public final int DEFAULT_EDGE_LENGTH = 10;
	public final float CARDINAL = DEFAULT_EDGE_LENGTH;
	public final float DIAG = (float) Math.sqrt(Math.pow(CARDINAL, 2) * 2);
	public final double ON_NODE_PRECISION = 0.1;
	
	/*
	 * Direction constants
	 */
	
	public final float N = (float) Math.PI/2;
	public final float NE = (float) (Math.PI/4);
	public final float E = (float) (Math.PI*2);
	public final float SE = (float) ((7*Math.PI)/4);
	public final float S = (float) ((3*Math.PI)/2);
	public final float SW = (float) ((5*Math.PI)/4);
	public final float W = (float) Math.PI;
	public final float NW = (float) ((3*Math.PI)/4);
	
	/*
	 * End direction constants
	 */
	
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
