package algorithms.divideConquer;

import java.util.Arrays;

public class MergeSort {
	/**
	 *合并数组函数
	 * @param low 排序起始位
	 * @param mid
	 * @param high 排序终止位
	 */
	public void merge(int[]arr,int low,int mid,int high){
		int[]mergeArr=new int[high-low+1];
		//指向左边第一个位置
		int i=low;
		//指向右边第一个位置
		int j=mid+1;
		int k=0;
		//从两边第一个起，分别比较，将小的逐渐加入带合并数组中
		while(i<=mid&&j<=high){
			if(arr[i]<arr[j]){
				mergeArr[k++]=arr[i++];
			}else {
				mergeArr[k++]=arr[j++];
			}
		}
		//如果左边有剩余，则将剩余部分也加入到合并数组中，右边同理
		while(i<=mid){
			mergeArr[k++]=arr[i++];
		}
		while(j<=high){
			mergeArr[k++]=arr[j++];
		}
		//用已经排好序的数组覆盖原有的数组
		for(int k2=0;k2<mergeArr.length;k2++){
			arr[k2+low]=mergeArr[k2];
		}
	}

	/**
	 * 归并排序
	 * @param arr 待排序数组
	 * @param low 起始排序位
	 * @param high 排序结束位
	 */
	public void mergeSort(int[]arr, int low, int high) {
		int mid=(low+high)/2;
		if(low<high){
			mergeSort(arr,low,mid);
			mergeSort(arr,mid+1,high);
			merge(arr,low,mid,high);
		}
	}

	public static void main(String[]args){
		int[]arr={51, 46, 20, 18, 65, 97, 82, 30, 77, 50,21,19,29,29};
		MergeSort ms=new MergeSort();
		ms.mergeSort(arr,0,arr.length-1);
		System.out.println("归并排序结果为："+Arrays.toString(arr));
	}
}
