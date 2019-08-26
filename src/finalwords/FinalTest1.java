package finalwords;

public class FinalTest1 {
		public static void main(String[] args)  {
			String a = "hello2";
			final String b = "hello";
			final String b_=getHello();
			String d = "hello";
			String c = b + 2;
			String e = d + 2;
			String f = b_ + 2;
			System.out.println("1 "+(a == b));
			System.out.println("2 "+(b == b_));
			System.out.println("3 "+(a == b_));
			System.out.println("4 "+(a == c));
			System.out.println("5 "+(a == e));
			System.out.println("6 "+(a == f));
		}
	public static String getHello() {
		return "hello";
	}
	}
