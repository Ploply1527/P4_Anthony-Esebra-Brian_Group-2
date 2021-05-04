import java.awt.Graphics;

public class Ghost extends GameObject{

	//Region: Variables
	private boolean vulnerable;//This controls if the ghost can be eaten or not
	
	private int speeds[] = {1,2,3,4,5};//This is a possible list of speeds the ghosts can have
	//endRegion
	
	public Ghost(int xGrid, int yGrid, String imageName) {
		super(xGrid, yGrid, imageName);
		vulnerable = false;//Makes the ghost not able to be eaten
	}

	//Region: AI
	//endRegion
	
	//Region: Abstract Voids
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		animation();
	}
	
	protected void animation() {
		// TODO Auto-generated method stub
		
	}
	
	protected void movement()
	{
		
	}
	//endRegion
	
	//This method should be called when the invincible pellet is used
	public void turnVulnerable()
	{
		vulnerable = true;
		
		//Timer for invulnerabiliy time
	}
	
	//This is the collision done with the pacMan stuff
	public void collision(PacMan p)
	{
		
	}
	//This method should be called when pacman eats the ghost
	private void returnToStart()
	{
		while(true)
		{
			
		}
	}
}