package com.example.sort;

import java.io.UnsupportedEncodingException;

/**
 * Created by leador_yang on 2018/4/29.
 */

public class BubbleSort {

    // 冒泡排序
    public void bubbleSort(int[] list){
        int temp = 0;
        int len = list.length;

        // 外层循环,循环次数为N-1,N是数组长度
        int i,j;
        for (i = 0;i < len-1;i++){

            // 第i次循环时，数组前i(index从0到i-1)个元素已经是有序的,从末尾元素开始比较，每次和前一个元素进行比较,
            // 直到第i+1和i比较完为止,需要扫描到第i+1个(index=i+1)元素，需要比较N-i-1次,
            for (j=len-1;j > i;j--){
                if (list[j] < list[j-1]){
                    temp = list[j-1];
                    list[j-1] = list[j];
                    list[j] = temp;
                }
            }

            // 打印本次循环的数组排序结果
            try {
                String format = new String("Num %d:\t".getBytes(), "utf-8");
                System.out.format(format, i);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            printAll(list);
        }
    }

    // 优化，如果第i次循环没有元素移动，则表明已经有序，结束排序
    public void bubbleSort2(int[] list){
        int temp = 0;
        int len = list.length;
        boolean hasChanged = false; // 标记是否发生元素移动

        // 外层循环,循环次数为N-1,N是数组长度
        int i,j;
        for (i = 0;i < len-1;i++){
            // 每次循环前重置，保证不受前面循环的影响
            hasChanged = false;

            // 第i次循环时，数组前i(index从0到i-1)个元素已经是有序的,从末尾元素开始比较，每次和前一个元素进行比较,
            // 直到第i+1和i比较完为止,需要扫描到第i+1个(index=i+1)元素，需要比较N-i-1次,
            for (j=len-1;j > i;j--){
                if (list[j] < list[j-1]){
                    temp = list[j-1];
                    list[j-1] = list[j];
                    list[j] = temp;

                    hasChanged = true;
                }
            }

            // 打印本次循环的数组排序结果
            try {
                String format = new String("Num %d:\t".getBytes(), "utf-8");
                System.out.format(format, i);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            printAll(list);
            // 如果本次循环没有发生元素移动,则退出循环
            if (!hasChanged){
                break;
            }
        }
    }

    public void printAll(int[] list){
        for (int value : list){
            System.out.print(value + "\t");
        }
        System.out.println();
    }
}
