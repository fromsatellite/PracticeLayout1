package com.example.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 手写冒泡排序。
 */
public class BubbleSort2 {

    public void sort(int[] data){
        System.out.println("排序之前:");
        printfArray(data);
        int len = data.length;

        int i = 0,j = 0,temp;
        boolean isChange = false;
        for (i = 0; i < len-1 ;i++){
            isChange = false;
            for (j = len-1; j > i;j--){
                if (data[j] < data[j-1]){
                    temp = data[j-1];
                    data[j-1] = data[j];
                    data[j] = temp;
                    isChange = true;
                }
            }
            System.out.println("第" + i + "趟排序之后:");
            printfArray(data);
            // 如果此次内循环没有发生元素交换,就说明已经有序，终止循环
            if (!isChange)break;
        }
        System.out.println("排序之后:");
        printfArray(data);
    }

    public void recursiveSort(int[] data){
        List<Integer> finalList = new ArrayList<>();
        recursive(data, 0, finalList);
        System.out.println("递归实现冒泡排序之后:");
        for (int i : finalList){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public void recursive(int[] data, int start, List<Integer> list){
        System.out.println(" start = "+start);
        if (start < data.length-1) {
            int j, temp;
            for (j = data.length - 1; j > start; j--) {
                if (data[j] < data[j - 1]) {
                    temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
            list.add(data[start]);
            recursive(data, ++start, list);
        } else {
            return;
        }

        start--;
        System.out.println("递归在逐层返回 start = "+start);
        if (start == 0){
            System.out.println("递归结束了惹");
            list.add(data[data.length-1]);
        }
    }

    private void printfArray(int[] data){
        for (int i : data){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
