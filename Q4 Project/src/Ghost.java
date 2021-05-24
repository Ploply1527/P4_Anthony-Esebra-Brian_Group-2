import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Ghost extends GameObject{

	//Region: Variables
	private boolean vulnerable = false;//This controls if the ghost can be eaten or not
	private boolean eaten = false;//This sees if the ghost was eaten or not
	
	private int speeds[] = {1,2,3,4,5};//This is a possible list of speeds the ghosts can have
	
	private Image ghost1;
	private Image ghost2;
	private Image ghostVul1 = getImage("vul1");
	private Image ghostVul2 = getImage("vul2");
	private Image ghostEat = getImage("eyes");
	//endRegion
	
	public Ghost(int xGrid, int yGrid, String imageName, String g1) {
		super(xGrid, yGrid, imageName);
		vulnerable = false;//Makes the ghost not able to be eaten
		posY -= 9;
		ghost1 = getImage(imageName);
		ghost2 = getImage(g1);
	}

	//Region: AI
	//endRegion
	
	//Region: Abstract Voids
	public void paint(Graphics g, PacMan p) {
		// TODO Auto-generated method stub
		
		collision(p);
		movement(p);
		
		animation();
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(baseImage, tx, null);
	}
	
	protected void animation() {
		// TODO Auto-generated method stub
		if(eaten)
		{
			baseImage = ghostEat;
		}
		else
		{
			
			switch(timerCount / 4)
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
		
		
		tx.setToTranslation(posX - 10, posY - 17);
	}
	//endRegion
	
	//This method should be called when the invincible pellet is used
	public void turnVulnerable()
	{
		vulnerable = true;
		
		//Timer for invulnerabiliy time
	}
	
	//This is the collision done with the pacMan stuff
	public void collision(PacMan p)
	{
		if((p.getX() <= posX - 7 && p.getX() >= posX - 13) &&
		   (p.getY() <= posY - 14 && p.getY() >= posY - 20))
		{
			if(vulnerable)
			{
				returnToStart();
			}
			else
			{
				p.Die();
			}
		}
		
	}
	//This method should be called when pacman eats the ghost
	private void returnToStart()
	{
		eaten  = false;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
