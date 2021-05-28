import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class PacMan extends GameObject{

	//Region: Player Data
	private boolean isAlive = true;
	private boolean startUp = false;
	private final int speed = 3;
	private int lives = 3;
	Direction movement = Direction.Right;
	//endRegion
	
	//Region: Pacman sprites
	
	//Region: Alive sprites
	Image pacRight1 = getImage("PacRight1.png");
	Image pacRight2 = getImage("PacRight2.png");
	
	Image pacLeft1 = getImage("PacLeft1.png");
	Image pacLeft2 = getImage("PacLeft2.png");
	
	Image pacUp1 = getImage("PacUp1.png");
	Image pacUp2 = getImage("PacUp2.png");
	
	Image pacDown1 = getImage("PacDown1.png");
	Image pacDown2 = getImage("PacDown2.png");
	
	Image pacClose3 = getImage("PacClose.png");
	//endRegion
	
	//Region: Dead sprites
	Image pacDead;
	//endregion
	
	//endregion
	
	
	public PacMan(int xGrid, int yGrid, String imageName) {
		super(xGrid, yGrid, imageName);
		// TODO Auto-generated constructor stub
		posY -= 12;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stud
		animation();
		pelletEat();
		
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(baseImage, tx, null);
		/*
		g.setColor(Color.blue);
		g.fillRect(posX, posY, 4, 4);
		*/
	}
	
	public void reset()
	{
		super.reset();
	}
	
	public void Die()
	{
		reset();
	}
	protected void animation() {
		// TODO Auto-generated method stub
		timerCount++;
		if(isAlive)
		{   //Default pacman animation
			switch(timerCount/5)
			{
			case 0:
				switch(movement)
				{
					case Up:
						baseImage = pacUp1;
						break;
					case Down:
						baseImage = pacDown1;
						break;
					case Left:
						baseImage = pacLeft1;
						break;
					default:
						baseImage = pacRight1;
						break;
				}
				break;
			case 1:
				switch(movement)
				{
					case Up:
						baseImage = pacUp2;
						break;
					case Down:
						baseImage = pacDown2;
						break;
					case Left:
						baseImage = pacLeft2;
						break;
					default:
						baseImage = pacRight2;
						break;
				}
				break;
			case 2:
				baseImage = pacClose3;
				break;
			default:
				timerCount = 0;
				break;
			}
		}
		else
		{  //Pacman death animation
			if(timerCount != 0) { timerCount = 0; }
			
			switch(timerCount/4)
			{
				default:
					break;
			}
		}
		
	}
	
	///This is the actual stuff for the movement
	public void movement(Direction d)
	{
		boolean vertical = false;
		int dir = 1;
		
		//0: Up    1: Down   2: Left    3: Right
		//Collision within the 
		switch(d)
		{
		case Up://Move Up
			vertical = true;
			dir = -1;
			break;
		case Down://Move Down
			vertical = true;
			dir = 1;
			break;
		case Left://Move Left
			vertical = false;
			dir = -1;
			break;
		default://Move Right
			vertical = false;
			dir = 1;
			break;
		}
		
		CollisionCheck(vertical, dir, 4);//This is the void that does the collision checks
		
		if(!isAlive || startUp || collide)//Makes PacMan stay in place when dead
		{
			dir = 0;
			
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
			if(posX < 1) { posX = 613;}
			else if(posX > 614) { posX = 2;}
			
			gridX = Math.round(posX/22);
		}

		tx.setToTranslation(posX - 14, posY - 17);
		
		movement = d;
		
		//Adjust his grid position
	}
	
	//This is the class that is in charge of pellet eating
	//There is something wierd about this part
	private void pelletEat()
	{
		switch(board[gridY][gridX].getTileType())
		{
		case 1:
			board[gridY][gridX].pelletGet();
			GUI.score += 10;
			break;
		case 2:
			board[gridY][gridX].pelletGet();
			GUI.score += 50;
			Ghost.gVul = 4;
			break;
		default:
			break;
		}
	}
	
	
	private void Debug(int dir)
	{
		System.out.print("Dir: " + dir + " ");

		System.out.print(board[gridY][gridX].getTileType());
		System.out.println(" Grid:[" + gridX + ", " + gridY + "]");
	}
}
