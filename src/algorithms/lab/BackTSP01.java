package algorithms.lab;
//第二种下界：每个顶点应该有出入两条边，将邻接矩阵中每行最小两个元素相加在除以2，并向上取整，可得到一种下界
import java.util.Arrays;
import java.util.Random;
public class BackTSP01{
	public int n;//图G的顶点数
	public int[] x;//当前解
	public int[] bestx;//当前最优解
	public float bestc;//当前最优值
	public float cc;//当前费用
	public float[][] a;//图G的邻接矩阵
	public BackTSP01(float[][] aa,int nn){
		n=nn;
		x=new int[n+1];
		for(int i=1;i<=n;i++){
			x[i]=i;
		}
		bestc=Integer.MAX_VALUE;
		bestx=new int[n+1];
		cc=0;
		a=aa;

	}
	public void swap(int[] x,int i,int j){//交换函数
		int temp=x[i];
		x[i]=x[j];
		x[j]=temp;
	}
	public void backtrack(int i){
		if(i==n){
			if(a[x[n-1]][x[n]]!=-1&&a[x[n]][1]!=-1&&(bestc==Integer.MAX_VALUE)||cc+a[x[n-1]][x[n]]+a[x[n]][1]<bestc){
				for(int j=1;j<=n;j++){
					bestx[j]=x[j];
				}
				bestc=cc+a[x[n-1]][x[n]]+a[x[n]][1];
			}
		}else{
			for(int j=i;j<=n;j++){
				if(a[x[i-1]][x[j]]!=-1&&(bestc==Integer.MAX_VALUE||cc+a[x[i-1]][x[j]]<bestc)){
					swap(x,i,j);
					cc+=a[x[i-1]][x[i]];
					backtrack(i+1);
					cc-=a[x[i-1]][x[i]];
					swap(x,i,j);
				}
			}
		}
	}
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();    //获取开始时间
		Random rand=new Random();
		int n=12;
		float a[][]=new float[n+1][n+1];
		for(int i=1;i<=n;i++)
			for(int j=1;j<=n;j++) {
				if(i==j)a[i][j]=-1;
				else if(i>j)a[i][j]=a[j][i];
				else a[i][j]=rand.nextInt(50)+1;
			}

		BackTSP01 b=new BackTSP01(a,n);
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
