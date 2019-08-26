package reflection;

public class Factory {
	/**
	 * 采用反射的方式实现工厂模式
	 * @param className
	 * @return
	 */
	public Object getAnimals(String className)throws Exception{
		Class<?>cls=Class.forName(className);
		Object obj=cls.newInstance();
		return obj;
	}
}
