package algorithms.practice;

public class ClosedPoints {
	//存放点的横坐标
	int []x;
	//存放点的纵坐标
	int []y;
	public ClosedPoints(int[]a,int[]b){
		this.x=a;
		this.y=b;
	}

	/**
	 * 蛮力法求解最近点对问题
	 * @param x
	 * @param y
	 */
	public void resolution(int[]x,int[]y){
		int a=0,b=0;
		double d=Integer.MAX_VALUE;
		for(int i=0;i<x.length-1;i++) {
			for (int j = i+1; j < x.length; j++) {
				double temp = distance(x[i], y[i], x[j], y[j]);
				if (temp < d) {
					d = temp;
					a = i;
					b=j;
				}
			}
		}
		System.out.println("最近点对是第"+a+"、"+b+"两点");
		System.out.print(d);
	}
	/**
	 * 求解距离函数
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public double distance(int x1,int y1,int x2,int y2){
		return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[]args){
		int x[]={1,2,3,4,5,7,8,10};
		int y[]={2,5,2,4,8,5,7,6};
		ClosedPoints cp=new ClosedPoints(x,y);
		cp.resolution(x,y);
		//cp.distance(1,2,3,4);
    }
}
