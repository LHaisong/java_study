package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
	public static void main(String[]args){
		ExecutorService service= Executors.newCachedThreadPool();
		MyThread myThread=new MyThread();
	    service.submit(myThread);
	}
}
class MyThread implements Runnable{
	public int index=10;
	@Override
	public void run() {
		while (index>0) {
			index--;
			System.out.println(Thread.currentThread().getName() + " " + "剩余" + index);
		}
	}
}
