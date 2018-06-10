package com.example.binderpool.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.binderpool.BinderPool;

/**
 * Created by leador_yang on 2018/6/3.
 */

public class BinderPoolService extends Service {

    private static final String TAG = BinderPoolService.class.getSimpleName();

    private Binder binderPool = new BinderPool.BinderPoolImp();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binderPool;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
