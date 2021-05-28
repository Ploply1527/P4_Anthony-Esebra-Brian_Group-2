import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tile {
	
	private int x;
	private int y;
	
	public static int pelletCount;//This checks how many pellets the board has in total
	
	private int initTile;//This is the original type of tile
	private int tileType;//This determines what type of tile this is
	
	private Image tileSprite;//This is the tile sprite
	
	public Tile(int tType, int gx, int gy, int wallSprite)
	{
		x = gx*22;
		y = gy*22;
		
		initTile = tType;
		tileType = tType;
		
		switch(tType) {
		case 0:
			setWallType(wallSprite); //Set wall tile
			break;
		case 1:
			pelletCount++;//Increase amount of pellets there are
			//Set pellet tile
			break;
		case 2:
			pelletCount++;//Increase amount of pellets there are
			//Set big pellet tile
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	//Region: Public voids
	//This will be used to draw pellets
	public void paint(Graphics g)
	{
		drawPellet(g);
		debug(g);
	}
	///This will be used for collision detection
	public int getTileType()
	{
		return tileType;
	}
	
	///This will be used for pellet get stuff
	public void pelletGet()
	{
		if(tileType == 1 || tileType == 2)
		{
			tileType = 3;
			pelletCount--;//Decrease pellet count
			//Set no pellet tile
		}
	}
	//endRegion
	
	//Region:Helper Voids
	///This is a void that is meant to set the different types of wall sprite it will use
	public void setWallType(int wallType)
	{
		//IntValue = Up,Right,Down,Right
	}
	private void drawPellet(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
		switch(tileType)
		{
			case 1:
				g.fillRect(x + 8, y + 7, 6, 6);
				//Draw normal pellet
				break;
				
			case 2:
				g.fillOval(x+1, y+1, 20, 20);
				//Draw big pellet
				break;

			default:
				break;
		}
	}
	//endRegion
	
	//Region:Debug
	private void debug(Graphics g)
	{
		if(tileType%4 == 0)
		{
			g.setColor(Color.gray);
			g.fillRect(x, y, 22, 22);
		}
	}
}
