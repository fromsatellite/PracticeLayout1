package com.example.sort;

/**
 * Created by leador_yang on 2018/5/3.
 */

public class QuickSort {

    private int division(int[] list, int left, int right){
        // left作为base
        int base = list[left];
        // left和right重合时，分割完毕
        while (left < right){
            // 从右边向左边扫描,如果比base小则赋值给left
            while (left < right && list[right] >= base){
                right--;
            }
            list[left] = list[right];
            // 从左边扫描到右边，如果比base大就赋值给right
            while (left < right && list[left] <= base){
                left++;
            }
            list[right] = list[left];
        }
        // 最终left == right，所有的数都扫描了一遍,base的位置也就确定了
        list[left] = base;

        return left;
    }

    public void quickSort(int[] list, int left, int right){
        if (left < right){
            int baseIndex = division(list, left, right);

            System.out.format("base = %d:\t", list[baseIndex]);
            printPart(list, left, right);

            // 对base左侧的进行分割，确定分割后的base
            quickSort(list, left, baseIndex - 1);

            // 对base右侧的进行分割，确定分割后的base
            quickSort(list, baseIndex + 1, right);
        }
    }

    public void printPart(int[] list, int begin, int end){
        for (int i = 0;i< begin;i++){
            System.out.print("\t");
        }
        for (int i = begin;i<= end;i++){
            System.out.print("\t" + list[i]);
        }
        System.out.println();
    }
}
