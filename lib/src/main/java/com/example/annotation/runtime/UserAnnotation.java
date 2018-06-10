package com.example.annotation.runtime;

/**
 * Created by leador_yang on 2018/6/7.
 */
@MethodInfo(name = "Type",gid = Long.class)
public class UserAnnotation {

    @MethodInfo(name = "Field",id = 0,gid = Long.class)
    private Integer age;

    @MethodInfo(name = "Constructor",id = 1,gid = Long.class)
    public UserAnnotation(Integer age) {
        this.age = age;
    }

    @MethodInfo(name = "Method",id = 2,gid = Long.class)
    public void a(){
        age = 11;
    }

    @MethodInfo(name = "Method",id = 3,gid = Long.class)
    protected void b(){
        age = 11;
    }

    @MethodInfo(name = "Method",id = 4,gid = Long.class)
    private void c(){
        age = 11;
    }

    public void d(){
        age = 11;
    }
}
