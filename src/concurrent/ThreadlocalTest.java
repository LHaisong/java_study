package concurrent;

public class ThreadlocalTest {
	ThreadLocal<Long>longLocal=new ThreadLocal<>();
	ThreadLocal<String>stringLocal=new ThreadLocal<>();
	public void set(){
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}
	public long getLong(){
		return longLocal.get();
	}
	public String getString(){
		return stringLocal.get();
	}
	public static void main(String[]args) throws InterruptedException {
		ThreadlocalTest test=new ThreadlocalTest();
		test.set();
		System.out.println(test.getLong()+" "+test.getString());
		Thread t=new Thread(()->{
			test.set();
			System.out.println(test.getLong()+" "+test.getString());
		});
		t.start();
		t.join();
		System.out.println(test.getLong()+" "+test.getString());
	}
}
