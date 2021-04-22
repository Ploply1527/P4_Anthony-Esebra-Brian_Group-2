import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

///This should be a class that should be inherited from
public abstract class GameObject {

	private int x;
	private int y;
	
	private Image baseImage;
	
	public GameObject(int xPos, int yPos, String imgName)
	{
		x = xPos;
		y = yPos;
		
	}
	///This is going to be the main logic 
	public abstract void paint(Graphics g);
	
	
}
