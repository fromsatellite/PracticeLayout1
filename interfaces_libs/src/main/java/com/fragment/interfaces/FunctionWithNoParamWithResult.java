package com.fragment.interfaces;

/**
 * Created by leador_yang on 2018/5/28.
 */

public abstract class FunctionWithNoParamWithResult<Result> extends Function {
    public FunctionWithNoParamWithResult(String functionName) {
        super(functionName);
    }

    // 方法体
    public abstract Result function();
}
