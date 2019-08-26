package algorithms.lab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import static java.util.Arrays.sort;

public class BranchBoundTSP {
	static int MAX = 15;
	static int INF = 100000;
	//城市数量
	private int n;
	//上界
	private int upWeight;
	//下界
	private int lowWeight;
	//代价矩阵
	private int[][] weight;
	//已走过的城市
	private int[] visited;
	private int[][]visCity;
	//最优路径
	private int bestLen;
	//当前路径
	private int cLen;
	//最优方案
	int[]path=new int[n];

	Queen queen = new Queen();

	PriorityQueue<Node>p;

	//构造函数
	public BranchBoundTSP(int[][] w, int n_) {
		this.n = n_;
		this.weight = w;
		visited=new int[n];
		visCity=new int[n][n];
	}

	//节点类
	class Node {
		public int visitedCity[] = new int[MAX];    //已走过的城市
		int numbers;            //已走过的城市数量
		int endCity;            //已走过的最后一个城市
		int startCity;            //已走过的第一个城市
		public int sumLength;            //已走过的长度
		int aimFun;
		Node parent;
		int cLen=0;

		public Node(Node p,int l) {
			for (int i = 0; i < MAX; i++) {
				visitedCity[i] = 0;
			}
			this.parent=p;
			this.cLen=l;
			numbers = 1;
			endCity = 0;
			startCity = 0;
			sumLength = 0;
			aimFun = 0;

		}
	}

	/**求解部分解的下界
	 * 计算当前节点目标函数的值
	 * 由当前已遍历的路径的二倍加上进出其余未遍历
	 * 城市的最短路径，再加上出当前城市的最短路径
	 * 和进起始城市的最短路径，取其二分之一，
	 * 是当前节点的目标函数值，即当前节点的下界。
	 */
	public int calAimfun(Node node ) {
		int ret = node.sumLength * 2;
		int min1 = INF, min2 = INF;
		for (int i = 0; i < n; i++) {
			if (node.visitedCity[i] == 0 && min1 > weight[i][node.startCity]) {
				min1 = weight[i][node.startCity];
			}
		}
		for (int i = 0; i < n; i++) {
			if (node.visitedCity[i] == 0 && min2 > weight[node.endCity][i]) {
				min2 = weight[node.endCity][i];
			}
		}
		ret += (min1 + min2);
		for (int i = 0; i < n; i++) {
			if (node.visitedCity[i] == 0) {
				min1 = INF;
				min2 = INF;
				for (int j = 0; j < n; j++) {
					if (min1 > weight[i][j]) {
						min1 = weight[i][j];
					}
				}
				for (int j = 0; j < n; j++) {
					if (min2 > weight[j][i]) {
						min2 = weight[j][i];
					}
				}
				ret += (min1 + min2);
			}
		}

		return ret % 2 == 0 ? ret / 2 : (ret / 2 + 1);
	}

	/**
	 * 确定下界
	 *
	 * @return
	 */
	public int getLowWeight(int a, int b) {
		int downWeight = 0;
		int[] temp = new int[n];
		for (int i = 0; i < n; i++) {

		}
		if (a != 0 || b != 0) {
			downWeight += weight[a][b];
			temp = weight[a];
			sort(temp);
			if (temp[0] != weight[a][b]) {
				downWeight += temp[0] / 2;
			} else {
				downWeight += temp[1] / 2;
			}
			temp = weight[b];
			sort(temp);
			if (temp[0] != weight[a][b]) {
				downWeight += temp[0] / 2;
			} else {
				downWeight += temp[1] / 2;
			}
			for (int i = 0; i < n; i++) {
				if (i == a || i == b) {
					i++;
				} else {
					temp = weight[i];
					sort(temp);
					downWeight += (temp[0] + temp[i]) / 2;
				}
			}
		}
		return downWeight;
//		int downWeight=0;
//		int[]temp=new int[n];
//		for(int i=0;i<n;++i){
//			temp=weight[i];
//			sort(temp);
//			downWeight+=(temp[0]+temp[1])/2;
//		}
//		return downWeight;

	}

	/**
	 * 贪心法确定上界
	 */
	public int getUpWeight() {
		visited=new int[n];
		for (int i = 0; i < n; i++) {
			visited[i] = 0;
		}
		visited[0] = 1;
		return dfs(0, 0, 0);
	}

	public int dfs(int now, int count, int sumLen) {
		if (count == n - 1) {
			return (sumLen + weight[now][0]);
		}
		int min = INF;
		int next = -1;
		for (int j = 0; j < n; j++) {
			if (visited[j] == 0 && min > weight[now][j]) {
				min = weight[now][j];
				next = j;
			}
		}
		visited[next] = 1;
		return dfs(next, count + 1, sumLen + min);
	}

	/**
	 * 分支界限求解TSP问题
	 */
	public int BBTSP() {
		upWeight=getUpWeight();
		Node node=null;
		Node cNode=new Node(null,0);
		int cl=0;
		queen.put(cNode);
		queen.put(-1);
		node.startCity=1;
		for(int i=0;i<n;i++){
			node.visitedCity[i]=0;
			path[i]=0;
		}
		int level=1;
		node.visitedCity[0]=1;
		cNode=(Node) queen.get();
		while(!queen.isEmpty()){
			if(level<n){
				for(int j=0;j<n&&j!=level;j++){
					lowWeight=getLowWeight(node.startCity-1,j);
					if(lowWeight<upWeight){
						node=new Node(cNode,cl+weight[node.startCity-1][j]);
						//addLiveNode();
					}
				}
			}
		}
		return cl;
	}

	/**
	 * 入队
	 *
	 * @param n
	 * @param cc
	 */
	public void addLiveNode(Node n,int cc) {
			if (n.cLen <= bestLen) {
				bestLen = n.cLen;
			}
		 else {
			queen.put(n);//入队
		}
	}
	public static void main(String[]args){
		BackTSP.RandomFunc rc=new BackTSP.RandomFunc();
		List<Integer> integerList=new ArrayList<>();
		int n=10;
		//获取存放城市间路径长度的二维数组
		integerList=rc.getRandom(n);
		int[][]weight=rc.toArr(integerList,n);
		BranchBoundTSP bb=new BranchBoundTSP(weight,n);
		int minLen=bb.BBTSP();
		System.out.println(minLen);
	}
}
class Queen{
	private LinkedList ll=new LinkedList();
	//入队
	public void put(Object o){
		ll.addLast(o);
	}
	//出队
	public Object get(){
		return ll.removeFirst();
	}
	//判断队列是否为空
	public boolean isEmpty(){
		return ll.isEmpty();
	}
}