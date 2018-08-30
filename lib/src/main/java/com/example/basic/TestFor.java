package com.example.basic;

/**
 * Created by leador_yang on 2018/5/21.
 */

public class TestFor {

    public static void testLoop(){
        // 按照index从前往后循环遍历长度为size的集合，每隔period次刷新一次打印元素
        // int[] a = {1,2,3},period = 2,则打印结果为1,1,2,2,3,3,1,1,2.....
        int k = 0;
        int period = 20; // 刷新频率,表示每刷新20次刷新一次数据,即变化的频率(刷新间隔,表示两次刷新的时间间隔)
        int size = 3; // 刷新的数据长度
        int i1 = period*size;
//        for (k = 1; k <= i1; k++){
//            int i2 = k / period;
//            System.out.println("k = "+k+", i2 = "+i2+", i2 % size = "+(i2 % size));
//        }

        int i = 0;
        while (i < 100){
//            k += 1;
            if (k >= i1){
                k = 0;
            }
            int i2 = k / period;
            System.out.println("k = "+k+", i2 = "+i2+", i2 % size = "+(i2 % size));
            k++;
            i++;
        }
    }

    public static void testBoolean(){
        boolean layoutRequested = true;
        boolean mStopped = true;
        boolean mReportNextDraw = false;
        final boolean didLayout = layoutRequested && (!mStopped);
        System.out.println("didLayout = " + didLayout+", mStopped = "+mStopped);
    }
}
