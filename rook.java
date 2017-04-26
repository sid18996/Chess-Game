public class rook extends piece 
{
	rook(int row,int colm,char c)
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
		if(row!=r && colm!=c)
			return 0;
		int row_i =r,colm_i =c;
		while(row_i!= row)
		{
			if(row>r)
				row_i++;
			else
				row_i--;
			if(row_i!= row)
			{
				if(board.pic[row_i][colm].status== true)
					return 0;
			}
			else
			{
				if(board.pic[row_i][colm].status== false)
					return 1;
				else
					return 2;
			}
		}
		while(colm_i!= colm)
		{
			if(colm>c)
				colm_i++;
			else
				colm_i--;
			if(colm_i!= colm)
			{
				if(board.pic[row][colm_i].status== true)
					return 0;
			}
			else
			{
				if(board.pic[row][colm_i].status== false)
					return 1;
				else
					return 2;
			}
		}
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
		
	
	
