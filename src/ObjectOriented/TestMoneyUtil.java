package ObjectOriented;

public class TestMoneyUtil {
	public static void main(String[]args){
		MoneyUtil moneyUtil=new MoneyUtil();
		String s1="6.6";
		String s2="1.3";
		System.out.println(MoneyUtil.moneyAdd(s1,s2));
	}
}
