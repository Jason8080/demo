package com.gm.algorithm.str.reversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * StrReversal.
 *
 * @author : Jason.lee
 * @version : 1.0
 */
public class StrReversalApplication {

    /**
     * 需求: 将一串字符串从中间分别反转
     * 举例: 123456789 -> 432159876
     * 要求: 计算时空复杂度, 并尝试给出优化方案
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String content = "";
        System.out.println("========= 请输入排序反转内容 =========");
        while ((content = in.readLine()) != null) {
            long start = System.currentTimeMillis();
            reversal(content);
            long end = System.currentTimeMillis();
            System.out.println("========= 共计花费 " + (end - start) + " 毫秒值 =========");
            System.out.println("========= 请输入排序反转内容 =========");
        }
    }

    /**
     * 字符串反转算法
     * @param content
     */
    private static void reversal(String content) {
        char[] chars = content.toCharArray();
        int middleIndex = chars.length >> 1;
        left(chars, middleIndex);
        System.out.print(chars[middleIndex]);
        right(chars, middleIndex, chars.length);
        System.out.println();
    }

    private static void left(char[] chars, int index) {
        if (--index >= 0) {
            System.out.print(chars[index]);
            left(chars, index);
        }
    }

    private static void right(char[] chars, int middleIndex, int index) {
        if (--index > middleIndex) {
            System.out.print(chars[index]);
            right(chars, middleIndex, index);
        }
    }
}
