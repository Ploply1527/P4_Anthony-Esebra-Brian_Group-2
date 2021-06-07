import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Ghost extends GameObject{

	//Region: Variables
	private boolean vulnerable = false;//This controls if the ghost can be eaten or not
	private boolean eaten = false;//This sees if the ghost was eaten or not
	
	public static int gVul = 0;//This is to make all the ghosts vulnerable
	private long vulTimer = 0;//This is how long the ghosts were vulnerable
	private final int flashTime = 500;//This is how much later the ghosts will flash
	private final int maxVulTime = 600; //This is show long the vulnerablity time will last
	private int speed = 3;//This is a possible list of speeds the ghosts can have
	//Region: Images
	private Image ghost1;
	private Image ghost2;
	private Image ghostVul1 = getImage("vul1.png");
	private Image ghostVul2 = getImage("vul2.png");
	private Image ghostEat = getImage("eyes.png");
	//endRegion
	
	//endRegion
	
	public Ghost(int xGrid, int yGrid, String imageName, String g1) {
		super(xGrid, yGrid, imageName);
		
		ghost1 = getImage(imageName);
		ghost2 = getImage(g1);
		
		vulnerable = false;//Makes the ghost not able to be eaten
		//14
		posY += 70;
	}

	//Region: AI
	//endRegion
	
	//Region: Abstract Voids
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub	
		if(!gameOver) { animation(); }
		
		vulnerableCheck();
		
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(baseImage, tx, null);
	}
	//Endregion
	
	public void movCol(PacMan p)
	{
		movement(p);
		moveToPos(0,50);
		collision(p);
	}
	
	
	protected void animation() {
		// TODO Auto-generated method stub
		if(eaten)
		{
			baseImage = ghostEat;
		}
		else
		{
			switch(timerCount / 10)
			{
				case 0:
					if(vulnerable && !(flashTime < vulTimer && (vulTimer/7)%2 == 0))
					{
						baseImage = ghostVul1;
					}
					else
					{
						baseImage = ghost1;
					}
				break;
				
				case 1:
					if(vulnerable && !(flashTime < vulTimer && (vulTimer/7)%2 == 0))
					{
						baseImage = ghostVul2;
					}
					else
					{
						baseImage = ghost2;
					}
					break;
					
				default:
					timerCount = 0;
					break;
			}
		}
		timerCount++;
	}
	
	protected void movement(PacMan p)
	{
		collide = false;
		boolean vertical = false;
		int dir = 1;

		if(gameOver)//Stop when pacman is dead
		{
			speed = 0;
		}
		else if(eaten)//Run to center
		{
			moveToPos(308,1);
		}
		else if(vulnerable)//Run away from pacman when vulnerable
		{
			
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
		}
		else //This is default movement
		{
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
			
		}
		
	}
	//endRegion
	
	//This method should be called when the invincible pellet is used
	public void vulnerableCheck()
	{
		if(gVul > 0 && !vulnerable)
		{
			gVul--;
			vulTimer = 0;
			vulnerable = true;
		}
		
		if(vulnerable && vulTimer < 600)
		{
			vulTimer++;
		}
		else
		{
			vulTimer = 0;
			vulnerable = false;
		}
		//Timer for invulnerabiliy time
	}
	
	//This is the collision done with the pacMan stuff
	private void collision(PacMan p)
	{
		if((p.getX() <= posX - 7 && p.getX() >= posX - 13) &&
		   (p.getY() <= posY - 14 && p.getY() >= posY - 20))
		{
			if(vulnerable && !eaten)
			{
				GUI.score += 1000;//Increase points by 1000
				eaten = true;
				moveToPos(308,1);
			}
			else if(!vulnerable)
			{
				p.Die();
			}
		}
		
	}
	private void moveToPos(int x, int y)
	{
		if(Math.random() < 0.5)
		{
			moveToX(x,true);
		}
		else
		{
			moveToY(y,true);
		}
	}
	private void moveToX(int pX, boolean recBreak)//Helper method for movement
	//recBreak is meant to break out of any possible infinte recursion situations 
	{
		if(posX == pX && recBreak)
		{
			moveToY(posY,false);
		}
		else
		{
			int temp = posX - pX;
			int dir = temp / Math.abs(temp);
	
			CollisionCheck(false, dir, 4);
			
			if(collide)
			{
				dir = 0; 
			}
			
			posX += (speed*dir);	

			gridX = Math.round(posX/22);
		}
	}
	
	private void moveToY(int pY, boolean recBreak)
	{
		if(posY == pY && recBreak)
		{
			moveToX(posX, false);
		}
		else
		{
			//Check for colcheck5 when moving up but not down
			int temp = posY - pY;
			int mov = temp / Math.abs(temp);
			
			CollisionCheck(false, mov, (9 - mov)/2);
			
			if(!collide)
			{
				posY += (speed*mov);	
				gridY = Math.round((posY)/22);
			}
		}
	}
	
	//This is the void that moves away from the player
	private void moveAway(PacMan p)
	{
		if(posX != p.posX) {
			speed *= -1;
		}
	}
	
	@Override
	public void reset() 
	{
		super.reset();
		
	}
}