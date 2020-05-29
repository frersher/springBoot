package sorting;

import java.util.Arrays;

/**
 * Created by shchenbang on 2019/3/31
 * 快速排序：类似于递归，选择一个基准数据，其他数据分开，大的放右边小的放左边
 * 继续对两边的数据进行同样的操作
 *
 */
public class quickSort {

    public static void main(String[] args) {
        int[] a = {4,1,3,8,2,9,0,7,5,6};
//        int[] a = {5, 2, 8, 9, 1};
        sort(a, 0, a.length-1);
    }


    public static void sort(int[] a, int low, int hight) {
        int start = low;
        int end = hight;
        int key = a[low];

        while (end > start) {
            //从后向前比较
            while (end > start && key < a[end]) {
                //r如果后面的数比key大，继续向前找
                end--;
            }
            if (key >= a[end]) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }


            //从前向后比较
            while (end > start && key > a[start]) {
                start++;
            }
            if (key <= a[start]) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
        }

        if (start > low) {
            sort(a, low, start - 1);
        }
        if (end < hight) {
            sort(a, end + 1, hight);
        }
        System.out.println(Arrays.toString(a));
    }

}