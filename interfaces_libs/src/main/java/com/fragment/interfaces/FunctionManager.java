package com.fragment.interfaces;

import java.util.HashMap;

/**
 * Created by leador_yang on 2018/5/28.
 */

public class FunctionManager {
    private static final FunctionManager ourInstance = new FunctionManager();

    public static FunctionManager getInstance() {
        return ourInstance;
    }

    // 根据方法的四种特性构造四种容器:无参数无返回、无参数有返回、有参数有返回、有参数无返回
    private HashMap<String, FunctionWithNoParamNoResult> mapNoParamNoResult;
    private HashMap<String, FunctionWithNoParamWithResult> mapNoParamWithResult;
    private HashMap<String, FunctionWithParamNoResult> mapParamNoResult;
    private HashMap<String, FunctionWithParamWithResult> mapParamAndResult;

    private FunctionManager() {
        mapNoParamNoResult = new HashMap<>();
        mapNoParamWithResult = new HashMap<>();
        mapParamNoResult = new HashMap<>();
        mapParamAndResult = new HashMap<>();
    }

    // 1、添加一个function,添加到哪里？因为有多个Fragment就会存在有多个function.
    public FunctionManager addFunction(FunctionWithNoParamNoResult funtion){
        mapNoParamNoResult.put(funtion.getFunctionName(), funtion);
        return this;
    }

    public FunctionManager addFunction(FunctionWithNoParamWithResult funtion){
        mapNoParamWithResult.put(funtion.getFunctionName(), funtion);
        return this;
    }

    public FunctionManager addFunction(FunctionWithParamNoResult funtion){
        mapParamNoResult.put(funtion.getFunctionName(), funtion);
        return this;
    }

    public FunctionManager addFunction(FunctionWithParamWithResult funtion){
        mapParamAndResult.put(funtion.getFunctionName(), funtion);
        return this;
    }

    // 2、调用
    public void invokeFunction(String functionName) throws FunctionNotFoundException {
        if (functionName == null || "".equals(functionName)){
            return;
        }

        if (mapNoParamNoResult != null){
            FunctionWithNoParamNoResult functionWithNoParamNoResult = mapNoParamNoResult.get(functionName);
            if (functionWithNoParamNoResult != null){
                functionWithNoParamNoResult.function();
            } else {
                throw new FunctionNotFoundException();
            }
        }
    }

    public <Result> Result invokeFunction(String functionName, Class<Result> cls) throws FunctionNotFoundException {
        if (functionName == null || "".equals(functionName)){
            return null;
        }

        if (mapNoParamWithResult != null){
            FunctionWithNoParamWithResult functionWithNoParamWithResult = mapNoParamWithResult.get(functionName);
            if (functionWithNoParamWithResult != null){
                Result result = (Result) functionWithNoParamWithResult.function();
                return result;
            } else {
                throw new FunctionNotFoundException();
            }
        }

        return null;
    }

    public <Param> void invokeFunction(String functionName, Param data) throws FunctionNotFoundException {
        if (functionName == null || "".equals(functionName)){
            return;
        }

        if (mapParamNoResult != null){
            FunctionWithParamNoResult functionWithParamNoResult = mapParamNoResult.get(functionName);
            if (functionWithParamNoResult != null){
                functionWithParamNoResult.function(data);
            } else {
                throw new FunctionNotFoundException();
            }
        }
    }

    public <Param,Result> Result invokeFunction(String functionName, Class<Result> cls, Param data) throws FunctionNotFoundException {
        if (functionName == null || "".equals(functionName)){
            return null;
        }

        if (mapParamAndResult != null){
            FunctionWithParamWithResult functionWithParamWithResult = mapParamAndResult.get(functionName);
            if (functionWithParamWithResult != null){
                Result result = (Result) functionWithParamWithResult.function(data);
                return result;
            } else {
                throw new FunctionNotFoundException();
            }
        }

        return null;
    }
}
