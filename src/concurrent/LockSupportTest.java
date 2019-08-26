package concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
	public static void main(String[]args)throws InterruptedException{
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("thread 1 start");
				LockSupport.park(Thread.currentThread());
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread 1 end");
			}
		});
		thread1.start();
		System.out.println("main thread start,thread1 "+thread1.getState());
		Thread.sleep(2000);
		System.out.println("main thread running,thread1 "+thread1.getState());
		LockSupport.unpark(thread1);
		Thread.sleep(6000);
		System.out.println("main thread end,thread1 "+thread1.getState());
	}
}
