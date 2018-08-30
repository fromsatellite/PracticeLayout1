package com.example.test1;

public class TestClassLoad {

    static {
        System.out.println("外部类的静态代码块");
    }

    {
        System.out.println("外部类的普通代码块");
    }

    public TestClassLoad() {
        System.out.println("外部类的构造方法");
    }

    public void testClassLoad(){
        System.out.println("外部类的普通方法testClassLoad,此时还没看到内部类的任何输出");
        InnerCommonClass innerCommonClass = new InnerCommonClass();
        innerCommonClass.innerMethod();

        InnerStaticClass innerStaticClass = new InnerStaticClass();
        innerStaticClass.innerMethod();
//        InnerStaticClass innerStaticClass2 = new InnerStaticClass();
//        innerStaticClass2.innerMethod();
    }

    public class InnerCommonClass{
        int a = 10;
        // Inner class cannot have static declarations
        /*static {
            System.out.println("外部类的静态代码块");
        }*/

        {
            System.out.println("内部类的普通代码块----a=" + a);
        }

        public InnerCommonClass() {
            System.out.println("内部类的构造方法----a" + a);
            a = 0;
        }

        public void innerMethod(){
            System.out.println("内部类的方法----a=" + a);
        }
    }

    public static class InnerStaticClass{
        int a = 10;
        static { // 静态COntext不能访问非静态的成员变量
            System.out.println("静态内部类的静态代码块----");
        }

        {
            System.out.println("静态内部类的普通代码块----a=" + a);
        }

        public InnerStaticClass() {
            System.out.println("静态内部类的构造方法----a" + a);
            a = 0;
        }

        public static void innerStaticMethod(){
            System.out.println("静态内部类的静态方法----");
        }

        public void innerMethod(){
            System.out.println("静态内部类的普通方法----a=" + a);
        }
    }
}
