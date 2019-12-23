package com.wudimanong.demo.practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author joe
 */
public class ShortestSubStringTest {

    public static String getShortestSubString(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        // 记录目标字符串的起始索引
        int start = 0, end = str.length() - 1;

        // 记录目标字符串的开始位置
        int pStart = 0;
        Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();

        //遍历一次字符串，求出字符串不同字符的数目，并保存到一个列表
        for (int index = 0; index < str.length(); index++) {
            map.put(str.charAt(index), null);
        }
        int remainingCharacter = map.keySet().size();

        //遍历字符串
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.get(c) == null) {
                List list = new LinkedList<Integer>();
                map.put(c, list);
                remainingCharacter--;
            }
            map.get(c).add(i);
            while (map.get(str.charAt(pStart)).size() > 1) {
                map.get(str.charAt(pStart)).remove(0);
                pStart++;
            }
            if (remainingCharacter == 0) {
                if (i - pStart < end - start) {

                    start = pStart;
                    end = i;
                }
            }
        }
        return str.substring(start, end + 1);
    }

    public static void main(String args[]) {
        ShortestSubStringTest shortestSubStringTest = new ShortestSubStringTest();
        String str = shortestSubStringTest.getShortestSubString("abcddbccaaabcefggf");
        System.out.println(str);
    }

}
