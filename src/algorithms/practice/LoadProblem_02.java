package algorithms.practice;

import java.util.LinkedList;

public class LoadProblem_02 {
	//集装箱重量
	int[]w;
	//轮船1的载重量
	int c1;
	//轮船2的载重量
	int c2;
	//树的深度
	int n;
	//当前最优解
	int bestw;
	//所有货箱的重量和
	int s;
	//
	int ew=0;
	//剩余重量
	int[]r;

	LinkedList<HeapNode> heap;

	/**
	 * 子集空间树中结点类型为Node
	 * @author liuhaisong
	 *
	 */
	public static class Node{
		Node parent;//父结点
		boolean leftChild;//左儿子结点标志
		//构造方法
		public Node(Node par,boolean ch){
			parent=par;
			leftChild=ch;
		}
	}
	public class HeapNode implements Comparable{
		Node liveNode;
		 int uweight;
		 int level;
		 public HeapNode(Node node,int up,int lev){
		 	this.level=lev;
		 	this.liveNode=node;
		 	this.uweight=up;
		 }

		public int compareTo(Object x) {//升序排列
			int xu=((HeapNode)x).uweight;
			if(uweight<xu) return -1;
			if(uweight==xu) return 0;
			return 1;
		}
		public boolean equals(Object x){
			return uweight==((HeapNode)x).uweight;
		}
	}

	public void addLiveNode(int up,int lev,Node par,boolean flag){
		Node n=new Node(par,flag);
		HeapNode hn=new HeapNode(n,up,lev);
		heap.add(hn);
		//Connections.sort(heap);
	}
	public int maxLoad(int[]w,int c,int[]bestw){
		heap=new LinkedList<HeapNode>();
		int n=w.length-1;
		Node e=null;
		int i=1;//当前扩展节点层数
		int ew=0;//扩展节点对应的载重量
		int[]r=new int[n+1];
		//定义剩余重量
		for(int j=n-1;j>0;j--) {
			r[j] = r[j + 1] + w[j + 1];
		}
		//搜索子空间树
		while(i!=n+1){
			//非叶节点，检查当前节点的子节点
			if(ew+w[i]<=c){
				addLiveNode(ew+w[i],i+1,e,true);
			}
			//右儿子结点总为可行结点
			addLiveNode(ew+r[i],i+1,e,false);
			//取下一个节点
			HeapNode node=heap.pollFirst();
			i=node.level;
			e=node.liveNode;
			ew=node.uweight-r[i-1];
		}
		for(int j=n;j>0;j--){
			bestw[j]=(e.leftChild)?1:0;
			System.out.print(bestw[j]+" ");
			e=e.parent;
		}
		System.out.println();
		return ew;
	}

	public static void main(String[] args) {
		int n=4;
		int c=70;
		int w[]={20,10,26,15};//下标从1开始
		int[] bestx=new int[n+1];
		LoadProblem_02 lp=new LoadProblem_02();
		System.out.println("最优装载顺序为（1表示装入，0表示未装入）：");
		int ew=lp.maxLoad(w, c, bestx);
		System.out.println("最优装载重量为："+ew);
	}
}