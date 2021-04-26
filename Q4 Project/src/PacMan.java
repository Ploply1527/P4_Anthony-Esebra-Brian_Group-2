import java.awt.Graphics;

public class PacMan extends GameObject{

	//Region: Pacman sprites
	
	//endRegion
	public PacMan(int xPos, int yPos, String imageName) {
		super(xPos, yPos, imageName);
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
}
