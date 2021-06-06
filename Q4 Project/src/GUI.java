import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JLabel;

public class GUI{
	private static int tempSc = 0;
	private static int score = 0;
	private static int tempLif = 3;
	private static int lives = 3;
	//Should have topleft at (0,660)
	Image scoreText = getImage("Score.png");
	Image livesText = getImage("Lives.png");
	Image life;
	
	Image[] digits = new Image[8];
	//This contains data about numbers
	Image[] numbers = new Image[10];
	public GUI()
	{
		
		for(int i = 0; i < digits.length; i++)
		{
			digits[i] = digit(score,i);
	  	}
		
		life = digit(lives,8);
		
		reset();
	}
	
	public static void addScore(int sc) { score += sc; }
	
	public static void decrementLife() 
	{ 
		lives--;
	}
	
	public static boolean gameOver() { return (lives <= 0);}
	
	public static void reset()
	{
		tempSc = 0;
		score = 0;
		lives = 3;
		tempLif = 3;
	}
	
	public void paint(Graphics g)
	{
		//The one saving resources: Score
		if(tempSc != score && score < 100000000)
		{
			for(int i = 1; i <= digits.length; i++)
			{
				digits[i-1] = digit(score,i);
		  	}
		  
		  	tempSc = score;
		}
		
		//The one saving resources: Life
		if(tempLif != lives)
		{
			tempLif = lives;
		    life = digit(lives,8);
		}

		Graphics2D g2 = (Graphics2D) g;
		
		for(int i = 0; i < digits.length; i++)
		{
			g2.drawImage(digits[i], (100 + (15*i) + 2), 642, null);
		}
		g2.drawImage(scoreText, 0, 635, null);
		g2.drawImage(livesText, 495, 635,null);
		g2.drawImage(life, 600, 640, null);
	}

	private Image digit(int score, int digit)
	{
		int divisor = (int)Math.pow(10, 9 - digit);
		int dig = (score%divisor)/(divisor/10);
		String d = (dig + ".png");
		return getImage(d);
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
}
