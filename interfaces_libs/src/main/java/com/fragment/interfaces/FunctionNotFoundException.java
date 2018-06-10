package com.fragment.interfaces;

/**
 * Created by leador_yang on 2018/5/28.
 */

public class FunctionNotFoundException extends Exception {

    public FunctionNotFoundException() {
        super("没有找到对应的Function");
    }
}
