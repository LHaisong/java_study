package datastructure.Heap;

public class MaxHeap
{
    private int[]data;
    public MaxHeap(int[]data)
    {
        this.data=data;
        buildMaxHeap();
    }

    private void buildMaxHeap()
    {
        /*完全二叉树只有下标<=data.length/2-1的节点有子节点*/
        for(int i=data.length/2;i >= 0; i--)
        {
            siftUp(i);
        }
    }

    private void siftUp(int i)
    {
        int max=i;
        int l=left(i),r=right(i);

        if(l<data.length&&data[max]<data[l])
            max = l;
        if(r<data.length&&data[max]<data[r])
            max = r;
        if(max==i)
            return;

        swap(i,max);
        siftUp(max);
    }

    private void swap(int i, int max) {
        int temp=data[i];
        data[i]=data[max];
        data[max]=temp;
    }

    private int right(int i) {
        return 2*i+2;
    }

    private int left(int i)
    {
        return 2*i+1;
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
        siftUp(0);
    }
    //
    public void getElements(){
        for(int i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
    }
}
class MinK
{
    public  static void main(String [] args)
    {
        int[]data={56,275,12,6,45,478,41,1236,456,12,546,45,111,222,584,591,255,824};
        MaxHeap maxHeap=new MaxHeap(data);
        maxHeap.getElements();
        System.out.println();
        int[]minK=minK(data,5);
        for(int i=0;i<minK.length;i++){
            System.out.print(minK[i]+" ");
        }
    }

    private static int[] minK(int[] data, int k)
    {
        int[]minK=new int [k];
        for(int i=0;i<k; i++){
            minK[i]=data [i];
        }
        MaxHeap heap=new MaxHeap(minK);
        for(int i=k;i<data.length; i++){
            int root=heap.getRoot();
            if(root>data [i]){
                heap.setRoot(data [i]);
            }
        }
        return minK;
    }
}
