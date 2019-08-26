package concurrent;

public class YieldTest {
	public static void main(String[]args){
		Thread p=new Thread(new Producer(),"producerThread");
		Thread c=new Thread(new Consumer(),"consumerThread");
		p.setPriority(Thread.MIN_PRIORITY);
		c.setPriority(Thread.MIN_PRIORITY);
		p.start();
		c.start();
	}
	static class Producer extends Thread{
		public void run(){
			for(int i=0;i<5;i++){
				System.out.println("i am producer: "+i);
				 /**
				  *    Yield是一个静态的原生(native)方法
				 *     Yield告诉当前正在执行的线程把运行机会交给线程池中拥有相同优先级的线程。
				 *     Yield不能保证使得当前正在运行的线程迅速转换到可运行的状态
				 *     它仅能使一个线程从运行状态转到可运行状态，而不是等待或阻塞状态
				 */
				Thread.yield();
			}
		}
	}
	static class Consumer extends Thread{
		public void run(){
			for(int j=0;j<5;j++){
				System.out.println("i am consumer: "+j);
				Thread.yield();
			}
		}
	}
}
