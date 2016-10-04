package world;

import java.util.Random;

public class Environment {
	public static String[][] world;
	public int width = 5;
	public int height = 5;
	public int numOfDirtyTiles = 2;
	public int numOfObstacles = 2;
	
	public Environment(int w, int h, int dt, int o){
		this.width = w;
		this.height = h;
		this.numOfDirtyTiles = dt;
		this.numOfObstacles = o;
		
		CreateEnvironment();
	}
	
	public void CreateEnvironment(){
		world = new String[width][height];
		
		// Create the environment
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				world[i][j] = "P"; // P for path
			}
		}
		
		// Create obstacles
		int n = 0;
		Random rand = new Random();
		while(n < numOfObstacles){
			
			int randNumX = rand.nextInt(width);
			int randNumY = rand.nextInt(height);
			
			if(world[randNumX][randNumY] == "P" && (randNumX != 0 && randNumY != 0)){
				world[randNumX][randNumY] = "O"; // O for obstacle
				n++;
			}
		}
		
		n = 0;
		while(n < numOfDirtyTiles){
			
			int randNumX = rand.nextInt(width);
			int randNumY = rand.nextInt(height);
			
			if(world[randNumX][randNumY] == "P" && (randNumX != 0 && randNumY != 0)){
				world[randNumX][randNumY] = "D"; // D for dirt
				n++;
			}
		}
	}
	
	public void RemoveDirt(){
		numOfDirtyTiles --;
	}
	
	public int getNumberOfDirtyTiles(){
		return numOfDirtyTiles;
	}

}
