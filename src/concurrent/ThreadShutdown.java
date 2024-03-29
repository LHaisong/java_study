package concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadShutdown {
	public static void main(String[] args) throws Exception {
		Runner one = new Runner();
		Runner two = new Runner();
		Thread countThread = new Thread(one, "countThread");
		countThread.start();
		TimeUnit.SECONDS.sleep(1);
		countThread.interrupt();
		countThread = new Thread(two, "countThread");
		countThread.start();
		TimeUnit.SECONDS.sleep(1);
		two.cancel();
	}

	private static class Runner implements Runnable {
		private long i;
		private volatile boolean on = true;

		@Override
		public void run() {
			while (on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("count i=" + i);
		}

		public void cancel() {
			on = false;
		}
	}
}
