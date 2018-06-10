package com.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by leador_yang on 2018/5/8.
 */

public class ThreadParent implements Runnable {

    protected static ReentrantLock lock = new ReentrantLock();
    protected static Condition condition = lock.newCondition();


    protected void doTask(){}

    @Override
    public void run() {
        doTask();
    }
}
