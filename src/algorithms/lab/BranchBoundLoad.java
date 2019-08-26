package algorithms.lab;

import java.util.LinkedList;

/**
 * 分支界限求解装载问题
 */
public class BranchBoundLoad {
	public static void main(String[]args){
		int c1=50;
		int c2=50;
		int[]w={10,20,75};
		int n=w.length-1;
		BranchBoundLoad bb=new BranchBoundLoad(w,c1,c2,n);
		int bestw=bb.maxLoad(100);
		System.out.println(bestw);
		int[]s=bb.getBest();
		for(int i=0;i<s.length;i++){
			System.out.print(s[i]+" ");
		}
	}
	//最优装载
	private int bestw = 0;
	//当前装载
	private int cw = 0;
	//存储重量
	private int[] w;
	//记录剩余容量
	private int r = 0;
	//货箱数量
	private int n;
	//第一个货箱数量
	private int c1;
	//第二个货箱数量
	private int c2;
	//解空间
	int[]best;

	MyQueue mq=new MyQueue();

	/**
	 *
	 * @param w_ 物品重量
	 * @param c1_ 货船装载量
	 * @param c2_ 货船装载量
	 * @param n_  货船数量
	 */
	public BranchBoundLoad(int[] w_, int c1_, int c2_, int n_) {
		this.w = w_;
		this.c1=c1_;
		this.c2=c2_;
		n = n_;
		for(int f:w_){
			r+=f;
		}
	}

	/**
	 *
	 * @param c
	 * @return
	 */
	public int maxLoad(int c) {
		mq.put(-1);//初始化节点
		int i = 0;//节点的层
		best=new int[w.length];
		while (!mq.isEmpty()) {
			if (cw + w[i] <= c) {//左儿子判断约束
					//bestw = cw + w[i];
					//添加左儿子节点
				addLiveNode(cw + w[i], i);
			}
				//右儿子总是可行的
				addLiveNode(cw, i);
				//取下一个节点
				cw = (int) mq.get();
				if (cw == -1) {
					if (mq.isEmpty()) {
						return bestw;
					}
					mq.put(-1);
					//取下一个节点
					cw = (int) mq.get();
					i++;
				}
			}
			return bestw;
		}

	/**
	 * 返回装载方案
	 * @return
	 */
		public int[] getBest(){
		return best;
		}
	/**
	 * 入队
	 *
	 * @param wt
	 * @param i
	 */
	public void addLiveNode(int wt, int i) {
		if (i == n) { // 是叶子
			if (wt > bestw) {
				bestw = wt;
			}
		} else { // 不是叶子
			mq.put(wt);
		}
	}
}

/**
 * 自定义队列
 */
class MyQueue{
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
