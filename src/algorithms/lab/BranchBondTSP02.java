package algorithms.lab;
//第一种下界：下一个节点的下界是当前代价与到下一个要走的城市的距离和，即为：cc+a[x[t-1]][x[t]]<bestc

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

class BranchBoundTSP02 {
	int[][] a;//图G的邻接矩阵

	public BranchBoundTSP02(int[][] a) {
		this.a = a;
	}

	public static class HeapNode implements Comparable {
		int lcost;//子树费用的下界
		int cc;//当前费用
		int s;//根节点到当前节点的路径为x[0:s]
		int[] x;//需要进一步搜索的顶点是x[s+1:n-1]

		//构造方法
		public HeapNode(int lc, int ccc, int ss, int[] xx) {
			lcost = lc;
			cc = ccc;
			s = ss;
			x = xx;
		}

		public int compareTo(Object x) {
			int xlc = ((HeapNode) x).lcost;
			if (lcost < xlc) return -1;
			if (lcost == xlc) return 0;
			return 1;
		}
	}

	public int BBTSP(int[] v) {
		int n = v.length - 1;//节点数
		LinkedList<HeapNode> heap = new LinkedList<HeapNode>();
		//初始化
		int[] x = new int[n];
		for (int i = 0; i < n; i++)
			x[i] = i + 1;
		HeapNode heapNode = new HeapNode(0, 0, 0, x);
		int bestc = Integer.MAX_VALUE;

		//搜索解空间排列树
		while (heapNode != null && heapNode.s < n - 1) {
			//非叶节点
			x = heapNode.x;
			if (heapNode.s == n - 2) {
				//当前扩展结点是叶节点的父节点
				//再加两条边构成回路
				//所构成回路是否优于当前最优解
				if (a[x[n - 2]][x[n - 1]] != -1 && a[x[n - 1]][1] != -1 && heapNode.cc + a[x[n - 2]][x[n - 1]] + a[x[n - 1]][1] < bestc) {
					//找到费用更小的回路
					bestc = heapNode.cc + a[x[n - 2]][x[n - 1]] + a[x[n - 1]][1];
					heapNode.cc = bestc;
					heapNode.lcost = bestc;
					heapNode.s++;
					heap.add(heapNode);
					Collections.sort(heap);
					//将最优解复制到v[1...n]
					for (int i = 0; i < n; i++)
						v[i + 1] = x[i];
				}
			} else {//内部结点
				//产生当前扩展结点的儿子结点
				for (int i = heapNode.s + 1; i < n; i++) {
					if (a[x[heapNode.s]][x[i]] != -1) {
						//可行儿子结点
						int b = heapNode.cc + a[x[heapNode.s]][x[i]];//下界(越大越好)
						if (b < bestc) {
							//子树可能含有最优解，结点插入最小堆
							int[] xx = new int[n];
							for (int j = 0; j < n; j++)
								xx[j] = x[j];
							//交换
							xx[heapNode.s + 1] = x[i];
							xx[i] = x[heapNode.s + 1];
							HeapNode node = new HeapNode(b, b, heapNode.s + 1, xx);
							heap.add(node);
							Collections.sort(heap);
						}
					}
				}

			}

			//取下一个扩展结点
			heapNode = heap.poll();
		}
		return bestc;
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();    //获取开始时间
		Random rand = new Random();
		int n = 8;
		int a[][] = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++) {
				if (i == j) a[i][j] = -1;
				else if (i > j) a[i][j] = a[j][i];
				else a[i][j] = rand.nextInt(50) + 1;
			}
		BranchBoundTSP02 b = new BranchBoundTSP02(a);
		int[] v = new int[n + 1];
		System.out.println("最短回路长为：" + b.BBTSP(v));
		System.out.print("最短回路为：" + v[0]);
		for (int i = 1; i <= n; i++) {
			System.out.print("->" + v[i]);
		}
		System.out.println("->" + v[0]);
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
	}
}