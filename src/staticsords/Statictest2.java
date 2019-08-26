package staticsords;

 class Test2 {
	Person person = new Person("Test");
	static{
		System.out.println("test static");
	}

	public Test2() {
		System.out.println("test constructor");
	}

	public static void main(String[] args) {
		new MyClass();
	}
}

class Person{
	static{
		System.out.println("person static");
	}
	public Person(String str) {
		System.out.println("person "+str);
	}
}

/**
 * test static
 * myclass static
 * test constructor
 * myclass constructor
 *
 */

class MyClass extends Test2 {
	Person person = new Person("MyClass");
	static{
		System.out.println("myclass static");
	}

	public MyClass() {
		System.out.println("myclass constructor");
	}
}
