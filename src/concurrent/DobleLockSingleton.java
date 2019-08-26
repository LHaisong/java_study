package concurrent;

/**
 * 双重锁实现单例模式
 */
public class DobleLockSingleton {
	private static volatile DobleLockSingleton instance=null;
	private DobleLockSingleton(){
	}
	public static DobleLockSingleton getInstance(){
		if(instance==null){
			synchronized (DobleLockSingleton.class){
				if(instance==null){
					return instance=new DobleLockSingleton();
				}
			}
		}
		return instance;
	}
}
