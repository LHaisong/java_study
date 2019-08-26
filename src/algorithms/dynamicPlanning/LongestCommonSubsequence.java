package algorithms.dynamicPlanning;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonSubsequence {
	public static int longestConSeq(char[]strSeq1,char[]strSeq2,int[][]t){
		int[][]res=new int[strSeq1.length+1][strSeq2.length+1];
		return getLCS(res,t,strSeq1,strSeq2);
	}
	/**
	 * 求解最长公共子序列问题
	 * @param result 结果矩阵
	 * @param tra 跟踪矩阵
	 * @param s1 序列x
	 * @param s2 序列y
	 */
	public static int getLCS(int[][]result,int[][]tra,char[]s1, char[]s2){
		tra[0][0]=0;
		for(int i=0;i<s1.length+1;i++) result[i][0]=0;
		for(int j=0;j<s2.length+1;j++) result[0][j]=0;
		tra[0][0]=0;

		for(int i=1;i<=s1.length;i++){
			for(int j=1;j<=s2.length;j++){
				if(s1[i-1]==s2[j-1]){
					result[i][j]=result[i-1][j-1]+1;
					tra[i][j]=1;
				}
				else if(result[i-1][j]>result[i][j-1]){
					result[i][j]=result[i-1][j];
					tra[i][j]=2;
				}else {
					result[i][j]=result[i][j-1];
					tra[i][j]=3;
				}
			}
		}
		return result[s1.length][s2.length];
	}

	/**
	 * 最长公共子序列追踪算法
	 *
	 * @param s1 第一个序列
	 * @param tra 最长公共子序列追踪矩阵，m+1行,n+1列
	 * @param tailS1 第一个序列的结束位置，1,2,...,m
	 * @param tailS2 第二个序列的结束位置，1,2,...,n
	 */
	public static void trackback(char[]s1, int[][]tra, int tailS1, int tailS2){
		if(tailS1==0||tailS2==0) return;
		if (tra[tailS1][tailS2]==1) {
			trackback(s1,tra,tailS1-1,tailS2-1);
			System.out.print(s1[tailS1-1]+" ");
		}
		else if(tra[tailS1][tailS2]==2){
			trackback(s1, tra, tailS1-1, tailS2);
		}
		else trackback(s1, tra, tailS1, tailS2-1);
	}
	public static void main(String[]args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input first sequence:" );
		String s1=br.readLine();
		System.out.println("Input second sequence:");
		String s2=br.readLine();
		char[]seqS1=s1.toCharArray();
		char[]seqS2=s2.toCharArray();
		int[][]tra=new int[seqS1.length+1][seqS2.length+1];
		long startTime=System.currentTimeMillis();
		int len=longestConSeq(seqS1,seqS2,tra);
		long endTime=System.currentTimeMillis();
		System.out.println("the length of longest common sequence is:"+len);
		System.out.println("The longest common sequence is:");
		System.out.println("run time:"+(endTime-startTime)+"ms");
		trackback(seqS1,tra,seqS1.length,seqS2.length);
	}
}
