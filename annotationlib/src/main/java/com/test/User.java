package com.test;

import com.example.annotation.clazz.Seriable;

import java.util.List;

/**
 * Created by leador_yang on 2018/6/7.
 */

public class User {
    @Seriable
    String name;
    @Seriable
    String area;
    @Seriable
    int age;
    int weight;

    @Seriable
    List<Article> articleList;
}
