package algorithms.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 递归求解tsp
 */
public class BackTSP {
	public static int N;
	public int cl = 0; //当前路径的长度
	public int fl = Integer.MAX_VALUE; //当前只当的最大路径长度
	public int bestX[];
	public int x[];
	public int[][] weight;

	/**
	 * @param w      二维数组存放城市之间的距离
	 * @param x_     最优路径
	 * @param bestX_ 最优值
	 */
	public BackTSP(int[][] w, int[] x_, int[] bestX_) {
		this.bestX = bestX_;
		this.weight = w;
		this.x = x_;
		this.N = weight[0].length;
	}

	public int[] getBestX() {
		return bestX;
	}

	//获取最优路径值
	public int getMinLen() {
		return fl;
	}

	/**
	 * 判断第k个数是否不同与前k-1个数
	 *
	 * @param k
	 * @return bool
	 */
	private boolean nextValue(int k) {
		int i = 0;
		while (i < k) {
			if (x[k] == x[i]) {
				return false;
			}
			i += 1;
		}
		return true;
	}

	/**
	 * 第k条路径选择
	 *
	 * @param k
	 */
	private void backUp(int k) {
		if (k == N - 1) {
			for (int j = 1; j <= N; j++) {
				x[k] = (x[k] + 1) % N;
				if (nextValue(k) && cl + weight[x[k - 1]][x[k]] + weight[x[k]][0] < fl) {//如果最短路径,更新最优解
					fl = cl + weight[x[k - 1]][x[k]] + weight[x[k]][0];
					for (int i = 0; i < N; i++) {
						bestX[i] = x[i];
					}
				}
			}
		} else {
			for (int j = 1; j <= N; j++) {
				x[k] = (x[k] + 1) % N;
				if (nextValue(k) && cl + weight[x[k - 1]][x[k]] <= fl) {
					//此路可行
					cl += weight[x[k - 1]][x[k]];
					backUp(k + 1);
					cl -= weight[x[k - 1]][x[k]];
				}
			}
		}
	}

	public static void main(String[] args) {
		RandomFunc rc = new RandomFunc();
		List<Integer> integerList = new ArrayList<>();
		int n = 10;
		//获取存放城市间路径长度的二维数组
		integerList = rc.getRandom(n);
		int[][] weight = rc.toArr(integerList, n);
		//初始化存储最优路径和最优值的数组
		int[] x = new int[n];
		int[] bestx = new int[n];
		//
		BackTSP backTSP = new BackTSP(weight, x, bestx);
		backTSP.backUp(1);
		int minLen = backTSP.getMinLen();
		System.out.println(minLen);
		//获取最优路径并输出
		bestx = backTSP.getBestX();
		for (int i = 0; i < bestx.length; i++) {
			System.out.print(bestx[i] + " ");
		}

		for(int i=0;i<n;i++){
			for(int j=0;j<n;i++){
				System.out.print(weight[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 指定城市数量随机生成距离
	 */
	public static class RandomFunc {
		List<Integer> list =new ArrayList<>();
		int N;
		int size;

		public int getSize(int n){
			int temp=1;
			for(int i=1;i<n;i++){
				temp=temp*i;
			}
			return temp;
		}

		/**
		 * 生成随机数
		 * @param N
		 * @return
		 */
		public  List<Integer> getRandom(int N){
			Random random=new Random();
			size=getSize(N);
			for(int i=0;i<size;i++)
			{
				int num=1+random.nextInt(100);//生成小于100的随机数
				list.add(num);
				if(list.size()==size)
				{
					break;
				}
			}
			return list;
		}

		/**
		 * 将存在list中的随机数转换为二维数组
		 * @param list
		 * @param n
		 * @return
		 */
		public  int[][] toArr(List<Integer> list,int n){
			int[][]arr=new int[n][n];
			int a=0;
			for(int i=1;i<n;i++){
				for(int j=0;j<i;j++){
					arr[j][i]=arr[i][j]=list.get(a);
					a++;
				}
			}
			for(int k=0;k<n;k++){
				arr[k][k]=0;
			}
			return arr;
		}
	}
}

