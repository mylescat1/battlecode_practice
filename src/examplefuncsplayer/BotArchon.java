package examplefuncsplayer;

import battlecode.common.*;

public class BotArchon extends Global {

	public static void loop() throws GameActionException {
		
        while (true) {
            try { //Code to execute every round here:
            	
            	Global.loop_common();
        		
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
