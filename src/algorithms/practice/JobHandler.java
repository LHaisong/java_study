package algorithms.practice;

import static sun.security.krb5.internal.LoginOptions.MAX;

public class JobHandler {
	int number;//作业数量
	int x1[];//作业在机器1运行的时间
	int x2[];//作业在机器2运行的时间
	int sum = 0;//作业完成的总时间
	int bestsum = MAX;//作业完成的最优时间
	int order[];//作业完成的次序,要用于交换
	int bestorder[];//作业完成的最优的顺序
	int f1 = 0;//机器1累计的时间
	int f2[];//作业在机器2处理完累计的时间，即每一个作业的调度时间
	int vis[];//记录作业以否已被选

	public JobHandler(int[]a1,int[]a2,int n){
		this.f1=0;
		this.f2=a2;
		this.order=new int[n];
		this.x1=a1;
		this.x2=a2;
	}
	void backtrack(int cur){
		//到达边界
		if(cur > number){
			for(int i=1; i<=number; i++){
				bestorder[i] = order[i];
			}
			bestsum = sum;
		}else{
			//cur表示正在赋值的位置，cur+1去到下一层子节点，i递增，在当前层遍历兄弟节点
			for(int i=cur; i<=number; i++){
				f1 += x1[order[i]];
				f2[cur] = (f2[cur - 1] > f1 ? f2[cur -1] : f1) + x2[order[i]];
				sum += f2[cur];
            /*例如cur为2，i=3时，表示正在为作业队列的第2个位置赋作业3的值，所以此时的队列作业顺序应该是{1，3,2}, 所以要把i和cur的位置换掉；
            同理例如cur为1，i=2时，表示正在为作业队列的第1个位置赋作业2的值，此时的顺序是{2，1,3}，好好理解这一点
            swap函数就是要做这个事情
            */
				swap(order[cur], order[i]);
				if(sum < bestsum){//剪枝，如果当前sum都大于bestsum了，则不再遍历此节点
					backtrack(cur+1);
				}

				swap(order[cur], order[i]);
				sum -= f2[cur];
				f1 -= x1[order[i]];
			}
		}
	}
	public void swap(int a,int b){
		int temp=a;
		a=b;
		b=temp;
	}

	public int getBesttime() {
		return bestsum;
	}
	public int[] getBestlist() {
		return bestorder;
	}
	public static void main(String[]args){
		int[]a1={2,3,2};
		int[]a2={1,1,3};
		int n= a1.length;
		JobHandler jb=new JobHandler(a1,a2,n);
		jb.backtrack(1);
		jb.getBesttime();
		int[]p=jb.getBestlist();
		for(int i=0;i<n;i++){
			System.out.print(p[i]);
		}
	}
}
