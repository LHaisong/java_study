package algorithms.dynamicPlanning;

public class MyMatrixChain {

	public int matrixChain(int []p,int[][]s){
		int[][]resultMatrix=new int[p.length][p.length];
		for(int i=0; i<p.length; i++){
			for(int j=0; j<p.length; j++){
				resultMatrix[i][j] = -1;
				s[i][j] = -1;//追踪矩阵
			}
		}
		for (int i = 0; i <  resultMatrix.length; i++) resultMatrix[i][i] = 0;

		for(int r=2;r<p.length-1;r++){//从补步长为2开始逐步计算
			for(int i=1;i<p.length-r;i++){
				int j=i+r-1;
				resultMatrix[i][j] = resultMatrix[i+1][j] + p[i-1]*p[i]*p[j];
				s[i][j]=i;
				for(int k=i+1;k<j;k++){
					int temp=resultMatrix[i][k]+resultMatrix[k+1][j]+p[i-1]*p[k]*p[j];
					if(temp<resultMatrix[i][j]){
						resultMatrix[i][j]=temp;
						s[i][j]=k;
					}
				}
			}
		}
		return resultMatrix[0][p.length-1];
	}
	public static void main(String[]args){
		int[]p={30,35,15,5,10,20,25,12,25};
		int[][]s=new int[p.length+1][p.length+1];
		int num=new MyMatrixChain().matrixChain(p,s);
		System.out.println(num);
	}
}
