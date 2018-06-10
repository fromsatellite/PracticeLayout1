package com.fragment.interfaces;

/**
 * Created by leador_yang on 2018/5/28.
 */

public abstract class FunctionWithNoParamNoResult extends Function {
    public FunctionWithNoParamNoResult(String functionName) {
        super(functionName);
    }

    // 方法体
    public abstract void function();
}
