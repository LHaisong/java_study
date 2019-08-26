package concurrent;

public class SynchronizedTest implements Runnable{
	static int i=0;
	public synchronized void increase(){
		i++;
	}
	public static void main(String[]args)throws InterruptedException{
		SynchronizedTest instance=new SynchronizedTest();
		Thread t1=new Thread(instance);
		Thread t2=new Thread(instance);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}

	@Override
	public void run() {
		for(int j=0;j<100000;j++) {
			increase();
		}
	}
}
