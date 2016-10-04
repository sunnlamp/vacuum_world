package world;
import java.util.*;

public class Agent implements AgentInterface {

	private int x = 0; 	// Agent's
	private int y = 0;
	private Environment environment;
	private int battery = 50;
	private int performance = 0;

	public Agent(int x, int y, Environment env){
		this.x = x;
		this.y = y;
		this.environment = env;
		Run(environment.world);
	}

	// Your code here ....
	public void MoveLeft() {
		  if (x == 0) return;
      if (x > 0 && (CheckForObstacle(x - 1, y))) {
		   x--;
			 if(CheckForDirt(environment.world)) {
 				CleanDirt(environment.world);
 			 } else {
				 environment.world[x][y] = "V";
			 }
		   BatteryReduction();
      } else return;
	}

	public void MoveRight() {
		  if (x == 4) return;
      if (x < 4 && (CheckForObstacle(x + 1, y))) {
   			x++;
				if(CheckForDirt(environment.world)) {
					CleanDirt(environment.world);
				} else {
					environment.world[x][y] = "V";
				}
   			BatteryReduction();
      } else return;
	}

	public void MoveDown() {
      if (y == 4) return;
      if (y < 4 && (CheckForObstacle(x, y + 1))) {
		  	y++;
				if(CheckForDirt(environment.world)) {
	 		 		CleanDirt(environment.world);
	 			} else {
					environment.world[x][y] = "V";
				}
	   		BatteryReduction();
      } else return;
	}

	public void MoveUp() {
		  if (y == 0) return;
      if (y > 0 && (CheckForObstacle(x, y - 1))) {
   			y--;
				if(CheckForDirt(environment.world)) {
					CleanDirt(environment.world);
				} else {
					environment.world[x][y] = "V";
				}
   			BatteryReduction();
      } else return;
	}

	public void MoveLeftUp() {
		  if (x == 0 && y == 0) return;
      if (x > 0 && y > 0 && (CheckForObstacle(x - 1, y - 1))) {
	   		x--;
	   		y--;
				if(CheckForDirt(environment.world)) {
					CleanDirt(environment.world);
				} else {
					environment.world[x][y] = "V";
				}
   			BatteryReduction();
      } else return;
	}

	public void MoveLeftDown() {
			if (x == 0 && y == 4) return;
      if ((x > 0) && (y < 4) && (CheckForObstacle(x - 1, y + 1))) {
	   		x--;
	   		y++;
				if(CheckForDirt(environment.world)) {
					CleanDirt(environment.world);
				} else {
					environment.world[x][y] = "V";
				}
   			BatteryReduction();
      } else return;
	}

	public void MoveRightUp() {
      if (x == 4 && y == 0) return;
			if ((x < 4) && (y > 0) && (CheckForObstacle(x + 1, y - 1))) {
		   	x++;
		   	y--;
				if(CheckForDirt(environment.world)) {
					CleanDirt(environment.world);
				} else {
					environment.world[x][y] = "V";
				}
	   		BatteryReduction();
      } else return;
	}

	public void MoveRightDown() {
			if (x == 4 && y == 4) return;
      if ((x < 4 && y < 4) && (CheckForObstacle(x + 1, y + 1))) {
	   		x++;
	   		y++;
				if(CheckForDirt(environment.world)) {
					CleanDirt(environment.world);
				} else {
					environment.world[x][y] = "V";
				}
   			BatteryReduction();
      } else return;

	}
	// Use this method to get the agent to change direction,
	// when it hits the end of the world or an obstacle
	public void GetNewDirection() throws ArrayIndexOutOfBoundsException{
		Random randomInt = new Random();
		int ran = randomInt.nextInt(8) + 1;

			switch (ran) {
				case 1: MoveLeft();
								break;
				case 2: MoveRight();
								break;
				case 3: MoveDown();
								break;
				case 4: MoveUp();
								break;
				case 5: MoveLeftUp();
								break;
				case 6: MoveLeftDown();
								break;
				case 7: MoveRightUp();
								break;
				case 8: MoveRightDown();
								break;
         }
	}
	// P is for path, O is for obstacle, D is for dirt
	// If value at position of vacuum is D, call
	// CleanDirt and return true, else return false
	public boolean CheckForDirt(String [][] world) {
		if (world[x][y].equals("D"))
			return true;
		else
			return false;
	}

	public boolean CheckForObstacle(int x, int y) {
		if (environment.world[x][y].equals("P") ||
				environment.world[x][y].equals("V") ||
				environment.world[x][y].equals("D"))
			return true;
		else
			return false;
	}

	public void CleanDirt(String [][] world) {
		environment.RemoveDirt();
		environment.world[x][y] = "P";
		performance = performance + 15;
	}
	// Not sure what this is for yet.
	public void Run(String [][] world) {
    environment.world[x][y] = "V";
		System.out.println("All visited tiles will be marked with V.");
		System.out.println("Initial state of world: ");
    PrintWorld(environment.world);

		while(battery != 0 && environment.getNumberOfDirtyTiles() != 0) {
			GetNewDirection();
		}

    System.out.println();
		PrintWorld(environment.world);
		System.out.println();

		if (environment.getNumberOfDirtyTiles() == 0) {
			System.out.println("Agent cleaned all the tiles.");
			System.out.println("Battery remaining: " + battery);
			System.out.println("Performance is " + performance);
		} else if (battery == 0) {
			System.out.println("The agent ran out of battery before");
			System.out.println("cleaning all the tiles...");
			System.out.println("Performance is " + performance);
		}
	}
	
	// Print current state of vacuum world?
	public void PrintWorld(String [][] world) {
		for(int i = 0; i < environment.world.length; i++) {
			for(int j = 0; j < environment.world.length; j++) {
				System.out.print(environment.world[i][j]);
			}
				System.out.println();
		}
	}


	public void BatteryReduction() {
		battery--;
		performance = performance - 1;
	}

}
