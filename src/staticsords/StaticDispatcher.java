package staticsords;

public class StaticDispatcher {
	static abstract class Human{
	}
	static class Man extends Human{
	}
	static class Woman extends Human{
	}
	public void sayHello(Human guy){
		System.out.println("hello guy");
	}
	public void sayHello(Man guy){
		System.out.println("hello man");
	}
	public void sayHello(Woman guy){
		System.out.println("hello lady");
	}
	public static void main(String[]args){
		Human m=new Man();
		Human w=new Woman();
		StaticDispatcher sd=new StaticDispatcher();
		sd.sayHello(m);
		sd.sayHello(w);
	}
}
