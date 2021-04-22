import java.awt.Graphics;

public class Ghost extends GameObject{

	private boolean vulnerable;//This controls if the ghost can be eaten or not
	
	public Ghost(int xPos, int yPos, String imageName) {
		super(xPos, yPos, imageName);
		vulnerable = false;//Makes the ghost not able to be eaten
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	public static void turnVulnerable()
	{
		vulnerable = true;
	}
}
