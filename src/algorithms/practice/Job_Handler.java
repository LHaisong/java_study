package algorithms.practice;

public class Job_Handler {
	//存放各作业在机器上的处理时间
	int[][]time;
	//作业数量
	int n;
	//当前最优次序
	int[]bestlist;
	//当前最短时间
	int ctime;
	//最优时间
	int besttime;
    //
	int[]a;
	//调度机1上完成的时间
	int[] f2;
	//作业机1上的调度时间
	int f1;
	/**
	 *
	 * @param
	 * @param sum
	 */
	public Job_Handler(int[][]handtime,int sum){
		this.time=handtime;
		this.n=sum;
		this.f1=0;
		this.a=new int[sum];
		this.f2 =new int[sum];
		this.bestlist=new int[sum];
		this.ctime=0;
		this.besttime=0;
	}
	public void backtrack(int i){
		if(i>n){
			if(ctime>besttime){
				besttime=ctime;//更新
				for(int k=0;k<n;k++){
                    bestlist[k]=a[k];
				}
				return;
			}
		}else{
			for(int j=i;j<n;j++){
                a[i]=1;
                f1+=time[i][0];
                f2[i]=Math.max(f2[i-1],f1)+time[i][0];
                ctime+=f2[i];
                if(ctime<besttime){
                	backtrack(i+1);
				}
				ctime-=f2[i];
				f1-=time[i][0];
				a[i]=0;
			}
		}
	}

	public int getBesttime() {
		return besttime;
	}
	public int[] getBestlist() {
		return bestlist;
	}

	public static void main(String[]args){
		int n=3;
		int[][]time={{2,1},{3,1},{2,3}};
		Job_Handler jh=new Job_Handler(time,n);
		jh.backtrack(1);
		jh.getBesttime();
		System.out.print("\n");
		int[]order=jh.getBestlist();
		for(int i=0;i<n;i++){
			System.out.print(order[i]);
		}
	}
}
