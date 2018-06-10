package com.example.sort;

/**
 * Created by leador_yang on 2018/5/4.
 */

public class InsertSort {


    public void insertSort(int[] list) {
        // 打印第一个元素
        System.out.format("i = %d:\t", 0);
        printPart(list, 0, 0);

        for (int i =1;i < list.length;i++){
            int j = 0;
            int temp = list[i];

            for (j = i-1;j>=0 && temp<list[j];j--){
                list[j+1] = list[j];
            }
            // 跳出内循环时，j已经-1了
            list[j+1] = temp;

            // 打印第一个元素
            System.out.format("i = %d:\t", i);
            printPart(list, 0, i);
        }
    }

    // 打印序列
    public void printPart(int[] list, int begin, int end) {
        for (int i = 0; i < begin; i++) {
            System.out.print("\t");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(list[i] + "\t");
        }
        System.out.println();
    }




}
