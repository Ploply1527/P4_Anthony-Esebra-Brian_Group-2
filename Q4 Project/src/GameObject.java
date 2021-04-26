import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

///This should be a class that should be inherited from
public abstract class GameObject {

	//Region:Position Variables
	private int gridX;//This is where on the x-axis on the grid the ghost is on grid
	private int gridY;//This is where on the y-axis on the grid the ghost is on grid
	
	private int posX;//This is the x pixel position the ghost is on the screen
	private int posY;//This is the y pixel position the ghost is on the screen
	//endRegion
	
	private Image baseImage;//This is the default image that the game object will use
	
	public GameObject(int xPos, int yPos, String imgName)
	{
		gridX = xPos;
		gridY = yPos;
		
		baseImage = getImage(imgName); //load the base image
		//posX = (pixelCountX / 28) * gridX;
		//posY = (pixelCountY / 31) * gridY;
		
	}
	
	
	///This is going to be the main logic 
	public abstract void paint(Graphics g);
	
	//This is the animation for each gameObject
	//Protected means that only classes that inherit from this can access it
	protected abstract void animation();
	
	//Region: Getters and Setters
	public int getX() {return posX;}
	
	//End Region
	
	//I ripped this straight out of the duck hunt code
	protected Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = GameObject.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
