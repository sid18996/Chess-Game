public class knight extends piece
{
	knight(int row,int colm,char c)
	{
		this.status=true;
		this.position[0]=row;
		this.position[1]=colm;
		this.colour = c;
	}
	public int move(int row,int colm)					//Return 1 when Move Successfully else 0
	{
		int result = check_move(row,colm);
		if(result==0)
		{
			System.out.printf("Invalid Move, Try Another\n");
			return 0;
		}
		else if(result==1)
		{
			board.store_i=board.pic[position[0]][position[1]];
			board.store_f=board.pic[row][colm];
			board.pic[row][colm]=board.pic[position[0]][position[1]];
			board.pic[row][colm].position[0]=row;
			board.pic[row][colm].position[1]=colm;
			return 1;
		}
		else
		{
			board.store_i=board.pic[position[0]][position[1]];
			board.store_f=board.pic[row][colm];
			board.pic[row][colm].status=false;
			System.out.println("Capture!!!");
			board.pic[row][colm]=board.pic[position[0]][position[1]];
			board.pic[row][colm].position[0]=row;
			board.pic[row][colm].position[1]=colm;
			return 1;
			
		}
		
	}
	int check_move(int row,int colm)
	{
		if(board.pic[row][colm].status == true && board.pic[row][colm].colour == this.colour)
			return 0;
		int r = position[0];
		int c = position[1];
		int mod_r=0,mod_c=0;
		mod_r = Math.abs(row-r);
		mod_c = Math.abs(colm-c);
		if((mod_r==2 && mod_c==1) || (mod_r==1 && mod_c==2))
		{
			if(board.pic[row][colm].status== false)
				return 1;
			else
				return 2;
		}
		else
			return 0;
		
	}
	public String class_name()
	{
		String name= this.getClass().getSimpleName();
		return name;
	}
	public int move_check(int row,int colm)					//Return 1 when Kill Successfully else 0
	{
		if(status==false)
			return 0;
		int result = check_move(row,colm);
		return result;
	}
}