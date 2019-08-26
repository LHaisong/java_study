package concurrent;

import algorithms.lab.BackTSP01;

import java.util.Map;
import java.util.concurrent.*;

public class BankWaterService implements Runnable{
	CyclicBarrier c=new CyclicBarrier(4,this);

	ConcurrentHashMap<String,Integer>map=new ConcurrentHashMap<String,Integer>();

	private ExecutorService service= Executors.newFixedThreadPool(4);

	private void count(){
		for(int i=0;i<4;i++){
			int finalI = i;
			service.execute(new Runnable() {
				@Override
				public void run() {
					map.put(Thread.currentThread().getName(),finalI);
					try{
						c.await();
					}catch (InterruptedException e){
						e.printStackTrace();
					}catch (BrokenBarrierException e){
						e.printStackTrace();
					}
				}
			});
		}
	}

	@Override
	public void run() {
		int result=0;
		for(Map.Entry<String,Integer>sheet:map.entrySet()){
			result+=sheet.getValue();
		}
		map.put("result",result);
		System.out.println(result);
	}

	public static void main(String[]args){
		BankWaterService bankWaterService=new BankWaterService();
		bankWaterService.count();
	}
}
