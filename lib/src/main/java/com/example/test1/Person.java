package com.example.test1;

/**
 * Created by leador_yang on 2018/5/17.
 */

public class Person {

    public void test(){
        Animal animal = new Animal();
        animal.testProtected();
        animal.testPublic();
        animal.testDefault();
    }
}
