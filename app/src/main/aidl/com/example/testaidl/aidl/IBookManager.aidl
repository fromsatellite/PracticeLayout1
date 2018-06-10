// IBookManager.aidl
package com.example.testaidl.aidl;

// 自定义的Parcelable对象或者aidl接口必须要显式的import,否则build/clean会报错
import com.example.testaidl.aidl.Book;
import com.example.testaidl.aidl.IOnNewBookArrivedListener;

// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);


     List<Book> getBookList();

     void addBook(in Book book);

     void registerListener(IOnNewBookArrivedListener listener);

     void unregisterListener(IOnNewBookArrivedListener listener);
}
