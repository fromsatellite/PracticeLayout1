package com.example.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.binderpool.impl.ComputeImp;
import com.example.binderpool.impl.SecurityCenterImpl;
import com.example.binderpool.service.BinderPoolService;

import java.util.concurrent.CountDownLatch;

/**
 * Created by leador_yang on 2018/6/2.
 */

public class BinderPool {
    private static final String TAG = BinderPool.class.getSimpleName();
    public static final int BINDER_NONE = -1;
    public static final int BINDER_SECURITY_CENTER = 0;
    public static final int BINDER_COMPUTE = 1;

    private Context context;
    private IBinderPool binderPool;
    private static volatile BinderPool sInstance;
    private CountDownLatch connectBinderPoolCountDownLatch;

    private BinderPool(Context context) {
        this.context = context.getApplicationContext();
        // 初始化bindService
        connectBinderPoolService();
    }

    public static BinderPool getInstance(Context context) {
        if (sInstance == null){
            synchronized (BinderPool.class){
                if (sInstance == null){
                    sInstance = new BinderPool(context);
                }
            }
        }
        return sInstance;
    }

    private synchronized void connectBinderPoolService(){
        connectBinderPoolCountDownLatch = new CountDownLatch(1);
        Intent service = new Intent(this.context, BinderPoolService.class);
        this.context.bindService(service, binderPoolConnection, Context.BIND_AUTO_CREATE);
        try {
            connectBinderPoolCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解绑服务
     */
    public synchronized void disconnectBinderPoolService(){
        this.context.unbindService(binderPoolConnection);
    }

    /**
     * query binder by binderCode from binderPool.
     * @param binderCode
     *           the unique token of binder.
     * @return specific Binder{@link IBinder} whose token is binderCode<br/>
     *        return null when not found or {@link BinderPoolService} died.
     */
    public IBinder queryBinder(int binderCode){
        IBinder binder = null;
        if (binderPool != null){
            try {
                binder = binderPool.queryBinder(binderCode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return binder;
    }

    private ServiceConnection binderPoolConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderPool = IBinderPool.Stub.asInterface(service);
            try {
                binderPool.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            connectBinderPoolCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d("@@@", "binderDied tname:" + Thread.currentThread().getName());
            if (binderPool == null){
                return;
            }
            binderPool.asBinder().unlinkToDeath(deathRecipient, 0);
            binderPool = null;
            // 重新绑定远程Service
            connectBinderPoolService();
        }
    };

    public static class BinderPoolImp extends IBinderPool.Stub{

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode){
                case BINDER_SECURITY_CENTER:
                    binder = new SecurityCenterImpl();
                    break;
                case BINDER_COMPUTE:
                    binder = new ComputeImp();
                    break;
                default:
                    break;
            }
            return binder;
        }
    }
}
