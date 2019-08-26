package datastructure.Heap;

/**
 * 利用最小堆求解topK问题
 * 求得一组元素中最大的k个元素
 */
//public class MinHeap {
//	int count;
//
//	int heapSize;
//
//	int []heap;
//
//	/**
//	 *将无序数组转换为最小堆
//	 * @param arr
//	 */
//	public MinHeap(int[]arr){
//		heapSize=arr.length;
//		heap=new int[heapSize];
//		for(int i=0;i<arr.length;i++){
//			heap[i]=arr[i];//复制数组
//		}
//		int n=arr.length;
//			count=n;
//		int curPosition=(count-2)/2;//指向树中倒数第二层最左边的节点（即初始调整节点）
//		while(curPosition>=0){
//			siftDown(curPosition,count-1);//自下而上逐步调整
//			curPosition--;//换分支节点
//		}
//	}
//
//	/**
//	 *下滑算法逐步完成最小堆的构建
//	 * @param start
//	 * @param m
//	 */
//	public void siftDown(int start,int m){
//		int i=start,j=2*i+1;
//		int temp=heap[i];
//		while(j<=m){
//			if(j<m&&heap[j]>heap[j+1]){
//				//swap(heap[j],heap[j+1]);//如果节点i的左子大于右子，则交换
//				j++;
//			}
//			if(temp<=heap[j]){//如果节点i小于其左儿子，结束
//				break;
//			}
//			else {
//				heap[i]=heap[j];//节点i和其左儿子交换
//				i=j;
//				j=2*j+1;
//			}
//		}
//		heap[i]=temp;
//	}
//	/**
//	 *交换函数
//	 * @param i
//	 * @param j
//	 */
//	public void swap(int i,int j){
//		int temp=j;
//		j=i;
//		i=temp;
//	}
//	/**
//	 * 获取最小值的函数
//	 * @return 根节点
//	 */
//	public int getTopKElements(){
//		//System.out.print("最大的"+k+"个元素是：");
//		for(int i=0;i<heap.length;i++){
//			System.out.print(heap[i]+" ");
//		}
//		//System.out.print(heap[0]);
//		return 0;
//	}
//	public static void main(String[]args){
//		int[]arr={56,275,12,18,45,478,41,1236,456,12,546,45};
//		MinHeap mh=new MinHeap(arr);
//		int k=4;
//		mh.getTopKElements();
//	}
//}
class MinHeap
{
	// 堆的存储结构 - 数组
	private int[] data;

	// 将一个数组传入构造方法，并转换成一个小根堆
	public MinHeap(int[] data)
	{
		this.data = data;
		buildHeap();
	}

	// 将数组转换成最小堆
	private void buildHeap()
	{
		// 完全二叉树只有数组下标小于或等于 (data.length) / 2 - 1 的元素有孩子结点，遍历这些结点。
		// *比如上面的图中，数组有10个元素， (data.length) / 2 - 1的值为4，a[4]有孩子结点，但a[5]没有*
		for (int i = (data.length) / 2 - 1; i >= 0; i--)
		{
			// 对有孩子结点的元素heapify
			siftDown(i);
		}
	}
//下滑算法逐渐构造最小堆
	private void siftDown(int i)
	{
		// 获取左右结点的数组下标
		int l = left(i);
		int r = right(i);

		// 这是一个临时变量，表示 跟结点、左结点、右结点中最小的值的结点的下标
		int smallest = i;

		// 存在左结点，且左结点的值小于根结点的值
		if (l < data.length && data[l] < data[i])
			smallest = l;

		// 存在右结点，且右结点的值小于以上比较的较小值
		if (r < data.length && data[r] < data[smallest])
			smallest = r;

		// 左右结点的值都大于根节点，直接return，不做任何操作
		if (i == smallest)
			return;

		// 交换根节点和左右结点中最小的那个值，把根节点的值替换下去
		swap(i, smallest);

		// 由于替换后左右子树会被影响，所以要对受影响的子树再进行heapify
		siftDown(smallest);
	}

	// 获取右结点的数组下标
	private int right(int i)
	{
		return 2*i+2;
	}

	// 获取左结点的数组下标
	private int left(int i)
	{
		return 2*i + 1;
	}

	// 交换元素位置
	private void swap(int i, int j)
	{
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	// 获取对中的最小的元素，根元素
	public int getRoot()
	{
		return data[0];
	}

	// 替换根元素，并重新heapify
	public void setRoot(int root)
	{
		data[0] = root;
		siftDown(0);
	}
	//
	public void getElements(){
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
	}
}
class TopK
{
	public static void main(String[] args)
	{
		// 源数据
		int[] data = {56,275,12,6,45,478,41,1236,456,12,546,45,111,222,584,591,255,824};

// 获取Top5
		int[] top5 = topK(data, 5);
		MinHeap mp=new MinHeap(data);
		mp.getElements();
		System.out.println("\n");
		for(int i=0;i<5;i++)
		{
			System.out.println(top5[i]+" ");
		}
	}

	// 从data数组中获取最大的k个数
	private static int[] topK(int[] data,int k)
	{
		// 先取K个元素放入一个数组topk中
		int[] topk = new int[k];
		for(int i = 0;i< k;i++)
		{
			topk[i] = data[i];
		}

		// 转换成最小堆
		MinHeap heap = new MinHeap(topk);

		// 从k开始，遍历data
		for(int i= k;i<data.length;i++)
		{
			int root = heap.getRoot();

			// 当数据大于堆中最小的数（根节点）时，替换堆中的根节点，再转换成堆
			if(data[i] > root)
			{
				heap.setRoot(data[i]);
			}
		}

		return topk;
	}
}

