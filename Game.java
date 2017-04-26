import java.util.*;
public class Game
{
	public static void main(String arg[])
	{
		board brd =new board();
		System.out.printf("Let's Play\n");
		int win=brd.win_status();
		if(win==1)
			System.out.println("Black win");
		else if(win==2)
			System.out.println("White win");
	}
}