package com.example.thread;

/**
 * Created by leador_yang on 2018/5/8.
 */

public class ThreadOne implements Runnable {

//    static Object lock = new Object();

    @Override
    public void run() {
        try {
            synchronized (ThreadOne.class){
                System.out.println("ThreadOne is handing lock");
                ThreadOne.class.wait();
                System.out.println("ThreadOne is giving up lock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
