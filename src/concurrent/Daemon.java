package concurrent;

public class Daemon {
	public static void main(String[]args){
		Thread thread=new Thread(new DaemonRunner(),"DaemonRunner");
		thread.setDaemon(true);//必须在启动之前设置
		thread.start();
	}

	private static class DaemonRunner implements Runnable {
		public void run(){
			try{
				ThreadState.SleepUtils.second(10);
			}finally {
				System.out.println("DaemonThread finally run");
			}
		}
	}
}
