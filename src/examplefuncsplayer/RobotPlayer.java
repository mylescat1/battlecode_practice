package examplefuncsplayer;
import battlecode.common.*;

public strictfp class RobotPlayer {

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * If this method returns, the robot dies!
    **/
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        // The robotcontroller for this bot is passed to Global. This allows all other classes to access it.
        Global.init(rc);

        // Here, we've separated the controls into a different method for each RobotType.
        // You can add the missing ones or rewrite this into your own control structure.
        switch (rc.getType()) {
        
            case ARCHON:
                BotArchon.loop();
                break;
                
            case GARDENER:
                BotGardener.loop();
                break;
                
            case SOLDIER:
                BotSoldier.loop();
                break;
                
            case LUMBERJACK:
                BotLumberjack.loop();
                break;
                
            case SCOUT:
            	BotScout.loop();
            	break;
            	
            case TANK:
            	BotTank.loop();
            	break;
        }
	}  
}
