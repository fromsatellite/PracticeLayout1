package com.example;

import com.example.sort.BubbleSort2;
import com.example.sort.Telephone;

public class MyClass {
    public static void main(String[] args){
        System.out.println("这是MyClass");
//        System.out.println("x = "+test());
//        int[] list = {1, 2, 3, 4, 5};
//        BubbleSort bubbleSort = new BubbleSort();
//        bubbleSort.bubbleSort(list);
//
//        System.out.println("MyClass sec");
//        int[] list2 = {1, 2, 3, 4, 5};
//        bubbleSort.bubbleSort2(list2);

//        QuickSort quickSort = new QuickSort();
//        int[] array2 = {5, 6, 3, 7, 8, 2, 9, 0};
//        System.out.print("before:\t\t");
//        quickSort.printPart(array2, 0, array2.length - 1);
//        quickSort.quickSort(array2, 0, array2.length - 1);
//        System.out.print("after:\t\t");
//        quickSort.printPart(array2, 0, array2.length - 1);
//        Object lock = new Object();
//        Thread one = new Thread(new ThreadOne());
//        Thread two = new Thread(new ThreadTwo());
//        Thread one = new Thread(new ThreadLOne());
//        Thread two = new Thread(new ThreadLTwo());
//        one.start();
//        two.start();

//        BinaryCompute binaryCompute = new BinaryCompute();
//        System.out.println("10(1010)  3(0011) diff-bits is "+binaryCompute.countBitDiff(10, 3));

//        TestHelper.testWildCardType();
//        TestHelper.testTypeVariable();
//        TestHelper.testGenericArrayType();
//        TestHelper.printConstructor(Person.class.getName());
//        Person person = TestHelper.getPerson("CHINA", 18);
//        TestHelper.printField(Person.class.getName());
//        TestHelper.getAge(person);
//        TestHelper.printMethod(Person.class.getName());
//        TestHelper.changeCountry(person, "UK");
//        TestHelper.testArrayClass();
//        TestHelper.getGenericType();
//        TestFor.testLoop();

//        try {
//            ParseAnnotation.parseTypeAnnotation();
//            ParseAnnotation.parseFieldAnnotation();
//            ParseAnnotation.parseConstructAnnotation();
//            ParseAnnotation.parseMethodAnnotation();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        TestFor.testBoolean();
//        System.out.println("1/2 = "+(1/2)+", 3/4 = "+(3/4)+", (int)(5000-scrollX%icon_width*ratio) = "
//                +(int)(5000-4%3*5000/2));

//        String s1 = "abc";
//        String s2 = new String("abc");
//        System.out.println(s1 == s2);
//
//        MyDynamicProxy proxy = new MyDynamicProxy();
//        proxy.onProxy();
//        proxy.sayHello();
//        LinkedHashMapSample.testRemove();

//        TestClassLoad testClassLoad = new TestClassLoad();
//        testClassLoad.testClassLoad();
//        TestClassLoad testClassLoad2 = new TestClassLoad();

        int[] data = {4,2,7,1,8};
        BubbleSort2 bubbleSort2 = new BubbleSort2();
//        bubbleSort2.sort(data);
        bubbleSort2.recursiveSort(data);
        Telephone telephone = new Telephone();
        telephone.start("234");
    }

    public static int test(){
        int x = 1;
        try {
            return 1;
        } catch (Exception e){

        } finally {
            return 2;
        }
    }
}
