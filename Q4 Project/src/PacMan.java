import java.awt.Graphics;

public class PacMan extends GameObject{

	//Region: Pacman sprites
	
	//endRegion
	public PacMan(int xGrid, int yGrid, String imageName) {
		super(xGrid, yGrid, imageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		animation();
	}

	protected void animation() {
		// TODO Auto-generated method stub
		
	}
	
	///This is the actual stuff for the movement
	public void movement()
	{
		
	}
}
