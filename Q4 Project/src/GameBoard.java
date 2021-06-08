import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Arrays;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener, KeyListener{

	//The titleScreen
	private Image titleScreen = getImage("Title.png");
	private boolean startGame = false;
	private boolean startUp = true;
	private Image titleText = getImage("PressButton.png");
	
	//Other text
	private Image gameOver = getImage("GameOver.png");
	private Image ready = getImage("Ready.png");
	//endRegion
	
	//Region:Timers
	private int startUpTimer = 0;
	private int waitTimer = 0;
	//endRegion
	
	//Region: Chimes
	Music startChime;
	Music doneChime;
	//endRegion
	
	//Region: Loud Mode
	static boolean loud = false;
	//endRegion
	
	//The board for pacMan
	private Image boardImage = getImage("PacBoard.png");
	private AffineTransform tx = AffineTransform.getTranslateInstance(0, 0);
	
	
	//Region: Triggers
	public static boolean resetTrig = false;
	public static boolean deadTrig = false;
	boolean levelBeat = false;
	//endRegion
	
	private final int a = 0;
	int i = 3;
	//The move ability of the board
	//PacMan board is 28 x 31
	// 0 = false, 1 = true/has pellet, 2 = true/big pellet, 3 = true/no Pellet, 4 = ghost only
	int [][] tileSet =
	{
  //{0,1,2,3,4,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6}
	{a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a},//0
	{a,1,1,1,1,1,1,1,1,1,1,1,1,a,a,1,1,1,1,1,1,1,1,1,1,1,1,a},//1
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//2
	{a,2,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,2,a},//3
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//4
	{a,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,a},//5
	{a,1,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,1,a},//6
	{a,1,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,1,a},//7
	{a,1,1,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,1,1,a},//8
	{a,a,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,a,a},//9
	{a,a,a,a,a,a,1,a,a,3,3,3,3,3,3,3,3,3,3,a,a,1,a,a,a,a,a,a},//10
	{a,a,a,a,a,a,1,a,a,3,i,i,i,i,i,i,i,i,3,a,a,1,a,a,a,a,a,a},//11
	{a,a,a,a,a,a,1,a,a,3,i,i,i,i,i,i,i,i,3,a,a,1,a,a,a,a,a,a},//12
	{3,3,3,3,3,3,1,3,3,3,i,i,i,i,i,i,i,i,3,3,3,1,3,3,3,3,3,3},//13
	{a,a,a,a,a,a,1,a,a,3,i,i,i,i,i,i,i,i,3,a,a,1,a,a,a,a,a,a},//14
	{a,a,a,a,a,a,1,a,a,3,i,i,i,i,i,i,i,i,3,a,a,1,a,a,a,a,a,a},//15
	{a,a,a,a,a,a,1,a,a,3,3,3,3,3,3,3,3,3,3,a,a,1,a,a,a,a,a,a},//16
	{a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a},//17
	{a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a},//18
	{a,1,1,1,1,1,1,1,1,1,1,1,1,a,a,1,1,1,1,1,1,1,1,1,1,1,1,a},//19
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//20
	{a,2,1,1,a,a,1,1,1,1,1,1,1,3,3,1,1,1,1,1,1,1,a,a,1,1,2,a},//21
	{a,a,a,1,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,1,a,a,a},//22
	{a,a,a,1,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,1,a,a,a},//23
	{a,1,1,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,1,1,a},//24
	{a,1,a,a,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,a,a,1,a},//25
	{a,1,a,a,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,a,a,1,a},//26
	{a,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,a},//27
	{a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a} //28
	};
	
	//Region: GameObjects
	//Region: Pac Man
	static PacMan pacMan = new PacMan(14,22,"PacClose.png", loud);
	static Direction direction = Direction.Up;
	
	//Region: Ghosts
	//TODO: THeres an issue when generating ghosts
	static Ghost blue = new Ghost(13,14,"blue1.png","blue2.png");
	static Ghost orange = new Ghost(12,13,"orange1.png","orange2.png");
	static Ghost red = new Ghost(15,14,"red1.png","red2.png");
	static Ghost pink = new Ghost(16,13,"pink1.png","pink2.png");
	//endRegion
	GUI gui = new GUI();
	//endRegion
	
	///This is the base logic for the game board 
	public void paint(Graphics g)
	{
		super.paintComponent(g);// This is for refresh
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 616, 700);
		
		Graphics2D g2 = (Graphics2D) g;
		if(startGame)
		{
			g2.drawImage(boardImage, tx, null);
			gui.paint(g);
			
			DrawTiles(g);
			if(startUp)
			{
				startUp();	
				g2.drawImage(ready,260, 300, null);
			}
			else
			{
				pacMan.movement(direction);
				pacMan.paint(g);
				
				blue.movCol(pacMan);	
				orange.movCol(pacMan);		
				red.movCol(pacMan);		
				pink.movCol(pacMan);

				blue.paint(g);
				orange.paint(g);
				red.paint(g);
				pink.paint(g);
			}
			if(deadTrig)
			{
				g2.drawImage(gameOver,260, 300, null);
			}
		}
		else
		{
			g2.drawImage(titleScreen, tx, null);  //TitleScreen	
			g2.drawImage(titleText,150, 400, null);//The other text
		}
		
		resetTriggers();
	}
	
	//This is the constructor for the gameboard
	public GameBoard()
	{
		init(0,35);
		CreateFrame(); //This creates the game frame
		
		GameObject.setGrid(tileSet,true);//This sets all of the tiles
	}
	
	void CreateFrame()
	{
		JFrame frame = new JFrame("Pac Man");
		frame.setSize(616,700);
		
		frame.setResizable(false);
		frame.add(this);
		
		Timer t = new Timer(16,this);
		t.start();
		
		//this part makes sure the x button closes the program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		//make the frame show up
		frame.setVisible(true);
	}
	
	//Region: GUI Stuff
	//endRegion
	
	//Region: Reseting
	
	private void resetTriggers()
	{
		//This is when player collects all pellets
		if(Tile.pelletCount <= 0)
		{
			System.out.println("All Clear");
			if(!levelBeat)
			{
				//Make all gameObjects stop in place
				GameObject.freeze();
				//Play level complete chime
				levelBeat = true;
			}
			
			waitTimer++;
			if(waitTimer > 400)
			{
				GameObject.setGrid(tileSet,false);//This sets all of the tiles
				waitTimer = 0;
				resetGame();
			}
		}
		else if(resetTrig)//This is the one that resets game
		{
			GameObject.freeze();
			///Wait a bit
			waitTimer++;
			if(waitTimer > 130)
			{
				waitTimer = 0;
				resetTrig = false;
				resetGame();
			}
		}
		else if(deadTrig)//This is when player ran out of lives
		{
			//Display GameOver text
			GameObject.freeze();
			endGame();
		}
	}
	
	private void startUp()
	{
		if(startUpTimer >= 290)
		{
			startUp = false;
			startUpTimer = -1;
		}
		
		//Tells the get ready part to disappear after timer finished
		direction = Direction.Up;
		//Tell all gameobjects to stay still
		startUpTimer++;
	}
	
	public void resetGame()
	{
		startUp = true;
		pacMan.reset();
		direction = Direction.Up;
		
		blue.reset();
		orange.reset();
		red.reset();
		pink.reset();
		
		startChime = new Music("PacStart.wav",false);
		startChime.run();//Play Chime
	}
	
	public void endGame()
	{
		waitTimer++;
		if(waitTimer > 130)
		{
			waitTimer = 0;
			GameObject.setGrid(tileSet,false);//This sets all of the tiles
			GUI.reset();
			startGame = false;
		}
	}
	
	//endRegion
	
	void DrawTiles(Graphics g)
	{
		for(int i = 0; i < tileSet.length; i++)
		{
			for(int o = 0; o < tileSet[i].length; o++)
			{
				GameObject.board[i][o].paint(g);
			}
		}
	}
	
	//Region: Input
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub	
		//Start game
		if(!startGame)
		{
			init(0,0);
			startGame = true;
			resetGame();
		}
		switch(arg0.getKeyCode()) {
			
			case 38: //up
				direction = Direction.Up;
				break;
			case 40: //down
				direction = Direction.Down;
				break;
			case 37://left
				direction = Direction.Left;
				break;	
			case 39: //right
				direction = Direction.Right;
				break;
				
				//Debug
			case KeyEvent.VK_1:
				pacMan.Die();
				break;	
		}
		
		//Reset the game on button press when key is pressed and player is dead
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.514, 0.476);
	}
	//endRegion
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = GameBoard.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
}
