package concurrent;

import java.util.concurrent.*;


public class ThreadPoolTest {
	static int index1=5;
	static int index2=0;
	static int index3=0;
	public static void main(String[] args) throws Exception{
		/**
		 * 这种方式存在的缺点：
		 * Executors各个方法的弊端：
		 * 1) newFixedThreadPoolfPnewSingleThreadExecutor:
		 * 主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至00M。
		 * 2) newCachedThreadPoolfPnewScheduledThreadPool:
		 * 主要问題是线程数最大数是Integer.MAX_VALUE,可能会创建数置非常多的线程，甚至OOM。
		 */
		//ExecutorService pool = Executors.newFixedThreadPool(3);
		//推荐使用这种方式创建线程池，利于使用者理解各参数的作用，规避资源耗尽的风险
		ThreadPoolExecutor pool=new ThreadPoolExecutor(3,5,
				0L,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));
		for(int i=0;i<5;i++){
			/**
			 * submit()和execute()都可以用于向线程池提交任务
			 * 区别：
			 * submit()方法可传入Callable对象和Runnable对象
			 * 而execute()只能传入Runnable对象，所以submit()方法的灵活性更高
			 * 如果线程是以继承Thread类的方式创建，则两者都可以用于提交任务
			 */
		Future<Integer> res=pool.submit(new CallableTask());
		System.out.println(res.get());
		pool.execute(new RunnableTask());
		pool.execute(new ThreadTask());
		}
	}

	static class CallableTask implements Callable<Integer> {
		public Integer call()throws InterruptedException{
			Thread.sleep(1000);
			System.out.print(Thread.currentThread().getName()+"  index1 ");
			return --index1;
		}
	}

	static class RunnableTask implements Runnable {
		public void run(){
			System.out.println(Thread.currentThread().getName()+"  index2 "+ ++index2);
		}
	}

	static class ThreadTask extends Thread{
		public void run(){
			System.out.println(Thread.currentThread().getName()+"  index3 "+index3);
		}
	}
}