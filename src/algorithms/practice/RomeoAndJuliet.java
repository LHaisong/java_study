package algorithms.practice;

public class RomeoAndJuliet {
	public int[]dx={1,1,0,-1,-1,-1,0,1};
	public int[]dy={0,1,1,1,0,-1,-1,-1};
	//迷宫行列数量
	public int n;
	public int m;
	//封闭房间个数
	int k;
	//地图矩阵
	public int[][]map;
	//已走过路径
	int path[];
	int p,q,r,s;
	int num=0;
	int minturn=Integer.MIN_VALUE;
	int[]best;
	public RomeoAndJuliet(int n_,int m_,int[][]map_,int r_,int j_){
		n=n_;
		m=m_;
		r=r_;
		s=j_;
		map=new int[n*m][n*m];
		map=map;
		path=new int[n*m];
		best=new int[n*m];
	}
	public void backtrack(int x,int y,int cur)
	{
		if(cur>=n*m-k)
		{
			if(x!= r || y!=s)  return;
			int turn=0;
			for(int i=2;i<cur;i++)
				if(path[i]!=path[i-1])   turn++;
			if(turn<minturn)
			{
				//复制最短路径
				for(int i=0;i<path.length;i++){
					best[i]=path[i];
				}
				minturn=turn;
				num=1;
			}
			else if(turn==minturn)   num++;
			return;
		}
		//向下寻路
		for(int i=0;i<8;i++)
		{
			int xx=x+dx[i];
			int yy=y+dy[i];
			if(map[xx][yy]==-1)  continue;
			if(xx<1||xx>n||yy<1||yy>m)  continue;
			path[cur]=i;
			map[xx][yy]=-1;
			backtrack(xx,yy,cur+1);
			map[xx][yy]=0;
		}
	}
	int[] getPath()
	{
		int x=p,y=q;
		map[p][q]=1;
		for(int i=1;i<n*m-k;i++)
		{
			int xx=x+dx[best[i]];
			int yy=y+dy[best[i]];
			map[xx][yy]=map[x][y]+1;
			x=xx;
			y=yy;
		}
		return best;
	}
	public static void main(String[]args){
		int p=1;
		int q =1;
		int r=2;
		int s=2;
		int m=3;
		int n=4;
		int[][]map=new int[m*n][m*n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				map[i][j]=0;
			}
		}
		RomeoAndJuliet rj=new RomeoAndJuliet(n,m,map,r,s);
		rj.backtrack(p,q,1);
		int[]path=rj.getPath();
		for(int i=0;i<m*n;i++){
			System.out.print(path[i]);
		}
	}
}
