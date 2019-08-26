package concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class CopyOnWriteArrayListTest {
	private AtomicLong contentNum;

	private List<String>list;

	private DateFormat dateFormat;
	private final ExecutorService threadPool;

	public CopyOnWriteArrayListTest() {
		threadPool = Executors.newFixedThreadPool(10);
		dateFormat=new SimpleDateFormat("HH:mm:ss");
		list=new CopyOnWriteArrayList<>();
		contentNum=new AtomicLong();
	}

	public void doExec(int num) throws InterruptedException{
		for(int i=0;i<num;i++){
			list.add(i,"main-content-"+i);
		}
		for(int i=0;i<5;i++){
			threadPool.execute(new Writer(list,i));
		}
		for(int i=0;i<10;i++){
			threadPool.execute(new Reader(list));
		}
		threadPool.shutdown();

	}
	class Writer implements Runnable{
		private final List<String>copyOnWriterList;
		int i;
        public Writer(List<String> copyOnWriterList,int i){
        	this.copyOnWriterList=copyOnWriterList;
        	this.i=i;
		}
		@Override
		public void run() {
        	copyOnWriterList.add(i,"content-"+contentNum.incrementAndGet());
        	System.out.println(Thread.currentThread().getName()+"Writer content "+contentNum.get()+" "+dateFormat.format(new Date()));
			System.out.println(Thread.currentThread().getName() + ": remove " + copyOnWriterList.remove(i));
		}
	}

	class Reader implements Runnable{
        private final List<String> list;

		public Reader(List<String> list) {
			this.list = list;
		}
		@Override
		public void run() {
			for(String s:list){
				System.out.println(Thread.currentThread().getName()+":read "+s+" "+dateFormat.format(new Date()));
			}
		}
	}
	public static void main(String[]args)throws InterruptedException{
		CopyOnWriteArrayListTest copy=new CopyOnWriteArrayListTest();
		copy.doExec(5);
	}
}
