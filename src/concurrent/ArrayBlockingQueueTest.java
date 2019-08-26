package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueueTest {
	private static int[]data;
	private static int count=0;
	private final static Lock lock=new ReentrantLock();
	static Condition full=lock.newCondition();
	static Condition empty=lock.newCondition();

	public ArrayBlockingQueueTest(int maxSize){
		this.data=new int[maxSize];
	}
	public static void main(String[]args){
		Thread p=new Thread(new Producer());
		Thread c=new Thread(new Consumer());

		p.start();
		c.start();

	}
	static class Producer implements Runnable{
		public void run(){
			produce();
		}
        public static void produce(){
			lock.lock();
			try {
				//集合已满
				while (count>5){
					//资源已满
					System.out.println("集合已满，等待....");
					//挂起线程
					full.await();
				}
				count++;
				empty.signal();
			}catch (InterruptedException e){
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
	}
	static class Consumer implements Runnable{
       public void run(){
       	 consum();
	   }
	   public static void consum() {
       	lock.lock();
		   try {
			   while (count<=0) {
			   	System.out.println("没有可消费的资源，阻塞........");
			   	empty.await();
			   }
			   count--;
			   full.await();
		   }catch (Exception e){
		   	e.printStackTrace();
		   }finally {
		   	lock.unlock();
		   }
	   }
	}
}
