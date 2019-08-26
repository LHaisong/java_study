package concurrent;


public class CreateThread {
	private static int tickets=100;
	public static void main(String[]args){
		/**
		 * 继承thread类，对其进行扩展
		 */
//        MultiThreads m1=new MultiThreads("windows 1");
//		MultiThreads m2=new MultiThreads("windows 2");
//		MultiThreads m3=new MultiThreads("windows 3");
//		m1.start();
//		m2.start();
//		m3.start();
		MultiThreadsRunnable mr=new MultiThreadsRunnable();
		Thread t1=new Thread(mr);
		Thread t2=new Thread(mr);
		Thread t3=new Thread(mr);
		t1.start();
		t2.start();
		t3.start();
	}

//	 static class MultiThreads extends Thread{
//		 @Override
//		 public void run() {
//			 while (tickets>0){
//			 	tickets--;
//			 	System.out.println("剩余"+tickets+ " "+Thread.currentThread().getName());
//			 }
//		 }
//	 }
	static class MultiThreadsRunnable implements Runnable{

		@Override
		public void run() {
			while (tickets>0){
				tickets--;
				System.out.println("剩余"+tickets+ " "+Thread.currentThread().getName());
			}
		}
	}
}
