public class king extends piece
{
	king(int row,int colm,char c)
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
		
		int position_status_move = check_position_move(row,colm);
		if(position_status_move== 1 && board.pic[row][colm].status == false)
			return 1;
		if(position_status_move == 1 && board.pic[row][colm].status == true)
			return 2;
		else 
			return 0;
	}
	int check_position_move(int row,int colm)
	{
		int r = position[0];
		int c = position[1];
		if((row==r+1 && colm==c-1) || (row==r+1 && colm==c) || (row==r+1 && colm==c+1))
			return 1;
		if((row==r-1 && colm==c-1) || (row==r-1 && colm==c) || (row==r-1 && colm==c+1))
			return 1;
		if((row==r && colm==c-1) || (row==r && colm==+c+1))
			return 1;
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