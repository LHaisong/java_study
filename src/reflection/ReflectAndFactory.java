package reflection;

public class ReflectAndFactory {
	public static void main(String[]args)throws Exception{
		Factory factory=new Factory();
		Animals an=(Animals) factory.getAnimals("reflection.Man");
		an.speak();
	}
}
