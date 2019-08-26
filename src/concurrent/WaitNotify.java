package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
	static boolean flag=true;
	static Object o =new Object();
	public static void main(String[]args)throws Exception{
		Thread waitThread=new Thread(new Wait(),"waitThread");
		waitThread.start();
		TimeUnit.SECONDS.sleep(1);
		Thread notifyThread=new Thread(new Notify(),"notifyThread");
		notifyThread.start();
	}
	static class Wait implements Runnable{
		public void run(){
			synchronized (o){
				while (flag){
					try {
						System.out.println(Thread.currentThread()+"flag is true,wait@"+
								new SimpleDateFormat("HH:mm:ss").format(new Date()));
						o.wait();
					}catch (InterruptedException e){
					}
				}
				System.out.println(Thread.currentThread()+"flag is false,wait@"+
						new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}
	static class Notify implements Runnable{
		public void run(){
			synchronized (o){
				System.out.println(Thread.currentThread()+"hold o notify@"+
						new SimpleDateFormat("HH:mm:ss").format(new Date()));
				o.notifyAll();
				flag=false;
				ThreadState.SleepUtils.second(5);
			}
			synchronized (o){
				System.out.println(Thread.currentThread()+"hold o again sleep@"+
						new SimpleDateFormat("HH:mm:ss").format(new Date()));
				ThreadState.SleepUtils.second(5);
			}
		}
	}
}
