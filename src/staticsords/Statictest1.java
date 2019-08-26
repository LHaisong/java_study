package staticsords;
//3412
public class Statictest1 extends Base{

	static{
		System.out.println("1test static");
	}

	public Statictest1(){
		System.out.println("2test constructor");
	}

	public static void main(String[] args) {
		new Statictest1();
	}
}

class Base{

	static{
		System.out.println("3base static");
	}

	public Base(){
		System.out.println("4base constructor");
	}
}
