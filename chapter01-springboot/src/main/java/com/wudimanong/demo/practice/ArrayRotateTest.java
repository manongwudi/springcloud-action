package com.wudimanong.demo.practice;

import java.util.Arrays;

/**
 * @author joe
 */
public class ArrayRotateTest {

    public static int[] rotate(int[] nums, int k) {
        int length = nums.length;
        while (k > 0) { //循环几次，就是前面需要依次挪动位置的元素个数
            int t = 0;
            t = nums[length - 1];
            for (int j = length - 2; j >= 0; j--) { //从倒数第二个数字开始，倒叙循环
                nums[j + 1] = nums[j];
            }
            nums[0] = t;
            k--;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        int[] result = rotate(nums, 3);

        System.out.println(Arrays.toString(result));
    }
}
