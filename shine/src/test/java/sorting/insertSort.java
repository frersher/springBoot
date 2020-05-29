package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 插入排序
 * 基本思想:将一个待排的元素，插入到一个已经排好序的一组元素中
 *
 * @author wb-cb236432
 * @create 2018-07-17 16:23
 **/
public class insertSort {

    private static List<List<Integer>> data = new ArrayList<>();

    private static void oom() {
        for (int i = 0; i < 1000; i++) {
            List<Integer> rawList = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
            System.out.println(rawList.size());
            data.add(rawList.subList(0, 1));
        }
    }


    public static void main(String[] args) {
        int[] sry = new int[]{5,2,1,3};
//        sortMethd(sry);
        sortMethd(sry);
        System.out.printf(Arrays.toString(sry));
    }

    /**
     * 4,2,5,3,7,9
     * <p>
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
     * @param nums
     * @return
     */

    private static int[] sortMethd(int[] nums) {
        if (null != nums && nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                for (int j = i; j > 0; j--) {
                    if(nums[j] < nums[j-1]) {
                        int temp = nums[j];
                        nums[j] = nums[j - 1];
                        nums[j - 1] = temp;
                    }
                }
            }
        }
        return nums;
    }

    private static int[] popSort(int[] nums) {
        if (null != nums && nums.length > 1) {
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = 0; j < nums.length - 1 - i; j++) {
                    if (nums[j] > nums[j + 1]) {
                        int temp = nums[j + 1];
                        nums[j + 1] = nums[j];
                        nums[j] = temp;
                    }
                }

            }

        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }

}
