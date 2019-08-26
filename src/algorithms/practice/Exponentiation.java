package algorithms.practice;

public class Exponentiation {

	public int solution(int x,int n){
		int result;
		if(n==1){
			System.out.println(n);
		}
		if(n%2==0){
			result=solution(x,n/2)*solution(x,n/2);
		}else {
			result=solution(x,(n-1)/2)*solution(x,(n+1)/2);
		}
		System.out.println(result);
		return 0;
	}
	public void solution1(int x,int n){
		int result=1;
		long startTime=System.currentTimeMillis();
		for(int i=0;i<n;i++){
			result=result*x;
		}
		System.out.println(result);
		long endTime=System.currentTimeMillis();
		long time=endTime-startTime;
		System.out.println("运行时间"+time);

	}

	public static void main(String[]args){
		Exponentiation et=new Exponentiation();
		//et.solution1(10,4);
		et.solution(10,4);
	}
}
