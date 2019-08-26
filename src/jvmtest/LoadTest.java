package jvmtest;

 class LoadTest {

	public static void main(String[]args){
		System.out.println(SubClass.value);
	}
}
 class SuperClass
{
	public static int value = 123;

	static
	{
		System.out.println("SuperClass init");
	}
}

 class SubClass extends SuperClass
{
	static
	{
		System.out.println("SubClass init");
	}
}
