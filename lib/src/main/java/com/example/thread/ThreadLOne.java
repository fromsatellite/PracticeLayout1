package com.example.thread;

/**
 * Created by leador_yang on 2018/5/8.
 */

public class ThreadLOne extends ThreadParent {

    @Override
    protected void doTask() {
//        super.doTask();
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+"==>>await");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+"==>>go on");
    }
}
