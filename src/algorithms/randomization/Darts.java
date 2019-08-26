package algorithms.randomization;

import java.util.Random;

public class Darts {
	public static double darts(int n)
	{ // 用随机投点法计算值
		int k=0;
		for (int i=1;i <=n; i++) {
			double x=Uniform(0, 1);
			double y=Uniform(0, 1);
			if ((x*x+y*y)<=1) k++;
		}
		return 4*k/(double)n;
	}
    public static double Uniform(int x,int y){
		Random random=new Random();
		return random.nextDouble();
	}
	public static void main(String[]args){
		System.out.println("π的近似值为："+darts(21474836));
	}
}
