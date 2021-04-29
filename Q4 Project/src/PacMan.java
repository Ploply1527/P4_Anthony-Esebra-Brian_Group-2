import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class PacMan extends GameObject{

	//Region: Pacman sprites
	Image pacMan1 = getImage("PacMan1.png");
	Image pacMan2;
	Image pacMan3;
	
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
		timerCount++;
		switch(timerCount/20)
		{
		case 0:
			break;
			
		default:
			timerCount = 0;
			break;
		}
	}
	
	///This is the actual stuff for the movement
	public void movement()
	{
		
	}
}
