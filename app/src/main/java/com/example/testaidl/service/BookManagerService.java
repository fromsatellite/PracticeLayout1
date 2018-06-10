package com.example.testaidl.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.testaidl.aidl.Book;
import com.example.testaidl.aidl.IBookManager;
import com.example.testaidl.aidl.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by leador_yang on 2018/5/27.
 */

public class BookManagerService extends Service {

    private AtomicBoolean isServiceDestroyed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<>();
//    private CopyOnWriteArrayList<IOnNewBookArrivedListener> listeners = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> callbackList = new RemoteCallbackList<>();

    private Binder serviceBinder = new IBookManager.Stub(){
        @Override
        public IBinder asBinder() {
            return super.asBinder();
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            // 验证方式之二:
            int check = checkCallingOrSelfPermission("com.example.testaidl.aidl.permission.ACCESS_BOOK_SERVICE");
            if (check == PackageManager.PERMISSION_DENIED){
                Log.d("@@@", "onTransact false.");
                return false;
            }

            /*
             * 基本上就是通过获取调用者的PID，通过遍历当前运行的app，来得到包名。而实际上，这段代码是有问题的，
             * 因为一个apk，并不一定只有一个进程，也即可能有多个PID。针对我测试的那种情况，调用的进程是app_process，
             * 所以遍历过后，并不能得到apk的包名，而我的同事的情况刚好只有一个进程，那自然就没问题了。
             * 怎么修改呢？更准确的方法是使用UID来进行判断，我们知道，在android中，每一个进程都有且只有一个UID。
             */
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0){
                packageName = packages[0];
            }
            if (!packageName.startsWith("com.hencoder")){
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            bookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (!listeners.contains(listener)){
//                listeners.add(listener);
//            } else {
//                Log.d("@@@", "alteady exists.");
//            }
//            Log.d("@@@", "registerListener, size:" + listeners.size());
            callbackList.register(listener);

            callbackList.beginBroadcast();
            Log.d("@@@", "registerListener, current size:" + callbackList.getRegisteredCallbackCount());
            callbackList.finishBroadcast();
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (listeners.contains(listener)){
//                listeners.remove(listener);
//                Log.d("@@@", "unregisterListener succeed.");
//            } else {
//                Log.d("@@@", "not found, can not unregisterListener.");
//            }
//            Log.d("@@@", "unregisterListener, current size:" + listeners.size());
            callbackList.unregister(listener);

            callbackList.beginBroadcast();
            Log.d("@@@", "unregisterListener, current size:" + callbackList.getRegisteredCallbackCount());
            callbackList.finishBroadcast();
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        bookList.add(new Book(1, "Android"));
        bookList.add(new Book(2, "IOS"));
        new Thread(new ServiceWork()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 验证方式之一：用权限验证
//        int check = checkCallingOrSelfPermission("com.example.testaidl.aidl.permission.ACCESS_BOOK_SERVICE");
//        if (check == PackageManager.PERMISSION_DENIED){
//            Log.d("@@@", "onBind null.");
//            return null;
//        }
        Log.d("@@@", "onBind serviceBinder.");
        return serviceBinder;
    }

    @Override
    public void onDestroy() {
        isServiceDestroyed.set(true);
        super.onDestroy();
    }

    /*
     * 如果客户端的listener.onNewBookArrived(book)耗时,确保服务端的onNewBookArrived方法不能在UI线程
     */
    private void onNewBookArrived(Book book) throws RemoteException{
        bookList.add(book);
        // List管理监听
//        Log.d("@@@", "onNewBookArrived, notify listeners:" + listeners.size());
//        for (int i = 0; i < listeners.size();i++){
//            IOnNewBookArrivedListener listener = listeners.get(i);
//            Log.d("@@@", "onNewBookArrived, notify listener:" + listener);
//            listener.onNewBookArrived(book);
//        }
        // RemoteCallbackList管理监听
        final int N = callbackList.beginBroadcast();
        for (int i = 0; i < N;i++){
            IOnNewBookArrivedListener listener = callbackList.getBroadcastItem(i);
            if (listener != null){
                Log.d("@@@", "onNewBookArrived, notify listener:" + listener);
                listener.onNewBookArrived(book);
            }
        }
        callbackList.finishBroadcast();
    }

    private class ServiceWork implements Runnable{

        @Override
        public void run() {
            while (!isServiceDestroyed.get()){
                try {
                    Thread.sleep(5000);
                    int bookId = bookList.size() + 1;
                    Book newBook = new Book(bookId, "new book#" + bookId);
                    onNewBookArrived(newBook);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
