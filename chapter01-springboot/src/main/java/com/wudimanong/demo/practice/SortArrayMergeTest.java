package com.wudimanong.demo.practice;

import java.util.Arrays;

/**
 * @author joe
 */
public class SortArrayMergeTest {

    public static int[] merge(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int a = 0;//nums1数组下标
        int b = 0;//nums2数组下标
        for (int i = 0; i < result.length; i++) {
            if (a < nums1.length && b < nums2.length) {//两个数组未遍历完
                if (nums1[a] < nums2[b]) {
                    result[i] = nums1[a];
                    a++;
                } else {
                    result[i] = nums2[b];
                    b++;
                }
            } else if (a < nums1.length) {
                result[i] = nums1[a];
                a++;
            } else if (b < nums1.length) {
                result[i] = nums2[b];
                b++;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] num1s = {1, 2, 3, 7, 8, 9};
        int[] num2s = {4, 5, 6};
        int[] result = merge(num1s, num2s);
        System.out.println(Arrays.toString(result));
    }
}
