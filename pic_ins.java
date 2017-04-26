public class pic_ins extends piece
{
	pic_ins(int row,int colm)
	{
		this.status=false;
		this.position[0]=row;
		this.position[1]=colm;
		this.colour = 'X';
	}
	public int move(int row,int colm)
	{
		int result = check_move(row,colm);
		if(result==0)
		{
			System.out.printf("Invalid Move, Try Another");
			return 0;
		}
		else if(result==1)
		{
			board.store_i=board.pic[position[0]][position[1]];
			board.store_f=board.pic[row][colm];
			board.pic[position[0]][position[1]].status = false;
			this.position[0]=row;
			this.position[1]=colm;
			return 1;
		}
		else
		{
			board.store_i=board.pic[position[0]][position[1]];
			board.store_f=board.pic[row][colm];
			board.pic[row][colm].status=false;
			board.pic[position[0]][position[1]].status = false;
			this.position[0]=row;
			this.position[1]=colm;
			return 1;
		}
	}
	int check_move(int row,int colm)
	{
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