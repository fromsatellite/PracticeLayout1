package com.example.reflect;

import java.util.HashMap;

/**
 * Created by leador_yang on 2018/5/17.
 */

public class Person {
    public String country;
    public String city;
    private String name;
    private String province;
    private Integer height;
    private Integer age;

    public Person() {
    }

    private Person(String country, String city, String name) {
        this.country = country;
        this.city = city;
        this.name = name;
    }

    public Person(String country, Integer age) {
        this.country = country;
        this.age = age;
    }

    private String getMobile(String number) {
        String mobile = "010-110" + "-" + number;
        return mobile;
    }

    private void setCountry(String country) {
        this.country=country;

    }

    public void getGenericHelper(HashMap<String, Integer> hashMap, String list) {
    }




    @Override
    public String toString() {
        return "Person [ country = "+country+", city = "+city+", name = "+name+", province = "+province+
                ", height = "+height+", age = "+age+" ]";
    }
}
