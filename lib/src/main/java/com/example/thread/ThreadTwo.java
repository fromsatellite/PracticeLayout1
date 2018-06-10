package com.example.thread;

/**
 * Created by leador_yang on 2018/5/8.
 */

public class ThreadTwo implements Runnable {

//    Object lock;

    @Override
    public void run() {
        try {
            synchronized (ThreadOne.class){
                System.out.println("ThreadTwo is handing lock");
                System.out.println("ThreadTwo is giving up lock");
                ThreadOne.class.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
