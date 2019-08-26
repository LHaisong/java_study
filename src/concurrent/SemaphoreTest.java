package concurrent;

		import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[]args){
		int N=4;
		Semaphore semaphore=new Semaphore(2);
		for(int i=0;i<N;i++){
			new Thread(new Jober(i,semaphore)).start();
		}
	}
	static class Jober implements Runnable{
		int num;
		Semaphore semaphore;
		public Jober(int num,Semaphore s){
			this.semaphore=s;
			this.num=num;
		}
		@Override
		public void run() {
			try {
				semaphore.acquire();
				System.out.println("这是第"+num+"个请求");
				System.out.println("使用中......");
				Thread.sleep(3000);
				System.out.println("使用完毕，释放");
				semaphore.release();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
