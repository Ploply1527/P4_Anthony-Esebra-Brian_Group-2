import java.awt.Graphics;

///This is the class that makes the game run
public class Driver {
	
	GameBoard board = new GameBoard();//This is the gameboard
	
	//Region: Ghosts
	Ghost blue = new Ghost(1,1,"Blue");
	Ghost orange = new Ghost(1,1,"Blue");
	Ghost red = new Ghost(1,1,"Blue");
	Ghost pink = new Ghost(1,1,"Blue");
	
	
	public void draw(Graphics g)
	{
		
	}
}
