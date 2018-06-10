// IBinderPool.aidl
package com.example.binderpool;

// Declare any non-default types here with import statements

interface IBinderPool {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

     /**
      * @param binderCode, the unique token of specific Binder<br/>
      * @return specific Binder whose token is binderCode.
      */
     IBinder queryBinder(int binderCode);
}
