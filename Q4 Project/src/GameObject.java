import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.geom.AffineTransform;

///This should be a class that should be inherited from
public abstract class GameObject {

	//Region:Position Variables
	protected int gridX;//This is where on the x-axis on the grid the ghost is on grid
	protected int gridY;//This is where on the y-axis on the grid the ghost is on grid
	
	protected int posX;//This is the x pixel position the ghost is on the screen
	protected int posY;//This is the y pixel position the ghost is on the screen
	
	protected int startX;//This is xGrid of where the gameObject will start from
	protected int startY;//This is yGrid of where the gameObejct will start from
	
	protected boolean collide = false;
	protected AffineTransform tx = AffineTransform.getTranslateInstance(posX, posY);
	
	//PacMan board is 28 x 31
	//Scale is x22 larger.
	protected static int[][] grid;
	protected static Tile[][] board = new Tile[29][28];
	//endRegion
	
	//Region: Animation Variables
	protected Image baseImage;//This is the default image that the game object will use
	protected int timerCount = 0; //This is the timer for the animation
	protected final int changeFrame = 20; //This is how many frames later animation will change
	//endRegion
	
	//Region: GameState Variables
	protected static boolean gameOver = false;
	//endRegion
	
	
	public GameObject(int xGrid, int yGrid, String imgName)
	{
		gridX = xGrid;
		gridY = yGrid;
		startX = xGrid;
		startY = yGrid; 
		
		baseImage = getImage(imgName); //load the base image
		posX = gridX * 22;
		posY = gridY * 22;
		
		init(posX, posY);//Initialize the position
	}
	
	//Region: Abstract voids
	///This is going to be the main logic 
	public abstract void paint(Graphics g);
	
	//This is the animation for each gameObject
	//Protected means that only classes that inherit from this can access it
	protected abstract void animation();
	
	//endregion
	
	//Game Resetting
	public static void freeze()
	{
		gameOver = true;
	}
		
	//This resets the gameObject
	public void reset() 
	{
		gameOver = false;
		gridX = startX;
		gridY = startY;
		posX = startX * 22; 
		posY = startY * 22 - 9;
	}
		
	//Region:Movement
	protected void CollisionCheck(boolean vertical, int dir, int div)
	{
		int tempPos;
		if(vertical)
		{
			tempPos = (posY + 12 * dir)/22;
			collide = (grid[tempPos][gridX] % div == 0)
			|| (grid[tempPos][((posX + 2)/ 22)] % div == 0)
			|| (grid[tempPos][((posX - 2)/ 22)] % div == 0);
			
		}
		
		else
		{
			tempPos = (posX + (15 + (6*dir))* dir)/22;
			//Took the easy way out of a problem. Never punished lol
			try 
			{
				collide = (grid[gridY][tempPos] % div == 0)
					|| (grid[((posY + 2)/ 22)][tempPos] % div == 0)  
					|| (grid[((posY - 2)/ 22)][tempPos] % div == 0);
		    } 
			catch(ArrayIndexOutOfBoundsException e) 
			{
				tempPos -= 1;
		    }         
		}
	}
	//endRegion
	
	//Region: Getters and Setters
	public int getX() {return posX;}
	public int getY() {return posY;}
	
	public static void setGrid(int[][] g, boolean first) 
	{
		grid = g; 
		for(int i = 0; i < g.length; i++)
		{
			for(int o = 0; o < g[i].length; o++)
			{
				if(first) {board[i][o] = new Tile(g[i][o],o,i, 0);}
				else { board[i][o].setTile(g[i][o]);}
			}
		}
	}
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
	
	//This is for initialization
	private void init(double a, double b) {
		tx.setToTranslation(a-14, b-17);
		tx.scale(1, 1);
	}
}
