package staticsords;

public class StaticThis {
	static int value = 33;

	public static void main(String[] args) throws Exception{
		new StaticThis().printValue();
	}

	private void printValue(){
		int value = 3;
		System.out.println(this.value);
	}
}