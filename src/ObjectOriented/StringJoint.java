package ObjectOriented;

import java.util.Collections;

public class StringJoint {
	/**
	 * 字符串拼接的常用方式
	 */
	public static void main(String[]args){
		StringBuffer sb=new StringBuffer();
		String s1="ab";
		String s2="cd";
		String s3=s1+s2;
		String s4=s1.concat(s2);
		String s5=sb.append(s1).append(s2).toString();
	}
}
