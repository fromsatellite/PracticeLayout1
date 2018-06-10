package com.example.annotation.runtime;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by leador_yang on 2018/6/7.
 */

public class ParseAnnotation {

    final static String className = UserAnnotation.class.getName();

    /**
     * 简单打印出{@link UserAnnotation}类中所使用到的类注解(ElementType.TYPE),
     * 该方法只打印了{@link MethodInfo}类型的注解
     * @throws ClassNotFoundException
     */
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        System.out.println("parseTypeAnnotation()-------------------");
        Class clazz = Class.forName(className);

        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations){
            if (annotation instanceof MethodInfo){
                MethodInfo methodInfo = (MethodInfo) annotation;
                System.out.println("name = "+methodInfo.name()+", id = "+methodInfo.id()
                        + ", gid = "+methodInfo.gid());
            }
        }
    }

    /**
     * 简单打印出{@link UserAnnotation}类中所使用到的成员变量注解(ElementType.FIELD),
     * 该方法只打印了{@link MethodInfo}类型的注解
     * @throws ClassNotFoundException
     */
    public static void parseFieldAnnotation() throws ClassNotFoundException {
        System.out.println("parseFieldAnnotation()-------------------");
        Class clazz = Class.forName(className);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            /*
             * 判断方法中是否有MethodInfo类型的注解
             */
            boolean hasAnnotation = field.isAnnotationPresent(MethodInfo.class);
            if (hasAnnotation){
                /*
                 * 根据注解类型返回方法中指定类型的注解
                 */
                MethodInfo methodInfo = field.getAnnotation(MethodInfo.class);
                System.out.println("name = "+methodInfo.name()+", id = "+methodInfo.id()
                        + ", gid = "+methodInfo.gid());
            }
        }
    }

    /**
     * 简单打印出UserAnnotation 类中所使用到的方法注解(ElementType.METHOD),
     * 该方法只打印了 {@link MethodInfo} 类型的注解
     * @throws ClassNotFoundException
     */
    public static void parseMethodAnnotation() throws ClassNotFoundException {
        System.out.println("parseMethodAnnotation()-------------------");
        Class clazz = Class.forName(className);
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            /*
             * 判断方法中是否有MethodInfo类型的注解
             */
            boolean hasAnnotation = method.isAnnotationPresent(MethodInfo.class);
            if (hasAnnotation){
                /*
                 * 根据注解类型返回方法中指定类型的注解
                 */
                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                System.out.println("name = "+methodInfo.name()+", id = "+methodInfo.id()
                        + ", gid = "+methodInfo.gid());
            }
        }
    }

    /**
     * 简单打印出UserAnnotation 类中所使用到的构造方法注解(ElementType.CONSTRUCTOR),
     * 该方法只打印了 {@link MethodInfo} 类型的注解
     * @throws ClassNotFoundException
     */
    public static void parseConstructAnnotation() throws ClassNotFoundException {
        System.out.println("parseConstructAnnotation()-------------------");
        Class clazz = Class.forName(className);
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors){
            /*
             * 判断方法中是否有MethodInfo类型的注解
             */
            boolean hasAnnotation = constructor.isAnnotationPresent(MethodInfo.class);
            if (hasAnnotation){
                /*
                 * 根据注解类型返回方法中指定类型的注解
                 */
                MethodInfo methodInfo = (MethodInfo) constructor.getAnnotation(MethodInfo.class);
                System.out.println("name = "+methodInfo.name()+", id = "+methodInfo.id()
                        + ", gid = "+methodInfo.gid());
            }
        }
    }
}
