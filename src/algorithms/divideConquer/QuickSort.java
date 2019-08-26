package algorithms.divideConquer;

import java.util.Arrays;

//public class QuickSort {
//	public void quick_sort(int[]arr,int l,int r) {
//		int i = l, j = r;
//		int x = arr[l];
//		while (i < j) {
//			while (i < j) {
//				j--;
//				if (arr[j] < x) {
//					arr[i] = arr[j];
//					break;
//				}
//			}
//			while (i < j) {
//				i++;
//				if (arr[i] >= x) {
//					arr[j] = arr[i];
//					break;
//				}
//			}
//		}
//			arr[i] = x;
//			quick_sort(arr, l, i - 1);
//			quick_sort(arr, i + 1, r);
//	}
//
//	public static void main(String[]args){
//		int arr[]={7,5,2,6,8,1,4,9,3};
//		QuickSort qs=new QuickSort();
//		qs.quick_sort(arr,0,arr.length-1);
//	}
//}
public class QuickSort {
	public static void quick_sort(int a[], int low, int hight) {
		int i, j, index;
		if (low > hight) {
			return;
		}
		i = low;
		j = hight;
		index = a[i]; // 用子表的第一个记录做基准
		while (i < j) { // 从表的两端交替向中间扫描
			while (i < j && a[j] >= index)
				j--;
			if (i < j)
				a[i++] = a[j];// 用比基准小的记录替换低位记录
			while (i < j && a[i] < index)
				i++;
			if (i < j) // 用比基准大的记录替换高位记录
				a[j--] = a[i];
		}
		a[i] = index;// 将基准数值替换回 a[i]
		quick_sort(a, low, i - 1); // 对低子表进行递归排序
		quick_sort(a, i + 1, hight); // 对高子表进行递归排序

	}

	public static void quickSort(int a[]) {
		quick_sort(a, 0, a.length - 1);
	}

	public static void main(String[] args) {

		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49 };
		System.out.println(Arrays.toString(a));
		quickSort(a);
		System.out.println(Arrays.toString(a));
	}
}