package com.example.annotation.clazz;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by leador_yang on 2018/6/7.
 */
@Documented
// 表示是编译时注解
@Retention(RetentionPolicy.CLASS)
// 表示可以作用于类、接口、成员变量
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Seriable {
}
