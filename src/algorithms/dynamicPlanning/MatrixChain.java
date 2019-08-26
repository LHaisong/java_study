package algorithms.dynamicPlanning;

/**
 *
 * @author lenovo
 *
 */
public class MatrixChain {
	/**
	 * 使用递归的方法
	 * @param p 用于存放n个矩阵的n+1个行列值
	 * @param t 用于存放矩阵链的断点
	 * @param i 矩阵链开始位置
	 * @param j 矩阵链结束位置
	 * @return 最小乘次
	 *
	 */
	public  int recumatrixChain(int[] p, int[][]t, int i, int j) {
		if(i==j) {
			return 0;
		}
		int minValue= recumatrixChain(p, t,i, i)+ recumatrixChain(p,t, i+1, j)+p[i-1]*p[i]*p[j];
		t[i][j]=i;
		for(int k=i+1;k<j;k++) {
			int temp= recumatrixChain(p, t,i, k)+ recumatrixChain(p,t, k+1, j)+p[i-1]*p[k]*p[j];
			if(temp<minValue) {
				minValue=temp;
				t[i][j]=k;
			}
		}
		return minValue;
	}

	/**
	 * 非递归方法求解矩阵连乘问题最优解
	 * @param p n个矩阵的n+1个行列值,p[0,...,n]
	 * @param t 分裂点追踪矩阵,n+1阶,t[0,...,n]
	 * @return 矩阵链p的最小乘次
	 */
	public int matrixChain(int[] p, int[][] t){

		int[][] multiplyNumMatrix = new int[p.length][p.length];//最小乘次矩阵
		for(int i=0; i<p.length; i++){
			for(int j=0; j<p.length; j++){
				multiplyNumMatrix[i][j] = -1;
				t[i][j] = -1;//追踪矩阵
			}
		}

		for(int i=0; i<multiplyNumMatrix.length; i++) multiplyNumMatrix[i][i] = 0;//一个矩阵乘次为0，即步长为1的矩阵链乘次为0

		for(int r=2; r<p.length; r++){//步长从2到矩阵链长度
			for(int i=1; i<=p.length-r; i++){//计算所有步长为r的链中的最小乘次链
				int j = i + r - 1;//2 3 4
				multiplyNumMatrix[i][j] = multiplyNumMatrix[i+1][j] + p[i-1]*p[i]*p[j];
				t[i][j] = i;//12 23 34......//13 24 35....
				for(int k=i+1; k<j; k++){
					int temp = multiplyNumMatrix[i][k] + multiplyNumMatrix[k+1][j] + p[i-1]*p[k]*p[j];
					if(temp < multiplyNumMatrix[i][j]){
						multiplyNumMatrix[i][j] = temp;
						t[i][j] = k;
					}
				}
			}
		}
		return multiplyNumMatrix[1][p.length-1];
	}
	/**
	 *
	 * @param p 用于存放n个矩阵的n+1个行列值
	 * @param t 分裂点追踪矩阵
	 * @return
	 */
	public int returnChain(int[]p,int[][]t) {
		int matrixLength=p.length-1;
		return recumatrixChain(p, t, 1, matrixLength);
	}
	/**
	 *
	 * @param args
	 */
	public static void main(String[]args) {

		int p[]=new int[] {30,35,15,5,10,20,25,12,25};
		int [][]s = new int[p.length+1][p.length+1];
		long startTime1 = System.nanoTime();    //获取开始时间
		int min=new MatrixChain().returnChain(p, s);
		long endTime1 = System.nanoTime();
		System.out.println(min+" "+(endTime1-startTime1)+"ns");

		long startTime2 = System.nanoTime();//获取开始时间
		int num=new MatrixChain().matrixChain(p,s);
		long endTime2 = System.nanoTime();
		System.out.println(num+" "+(endTime2-startTime2)+"ns");
	}
}
