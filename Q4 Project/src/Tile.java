import java.awt.Image;

public class Tile {
	
	public static int pelletCount;//This checks how many pellets the board has in total
	
	private int tileType;//This determines what type of tile this is
	
	private Image tileSprite;
	
	public Tile(int tType)
	{
		tileType = tType;
		switch(tType) {
		case 0:
			//Set wall tile
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
			//Set no pellet tile
			break;
		case 4:
			//Set ghost zone tile
			break;
		}
	}
	
	//Region: Public voids
	
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
	private void setWallType()
	{
		
	}
	//endRegion
}
