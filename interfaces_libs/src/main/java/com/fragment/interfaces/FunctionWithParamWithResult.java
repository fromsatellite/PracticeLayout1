package com.fragment.interfaces;

/**
 * Created by leador_yang on 2018/5/28.
 */

public abstract class FunctionWithParamWithResult<Param, Result> extends Function {
    public FunctionWithParamWithResult(String functionName) {
        super(functionName);
    }

    // 方法体
    public abstract Result function(Param data);
}
