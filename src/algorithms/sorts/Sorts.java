package algorithms.sorts;

import java.util.Arrays;

public class Sorts {

	/**
	 * 冒泡排序
	 * @param arr
	 * @return
	 */
	static void bubbleSort(int[]arr){
		for(int i=0;i<arr.length;i++){
			for(int j=i+1;j<arr.length-1;j++){
				if(arr[i]>arr[j]){
					int temp =arr[j];
					arr[j]=arr[i];
					arr[i]=temp;
				}
			}
		}
	}

	/**
	 * 插入排序
	 * @param arr
	 * @return
	 */
	static void insertSort(int[]arr){
		int insertData,j;
		for(int i=1;i<arr.length;i++){
			insertData=arr[i];
			 j=i-1;
			while (j>0&&arr[j]<insertData){
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1]=insertData;
		}
	}

	/**
	 * 快速排序
	 * @param arr
	 * @param low
	 * @param high
	 */
	static void quickSort(int[]arr, int low, int high){
		int i=low;

		int j=high;

		if(i>j) return;
		int index=arr[low];
		while(i<j) {
			while(i<j&&arr[j]>=index)
				j--;
			if(i<j)
				arr[i++]=arr[j];
			while(i<j&&arr[i]<index)
				i++;
			if(i<j)
				arr[j--]=arr[i];
		}
		arr[i]=index;
		quickSort(arr,low,i-1);
		quickSort(arr,i+1,high);
	}

	/**
	 * 选择排序
	 * @param arr
	 */
	static void selectSort(int[]arr){
		if(arr.length<0) return;
		for(int i=0;i<arr.length;i++){
			int min=i;
			for(int j=arr.length-1;j>i;j--){
				if(arr[j]<arr[min]){
					min=j;
				}
			}
			if(i!=min){
				int temp=arr[min];
				arr[min]=arr[i];
				arr[i]=temp;
			}
		}
	}

	/**
	 * 归并排序
	 * @param arr
	 * @param low
	 * @param high
	 */
	static void mergeSort(int[] arr,int low,int high){

		int mid=(low+high)/2;
		if(low<high) {
			mergeSort(arr,low,mid);
			mergeSort(arr,mid+1,high);
			merge(arr,low,mid,high);
		}
	}
	static void merge(int[]arr,int low,int mid,int high){

		int[]mergeArr=new int[high-low+1];
		int i=low,j=mid+1,index=0;
		while(i<=mid&&j<=high){
			if(arr[i]<arr[j]){
				mergeArr[index++]=arr[i++];
			}else {
				mergeArr[index++]=arr[j++];
			}
		}
		//如果左边有剩余，则将剩余部分也加入到合并数组中，右边同理
		while(i<=mid){
			mergeArr[index++]=arr[i++];
		}
		while(j<=high){
			mergeArr[index++]=arr[j++];
		}
		//覆盖原有数组
		for(int k=0;k<mergeArr.length;k++){
			arr[k+low]=mergeArr[k];
		}
	}
	public static void main(String[]args){

		int[] arr={1,5,4,8,9,7,6,13,2};
		//quickSort(arr,0,arr.length-1);
		//selectSort(arr);
		mergeSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));

	}

}
