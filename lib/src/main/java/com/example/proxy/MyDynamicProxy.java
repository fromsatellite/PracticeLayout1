package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyDynamicProxy {

    interface Hello{
        void sayHello(String content);
    }

    private static class HelloImpl implements Hello{
        @Override
        public void sayHello(String content) {
            System.out.println(content);
        }
    }

    private static Hello hello;

    public MyDynamicProxy() {
        if (hello == null){
            hello = new HelloImpl();
        }
    }

    public void onProxy(){
//        HelloImpl hello = new HelloImpl();

        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代理实例
        Hello proxyHello = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(),handler);
        // 调用代理方法
//        proxyHello.sayHello("Hello World.");
        hello = proxyHello;
    }

    public void sayHello(){
        hello.sayHello("Hello World.");
    }

    class MyInvocationHandler implements InvocationHandler{

        private Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("Invoking sayHello");

            String newWord = "Invoking sayHello";
            args[0] = newWord;
            return method.invoke(target, args);
        }
    }
}
