import java.awt.Image;

public class Tile {
	
	private boolean hasPellet;//Boolean to check if there is a pellet or not
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
			//Set pellet tile
			break;
		case 2:
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
	
	///This will be used for collision detection
	public int getTileType()
	{
		return tileType;
	}
	
	public void pelletGet()
	{
		if(tileType == 1 || tileType == 2)
		{
			tileType = 3;
			//Set no pellet tile
		}
	}
}
