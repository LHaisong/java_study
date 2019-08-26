package algorithms.practice;
/**
 *
 * @author lenovo
 *
 */
public class Matrix_multiplication {
	/**
	 *
	 * @param arr1
	 * @param arr2
	 */
	public void solution(int [][]arr1,int [][]arr2){
		int c[][] = new int[arr1.length][arr2[0].length];

		int x,i,j;
		for(i = 0;i<arr1.length ;i++)
		{
			for(j = 0;j<arr2[0].length;j++)
			{
				int temp = 0;
				for(x = 0;x<arr2.length;x++)
				{
					temp+=arr1[i][x]*arr2[x][j];

				}
				c[i][j] = temp;

			}
		}
		System.out.println("矩阵相乘后结果为");
		for(int m = 0;m<arr1.length;m++)
		{
			for(int n = 0;n<arr2[0].length;n++)
			{
				System.out.print(c[m][n]+"\t");
			}
			System.out.println();
		}
	}

	public static void main(String[]args) {
		Matrix_multiplication mm=new Matrix_multiplication();
		int[][]arr1= {{1,2},{1,2}};
		int[][]arr2= {{2,1},{2,1}};
		mm.solution(arr1, arr2);
	}
}

