import java.util.ArrayList;
//This is a class for debugging purposes
public class Debug {
	
	//This is the list of debug stuff
	private static DebugLog[] debugList = new DebugLog[8];
	private static String[] debugDisplay = new String[8];
	private static boolean change = false;
	
	private static int filledSpace = 0;
	
	public Debug(){}
	
	public static void Log(String s)
	{
		boolean unique = true;
		for(int i = 0; i < debugList.length; i++)
		{
			if(debugList[i].getLog().equals(s))
			{
				debugList[i].increase();
				unique = false;
			}
			else if(debugList[i] == null)
			{
				debugList[filledSpace] = new DebugLog(s);
				filledSpace++;
			}
		}
		if(unique && filledSpace >= debugList.length)
		{
			debugList[filledSpace%debugList.length] = new DebugLog(s);
			filledSpace++;
		}
		change = true;
	}
	
	public void Display()
	{
		//Update debugDisplay if there has been change
		if(change)
		{
			for(int i = 0; i < (filledSpace%debugList.length); i++)
			{	
				String s = "";
				s += debugList[i].getLog();
				//This is for spaces
				for(int o = 0; o < 20 - debugList[i].getLog().length() - debugList[i].getDigit(); o++)
				{
					s+= " ";
				}
				s+= debugList[i].getCount();
			}
			change = false;
		}
		
		
		//Displays the log
		for(int i = 0; i < (filledSpace%debugDisplay.length); i++)
		{
			System.out.println(debugDisplay[i]);
		}
	}
}

class DebugLog
{
	int logCount = 0;
	String log = "";
	
	public DebugLog(String debug)
	{
		log = debug;
	}
	
	public String getLog() { return log; }
	public int getCount()  {return logCount;}
	
	public int getDigit() 
	{
		int temp = logCount;
		int digits = 1;
		
		while(temp > 10)
		{
			temp/=10;
			digits++;
		}
		
		return digits;
	}
	
	public void increase() { logCount++; }
}