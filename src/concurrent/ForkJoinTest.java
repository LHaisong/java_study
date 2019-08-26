package concurrent;

import com.sun.corba.se.impl.orbutil.closure.Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Integer> {
	private static final int THRESHOLD=10;
    private int start,end;
    public ForkJoinTest(int s,int e){
    	this.start=s;
    	this.end=e;
	}
	@Override
	protected Integer compute() {
		int sum=0;
		boolean canCompute=(end-start)<THRESHOLD;
		if(canCompute){
			for(int i=start;i<=end;i++){
				sum+=i;
			}
		}else {
			int middle=(start+end)/2;
			ForkJoinTest leftTask=new ForkJoinTest(start,middle);
			ForkJoinTest rightTask=new ForkJoinTest(middle+1,end);
			leftTask.fork();
			rightTask.fork();
			int leftResult=leftTask.join();
			int rightResult=rightTask.join();
			sum=leftResult+rightResult;
		}
		return sum;
	}

	public static void main(String[]args){
		ForkJoinPool forkJoinPool=new ForkJoinPool();
		ForkJoinTest forkJoinTest=new ForkJoinTest(1,20);
		ForkJoinTask<Integer> r=forkJoinPool.submit(forkJoinTest);
		try {
			System.out.println(r.get());
		}catch (InterruptedException e1){

		}catch (ExecutionException e){

		}
	}
}
