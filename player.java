import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class player
{
	Scanner sn = new Scanner(System.in);
	char colour;
	king kig1;
	king kig2;
	pawn pwn1[]=new pawn[8];
	queen qun1;
	rook rok1[]=new rook[2];
	knight knt1[]=new knight[2];
	bishup bsp1[]=new bishup[2];
	
	
	pawn pwn2[]=new pawn[8];
	queen qun2;
	rook rok2[]=new rook[2];
	knight knt2[]=new knight[2];
	bishup bsp2[]=new bishup[2];
	
	pic_ins ins[]=new pic_ins[64];
	player(char c)
	{
		colour=c;
		if(c =='B')
		{
			
			kig1 = new king(0,4,'B');
			qun1 = new queen(0,3,'B');
			rok1[0]= new rook(0,0,'B');
			rok1[1]= new rook(0,7,'B');
			knt1[0]= new knight(0,1,'B');
			knt1[1]= new knight(0,6,'B');
			bsp1[0]= new bishup(0,2,'B');
			bsp1[1]= new bishup(0,5,'B');
			for(int i=0;i<8;i++)
				pwn1[i]= new pawn(1,i,'B');
			
			board.pic[0][4]=kig1;
			board.pic[0][3]=qun1;
			board.pic[0][0]=rok1[0];
			board.pic[0][7]=rok1[1];
			board.pic[0][1]=knt1[0];
			board.pic[0][6]=knt1[1];
			board.pic[0][2]=bsp1[0];
			board.pic[0][5]=bsp1[1];
			for(int i=0;i<8;i++)
				board.pic[1][i]=pwn1[i];
			
		}
		if(c == 'W')
		{
			
			kig2 = new king(7,4,'W');
			qun2 = new queen(7,3,'W');
			rok2[0]= new rook(7,0,'W');
			rok2[1]= new rook(7,7,'W');
			knt2[0]= new knight(7,1,'W');
			knt2[1]= new knight(7,6,'W');
			bsp2[0]= new bishup(7,2,'W');
			bsp2[1]= new bishup(7,5,'W');
			for(int i=0;i<8;i++)
				pwn2[i]= new pawn(6,i,'W');
			
			board.pic[7][4]=kig2;
			board.pic[7][3]=qun2;
			board.pic[7][0]=rok2[0];
			board.pic[7][7]=rok2[1];
			board.pic[7][1]=knt2[0];
			board.pic[7][6]=knt2[1];
			board.pic[7][2]=bsp2[0];
			board.pic[7][5]=bsp2[1];
			for(int i=0;i<8;i++)
				board.pic[6][i]=pwn2[i];
						
		}
		
	}
	
	public void creat_empty()
	{
		int l=0;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				ins[l++]=new pic_ins(i,j);
			}
		}
		l=16;
		for(int i=2;i<=5;i++)
		{
			for(int j=0;j<8;j++)
			{
				board.pic[i][j]=ins[l++];
			}
		}
	}
	
	public int play_chance()
	{
		int row_i=0,colm_i=0,row_f=0,colm_f=0;
		boolean successful_move=false;
		while(successful_move==false)
		{
			int loop =1;
			int l=1;
			while(loop==1){
				try
				{
					if(l==0)
						sn.nextLine();
					System.out.printf("Player %c \n Enter Piece Initial and Final Positions\n",colour);
					sn.hasNextInt();
					row_i=sn.nextInt();
					colm_i=sn.nextInt();
					row_f=sn.nextInt();
					colm_f=sn.nextInt();
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("Invalid Input");
					l=0;
					continue;
				}
			}
			int res1=test1(row_i,colm_i,row_f,colm_f);
			if(res1==1)
				continue;
			
			char colr=colour;	
					
			if(board.pic[row_i][colm_i].colour==colr)
			{
				int move_status = board.pic[row_i][colm_i].move(row_f,colm_f);
				if(move_status==1)
				{
					ins[(8*row_i)+colm_i]= new pic_ins(row_i,colm_i);
					board.pic[row_i][colm_i]=ins[(8*row_i)+colm_i];
					successful_move=true;
				}
				else
					System.out.println("You Chosen  Wrong Final Position of Piece, Choice a Valied One");
			}
			else
			{
				System.out.println("You Chosen Wrong Piece, Choice a Valied One");
			}
			
			
		}
		if(board.pic[row_f][colm_f].colour=='W')
		{
			int check_s=self_check(2);
			if(check_s==1)
			{
				System.out.println("Invalid Move");
				board.pic[row_i][colm_i]=board.store_i;
				board.pic[row_i][colm_i].position[0]=row_i;
				board.pic[row_i][colm_i].position[1]=colm_i;
				board.pic[row_f][colm_f]=board.store_f;
				//System.out.println(board.pic[row_i][colm_i].colour+" "+board.pic[row_i][colm_i].status+" "+board.pic[row_i][colm_i].position[0]+" "+board.pic[row_i][colm_i].position[1]);
				return 1;
			}
		}
		else
		{
			int check_s=self_check(1);
			if(check_s==1)
			{
				System.out.println("Invalid Move");
				board.pic[row_i][colm_i]=board.store_i;
				board.pic[row_i][colm_i].position[0]=row_i;
				board.pic[row_i][colm_i].position[1]=colm_i;
				board.pic[row_f][colm_f]=board.store_f;
				System.out.println(board.pic[row_i][colm_i].colour+" "+board.pic[row_i][colm_i].status+" "+board.pic[row_i][colm_i].position[0]+" "+board.pic[row_i][colm_i].position[1]);
				return 1;
			}
		}
		return 0;
	}
	
	int test1(int row_i,int colm_i,int row_f,int colm_f)
	{
		if((row_i>7 ||row_i<0) || (row_f>7 ||row_f<0) || (colm_i>7 ||colm_i<0) || (colm_f>7 ||colm_f<0))
		{
			System.out.println("Invalid Inputs");
			return 1;
		}
		if(board.pic[row_i][colm_i].status == false)
		{
			System.out.println("Sorry, Position is Empty");
			return 1;
		}
			return 0;
	}
	
	boolean piece_status(int ply)
	{
		if(ply==1)
		{
			return kig1.status;
		}
		else
			return kig2.status;
	}
	
	int check(int p_c)
	{
		int flag;
		if(p_c==1)
		{
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if((board.pic[i][j].status==true) && (board.pic[i][j].colour=='W'))
					{
						int row=kig1.position[0];
						int colm=kig1.position[1];
						flag=board.pic[i][j].move_check(row,colm);
						if(flag==2)
						{
							System.out.printf("W%s is Giving Check\n",board.pic[i][j].getClass().getSimpleName());
							if(check_mate(1)==1)
								return 1;
						}
					}
				}
			}
		}
		if(p_c==2)	
		{
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if((board.pic[i][j].status==true) && (board.pic[i][j].colour=='B'))
					{
						int row=kig2.position[0];
						int colm=kig2.position[1];
						flag=board.pic[i][j].move_check(row,colm);
						if(flag==2)
						{
							System.out.printf("B%s is Giving Check\n",board.pic[i][j].getClass().getSimpleName());
							if(check_mate(2)==1)
								return 1;
						}
					}
				}
			}
		}
		return 0;
	}
	
	int self_check(int p_c)
	{
		int flag;
		if(p_c==1)
		{
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if((board.pic[i][j].status==true) && (board.pic[i][j].colour=='W'))
					{
						int row=kig1.position[0];
						int colm=kig1.position[1];
						flag=board.pic[i][j].move_check(row,colm);
						if(flag==2)
							return 1;
					}
				}
			}
		}
		if(p_c==2)	
		{
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if((board.pic[i][j].status==true) && (board.pic[i][j].colour=='B'))
					{
						int row=kig2.position[0];
						int colm=kig2.position[1];
						flag=board.pic[i][j].move_check(row,colm);
						if(flag==2)
							return 1;
					}
				}
			}
		}
		return 0;
	}
	
	
	int test(int row_i,int colm_i)
	{
		if((row_i>7 ||row_i<0) || (colm_i>7 ||colm_i<0))
			return 0;
		return 1;
	}
	
	
	int check_mate(int p)
	{
		int c_m=0;
		if(p==1)
		{
		int X=kig1.position[0];
		int Y=kig1.position[1];
		board.store_k=kig1;

		int s=test(X+1,Y);
		if(s==1)
		{
		board.store_p=board.pic[X+1][Y];
		int f=kig1.move_check(X+1,Y);
		if(f==1 || f==2)
		{
			board.pic[X+1][Y]=kig1;
			board.pic[X+1][Y].position[0]=X+1;
			board.pic[X+1][Y].position[1]=Y;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(1);	
			if(check_s!=1)
			{
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X+1][Y]=board.store_p;
				return 0;
			}
			board.pic[X][Y]=board.store_k;
			board.pic[X][Y].position[0]=X;
			board.pic[X][Y].position[1]=Y;
			board.pic[X+1][Y]=board.store_p;
			System.out.println(c_m);
			c_m++;
		}
		}
		s=test(X+1,Y-1);
		if(s==1)
		{
		board.store_p=board.pic[X+1][Y-1];
		int f=kig1.move_check(X+1,Y-1);
		if(f==1 || f==2)
		{
		board.pic[X+1][Y-1]=kig1;
		board.pic[X+1][Y-1].position[0]=X+1;
		board.pic[X+1][Y-1].position[1]=Y-1;
		board.pic[X][Y]=ins[(8*X)+Y];
		int check_s=self_check(1);	
			if(check_s!=1)
			{
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X+1][Y-1]=board.store_p;
				return 0;
			}
			board.pic[X][Y]=board.store_k;
			board.pic[X][Y].position[0]=X;
			board.pic[X][Y].position[1]=Y;
			board.pic[X+1][Y-1]=board.store_p;
			System.out.println(c_m);
			c_m++;
		}
		}
		s=test(X+1,Y+1);
		if(s==1)
		{
		board.store_p=board.pic[X+1][Y+1];
		int f=kig1.move_check(X+1,Y+1);
		if(f==1 || f==2)
		{
		board.pic[X+1][Y+1]=kig1;
		board.pic[X+1][Y+1].position[0]=X+1;
		board.pic[X+1][Y+1].position[1]=Y+1;
		board.pic[X][Y]=ins[(8*X)+Y];
		int check_s=self_check(1);	
			if(check_s!=1)
			{
				System.out.println(c_m);
				c_m++;
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X+1][Y+1]=board.store_p;
				return 0;
			}
			board.pic[X][Y]=board.store_k;
			board.pic[X][Y].position[0]=X;
			board.pic[X][Y].position[1]=Y;
			board.pic[X+1][Y+1]=board.store_p;
			System.out.println(c_m);
			c_m++;
		}
		}

		s=test(X,Y-1);
		if(s==1)
		{
		board.store_p=board.pic[X][Y-1];
		int f=kig1.move_check(X,Y-1);
		if(f==1 || f==2)
		{
		board.pic[X][Y-1]=kig1;
		board.pic[X][Y-1].position[0]=X;
		board.pic[X][Y-1].position[1]=Y-1;
		board.pic[X][Y]=ins[(8*X)+Y];
		int check_s=self_check(1);	
			if(check_s!=1)
			{
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X][Y-1]=board.store_p;
				return 0;
			}
			board.pic[X][Y]=board.store_k;
			board.pic[X][Y].position[0]=X;
			board.pic[X][Y].position[1]=Y;
			board.pic[X][Y-1]=board.store_p;
		}
			System.out.println(c_m);
			c_m++;
		}
		s=test(X,Y+1);
		if(s==1)
		{
		board.store_p=board.pic[X][Y+1];
		int f=kig1.move_check(X,Y+1);
		if(f==1 || f==2)
		{
			board.pic[X][Y+1]=kig1;
			board.pic[X][Y+1].position[0]=X;
			board.pic[X][Y+1].position[1]=Y+1;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(1);	
				if(check_s!=1)
				{
					board.pic[X][Y]=board.store_k;
					board.pic[X][Y].position[0]=X;
					board.pic[X][Y].position[1]=Y;
					board.pic[X][Y+1]=board.store_p;
					return 0;
				}
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X][Y+1]=board.store_p;
		}
				System.out.println(c_m);
				c_m++;
		}

		s=test(X-1,Y);
		System.out.println(c_m);
			c_m++;
		if(s==1)
		{
		board.store_p=board.pic[X-1][Y];	
		int f=kig1.move_check(X-1,Y);
		if(f==1 || f==2)
		{
			board.pic[X-1][Y]=kig1;
			board.pic[X-1][Y].position[0]=X-1;
			board.pic[X-1][Y].position[1]=Y;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(1);	
				if(check_s!=1)
				{
					board.pic[X][Y]=board.store_k;
					board.pic[X][Y].position[0]=X;
					board.pic[X][Y].position[1]=Y;
					board.pic[X-1][Y]=board.store_p;
					return 0;
				}
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X-1][Y]=board.store_p;
				System.out.println(c_m);
				c_m++;
		}
		}
		s=test(X-1,Y-1);
		System.out.println(c_m);
			c_m++;
		if(s==1)
		{
		board.store_p=board.pic[X-1][Y-1];	
		int f=kig1.move_check(X-1,Y-1);
		if(f==1 || f==2)
		{
			board.pic[X-1][Y-1]=kig1;
			board.pic[X-1][Y-1].position[0]=X-1;
			board.pic[X-1][Y-1].position[1]=Y-1;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(1);	
				if(check_s!=1)
				{
					board.pic[X][Y]=board.store_k;
					board.pic[X][Y].position[0]=X;
					board.pic[X][Y].position[1]=Y;
					board.pic[X-1][Y-1]=board.store_p;
					return 0;
				}
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X-1][Y-1]=board.store_p;
				System.out.println(c_m);
				c_m++;
		}
		}
		s=test(X-1,Y+1);
		System.out.println(c_m);
			c_m++;
		if(s==1)
		{
		board.store_p=board.pic[X-1][Y+1];
		int f=kig1.move_check(X-1,Y+1);
		if(f==1 || f==2)
		{
			board.pic[X-1][Y+1]=kig1;
			board.pic[X-1][Y+1].position[0]=X-1;
			board.pic[X-1][Y+1].position[1]=Y+1;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(1);	
				if(check_s!=1)
				{
					board.pic[X][Y]=board.store_k;
					board.pic[X][Y].position[0]=X;
					board.pic[X][Y].position[1]=Y;
					board.pic[X-1][Y+1]=board.store_p;
					return 0;
				}
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X-1][Y+1]=board.store_p;
		}
				System.out.println(c_m);
				c_m++;
		}
		return 1;
		}
		if(p==2)
		{
		int X=kig2.position[0];
		int Y=kig2.position[1];
		board.store_k=kig2;

		int s=test(X+1,Y);
		if(s==1)
		{
		board.store_p=board.pic[X+1][Y];
		int f=kig2.move_check(X+1,Y);
		if(f==1 || f==2)
		{
			board.pic[X+1][Y]=kig2;
			board.pic[X+1][Y].position[0]=X+1;
			board.pic[X+1][Y].position[1]=Y;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(2);	
			if(check_s!=1)
			{
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X+1][Y]=board.store_p;
				return 0;
			}
			board.pic[X][Y]=board.store_k;
			board.pic[X][Y].position[0]=X;
			board.pic[X][Y].position[1]=Y;
			board.pic[X+1][Y]=board.store_p;
		}
			System.out.println(c_m);
			c_m++;
		}
		s=test(X+1,Y-1);
		if(s==1)
		{
		board.store_p=board.pic[X+1][Y-1];
		int f=kig2.move_check(X+1,Y-1);
		if(f==1 || f==2)
		{
		board.pic[X+1][Y-1]=kig2;
		board.pic[X+1][Y-1].position[0]=X+1;
		board.pic[X+1][Y-1].position[1]=Y-1;
		board.pic[X][Y]=ins[(8*X)+Y];
		int check_s=self_check(2);	
			if(check_s!=1)
			{
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X+1][Y-1]=board.store_p;
				return 0;
			}
			board.pic[X][Y]=board.store_k;
			board.pic[X][Y].position[0]=X;
			board.pic[X][Y].position[1]=Y;
			board.pic[X+1][Y-1]=board.store_p;
		}
			System.out.println(c_m);
			c_m++;
		}
		s=test(X+1,Y+1);
		if(s==1)
		{
		board.store_p=board.pic[X+1][Y+1];
		int f=kig2.move_check(X+1,Y+1);
		if(f==1 || f==2)
		{
		board.pic[X+1][Y+1]=kig2;
		board.pic[X+1][Y+1].position[0]=X+1;
		board.pic[X+1][Y+1].position[1]=Y+1;
		board.pic[X][Y]=ins[(8*X)+Y];
		int check_s=self_check(2);	
			if(check_s!=1)
			{
				System.out.println(c_m);
				c_m++;
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X+1][Y+1]=board.store_p;
				return 0;
			}
			board.pic[X][Y]=board.store_k;
			board.pic[X][Y].position[0]=X;
			board.pic[X][Y].position[1]=Y;
			board.pic[X+1][Y+1]=board.store_p;
		}
			System.out.println(c_m);
			c_m++;
		}

		s=test(X,Y-1);
		if(s==1)
		{
		board.store_p=board.pic[X][Y-1];
		int f=kig2.move_check(X,Y-1);
		if(f==1 || f==2)
		{
		board.pic[X][Y-1]=kig2;
		board.pic[X][Y-1].position[0]=X;
		board.pic[X][Y-1].position[1]=Y-1;
		board.pic[X][Y]=ins[(8*X)+Y];
		int check_s=self_check(2);	
			if(check_s!=1)
			{
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X][Y-1]=board.store_p;
				return 0;
			}
			board.pic[X][Y]=board.store_k;
			board.pic[X][Y].position[0]=X;
			board.pic[X][Y].position[1]=Y;
			board.pic[X][Y-1]=board.store_p;
		}
			System.out.println(c_m);
			c_m++;
		}
		s=test(X,Y+1);
		if(s==1)
		{
		board.store_p=board.pic[X][Y+1];
		int f=kig2.move_check(X,Y+1);
		if(f==1 || f==2)
		{
			board.pic[X][Y+1]=kig2;
			board.pic[X][Y+1].position[0]=X;
			board.pic[X][Y+1].position[1]=Y+1;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(2);	
				if(check_s!=1)
				{
					board.pic[X][Y]=board.store_k;
					board.pic[X][Y].position[0]=X;
					board.pic[X][Y].position[1]=Y;
					board.pic[X][Y+1]=board.store_p;
					return 0;
				}
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X][Y+1]=board.store_p;
		}
				System.out.println(c_m);
				c_m++;
		}

		s=test(X-1,Y);
		System.out.println(c_m);
			c_m++;
		if(s==1)
		{
		board.store_p=board.pic[X-1][Y];	
		int f=kig2.move_check(X-1,Y);
		if(f==1 || f==2)
		{
			board.pic[X-1][Y]=kig2;
			board.pic[X-1][Y].position[0]=X-1;
			board.pic[X-1][Y].position[1]=Y;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(2);	
				if(check_s!=1)
				{
					board.pic[X][Y]=board.store_k;
					board.pic[X][Y].position[0]=X;
					board.pic[X][Y].position[1]=Y;
					board.pic[X-1][Y]=board.store_p;
					return 0;
				}
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X-1][Y]=board.store_p;
		}
				System.out.println(c_m);
				c_m++;
		}
		s=test(X-1,Y-1);
		System.out.println(c_m);
			c_m++;
		if(s==1)
		{
		board.store_p=board.pic[X-1][Y-1];	
		int f=kig2.move_check(X-1,Y-1);
		if(f==1 || f==2)
		{
			board.pic[X-1][Y-1]=kig2;
			board.pic[X-1][Y-1].position[0]=X-1;
			board.pic[X-1][Y-1].position[1]=Y-1;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(2);	
				if(check_s!=1)
				{
					board.pic[X][Y]=board.store_k;
					board.pic[X][Y].position[0]=X;
					board.pic[X][Y].position[1]=Y;
					board.pic[X-1][Y-1]=board.store_p;
					return 0;
				}
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X-1][Y-1]=board.store_p;
		}
				System.out.println(c_m);
				c_m++;
		}
		s=test(X-1,Y+1);
		System.out.println(c_m);
			c_m++;
		if(s==1)
		{
		board.store_p=board.pic[X-1][Y+1];
		int f=kig2.move_check(X-1,Y+1);
		if(f==1 || f==2)
		{
			board.pic[X-1][Y+1]=kig2;
			board.pic[X-1][Y+1].position[0]=X-1;
			board.pic[X-1][Y+1].position[1]=Y+1;
			board.pic[X][Y]=ins[(8*X)+Y];
			int check_s=self_check(2);	
				if(check_s!=1)
				{
					board.pic[X][Y]=board.store_k;
					board.pic[X][Y].position[0]=X;
					board.pic[X][Y].position[1]=Y;
					board.pic[X-1][Y+1]=board.store_p;
					return 0;
				}
				board.pic[X][Y]=board.store_k;
				board.pic[X][Y].position[0]=X;
				board.pic[X][Y].position[1]=Y;
				board.pic[X-1][Y+1]=board.store_p;
		}
				System.out.println(c_m);
				c_m++;

		}
		return 1;
		}
		return 0;
		} 
}
	