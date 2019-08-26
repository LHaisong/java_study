package algorithms.lab;

//第二种下界，部分解的代价+(部分解城市不在路径上的最小出边+其他城市最小两个出边）/2
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import java.util.Scanner;
class BranchBond03 {
	int[][] a;//图G的邻接矩阵
	public BranchBond03(int[][] a){
		this.a=a;
	}
	public static class HeapNode implements Comparable{
		int lcost;//子树费用的下界
		int cc;//当前费用
		int s;//根节点到当前节点的路径为x[0:s],记录树的深度
		int[] x;//需要进一步搜索的顶点是x[s+1:n-1]

		//构造方法
		public HeapNode(int lc,int ccc,int ss,int[] xx){
			lcost=lc;
			cc=ccc;
			s=ss;
			x=xx;
		}
		public int compareTo(Object x){
			int xlc=((HeapNode) x).lcost;
			if(lcost<xlc) return -1;
			if(lcost==xlc) return 0;
			return 1;
		}
	}

	public int bb(int[] v){
		int n=v.length-1;//节点数
		LinkedList<HeapNode> heap=new LinkedList<HeapNode>();
		//EdgeMinOut[i]=i的最小出边费用
		int[][] minOut=new int[n+1][2];
		//找到每个城市最小两条出边并按从小到大排序
		for(int i=1;i<=n;i++) {
			int min=Integer.MAX_VALUE;
			for(int j=1;j<=n;j++)
				if(a[i][j]!=-1&&a[i][j]<Integer.MAX_VALUE&&a[i][j]<min)
					min=a[i][j];
			minOut[i][0]=min;
		}
		for(int i=1;i<=n;i++) {
			int min=Integer.MAX_VALUE;
			int count=0;
			for(int j=1;j<=n;j++) {
				if(a[i][j]==minOut[i][0])
					count++;
				if(a[i][j]!=-1&&a[i][j]>minOut[i][0]&&a[i][j]<Integer.MAX_VALUE&&a[i][j]<min)
					min=a[i][j];
			}
			if(count>=2)
				minOut[i][1]=minOut[i][0];
			else
				minOut[i][1]=min;
		}

		//初始化
		int[] x=new int[n];
		for(int i=0;i<n;i++)
			x[i]=i+1;
		HeapNode enode=new HeapNode(0,0,0,x);
		int bestc=Integer.MAX_VALUE;

		//搜索排列空间树
		while(enode!=null&&enode.s<n-1){
			//非叶节点
			x=enode.x;
			if(enode.s==n-2){
				//当前扩展结点是叶节点的父节点
				//再加两条边构成回路
				//所构成回路是否优于当前最优解
				if(a[x[n-2]][x[n-1]]!=-1&&a[x[n-1]][1]!=-1&&enode.cc+a[x[n-2]][x[n-1]]+a[x[n-1]][1]<bestc){
					//找到费用更小的回路
					bestc=enode.cc+a[x[n-2]][x[n-1]]+a[x[n-1]][1];
					enode.cc=bestc;
					enode.lcost=bestc;
					enode.s++;
					heap.add(enode);
					Collections.sort(heap);
					//将最优解复制到v[1...n]
					for(int i=0;i<n;i++)
						v[i+1]=x[i];
				}
			}else{//内部结点
				//产生当前扩展结点的儿子结点
				for(int i=enode.s+1;i<n;i++){
					if(a[x[enode.s]][x[i]]!=-1){
						//可行儿子结点
						int cc=enode.cc+a[x[enode.s]][x[i]];
						int temp;
						temp=x[enode.s+1];
						x[enode.s+1]=x[i];
						x[i]=temp;
						int low;//下界
						int sum=0;
						for(int j=enode.s+2;j<n;j++) {
							sum=sum+minOut[x[j]][0]+minOut[x[j]][1];
						}
						if(a[x[0]][x[1]]!=minOut[1][0]&&a[x[i-1]][x[i]]!=minOut[x[i]][0]) {
							low=(cc*2+minOut[1][0]+minOut[x[i]][0]+sum)/2;
						}
						else if(a[x[0]][x[1]]==minOut[1][0]&&a[x[i-1]][x[i]]==minOut[x[i]][0]) {
							low=(cc*2+minOut[1][1]+minOut[x[i]][1]+sum)/2;
						}
						else if(a[x[0]][x[1]]!=minOut[1][0]&&a[x[i-1]][x[i]]==minOut[x[i]][0]) {
							low=(cc*2+minOut[1][0]+minOut[x[i]][1]+sum)/2;
						}
						else
							low=(cc*2+minOut[1][1]+minOut[x[i]][0]+sum)/2;
						temp=x[enode.s+1];
						x[enode.s+1]=x[i];
						x[i]=temp;
						if(low<bestc){
							//子树可能含有最优解，结点插入最小堆
							int[] xx=new int[n];
							for(int j=0;j<n;j++)
								xx[j]=x[j];

							xx[enode.s+1]=x[i];
							xx[i]=x[enode.s+1];
							HeapNode node=new HeapNode(low,cc,enode.s+1,xx);
							heap.add(node);
							Collections.sort(heap);
						}
					}
				}

			}

			//取下一个扩展结点
			enode=heap.poll();
		}
		return bestc;
	}
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();//获取开始时间
		Random rand=new Random();
		int n=9;
		int a[][]=new int[n+1][n+1];
		for(int i=1;i<=n;i++)
			for(int j=1;j<=n;j++) {
				if(i==j)a[i][j]=-1;
				else if(i>j)a[i][j]=a[j][i];
				else a[i][j]=rand.nextInt(50)+1;
			}//随机生成不同城市规模
		BranchBond03 b=new BranchBond03(a);
		int []v=new int[n+1];
		System.out.println("最短回路长为："+b.bb(v));
		System.out.print("最短回路为："+ v[0]);
		for(int i=1;i<=n;i++){
			System.out.print("->"+v[i]);
		}
		System.out.println("->" + v[0]);
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
	}
}
