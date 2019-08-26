package algorithms.practice;


public class Queen {
	//皇后的数量
	private int n;
	//当前可行解
	private int[]num;
	//可行解的数量
	private int sum;
	/**
	 * 构造函数
	 * @param n 问题规模
	 */
	public Queen(int n) {
		this.n=n;
		this.sum=0;
		this.num=new int[n];
	}
	/**
	 * 递归回溯函数
	 * @param d 树的深度
	 */
	public void backtrack(int d) {
		if(d>=n) {
			sum++;
			outputX();
		}else {
			for(int i=0;i<num.length;i++) {
				num[d]=i;
				if(placeOk(d)) {
					backtrack(d+1);
				}
			}
		}
	}
	public void outputX() {
		for(int j=0;j<num.length;j++) {
			System.out.print(num[j]);
		}
		System.out.println();
	}
	/**
	 * 约束函数
	 * @param p
	 * @return
	 */
	public boolean placeOk(int p) {
		for(int k=0;k<p;k++) {
			if(Math.abs(p-k)==Math.abs(num[p]-num[k])||num[p]==num[k]) {
				return false;
			}
		}
		return true;
	}
	public int getSum() {
		return sum;
	}
	/**
	 * 测试函数
	 * @param args
	 */
	public static void main(String[]args) {
		int n=5;
		Queen queen=new Queen(8);
		queen.backtrack(0);
		int sum=queen.getSum();
		System.out.println(sum);
	}
}
