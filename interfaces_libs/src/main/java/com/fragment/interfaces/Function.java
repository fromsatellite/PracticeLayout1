package com.fragment.interfaces;

/**
 * Created by leador_yang on 2018/5/28.
 */

public abstract class Function {

    private String functionName;

    public Function(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }
}
