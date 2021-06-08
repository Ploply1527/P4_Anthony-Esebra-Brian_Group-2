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
	private final int speed = 0;//This is a possible list of speeds the ghosts can have
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
		//moveToPos(0,50);
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
		
		if(!gameOver)
		{
			timerCount++;
		}
	}
	
	protected void movement(PacMan p)
	{
		collide = false;
		if(gameOver)//Stop when pacman is dead
		{
			moveToPos(posX,posY);
			System.out.println("Don't Move");
		}
		else if(eaten)//Run to center
		{
			moveToPos(308,1);
		}
		else if(vulnerable)//Run away from pacman when vulnerable
		{
			
		}
		else //This is default movement
		{
			moveToPos(p.getX(), p.getY());//Move to PacMan Pos
		}
		tx.setToTranslation(posX - 14, posY - 17);
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
		if((p.getX() <= posX + 13 && p.getX() >= posX - 13) &&
		   (p.getY() <= posY + 3  && p.getY() >= posY - 23))
		{
			if(vulnerable && !eaten)
			{
				GUI.addScore(1000);//Increase points by 1000
				eaten = true;
			}
			else if(!vulnerable && p.isAlive)
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
			int dir = 0;
			if(posX != pX)
			{
				int temp = posX - pX;
				try 
				{
					dir = temp / Math.abs(temp);
				}
				catch(ArithmeticException e)
				{
					temp = 1;
				}
			}
	
			CollisionCheck(false, dir, 4);
			
			if(!collide)
			{
				posX -= speed*dir;	
				gridX = Math.round(posX/22);
			}	
		}
	}
	private void moveToY(int pY, boolean recBreak)
	{
		if((posY - 3 <= pY && posY + 3 >= pY)&& recBreak)
		{
			moveToX(posX, false);
		}
		else
		{
			int dir = 0;
			if(posY != pY)
			{
				int temp = posX - pY;
				try 
				{
					dir = temp / Math.abs(temp);
				}
				catch(ArithmeticException e)
				{
					temp = 1;
				}
			}
			CollisionCheck(true, dir, 4);
			
			if(!collide)
			{
				posY -= (speed*dir);	
				gridY = Math.round((posY)/22);
			}
		}
	}
	
	//This is the void that moves away from the player
	private void moveAway()
	{
		
	}
	
	@Override
	public void reset() 
	{
		super.reset();
		
	}
}
