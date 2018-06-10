package com.example.type;

import java.util.List;
import java.util.Map;

/**
 * Created by leador_yang on 2018/5/16.
 */

public class WildcardTypeBean<T> {
    private List<? extends Number> a;

    private List<? super String> b;

    private List<String> c;

    private Class<?> aClass;

    private Map<? extends String, ?> d;

    private Holder<String> holder;

    private T key;

    static class Holder<K>{

    }

    public void test(List<String>[] pTypeArray, T[] vTypeArray, List<String> list, String[] strings){

    }
}
