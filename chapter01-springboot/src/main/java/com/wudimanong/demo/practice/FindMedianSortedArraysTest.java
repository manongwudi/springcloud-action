package com.wudimanong.demo.practice;

/**
 * @author joe
 */
public class FindMedianSortedArraysTest {

    public static double findMedianSortedArray(int[] arrayA, int[] arrayB) {
        //长度值
        int m = arrayA.length;
        int n = arrayB.length;

        //如果数组A的长度大于数组B，则交互数组（将较短的数组放在前面）
        if (m > n) {
            int[] temp = arrayA;
            arrayA = arrayB;
            arrayB = temp;
            int tep = m;
            m = n;
            n = tep;
        }

        int start = 0;
        int end = m;
        int mid = (m + n + 1) / 2;

        while (start <= end) {
            int i = (start + end) / 2;
            int j = mid - i;
            if (i < end && arrayB[j - 1] > arrayA[i]) {
                //i偏小了，需要右移
                start = i + 1;
            } else if (i > start && arrayA[i - 1] > arrayB[j]) {
                //i偏大了，需要左移
                end = i - 1;
            } else {
                //i 刚好合适，或i已达到数组边界
                int maxLeft;
                if (i == 0) {
                    maxLeft = arrayB[j - 1];
                } else if (j == 0) {
                    maxLeft = arrayA[i - 1];
                } else {
                    maxLeft = Math.max(arrayA[i - 1], arrayB[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    //如果大数组长度是奇数，中位数就是左半部分的最大值
                    return maxLeft;
                }
                int minRight;
                if (i == m) {
                    minRight = arrayB[j];
                } else if (j == n) {
                    minRight = arrayA[i];
                } else {
                    minRight = Math.min(arrayB[j], arrayA[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String args[]) {
        //1,3,5,6,7,8,10,12,17,18,20
        int arrayB[] = new int[]{1, 10, 17, 18};
        int arrayA[] = new int[]{3, 5, 6, 7, 8, 12, 20};
        double result = findMedianSortedArray(arrayA, arrayB);
        System.out.println(result);
    }
}
