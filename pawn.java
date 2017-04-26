public class pawn extends piece
{
	//boolean is_atlastrow;
	pawn(int row,int colm,char c)
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
		int mod= Math.abs(row-position[0]);
		if(mod==2)
		{
			if(colour=='W' && (row==4 && (board.pic[row][colm].status==false && board.pic[row+1][colm].status==false)))
				return 1;
			else if(colour=='B' && (row==3 && (board.pic[row][colm].status==false && board.pic[row-1][colm].status==false)))
				return 1;
			else
				return 0;
		}
		int position_status_move = check_position_move(row,colm);
		if(position_status_move== 1 && board.pic[row][colm].status == false)
			return 1;
		
		int position_status_kill = check_position_kill(row,colm);
		if(position_status_kill == 1 && board.pic[row][colm].status == true)
			return 2;
		else 
			return 0;
		
	}
	int check_position_move(int r,int c)
	{
		char colour_playing= this.colour;
		int row= position[0];
		int colm= position[1];
		if(colour_playing == 'B')
		{
			if(row+1== r && colm== c)
				return 1;
		}
		else
		{
			if(row-1== r && colm == c)
				return 1;
		} 
			return 0;
	}
	int check_position_kill(int r,int c)
	{
		char colour_playing= this.colour;
		int row= position[0];
		int colm= position[1];
		if(colour_playing == 'B' && board.pic[r][c].status == true)
		{
			if((row+1== r && colm+1== c) || (row+1== r && colm-1== c))
				return 1;
		}
		if(colour_playing == 'W' && board.pic[r][c].status == true)
		{
			if((row-1== r && colm+1== c) || (row-1== r && colm-1== c))
				return 1;
		}
		else
			return 0;
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
