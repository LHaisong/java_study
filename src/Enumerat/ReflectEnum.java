package Enumerat;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

public class ReflectEnum {
	static PrintWriter pw=new PrintWriter(new OutputStreamWriter(System.out));
	enum Explore { HERE, THERE }
	public static Set<String> analyze(Class<?> enumClass) {
		pw.println("----- Analyzing " + enumClass + " -----");
		pw.println("Interfaces:");
		for(Type t : enumClass.getGenericInterfaces())
			pw.println(t);
		pw.println("Base: " + enumClass.getSuperclass());
		pw.println("Methods: ");
		Set<String> methods = new TreeSet<String>();
		for(Method m : enumClass.getMethods())
			methods.add(m.getName());
		pw.println(methods);
		return methods;
	}
	public static void main(String[]args){
		Set<String>exploreMethods=analyze(Explore.class);
		Set<String>enumMethods=analyze(Enum.class);
		for(String s:enumMethods){
			System.out.println(s);
		}
		System.out.println("Explore.containsAll(Enum)? " +
				exploreMethods.containsAll(enumMethods));
		System.out.println("Explore.removeAll(Enum): ");
		exploreMethods.removeAll(enumMethods);
		System.out.println(exploreMethods);
		// Decompile the code for the enum:
	}
}
