package com.example.sort;

/**
 * Created by leador_yang on 2018/5/4.
 */

public class ShellSort {

    public void shellSort(int[] list) {
        int gap = list.length / 2;

        while (gap >= 1){
            for (int i = gap;i<list.length;i++){
                int j = 0;
                int temp = list[i];

                for (j = i-gap; j>=0 && temp < list[j];j = j-gap){
                    list[j+gap] = list[j];
                }
                list[j+gap] = temp;
            }

            System.out.format("gap = %d:\t", gap);
            printAll(list);
            gap = gap / 2; // 减小增量

        }
    }

    // 打印完整序列
    public void printAll(int[] list) {
        for (int value : list) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }


}

