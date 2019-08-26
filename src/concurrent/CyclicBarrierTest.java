package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	static CyclicBarrier c=new CyclicBarrier(2);
	public static void main(String[]args){
		for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" 到了屏障点...");
					try {
						c.await();
						System.out.println("两个线程都到了屏障点....");
					}catch (InterruptedException e){
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
