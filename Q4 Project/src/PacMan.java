import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class PacMan extends GameObject{

	//Region: Player Data
	private boolean isAlive = true;
	private boolean startUp = false;
	private final int speed = 3;
	
	private boolean collide = false;
	//endRegion
	
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
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(baseImage, tx, null);
	}

	protected void animation() {
		// TODO Auto-generated method stub
		timerCount++;
		if(isAlive)
		{   //Default pacman animation
			switch(timerCount/20)
			{
			case 0:
				break;
			case 1:
				break;
			default:
				timerCount = 0;
				break;
			}
		}
		else
		{  //Pacman death animation
			if(timerCount != 0) { timerCount = 0; }
			
			switch(timerCount/10)
			{
			
			}
		}
		
	}
	
	///This is the actual stuff for the movement
	public void movement(Direction d)
	{
		boolean vertical = false;
		int dir = 1;
		collide = false;
		
		//0: Up    1: Down   2: Left    3: Right
		//Collision within the 
		switch(d)
		{
		case Up://Move Up
			vertical = true;
			dir = -1;
			//Collision Up
			//System.out.print(grid[(gridY)][gridX]);
			/*if(grid[gridY][gridX] % 4 == 0)
			 * {
			 * collide = true;
			 * }
			*/
			//System.out.println((grid[gridX][gridX] % 4));
			break;
		case Down://Move Down
			vertical = true;
			dir = 1;
			//Collision Down
			break;
		case Left://Move Left
			vertical = false;
			dir = -1;
			//Collision Left
			break;
		default://Move Right
			vertical = false;
			dir = 1;
			//Collision Right
			break;
		}
		
		
		if(!isAlive || startUp)//Makes PacMan stay in place when dead
		{
			dir = 0;
			System.out.print("Stuff");
		}
			
		//Move PacMan
		if(vertical)
		{
			posY += speed * dir;
			gridY = Math.round((posY)/22);
		}
		else
		{
			posX += speed * dir;	
			
			//Sideways warp thing
			if(posX < -3) { posX = 618;}
			else if(posX > 616) { posX = 0;}
			
			gridX = Math.round(posX/22);
		}

		tx.setToTranslation(posX, posY - 17);
		//Adjust his grid position
		//System.out.print(grid[gridY][gridX]);
		System.out.println("Grid:[" + posX/22 + ", " + gridY + "]");
	}
}
