package concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	public static void main(String[]args) {
		//构造方法，表示等待两个线程完成之后才能开始运作
		CountDownLatch latch = new CountDownLatch(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
			   try{
			   	  System.out.println("子线程1"+Thread.currentThread().getName()+"start........");
			   	  Thread.sleep(2000);
				   System.out.println("子线程1"+Thread.currentThread().getName()+"end........");
				   latch.countDown();
			   }catch (Exception e){
			   	e.printStackTrace();
			   }
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					System.out.println("子线程2"+Thread.currentThread().getName()+"start........");
					Thread.sleep(2000);
					System.out.println("子线程2"+Thread.currentThread().getName()+"end........");
					latch.countDown();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();

		try{
			System.out.println("等待两个子线程执行完毕....");
			latch.await();
			System.out.println("子线程执行完毕，开始执行主线程.....");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
