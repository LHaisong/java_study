package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Person {
	static String name="刘海松";
	int age=21;
	private static void setName(String s){
        name=s;
	}
	public static void eat(){

	}
	public static void main(String[]args)throws Exception{
		/**
		 * 反射机制获取class的3中方法
		 */
		System.out.println("-----------获取class对象-------------");
		Class c1=Class.forName("reflection.Person");
		Class c2=Person.class;
		Class c3=new Person().getClass();
		System.out.println(c1+"\n"+c2+"\n"+c3);
		//创建class对象的实例
		Object o=c1.newInstance();
		//获取属性
		System.out.println("--------获取属性---------");
		Field[]fs=c1.getDeclaredFields();
		for(Field f:fs){
			System.out.println(f);
		}
		System.out.println("----------获取方法-------");
		Method[]ms=c1.getMethods();
		for(Method m:ms){
			System.out.println(m);
		}
		System.out.println("---------修改属性--------");
		System.out.println(name);
		Method m=c1.getDeclaredMethod("setName", String.class);
		m.invoke(o,"liuhaisong");
		System.out.println(name);

	}
}
