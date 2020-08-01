package com.gm.algorithm.quick.sort;

import java.util.Arrays;

/**
 * @author Jason
 */
public class QuickSortApplication {

    public static void main(String[] args) {
        int[] sort = sort(13, 1, 19, 50, 11, 8, 2, 5, 3, 6, 7, 1, 4, 9, 7, 10, 20, 12);
        System.out.println(Arrays.toString(sort));
    }

    private static int[] sort(int... is) {
        if(is.length>0){
            System.out.println(Arrays.toString(is));
            quick(is, 0, is.length-1);
        }
        return is;
    }

    private static void quick(int[] is, int left, int right) {
        int i = left;
        int j = right;
        if(i<j){
            int k = is[i];
            while (i<j) {
                while (i < j && is[i] < k) {
                    i++;
                }
                while (i < j && is[j] >= k) {
                    j--;
                }
                if (i < j) {
                    swap(is, i, j);
                }
            }
            quick(is, left, i-1);
            quick(is, i+1, right);

        }
    }

    private static void swap(int[] is, int i, int j) {
        int swap = is[i];
        is[i] = is[j];
        is[j] = swap;
    }
}
