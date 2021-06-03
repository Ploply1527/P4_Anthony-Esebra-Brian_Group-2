import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class GUI extends JLabel{
	private static int score = 0;
	public static int lives = 3;
	private Font font = new Font("Serif", Font.BOLD, 55);
	
	//Should have topleft at (0,660)
	
	
	public GUI()
	{
		setFont(font);
		//super.setText("Score" + );
	}
	
	public static void addScore(int sc) { score += sc; }
	
	public static void decrementLife() { lives--; }
	
	public static boolean gameOver() { return (lives <= 0);}
	
	public static void reset()
	{
		score = 0;
		lives = 3;
	}
	
	public void paint(Graphics g)
	{
		
	}
	
	//This is the one that makes score into 8 digits
	private String eightDig(int score)
	{
		String ret = "";
		int s = score;
		int digit = 0;
		
		while(score > 9)
		{
			score /= 10;
			digit++;
		}
		
		for(int i = 0; i < 7-digit; i++)
		{
			ret += "0";
		}
		return (ret + s);
	}
}
