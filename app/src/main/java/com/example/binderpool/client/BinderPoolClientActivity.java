package com.example.binderpool.client;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.binderpool.BinderPool;
import com.example.binderpool.ICompute;
import com.example.binderpool.ISecurityCenter;
import com.example.binderpool.impl.ComputeImp;
import com.example.binderpool.impl.SecurityCenterImpl;
import com.hencoder.hencoderpracticelayout1.R;

/**
 * Created by leador_yang on 2018/6/3.
 */

public class BinderPoolClientActivity extends Activity {

    private BinderPool binderPool;
    private ISecurityCenter securityCenter;
    private ICompute compute;

    private Runnable doWork = new Runnable() {
        @Override
        public void run() {
            doWork();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        new Thread(doWork).start();
    }

    @Override
    protected void onDestroy() {
        if (binderPool != null){
            binderPool.disconnectBinderPoolService();
        }
        super.onDestroy();
    }

    private void doWork(){
        binderPool = BinderPool.getInstance(BinderPoolClientActivity.this);

        Log.d("@@@", "visit ISecurityCenter");
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        if (securityBinder != null){
            securityCenter = SecurityCenterImpl.asInterface(securityBinder);
            String msg = "helloworld-android";
            Log.d("@@@", "content:" + msg);
            try {
                String password = securityCenter.encrypt(msg);
                Log.d("@@@", "encrypt:" + password);
                String decrypt = securityCenter.decrypt(password);
                Log.d("@@@", "decrypt:" + decrypt);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        Log.d("@@@", "visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        if (computeBinder != null){
            compute = ComputeImp.asInterface(computeBinder);

            try {
                int add = compute.add(3,5);
                Log.d("@@@", "compute:" + add);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
