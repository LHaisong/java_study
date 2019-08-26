package jvmtest;

public class TestString {
	public static void test1(){
		String s1 = "Monday";
		String s2 = "Monday";
		if (s1 == s2)
		{
			System.out.println("s1 == s2");
		}
		else{
			System.out.println("s1 != s2");}
	}
	public static void test2(){
		String s3="java";
		String s4=new String("java");
		//s4=s4.intern();
		if(s3==s4){
			System.out.println("s3==s4");
		}else {
			System.out.println("s3!==s4");
		}
		if(s3.equals(s4)){
			System.out.println("s3 equals s4");
		}else {
			System.out.println("s3 not equals s4");
		}
	}
	public static void test3()
	{
		StringBuffer sb=new StringBuffer();
		Long startTime1=System.nanoTime();
        String s5="java";
        String s51=s5+"virtual";
        String s52=s51+"machine";
        String s53=s52+"test";
        Long endTime1=System.nanoTime();
        System.out.println(s53+" "+(endTime1-startTime1)+"ns");
		Long startTime2=System.nanoTime();
		sb=sb.append("java").append("virtual").append("machine").append("test");
		Long endTime2=System.nanoTime();
		System.out.println(sb+" "+(endTime2-startTime2)+"ns");
	}

	public static void main(String[] args) {
		//test1();
		//test2();
		test3();
	}
}
