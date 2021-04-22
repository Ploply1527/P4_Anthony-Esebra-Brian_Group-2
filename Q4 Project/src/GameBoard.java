import javax.swing.JFrame;

public class GameBoard {

	//PacMan board is 28 x 36
	public Tile[][] board = new Tile[28][31];
	
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
	///This is the base logic for the game board 
	public GameBoard()
	{
		CreateFrame(); //This creates the game frame
		SetTiles();    //This sets all of the tiles
	}
	
	void CreateFrame()
	{
		JFrame frame = new JFrame("Pac Man");
		frame.setSize(800,600);
		frame.add(frame, this);

		//this part makes sure the x button closes the program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//make the frame show up
		frame.setVisible(true);
	}
	
	void SetTiles()
	{
		for(int i = 0; i < tileSet.length; i++)
		{
			for(int o = 0; o < tileSet.length; o++)
			{
				board[i][o] = new Tile(tileSet[i][o]);
			}
		}
	}
}
