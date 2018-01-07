package examplefuncsplayer;

import java.util.EnumSet;

public interface GameConstants {
	
	public final int DEFAULT_EDGE_LENGTH = 1;
	public final float CARDINAL = DEFAULT_EDGE_LENGTH;
	public final float DIAG = (float) (Math.sqrt(((int)CARDINAL^2) + ((int)CARDINAL^2)));;
	public final double ON_NODE_PRECISION = 0.1;
	public final EnumSet<NodeVector> ALL_VECTORS = EnumSet.allOf(NodeVector.class);
	
}
