package algorithms.lab;

//第二种下界，部分解的代价+(部分解城市不在路径上的最小出边+其他城市最小两个出边）/2
import java.util.Random;
public class BackTSP02 {
	public int n=20;//图G的顶点数
	public int[] x;//当前解
	public int[] bestx;//当前最优解
	public int bestc;//当前最优值
	public int cc;//当前费用
	public int[][] a;//图G的邻接矩阵
	int[][] EdgeMinOut =new int[n+1][2];
	public BackTSP02(int[][] aa, int nn){
		n=nn;
		x=new int[n+1];
		for(int i=1;i<=n;i++){
			x[i]=i;
		}
		bestc=Integer.MAX_VALUE;
		bestx=new int[n+1];
		cc=0;
		a=aa;
		//EdgeMinOut[i]=i的最小出边费用

		//找到每个城市最小两条出边并按从小到大排序
		for(int i=1;i<=n;i++) {
			int min=Integer.MAX_VALUE;
			for(int j=1;j<=n;j++)
				if(a[i][j]!=-1&&a[i][j]<Integer.MAX_VALUE&&a[i][j]<min)
					min=a[i][j];
			EdgeMinOut[i][0]=min;
		}
		for(int i=1;i<=n;i++) {
			int min=Integer.MAX_VALUE;
			int count=0;
			for(int j=1;j<=n;j++) {
				if(a[i][j]== EdgeMinOut[i][0])
					count++;
				if(a[i][j]!=-1&&a[i][j]> EdgeMinOut[i][0]&&a[i][j]<Integer.MAX_VALUE&&a[i][j]<min)
					min=a[i][j];
			}
			if(count>=2)
				EdgeMinOut[i][1]= EdgeMinOut[i][0];
			else
				EdgeMinOut[i][1]=min;
		}

	}
	public void swap(int[] x,int i,int j){//交换函数
		int temp=x[i];
		x[i]=x[j];
		x[j]=temp;
	}
	public void backtrack(int i){
		if(i==n){
			//如果是最短路径则跟新最优解
			if(a[x[n-1]][x[n]]!=-1&&a[x[n]][1]!=-1&&(bestc==Integer.MAX_VALUE)||cc+a[x[n-1]][x[n]]+a[x[n]][1]<bestc){
				for(int j=1;j<=n;j++){
					bestx[j]=x[j];
				}
				bestc=cc+a[x[n-1]][x[n]]+a[x[n]][1];
			}
		}else{
			for(int j=i;j<=n;j++){
				if(a[x[i-1]][x[j]]!=-1){
					int cc1=cc+a[x[i-1]][x[j]];
					int temp;
					temp=x[i];
					x[i]=x[j];
					x[j]=temp;
					int low;//下界
					int sum=0;
					for(int k=i+1;k<=n;k++) {
						sum=sum+ EdgeMinOut[x[k]][0]+ EdgeMinOut[x[k]][1];
					}
					if(a[x[0]][x[1]]!= EdgeMinOut[1][0]&&a[x[i-1]][x[i]]!= EdgeMinOut[x[i]][0]) {
						low=(cc1*2+ EdgeMinOut[1][0]+ EdgeMinOut[x[i]][0]+sum)/2;
					}
					else if(a[x[0]][x[1]]== EdgeMinOut[1][0]&&a[x[i-1]][x[i]]== EdgeMinOut[x[i]][0]) {
						low=(cc1*2+ EdgeMinOut[1][1]+ EdgeMinOut[x[i]][1]+sum)/2;
					}
					else if(a[x[0]][x[1]]!= EdgeMinOut[1][0]&&a[x[i-1]][x[i]]== EdgeMinOut[x[i]][0]) {
						low=(cc1*2+ EdgeMinOut[1][0]+ EdgeMinOut[x[i]][1]+sum)/2;
					}
					else
						low=(cc1*2+ EdgeMinOut[1][1]+ EdgeMinOut[x[i]][0]+sum)/2;

					if(low<bestc){
						swap(x,i,j);
						cc+=a[x[i-1]][x[i]];
						backtrack(i+1);
						cc-=a[x[i-1]][x[i]];
						swap(x,i,j);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();    //获取开始时间
		Random rand=new Random();
		int n=12;
		int a[][]=new int[n+1][n+1];
		for(int i=1;i<=n;i++)
			for(int j=1;j<=n;j++) {
				if(i==j)a[i][j]=-1;
				else if(i>j)a[i][j]=a[j][i];
				else a[i][j]=rand.nextInt(50)+1;
			}
		BackTSP02 b=new BackTSP02(a,n);
		b.backtrack(2);
		System.out.println("最短回路长为："+b.bestc);
		System.out.print("最短回路为：");
		for(int i=1;i<n;i++){
			System.out.print(b.bestx[i]+"->");
		}
		System.out.print(b.bestx[n]);
		System.out.println("->"+b.bestx[1]);
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
	}
}

