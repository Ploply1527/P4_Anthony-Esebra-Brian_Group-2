import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Ghost extends GameObject{

	//Region: Variables
	private boolean vulnerable = false;//This controls if the ghost can be eaten or not
	private boolean eaten = false;//This sees if the ghost was eaten or not
	
	private int speed = 3;//This is a possible list of speeds the ghosts can have
	
	private Image ghost1;
	private Image ghost2;
	private Image ghostVul1 = getImage("vul1.png");
	private Image ghostVul2 = getImage("vul2.png");
	private Image ghostEat = getImage("eyes.png");
	//endRegion
	
	public Ghost(int xGrid, int yGrid, String imageName, String g1) {
		super(xGrid, yGrid, imageName);
		
		ghost1 = getImage(imageName);
		ghost2 = getImage(g1);
		
		vulnerable = false;//Makes the ghost not able to be eaten
		//14
		posY -= 9;
	}

	//Region: AI
	//endRegion
	
	//Region: Abstract Voids
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub	
		animation();
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(baseImage, tx, null);
	}
	//Endregion
	
	public void movCol(PacMan p)
	{
		movement(p);
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
					if(vulnerable)
					{
						baseImage = ghostVul1;
					}
					else
					{
						baseImage = ghost1;
					}
				break;
				
				case 1:
					if(vulnerable)
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
		if(eaten)//Run to center
		{
			returnToStart();
		}
		else if(vulnerable)//Run away from pacman when vulnerable
		{
			
		}
		else //This is default movement
		{
			if(collide)
			{
				posY += speed;
				gridY = Math.round((posY)/22);
			}
			else
			{
				posX += speed;
				gridX = Math.round(posX/22);
			}

			tx.setToTranslation(posX - 10, posY - 17);
			
			System.out.print(grid[gridX][gridY]);
			System.out.println("Grid:[" + posX/22 + ", " + gridY + "]");
		}
	}
	//endRegion
	
	//This method should be called when the invincible pellet is used
	public void turnVulnerable()
	{
		vulnerable = true;
		
		//Timer for invulnerabiliy time
	}
	
	//This is the collision done with the pacMan stuff
	private void collision(PacMan p)
	{
		if((p.getX() <= posX - 7 && p.getX() >= posX - 13) &&
		   (p.getY() <= posY - 14 && p.getY() >= posY - 20))
		{
			if(vulnerable)
			{
				eaten = true;
			}
			else
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
			moveToX(x,true);
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
			int mov = temp / Math.abs(temp);
	
			CollisionCheck(false, mov, 4);
			
			if(!collide)
			{
				posX += (speed*mov);	
			}
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
			}
		}
	}
	//This method should be called when pacman eats the ghost
	private void returnToStart()
	{
		//if() if point is not reached
		//moveToPos();
		/*
		else
		{
			eaten  = false;
		}
		 */
	}
}