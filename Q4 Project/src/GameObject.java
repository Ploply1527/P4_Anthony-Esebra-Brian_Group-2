import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

///This should be a class that should be inherited from
public abstract class GameObject {

	//Region:Position Variables
	protected int gridX;//This is where on the x-axis on the grid the ghost is on grid
	protected int gridY;//This is where on the y-axis on the grid the ghost is on grid
	
	protected int posX;//This is the x pixel position the ghost is on the screen
	protected int posY;//This is the y pixel position the ghost is on the screen
	//endRegion
	
	//Region: Animation Variables
	protected Image baseImage;//This is the default image that the game object will use
	protected int timerCount = 0; //This is the timer for the animation
	protected final int changeFrame = 20; //This is how many frames later animation will change
	//endRegion
	
	public GameObject(int xGrid, int yGrid, String imgName)
	{
		gridX = xGrid;
		gridY = yGrid;
		
		baseImage = getImage(imgName); //load the base image
		//posX = (pixelCountX / 28) * gridX;
		//posY = (pixelCountY / 31) * gridY;
		
	}
	
	
	///This is going to be the main logic 
	public abstract void paint(Graphics g);
	
	//This is the animation for each gameObject
	//Protected means that only classes that inherit from this can access it
	protected abstract void animation();
	
	//Region:Movement
	//endRegion
	
	//Region: Getters and Setters
	public int getX() {return gridX;}
	public int getY() {return gridY;}
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
