package ObjectOriented;


public class ValueTransfer {
	public static void main(String[]args){
		People p=new People();
		p.setName("我是马化腾");
		p.setAge(45);
		valueCrossTest(p);
		System.out.println("方法执行后的name："+p.getName());
		//int a=25;
		//float w=77.5f;
		//valueCrossTest(a,w);
		//System.out.println("方法执行后的age："+a);
		//System.out.println("方法执行后的weight："+w);
	}
//
//	private static void valueCrossTest(int a, float w) {
//		System.out.println("传入的a:"+a+"\n"+"传入的w:"+w);
//		a=35;
//		w=80.5f;
//		System.out.println("修改后的a:"+a+"\n"+"修改后的w:"+w);
//	}
	private static void valueCrossTest(People people){
		System.out.println("传入的people："+people.getName());people=new People();
		people.setName("Im 张小龙");
		System.out.println("after method:"+people.getName()+" "+people.getAge());
	}
}
