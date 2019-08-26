package Enumerat;

public class SwitchEnum {
	enum Singal{GREEN,YELLOW,RED}
	Singal color=Singal.RED;
	public void change(){
		switch (color){
			case RED:color=Singal.GREEN;
			break;
			case GREEN:color=Singal.YELLOW;
			break;
			case YELLOW:color=Singal.RED;
			break;
		}
	}

	/**
	 * enum中实现了toString()方法，可以对其进行覆盖
	 * @return
	 */
	public String toString(){
		return "the traffic is "+color;
	}
	public static void main(String[]args){
		SwitchEnum se=new SwitchEnum();
		for(int i=0;i<7;i++){
			System.out.println(se);
			se.change();
		}
	}
}
