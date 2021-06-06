import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class PacMan extends GameObject{

	//Region: Player Data
	private boolean isAlive = true;
	private boolean deadTrigger = true;
	private final int speed = 3;

	Direction movement = Direction.Right;
	//Sounds Effects
	Music waka;
	Music death;
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
	
	Image pacClose = getImage("PacClose.png");
	//endRegion
	
	//Region: Dead sprites
	Image pacDie1 = getImage("PacDie1.png");
	Image pacDie2 = getImage("PacDie2.png");
	Image pacDie3 = getImage("PacDie3.png");
	Image pacDie4 = getImage("PacDie4.png");
	Image pacDie5 = getImage("PacDie5.png");
	Image pacDie6 = getImage("PacDie6.png");
	Image pacDie7 = getImage("PacDie7.png");
	//endregion
	
	//endregion
	
	
	public PacMan(int xGrid, int yGrid, String imageName, boolean loud) {
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
	}
	
	public void reset()
	{
		super.reset();
		isAlive = true;
		deadTrigger = true;
	}
	public void Die()
	{
		GUI.decrementLife();
		isAlive = false;
	}
	
	protected void animation() {
		// TODO Auto-generated method stub
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
				baseImage = pacClose;
				break;
			default:
				timerCount = 0;
				break;
			}
		}
		else
		{  //Pacman death animation
			if(timerCount != 0 && deadTrigger) 
			{ 
				timerCount = 0;
				deadTrigger = false;
			}
			
			switch(timerCount/4)
			{
				case 0:
					baseImage = pacClose;
					break;
				case 1:
					baseImage = pacDie1;
					break;
				case 2:
					baseImage = pacDie2;
					break;
				case 3:
					baseImage = pacDie3;
					break;
				case 4:
					baseImage = pacDie4;
					break;
				case 5:
					baseImage = pacDie5;
					break;
				case 6:
					baseImage = pacDie6;
					break;
				case 7:
					baseImage = pacDie7;
					break;
				default:
					baseImage = null;//Make sprite diappear
					if(GUI.gameOver())
					{
						GameBoard.deadTrig = true;//GameOver
					}
					else
					{
						GameBoard.resetTrig = true;
					}
					break;
			}
		}
		
		if(!gameOver)
		{
			timerCount++;
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
		
		if(!isAlive || collide || gameOver)//Makes PacMan stay in place when dead
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
			else if(posX > 613) { posX = 2;}
			
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
			GUI.addScore(10);
			break;
		case 2:
			board[gridY][gridX].pelletGet();
			GUI.addScore(50);
			Ghost.gVul = 4;
			break;
		default:
			break;
		}
	}
	
	//Region: Debug
	private void Debug(int dir)
	{
		System.out.print("Dir: " + dir + " ");

		System.out.print(board[gridY][gridX].getTileType());
		System.out.println(" Grid:[" + gridX + ", " + gridY + "]");
	}
	//endRegion
}
