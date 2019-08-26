package concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {
	public static void main(String[]args)throws Exception{
		Thread sleepThread=new Thread(new SleepRunner(),"SleepThread");
		sleepThread.setDaemon(true);
		Thread busyThread=new Thread(new BusyRunner(),"SleepThread");
		busyThread.setDaemon(true);
		sleepThread.start();
		busyThread.start();
		TimeUnit.SECONDS.sleep(5);
		sleepThread.interrupt();
		busyThread.interrupt();
		System.out.println("sleep thread interrupted is "+sleepThread.isInterrupted());
		System.out.println("busy thread interrupted is "+busyThread.isInterrupted());
		ThreadState.SleepUtils.second(2);
	}

	private static class SleepRunner implements Runnable {
		@Override
		public void run() {
			while (true){
				ThreadState.SleepUtils.second(10);
			}
		}
	}

	private static class BusyRunner implements Runnable {
		@Override
		public void run() {
			while (true){

			}
		}
	}
}
