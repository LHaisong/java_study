package concurrent;

public class VolatileTest {
	public static volatile int race=0;
	public static final int THREAD_COUNT=5;

	public static void increase(){
		race++;
	}
	public static void main(String[]args){
		try{
			Thread[] threads=new Thread[THREAD_COUNT];
			for(int i=0;i<THREAD_COUNT;i++){
				threads[i]=new Thread(new Runnable() {
					@Override
					public void run() {
						for(int j=0;j<2000;j++){
							increase();
						}
					}
				});
			threads[i].start();
			}
			while (Thread.activeCount()>1){
				Thread.yield();
			}
			System.out.println(race);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
