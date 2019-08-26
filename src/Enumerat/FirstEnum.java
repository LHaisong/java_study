package Enumerat;

public class FirstEnum {
	enum Shrubbery{GROUND,CRAWLING,HANGING}
	public static void main(String[]args){
		for(Shrubbery s:Shrubbery.values()){
			/**
			 * s:输出toString()方法的返回值
			 * ordinal()返回声明时的序号
			 * getDeclaringClass()返回所属的类
			 */
			System.out.println(s+" "+s.ordinal()+" "+s.getDeclaringClass());
		}
	}
}
