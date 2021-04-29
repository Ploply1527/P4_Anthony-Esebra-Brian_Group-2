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
	public Tile[][] board = new Tile[31][28];
	//The board for pacMan
	private Image boardImage = getImage("PacBoard.png");

	private int x = 0,y = 0;
	
	private int x1 = 0;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	
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
	{a,a,a,a,a,a,1,a,a,3,a,3,3,4,4,3,3,a,3,a,a,1,a,a,a,a,a,a},//14
	{3,3,3,3,3,3,1,3,3,3,a,3,3,4,4,3,3,a,3,3,3,1,3,3,3,3,3,3},//15
	{a,a,a,a,a,a,1,a,a,3,a,3,3,3,3,3,3,a,3,a,a,1,a,a,a,a,a,a},//16
	{a,a,a,a,a,a,1,a,a,3,a,a,a,a,a,a,a,a,3,a,a,1,a,a,a,a,a,a},//17
	{a,a,a,a,a,a,1,a,a,3,3,3,3,3,3,3,3,3,3,a,a,1,a,a,a,a,a,a},//18
	{a,a,a,a,a,a,1,a,a,3,a,a,a,a,a,a,a,a,3,a,a,1,a,a,a,a,a,a},//19
	{a,a,a,a,a,a,1,a,a,3,a,a,a,a,a,a,a,a,3,a,a,1,a,a,a,a,a,a},//20
	{a,1,1,1,1,1,1,1,1,1,1,1,1,a,a,1,1,1,1,1,1,1,1,1,1,1,1,a},//21
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//22
	{a,1,a,a,a,a,1,a,a,a,a,a,1,a,a,1,a,a,a,a,a,1,a,a,a,a,1,a},//23
	{a,2,1,1,a,a,1,1,1,1,1,1,1,3,3,1,1,1,1,1,1,1,a,a,1,1,2,a},//24
	{a,a,a,1,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,1,a,a,a},//25
	{a,a,a,1,a,a,1,a,a,1,a,a,a,a,a,a,a,a,1,a,a,1,a,a,1,a,a,a},//26
	{a,1,1,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,a,a,1,1,1,1,1,1,a},//27
	{a,1,a,a,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,a,a,1,a},//28
	{a,1,a,a,a,a,a,a,a,a,a,a,1,a,a,1,a,a,a,a,a,a,a,a,a,a,1,a},//29
	{a,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,a},//30
	{a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a}//31
	};
	
	//Region: GameObjects

	PacMan pacMan = new PacMan(14,24,"Pacman1.png");
	/*
	//Region: Ghosts
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
		System.out.println("D");
		
		
		g.setColor(Color.yellow);
		g.fillOval(x1,40,34,34);
		x1 += 1;
	}
	
	//This is the constructor for the gameboard
	public GameBoard()
	{
		init(x,y);
		CreateFrame(); //This creates the game frame
		
		SetTiles();    //This sets all of the tiles
		//This is the one that sets the board image

		//URL imageURL = GameBoard.class.getResource("PacBoard.png");
		//boardImage = Toolkit.getDefaultToolkit().getImage(imageURL);
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
	
	void CreateFrame()
	{
		JFrame frame = new JFrame("Pac Man");
		frame.setSize(600,685);
		
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
	
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub	
		switch(arg0.getKeyCode()) {
			
			//slide right
			case 39:
				break;	
			case 37: //left
				break;
			case 38: //up
				break;
			case 40: //down
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
		tx.scale(0.5, 0.5);
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
