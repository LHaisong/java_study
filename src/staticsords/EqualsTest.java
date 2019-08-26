package staticsords;


public class EqualsTest {
	private int arg;
	public EqualsTest(int arg) {
		this.arg = arg;
	}

	public boolean equals(Object obj){
		if(obj instanceof EqualsTest){
			EqualsTest e= (EqualsTest) obj;
			return arg==e.arg;
		}
		return false;
	}
	public static void main(String[] args) {
		EqualsTest c1 = new EqualsTest(1);
		EqualsTest c2 = new EqualsTest(1);
		System.out.println(c1.equals(c2));
		System.out.println(c1 == c2);
	}
}
