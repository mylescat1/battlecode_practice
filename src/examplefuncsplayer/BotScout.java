package examplefuncsplayer;
import battlecode.common.*;

public class BotScout {

	public static void loop() throws GameActionException {
		
        while (true) {
            try { //Code to execute every round here:
            	
            	Global.loop_common();
        		Clock.yield();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
