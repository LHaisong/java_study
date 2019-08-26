package concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadState {
	public static void main(String[]args){
		new Thread(new TimeWaiting(),"TimeWaitingThread").start();
		new Thread(new Waiting(),"WaitingThread").start();
		new Thread(new Blocked(),"BlockedThread-1").start();
		new Thread(new Blocked(),"BlockedThread-2").start();
	}
	static class TimeWaiting implements Runnable{
		@Override
		public void run() {
			while (true){
				SleepUtils.second(100);
			}
		}
	}

	 static class Waiting implements Runnable {
		 @Override
		 public void run() {
			 while (true){
			 	synchronized (Waiting.class){
			 		try {
			 			Waiting.class.wait();
					}catch (InterruptedException e){
			 			e.printStackTrace();
					}
				}
			 }
		 }
	 }

	private static class Blocked implements Runnable {
		@Override
		public void run() {
			synchronized (Blocked.class){
				while (true){
					SleepUtils.second(100);
				}
			}
		}
	}

	public static class SleepUtils {
		public static final void second(long sec){
			try{
				TimeUnit.SECONDS.sleep(sec);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
