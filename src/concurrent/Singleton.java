package concurrent;

public class Singleton {
	private volatile static Singleton instance;

	public static Singleton getInstance(){
		if(instance==null){
			synchronized (Singleton.class){
				if(instance==null){
					instance=new Singleton();
				}
			}
		}
		return instance;
	}
	/**
	 public static class InstanceHolder(){
	    public static Instance instance=new Instance();
	 }
	 public static Instance getInstance(){
	    return InstanceHolder.instance;
	 }
	 */
}
