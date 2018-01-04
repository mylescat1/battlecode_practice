package examplefuncsplayer;
import battlecode.common.*;

public strictfp class RobotPlayer {
    static RobotController rc;

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * If this method returns, the robot dies!
    **/
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        // This is the RobotController object. You use it to perform actions from this robot,
        // and to get information on its current status.
        RobotPlayer.rc = rc;

        // Here, we've separated the controls into a different method for each RobotType.
        // You can add the missing ones or rewrite this into your own control structure.
        switch (rc.getType()) {
            case ARCHON:
                runArchon();
                break;
            case GARDENER:
                runGardener();
                break;
            case SOLDIER:
                runSoldier();
                break;
            case LUMBERJACK:
                runLumberjack();
                break;
        }
	}

    static void runArchon() throws GameActionException {
        System.out.println("I'm an archon!");
        Team enemy = rc.getTeam().opponent();
        MapLocation[] enArchonLoc = rc.getInitialArchonLocations(enemy);
        System.out.println(enArchonLoc[0]);
        
        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {

                // Generate a random direction
                Direction dir = randomDirection();

                // Randomly attempt to build a gardener in this direction
                if (rc.canHireGardener(dir) && Math.random() < .01) {
                    rc.hireGardener(dir);
                }

                // Move randomly
                //tryMove(randomDirection());
                shortestPath(enArchonLoc[0]);
                System.out.println("just did shortest path!");
                
                
                // Broadcast archon's location for other robots on the team to know
                MapLocation myLocation = rc.getLocation();
                rc.broadcast(0,(int)myLocation.x);
                rc.broadcast(1,(int)myLocation.y);

                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } catch (Exception e) {
                System.out.println("Archon Exception");
                e.printStackTrace();
            }
        }
    }

	static void runGardener() throws GameActionException {
        System.out.println("I'm a gardener!");

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {

                // Listen for home archon's location
                int xPos = rc.readBroadcast(0);
                int yPos = rc.readBroadcast(1);
                MapLocation archonLoc = new MapLocation(xPos,yPos);

                // Generate a random direction
                Direction dir = randomDirection();

                // Randomly attempt to build a soldier or lumberjack in this direction
                if (rc.canBuildRobot(RobotType.SOLDIER, dir) && Math.random() < .01) {
                    rc.buildRobot(RobotType.SOLDIER, dir);
                } else if (rc.canBuildRobot(RobotType.LUMBERJACK, dir) && Math.random() < .01 && rc.isBuildReady()) {
                    rc.buildRobot(RobotType.LUMBERJACK, dir);
                }

                // Move randomly
                tryMove(randomDirection());
                
                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } catch (Exception e) {
                System.out.println("Gardener Exception");
                e.printStackTrace();
            }
        }
    }

    static void runSoldier() throws GameActionException {
        System.out.println("I'm an soldier!");
        Team enemy = rc.getTeam().opponent();
        MapLocation[] enArchonLoc = rc.getInitialArchonLocations(enemy);

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {
                MapLocation myLocation = rc.getLocation();

                // See if there are any nearby enemy robots
                RobotInfo[] robots = rc.senseNearbyRobots(-1, enemy);

                // If there are some...
                if (robots.length > 0) {
                    // And we have enough bullets, and haven't attacked yet this turn...
                    if (rc.canFireSingleShot()) {
                        // ...Then fire a bullet in the direction of the enemy.
                        rc.fireSingleShot(rc.getLocation().directionTo(robots[0].location));
                    }
                }

                // Move randomly
                //tryMove(randomDirection());
                shortestPath(enArchonLoc[0]);
                System.out.println("SOLDIER: just did shortest path!");

                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } catch (Exception e) {
                System.out.println("Soldier Exception");
                e.printStackTrace();
            }
        }
    }

    static void runLumberjack() throws GameActionException {
        System.out.println("I'm a lumberjack!");
        Team enemy = rc.getTeam().opponent();

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {

                // See if there are any enemy robots within striking range (distance 1 from lumberjack's radius)
                RobotInfo[] robots = rc.senseNearbyRobots(RobotType.LUMBERJACK.bodyRadius+GameConstants.LUMBERJACK_STRIKE_RADIUS, enemy);

                if(robots.length > 0 && !rc.hasAttacked()) {
                    // Use strike() to hit all nearby robots!
                    rc.strike();
                } else {
                    // No close robots, so search for robots within sight radius
                    robots = rc.senseNearbyRobots(-1,enemy);

                    // If there is a robot, move towards it
                    if(robots.length > 0) {
                        MapLocation myLocation = rc.getLocation();
                        MapLocation enemyLocation = robots[0].getLocation();
                        Direction toEnemy = myLocation.directionTo(enemyLocation);

                        tryMove(toEnemy);
                    } else {
                        // Move Randomly
                        tryMove(randomDirection());
                    }
                }

                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } catch (Exception e) {
                System.out.println("Lumberjack Exception");
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a random Direction
     * @return a random Direction
     */
    static Direction randomDirection() {
        return new Direction((float)Math.random() * 2 * (float)Math.PI);
    }

    /**
     * Attempts to move in a given direction, while avoiding small obstacles directly in the path.
     *
     * @param dir The intended direction of movement
     * @return true if a move was performed
     * @throws GameActionException
     */
    static boolean tryMove(Direction dir) throws GameActionException {
        return tryMove(dir,20,3);
    }

    /**
     * Attempts to move in a given direction, while avoiding small obstacles direction in the path.
     *
     * @param dir The intended direction of movement
     * @param degreeOffset Spacing between checked directions (degrees)
     * @param checksPerSide Number of extra directions checked on each side, if intended direction was unavailable
     * @return true if a move was performed
     * @throws GameActionException
     */
    static boolean tryMove(Direction dir, float degreeOffset, int checksPerSide) throws GameActionException {

        // First, try intended direction
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        }

        // Now try a bunch of similar angles
        boolean moved = false;
        int currentCheck = 1;

        while(currentCheck<=checksPerSide) {
            // Try the offset of the left side
            if(rc.canMove(dir.rotateLeftDegrees(degreeOffset*currentCheck))) {
                rc.move(dir.rotateLeftDegrees(degreeOffset*currentCheck));
                return true;
            }
            // Try the offset on the right side
            if(rc.canMove(dir.rotateRightDegrees(degreeOffset*currentCheck))) {
                rc.move(dir.rotateRightDegrees(degreeOffset*currentCheck));
                return true;
            }
            // No move performed, try slightly further
            currentCheck++;
        }

        // A move never happened, so return false.
        return false;
    }

    /**
     * A slightly more complicated example function, this returns true if the given bullet is on a collision
     * course with the current robot. Doesn't take into account objects between the bullet and this robot.
     *
     * @param bullet The bullet in question
     * @return True if the line of the bullet's path intersects with this robot's current position.
     */
    static boolean willCollideWithMe(BulletInfo bullet) {
        MapLocation myLocation = rc.getLocation();

        // Get relevant bullet information
        Direction propagationDirection = bullet.dir;
        MapLocation bulletLocation = bullet.location;

        // Calculate bullet relations to this robot
        Direction directionToRobot = bulletLocation.directionTo(myLocation);
        float distToRobot = bulletLocation.distanceTo(myLocation);
        float theta = propagationDirection.radiansBetween(directionToRobot);

        // If theta > 90 degrees, then the bullet is traveling away from us and we can break early
        if (Math.abs(theta) > Math.PI/2) {
            return false;
        }

        // distToRobot is our hypotenuse, theta is our angle, and we want to know this length of the opposite leg.
        // This is the distance of a line that goes from myLocation and intersects perpendicularly with propagationDirection.
        // This corresponds to the smallest radius circle centered at our location that would intersect with the
        // line that is the path of the bullet.
        float perpendicularDist = (float)Math.abs(distToRobot * Math.sin(theta)); // soh cah toa :)

        return (perpendicularDist <= rc.getType().bodyRadius);
    }
    
    
    /**
     * The function finds the shortest (straight line) path to desired endpoint, then assesses all objects in straight line path and 
     * adjusts to the left or right of the direct path until it finds an opening. Once found, object moves. 
     *
     * @param the end location
     * @throws GameActionException 
     */    
    static void shortestPath(MapLocation desiredLocation) throws GameActionException {
    	MapLocation currentLoc = rc.getLocation();							//declare current location
    	Direction dirObstructs[] = new Direction[25];						//Map location of objects that obstruct direct path to desired location
    	Direction dirToNewLoc = currentLoc.directionTo(desiredLocation);	//find direction to desired location
    	Direction newDir = dirToNewLoc;										//Used to update depending on NewDirAdd/Sub
    	Direction toMove = null;											//Direction to move
    	int robotObsCount = 0;												//Robots obstructs array count
    	int treeObsCount = 0;												//Tree obstructs array count
    	int dirObsCount = 0;												//Tree obstructs array count
        int rad = 1;														//Sets initial radians variable for newDirAdd and new DirSub
    	
    	System.out.println("Made it to shortestPath function");				//DEBUG STATEMENT
    	
    	//Sense for robots, trees, bullets to see if anything will obstruct path at max sensor radius
    	//identify objects that lie in desired direction
    	RobotInfo[] nearbyRobots = rc.senseNearbyRobots();
    	TreeInfo[] nearbyTrees = rc.senseNearbyTrees();    	
    	
    	System.out.println("Sensed for robots and trees.");					//DEBUG STATEMENT
    	

    	//-----------------------------ROBOTS----------------------------------------------//
	    	//if something sensed, add radians + direction to array
	    	for (RobotInfo robot : nearbyRobots) {
	    		System.out.println("entered robots for-loop");					//DEBUG STATEMENT
	    		
	    		//if robot is null - the array is null and no robots have been sensed in the object's senseRadius area.  
	    		if (robot != null) {
		    		MapLocation robLoc = robot.getLocation();    				//get robot location
		    		Direction dirToRobLoc = currentLoc.directionTo(robLoc);		//get direction to robot location
		    		
	    			System.out.println("Robots sensed in radius: " + robot.getID());			//DEBUG STATEMENT
	    			
	    			//add dirToRobLoc to dirObstructs array
	    			 dirObstructs[dirObsCount] = dirToRobLoc;
	    			 dirObsCount++;
		

		    		//if robot location is in the direction of desired location, rotate # of radians
		    		if(dirToRobLoc.equals(newDir, 1))
		    		{
		    			if(robotObsCount % 2 == 0) {
		    				newDir = newDir.rotateRightRads(rad);		//rotate right # of rad to avoid object
			    			if (rad < 6) {
			    				rad +=1;
				    			System.out.println("ROBOTS FOUND: Rotating Right: " + rad);			//DEBUG STATEMENT
			    			} else {
			    				rad = 1;
			    			}
		    			} else {
		    				newDir = newDir.rotateLeftRads(rad);		//rotate left # of rad to avoid object
			    			if (rad < 6) {
			    				rad +=1;
				    			System.out.println("ROBOTS FOUND: Rotating Left: " + rad);			//DEBUG STATEMENT
			    			} else {
			    				rad = 1;
			    			}
		    			}
		    		} else {
		    	    	toMove = newDir;
		    			System.out.println("No robots found in direction of movement, updated toMove.");			//DEBUG STATEMENT
		    		}//end else
	    			robotObsCount++;					//add to count of array		
	    			System.out.println("RobotsCount updated.");
	    		}//end if (robot != null)
	    		
	    		else {
	    			toMove = newDir;
	    			System.out.println("ROBOTS IS NULL");			//DEBUG STATEMENT
	    		}//end else if (robot != null)
	    		System.out.println("Exiting robots for-loop");			//DEBUG STATEMENT
	    	}//end for

    	
    	//-----------------------------TREES----------------------------------------------//
	    	//if something sensed, add radians + direction to array
	    	for (TreeInfo tree : nearbyTrees) {
	    		//if robot is null - the array is null and no robots have been sensed in the object's senseRadius area.  
	    		System.out.println("Entered trees for-loop");					//DEBUG STATEMENT

	    		if (tree != null) {
		    		MapLocation treeLoc = tree.getLocation();    					//get robot location
		    		Direction dirToTreeLoc = currentLoc.directionTo(treeLoc);		//get direction to robot location
		    		boolean robotFound = false;										//boolean to identify objects that 
		    																		//have been identified as robots
		    		
	    			System.out.println("Trees sensed in radius: " + tree.getID());			//DEBUG STATEMENT

	    			//creating null point 
	    			for (Direction dir : dirObstructs) {
	    				if (dir != null) {
		    				if (dir.equals(dirToTreeLoc, 1)) {
		    					robotFound = true;
		    				} else {
		    					robotFound = false;
		    				}
	    				}
	    			}	

		    		//if tree location (or previously identified robot) is in the direction of desired location (by error of 1 radian)
	    			//or robot is in the same direction as dirToTreeLoc is looking
		    		if(dirToTreeLoc.equals(newDir, 1) || robotFound == true)				
		    		{
		    			if(treeObsCount % 2 == 0) {
		    				newDir = newDir.rotateRightRads(rad);		//rotate right # of rad to avoid object
			    			if (rad < 6) {
			    				rad +=1;
				    			System.out.println("TREES FOUND: Rotating Right: " + rad);			//DEBUG STATEMENT
			    			} else {
			    				rad = 1;
			    				//
			    			}
		    			} else {
		    				newDir = newDir.rotateLeftRads(rad);		//rotate left # of rad to avoid object
			    			if (rad < 6) {
			    				rad +=1;
				    			System.out.println("TREES FOUND: Rotating Left: " + rad);			//DEBUG STATEMENT
			    			} else {
			    				rad = 1;
			    			}
		    			}				
		    		} else if (robotFound == false){
		    	    	toMove = newDir;
		    			System.out.println("No trees found, moving.");			//DEBUG STATEMENT
		    		}//end else
		    		treeObsCount++;						//add to count of array
	    		}//end if (tree != null)
	    		
		    	else {
		    		toMove = newDir;
	    			System.out.println("TREE IS NULL");			//DEBUG STATEMENT
		    	}//end else if (tree != null)
	    		System.out.println("Exiting trees for-loop");			//DEBUG STATEMENT
	    	}//end for
    	
    	//-----------------------------MOVE----------------------------------------------//
    	if (rc.canMove(toMove)) {
    		try {
				rc.move(toMove);
	    		System.out.println("MOVING!");			//DEBUG STATEMENT
			} catch (GameActionException e) {
				// TODO Auto-generated catch block
				//System.out.println(e.getType());		//(should) print GameActionExceptionType, i.e. "CANT_DO_THAT"
				e.printStackTrace();
			}
    	}//end if	
    	else {
    		System.out.println("Can't Move! Trying a random direction");			//DEBUG STATEMENT
            // Try a random direction
            tryMove(randomDirection());
    	}
    	
    	//delete allocated memory of array
    	dirObstructs = null;
    }//end function 	

}//end class
