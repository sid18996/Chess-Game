public abstract class piece
{
	public boolean status= false;
	int position[] =new int[2];
	char colour='B';
	public abstract int move(int row,int colm);	
	public abstract String class_name();
	public abstract int move_check(int row,int colm);
	
}
