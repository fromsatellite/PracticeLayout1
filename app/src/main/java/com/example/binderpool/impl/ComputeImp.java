package com.example.binderpool.impl;

import android.os.RemoteException;

import com.example.binderpool.ICompute;

/**
 * Created by leador_yang on 2018/6/2.
 */

public class ComputeImp extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
