package algorithms.divideConquer;

public class IntegerDevide {
	public static int q(int n,int m){
		if(m==0||n==0){
			return 0;
		}else if(m>n){
			return q(n,n);
		}else if(m==n){
			return 1+q(n,m-1);
		}
		return q(n,m-1)+q(n-m,m);
	}
	public static void main(String[]args){
		int n=7;
		int m=7;
		System.out.print(q(6,3));
	}
}
