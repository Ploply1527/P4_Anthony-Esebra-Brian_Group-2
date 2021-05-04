import java.awt.Color;
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

	//PacMan board is 28 x 31
	//Scale is x22 larger.
	public Tile[][] board = new Tile[29][28];
	//The board for pacMan
	private Image boardImage = getImage("PacBoard.png");

	private AffineTransform tx = AffineTransform.getTranslateInstance(0, 0);
	
	private final int a = 0;
	//The move ability of the board
	// 0 = false, 1 = true/has pellet, 2 = true/big pellet, 3 = true/no Pellet, 4 = ghost only
	int [][] tileSet =
	{
  //{1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,9,0}
	{a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a},//1
	{a,1,1,1,1,1,1,1,1,1,1,1,1,a,a,1,1,1,1,1,1,1,1,1,1,1,1,a},//2
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//3
	{a,2,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,2,a},//4
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//5
	{a,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,a},//6
	{a,1,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,1,a},//7
	{a,1,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,1,a},//8
	{a,1,1,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,1,1,a},//9
	{a,a,a,a,a,a,1,a,a,a,a,a,3,a,a,3,a,a,a,a,a,1,a,a,a,a,a,a},//10
	{a,a,a,a,a,a,1,a,a,a,a,a,3,a,a,3,a,a,a,a,a,1,a,a,a,a,a,a},//11
	{a,a,a,a,a,a,1,a,a,3,3,3,3,3,3,3,3,3,3,a,a,1,a,a,a,a,a,a},//12
	{a,a,a,a,a,a,1,a,a,3,a,a,a,4,4,a,a,a,3,a,a,1,a,a,a,a,a,a},//13
	{3,3,3,3,3,3,1,3,3,3,a,3,3,4,4,3,3,a,3,3,3,1,3,3,3,3,3,3},//14
	{a,a,a,a,a,a,1,a,a,3,a,a,a,a,a,a,a,a,3,a,a,1,a,a,a,a,a,a},//15
	{a,a,a,a,a,a,1,a,a,3,3,3,3,3,3,3,3,3,3,a,a,1,a,a,a,a,a,a},//16
	{a,a,a,a,a,a,1,a,a,3,a,a,a,a,a,a,a,a,3,a,a,1,a,a,a,a,a,a},//17
	{a,a,a,a,a,a,1,a,a,3,a,a,a,a,a,a,a,a,3,a,a,1,a,a,a,a,a,a},//18
	{a,1,1,1,1,1,1,1,1,1,1,1,1,a,a,1,1,1,1,1,1,1,1,1,1,1,1,a},//19
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//20
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//21
	{a,2,1,1,a,a,1,1,1,1,1,1,1,3,3,1,1,1,1,1,1,1,a,a,1,1,2,a},//22
	{a,a,a,1,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,1,a,a,a},//23
	{a,a,a,1,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,1,a,a,a},//24
	{a,1,1,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,1,1,a},//25
	{a,1,a,a,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,a,a,1,a},//26
	{a,1,a,a,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,a,a,1,a},//27
	{a,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,a},//28
	{a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a} //29
	};
	
	//Region: GameObjects

	//Region: Pac Man
	PacMan pacMan = new PacMan(14,22,"Pacman1.png");
	int directionInput = 0;//This is the direction pacman is facing
	
	/*
	//Region: Ghosts
	Ghost[] ghostList = new Ghost[4];
	Ghost blue = new Ghost(1,1,"Blue");
	Ghost orange = new Ghost(1,1,"Blue");
	Ghost red = new Ghost(1,1,"Blue");
	Ghost pink = new Ghost(1,1,"Blue");
	*/
	//endRegion

	///This is the base logic for the game board 
	public void paint(Graphics g)
	{
		//Refresh is not working
		super.paintComponent(g);// This is for refresh
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(boardImage, tx, null); 
		
		pacMan.paint(g);
		pacMan.movement(directionInput);
	}
	
	//This is the constructor for the gameboard
	public GameBoard()
	{
		init(0,0);
		CreateFrame(); //This creates the game frame
		
		SetTiles();    //This sets all of the tiles
		SetObjects();  //This sets all of the gameObjects
	}
	
	void CreateFrame()
	{
		JFrame frame = new JFrame("Pac Man");
		frame.setSize(616,682);
		
		frame.setBackground(Color.black);
		frame.setResizable(false);
		frame.add(this);
		
		Timer t = new Timer(16,this);
		t.start();
		
		//this part makes sure the x button closes the program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//make the frame show up
		frame.setVisible(true);
	}
	
	void SetTiles()
	{
		for(int i = 0; i < tileSet.length; i++)
		{
			for(int o = 0; o < tileSet[i].length; o++)
			{
				board[i][o] = new Tile(tileSet[i][o]);
			}
		}
		System.out.println("Done!");
	}
	void SetObjects()
	{
		
	}
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub	
		switch(arg0.getKeyCode()) {
			
			case 38: //up
				directionInput = 0;
				break;
			case 40: //down
				directionInput = 1;
				break;
			case 37://left
				directionInput = 2;
				break;	
			case 39: //right
				directionInput = 3;
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
		tx.scale(0.514, 0.5);
	}
	
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