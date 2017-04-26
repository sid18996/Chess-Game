public class board
{
	boolean checkm_status;
	public static piece pic[][]= new piece[8][8];
	public static piece store_i;
	public static piece store_f;
	public static piece store_k;
	public static piece store_p;
	player ply1 =new player('B');
	player ply2 =new player('W');
	int win_s=0;
	int if_check=0;
	int win_status()
	{
		int f=0;
		ply1.creat_empty();
		while(checkm_status == false)
		{
			if(f!=1)
			{	
				display();
				int p1_c=ply2.play_chance();
				if(p1_c==1)
					continue;
				if_check=ply1.check(1);
				//if_check=ply1.check_mate(1);
				if(if_check==1)
				{
					checkm_status=true;
					win_s=2;
				}
				if(checkm_status == true)
					break;
			}
			f=0;
			display();
			int p2_c=ply1.play_chance();
			if(p2_c==1)
			{
				f=1;
				continue;
			}
			if_check=ply2.check(2);
			//if_check=ply2.check_mate(1);
			if(if_check==1)
			{
				checkm_status=true;
				win_s=1;
			}
		}
		return win_s;
	}

	void display()
	{
		System.out.println();
		System.out.printf("     ");
		for(int i=0;i<8;i++)
			System.out.printf(" %d   ",i);
		System.out.println();
		for(int i=0;i<8;i++)
		{
			System.out.printf(" %d:- ",i);
			for(int j=0;j<8;j++)
			{
				String name= pic[i][j].class_name();
				if(name=="king")
				{
					if(pic[i][j].colour=='B')
						System.out.printf("kigB ");
					else
						System.out.printf("kigW ");
				}
				else if(name=="queen")
				{
					if(pic[i][j].colour=='B')
						System.out.printf("qunB ");
					else
						System.out.printf("qunW ");
				}
				else if(name=="pawn")
				{
					if(pic[i][j].colour=='B')
						System.out.printf("pwnB ");
					else
						System.out.printf("pwnW ");
				}
				else if(name=="knight")
				{
					if(pic[i][j].colour=='B')
						System.out.printf("kntB ");
					else
						System.out.printf("kntW ");
				}
				else if(name=="rook")
				{
					if(pic[i][j].colour=='B')
						System.out.printf("rokB ");
					else
						System.out.printf("rokW ");
				}
				else if(name=="bishup")
				{
					if(pic[i][j].colour=='B')
						System.out.printf("bspB ");
					else
						System.out.printf("bspW ");
				}
				else if(name=="pic_ins")
				{
					if(pic[i][j].colour=='X')
						System.out.printf(" x   ");
					else
						System.out.printf(" x   ");
				}
			}
			System.out.printf("-:%d ",i);
			System.out.println();
			System.out.println();
		}
		System.out.printf("     ");
		for(int i=0;i<8;i++)
			System.out.printf(" %d   ",i);
		System.out.println();
		System.out.println();
	}	
	
}
	


	
