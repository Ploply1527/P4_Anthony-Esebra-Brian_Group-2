import java.awt.Graphics;

public class Ghost extends GameObject{

	//Region: Variables
	private boolean vulnerable;//This controls if the ghost can be eaten or not
	
	//endRegion
	
	public Ghost(int xPos, int yPos, String imageName) {
		super(xPos, yPos, imageName);
		vulnerable = false;//Makes the ghost not able to be eaten
	}

	//Region: Abstract Voids
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		animation();
	}
	
	protected void animation() {
		// TODO Auto-generated method stub
		
	}
	
	//endRegion
	
	//This method should be called when the invincible pellet is used
	public static void turnVulnerable()
	{
	}
	
	//This method should be called when pacman eats the ghost
	public void returnToStart()
	{
		
	}
}
