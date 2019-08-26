package datastructure.Set;

public class Set {
	public static void main(String[]args){
		String str="a(b(c(d,e),),f(g,h(i,)))";
		String[]strings=str.split("\\)|\\(|,");
		System.err.println(strings.length);
		for(int i=0;i<strings.length;i++){
			System.err.print(strings[i]+" ");
		}
	}
}
