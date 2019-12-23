package com.wudimanong.demo.practice;

/**
 * @author joe
 */
public class BigIntegerSumTest {

    public static String bigNumberSum(String bigNumberA, String bigNumberB) {
        int maxLength = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();
        int arrayA[] = new int[maxLength + 1];
        int arrayB[] = new int[maxLength + 1];
        for (int i = 0; i < bigNumberA.length(); i++) {
            arrayA[i] = bigNumberA.charAt(bigNumberA.length() - i - 1) - '0';
        }
        for (int i = 0; i < bigNumberB.length(); i++) {
            arrayB[i] = bigNumberB.charAt(bigNumberB.length() - i - 1) - '0';
        }

        //定义结果数组
        int result[] = new int[maxLength + 1];
        for (int i = 0; i < result.length; i++) {
            int temp = result[i];
            temp += arrayA[i];
            temp += arrayB[i];
            if (temp > 10) {
                temp = temp - 10;
                result[i + 1] = 1;
            }
            result[i] = temp;
        }
        //再次将结果数组逆序转成String
        boolean isFirst = true;
        StringBuffer resultBuffer = new StringBuffer();
        for (int i = result.length - 1; i >= 0; i--) {
            if (isFirst) {
                if (result[i] == 0) {
                    continue;
                }
                isFirst = false;
            }
            resultBuffer.append(result[i]);
        }
        return resultBuffer.toString();
    }

    public static void main(String args[]) {
        String bigNumberA = "9523372036854775807";
        String bigNumberB = "9623372036854775807";
        String result = bigNumberSum(bigNumberA, bigNumberB);
        System.out.println(result);
    }
}
