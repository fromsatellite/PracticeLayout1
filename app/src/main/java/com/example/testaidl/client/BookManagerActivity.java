package com.example.testaidl.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.testaidl.aidl.Book;
import com.example.testaidl.aidl.IBookManager;
import com.example.testaidl.aidl.IOnNewBookArrivedListener;
import com.example.testaidl.service.BookManagerService;
import com.hencoder.hencoderpracticelayout1.R;

import java.util.List;

/**
 * Created by leador_yang on 2018/5/28.
 */

public class BookManagerActivity extends Activity {

    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager remoteBookManager;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d("@@@", "receive new book:"+msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    // 给Binder设置死亡代理,分两步：
    /**
     * 1、声明一个{@link android.os.IBinder.DeathRecipient}对象
     */
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d("@@@", "binderDied tname:" + Thread.currentThread().getName());
            if (remoteBookManager == null){
                return;
            }
            remoteBookManager.asBinder().unlinkToDeath(deathRecipient, 0);
            remoteBookManager = null;
            // 重新绑定远程Service
            Intent intent = new Intent(BookManagerActivity.this, BookManagerService.class);
            BookManagerActivity.this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        // onServiceConnected和onServiceDisconnected都运行在UI线程，不能做耗时操作
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("@@@", "onServiceConnected tname:" + Thread.currentThread().getName());
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                remoteBookManager = bookManager;
                // 客户端绑定远程服务成功后,给binder设置死亡代理
                service.linkToDeath(deathRecipient, 0);
                /*
                 * 客户端调用远程服务的方法，被调用的服务端的方法本身运行在服务端的Binder线程池中，同时客户端线程被挂起,
                 * 如果服务端方法执行比较耗时，导致客户端线程长时间阻塞，如果客户端在UI线程来调用远程服务，则会
                 * 导致ANR，解决办法是另起子线程去调用
                 */
                List<Book> list = bookManager.getBookList();
                Log.d("@@@", "query book list, list type:" + list.getClass().getCanonicalName());
                Log.d("@@@", "query book list, list type:" + list.toString());
                Book newBook = new Book(3, "Android开发艺术探索");
                bookManager.addBook(newBook);
                Log.d("@@@", "add book:"+newBook);
                List<Book> newList = bookManager.getBookList();
                Log.d("@@@", "query book list, list type:" + newList.toString());
                bookManager.registerListener(onNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("@@@", "onServiceDisconnected tname:" + Thread.currentThread().getName());
        }
    };

    private IOnNewBookArrivedListener onNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        // 运行在客户端的Binder线程池中,不能访问UI内容，所以用Handler切换到UI线程
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            handler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (remoteBookManager != null
                && remoteBookManager.asBinder().isBinderAlive()){
            try {
                Log.d("@@@", "unregisterListener:" + onNewBookArrivedListener);
                remoteBookManager.unregisterListener(onNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
