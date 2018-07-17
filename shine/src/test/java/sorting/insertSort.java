package sorting;

import java.util.Arrays;

/**
 * 插入排序
 *基本思想:将一个待排的元素，插入到一个已经排好序的一组元素中
 * @author wb-cb236432
 * @create 2018-07-17 16:23
 **/
public class insertSort {
    public static void main(String[] args) {
            int[] ints =new int[]{4,2,5,3,7,9};
            int[] target = sortMethd(ints);
            //System.out.println(Arrays.toString(target));
    }

    /**
     * 4,2,5,3,7,9
     *
     * 4,5,2,3,7,9
     * 5,4,2,3,7,9
     * 5,4,3,2,7,9
     * 5,4,3,7,2,9
     * 5,4,7,3,2,9
     * 5,7,4,3,2,9
     * 7,5,4,3,2,9
     * 。。。。
     * 9,7,5,4,3,2
     *
     * @param target
     * @return
     */

    private static int[] sortMethd(int[] target) {
        if (null != target && target.length > 1) {
            for (int i = 1; i < target.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (target[j] > target[j - 1]) {
                        int temp = target[j];
                        target[j] = target[j - 1];
                        target[j - 1] = temp;
                    }
                    System.out.println(Arrays.toString(target));
                }
            }
        }
        return target;
    }
}
