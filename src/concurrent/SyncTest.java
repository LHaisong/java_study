package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class SyncTest {
	public int i=1;
	private static final int THREADS_COUNT=20;
	public static AtomicInteger race=new AtomicInteger(0);
	//public volatile static int race=0;

	public  synchronized static  void increase(){
		race.incrementAndGet();// 4300440ns
		//race++; //5544439ns
	}
	public static void main(String[]args){
		long startTime=System.nanoTime();
		Thread[]threads=new Thread[THREADS_COUNT];
		for(int i=0;i<THREADS_COUNT;i++){
			threads[i]=new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j=0;j<1000;j++){
						increase();
					}
				}
			});
			threads[i].start();
		}
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		long endTime=System.nanoTime();
		System.out.println(race+"  "+(endTime-startTime)+"ns");
	}
}
