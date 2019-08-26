package algorithms.divideConquer;

/**
 *
 * @author LHaisong
 *2018-11-11
 */
public class Perm {
	/**
	 *
	 * @param a 待全排列的序列
	 * @param k 排列起始位置
	 * @param m 排列结束位置
	 */
	public static void perm(int[]a,int k,int m) {
		if(k==m) {
			for(int j=0;j<a.length;j++) {
				System.out.print(a[j]);
			}
			System.out.println();
		}
		else{
			for(int i=k;i<=m;i++) {
				swap(a,k,i);//0 0
				perm(a,k+1,m);//
				swap(a,k,i);//0
			}
		}
	}
	public static void swap(int []a,int k,int i) {
		int temp=a[k];
		a[k]=a[i];
		a[i]=temp;
	}

	public static void main(String[]args) {
		int []a=new int[3];
		for(int i=0;i<3;i++) {
			a[i]=i;
		}
		perm(a,0,2);
	}
}

