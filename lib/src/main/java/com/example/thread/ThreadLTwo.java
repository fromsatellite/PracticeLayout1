package com.example.thread;

/**
 * Created by leador_yang on 2018/5/8.
 */

public class ThreadLTwo extends ThreadParent {

    @Override
    protected void doTask() {
//        super.doTask();
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+"==>>enter");
            Thread.sleep(1000);
            condition.signal();
            System.out.println(Thread.currentThread().getName()+"==>>finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
