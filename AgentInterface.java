package world;

public interface AgentInterface {

	public void MoveLeft();
	public void MoveRight();
	public void MoveDown();
	public void MoveUp();
	public void MoveLeftUp();
	public void MoveLeftDown();
	public void MoveRightUp();
	public void MoveRightDown();
	public void GetNewDirection();
	public boolean CheckForDirt(String [][] world);
	public void CleanDirt(String [][] world);
	public void Run(String [][] world);
	public void PrintWorld(String [][] world);
}
