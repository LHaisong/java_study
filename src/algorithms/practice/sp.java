package algorithms.practice;

import java.util.ArrayList;
import java.util.List;

public class sp {
	public void test(int k, int a) {
		if (k == a) {
			System.out.println("yes");
			System.exit(0);
		}
			System.out.println(k);
			for (int i = k; i < a; i++) {
				System.out.println("第" + i + "次 " + k);
				test(k + 1, a);
		}
	}
		public static void main (String[]args){
			int a = 5;
			int k = 1;
			//new sp().toArr();
			//new sp().test(k, a);
			System.out.println(a/2);
		}
	}
