package algorithms.practice;

public class LoadProblem_01 {
	//集装箱数量
	private int gNum;
	//集装箱重量数组
	private int[] weights;
	//最大载重
	private int capacity;
	//当前解
	private int[] x;
	//当前最优解
	private int[] bestx;
	//当前载重
	private int cw;
	//当前最优载重
	private int bestw;
	//剩余物品重量
	private int rWeight = 0;
	public LoadProblem_01(int[]w, int c)
	{
		this.gNum=w.length;
		this.weights=w;
		this.capacity=c;
		this.cw=0;
		this.bestw=0;
		this.x=new int[gNum];
		this.bestx=new int[gNum];
		this.rWeight=0;
		for(int i=0;i<gNum;i++){
			rWeight+=weights[i];
		}
	}

	/**
	 *回溯法求解装载问题
	 * @param i 树的深度
	 */
	public void backtrac(int i)
	{
		 // 到达叶节点
		if(i==gNum) {
			if(cw>bestw){//获取更优的装载量
				for(int j=0;j<gNum;j++){
					bestx[j]=x[j];//更新记录最优解
				}
				bestw=cw;//更新最优解
			}
			return;
		}
		//搜索子集树

		if(cw+weights[i]<=capacity)//搜索左子树
		{rWeight-=weights[i];
			x[i]=1;
			cw+=weights[i];
			backtrac(i+1);
			cw-=weights[i];
		}
		if(cw+rWeight>bestw)//搜索右子树
		{
			x[i]=0;
			backtrac(i+1);
		}
		rWeight+=weights[i];//返回父节点
	}

	/**
	 *
	 * @return 最大装载量
	 */
	public int getmaxLoad(){
		return bestw;
	}
	public int[] getLoadPlan(){
		return bestx;
	}
	public static void main(String[]args)throws ArrayIndexOutOfBoundsException{
		int[] w = {90, 80, 40, 30, 20, 12, 10};
        int c=195;
        LoadProblem_01 lp=new LoadProblem_01(w,c);
        lp.backtrac(0);
        System.out.println(lp.getmaxLoad());
        int[]p=lp.getLoadPlan();
        for(int i=0;i<w.length;i++){
        	System.out.print(p[i]);
		}
	}
}

