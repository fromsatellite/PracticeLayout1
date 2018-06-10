package com.example.type;

import com.example.reflect.Person;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;

/**
 * Created by leador_yang on 2018/5/16.
 */

public class TestHelper {

    public static void testTypeVariable(){
        try {
            System.out.println("---------------testTypeVariable----------");
            Field[] fields = WildcardTypeBean.class.getDeclaredFields();
            int len = fields.length;
            for (int i = 0;i < len;i++){
                Field field = fields[i];
                field.setAccessible(true);
                String fieldName = field.getName();
                System.out.print(fieldName+"----------");
                Type type = field.getGenericType();
                if (!(type instanceof TypeVariable)){
                    System.out.println("");
                    continue;
                }
                TypeVariable typeVariable = (TypeVariable) type;
                System.out.print("typeVariable : "+typeVariable+",name = "+typeVariable.getName()+
                        ", Declaration = " + typeVariable.getGenericDeclaration());
            }
            System.out.println();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    public static void testGenericArrayType(){
        System.out.println("---------------testGenericArrayType----------");
        Method method = WildcardTypeBean.class.getDeclaredMethods()[0];
        System.out.println("method = "+method);
        Type[] types = method.getGenericParameterTypes();
        for (Type type: types){
            System.out.println(type instanceof GenericArrayType);
        }
    }

    public static void testWildCardType(){
        try {
            System.out.println("------------testWildCardType----------");
            Field[] fields = WildcardTypeBean.class.getDeclaredFields();
            int len = fields.length;
            for (int i = 0;i < len;i++){
                Field field = fields[i];
                field.setAccessible(true);
                String fieldName = field.getName();
                System.out.print(fieldName+"----------");
                Type type = field.getGenericType();
                if (!(type instanceof ParameterizedType)){
                    System.out.println("");
                    continue;
                }
                ParameterizedType parameterizedType = (ParameterizedType) type;
                System.out.print("parameterizedType : "+parameterizedType+", rawType = "+parameterizedType.getRawType());

                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type paramType : actualTypeArguments){
                    if (!(paramType instanceof WildcardType)){
                        System.out.println(", ");
                        continue;
                    }
                    WildcardType wildcardType = (WildcardType) paramType;
                    System.out.print(", wildcardType : "+wildcardType);
                    Type[] lowerTypes = wildcardType.getLowerBounds();
                    if (lowerTypes != null && lowerTypes.length > 0){
                        System.out.print(", lowerTypes = "+lowerTypes[0]);
                    } else {
                        System.out.print(", lowerTypes = null");
                    }
                    Type[] upperTypes = wildcardType.getUpperBounds();
                    if (upperTypes != null && upperTypes.length > 0){
                        System.out.print(", upperTypes = "+upperTypes[0]);
                    } else {
                        System.out.print(", upperTypes = "+upperTypes);
                    }
                }
                System.out.println();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void printConstructor(String className){
        try {
            System.out.println("----------printConstructor : "+className);
            Class<?> aClass = Class.forName(className);
            Constructor<?>[] constructors = aClass.getConstructors();
            for (Constructor<?> constructor : constructors){
                System.out.println(constructor);
            }
            Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
            for (Constructor<?> dConstructor : declaredConstructors){
                System.out.println(dConstructor);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Constructor getConstructor(String className, Class<?>... clzs){
        System.out.println("----------printConstructor : "+className);
        Constructor<?> declaredConstructor = null;
        try {
            Class<?> aClass = Class.forName(className);
            declaredConstructor = aClass.getDeclaredConstructor(clzs);
            declaredConstructor.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return declaredConstructor;
        }

    }

    public static Person getPerson(String country, Integer age){
        Person beauty = null;
        Constructor constructor = getConstructor(Person.class.getName(), String.class, Integer.class);
        try {
            Object object = constructor.newInstance(country, age);
            beauty = (Person) object;
            System.out.println("getPerson() = "+beauty.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            return beauty;
        }
    }

    public static void printField(String className){
        System.out.println("----------printField : "+className);
        try {
            Class<?> aClass = Class.forName(className);
            Field[] fields = aClass.getFields();
            for (Field field : fields){
                System.out.println(field);
            }
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields){
                System.out.println(declaredField);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Field getField(String className, String fieldName){
        Field declaredField = null;
        try {
            Class<?> aClass = Class.forName(className);
            declaredField = aClass.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            return declaredField;
        }
    }

    public static void getAge(Person person){
        String className = person.getClass().getName();
        System.out.println("className = " + className);
        Field field = getField(className, "age");
        try {
            Integer integer = (Integer) field.get(person);
            System.out.println("integer = "+integer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void printMethod(String className){
        System.out.println("----------printMethod : "+className);
        try {
            Class<?> aCass = Class.forName(className);
            Method[] declaredMethods = aCass.getDeclaredMethods();
            for (Method method : declaredMethods){
                System.out.println(method);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Method getMethod(String className, String methodName, Class<?>... clzs){
        System.out.println("----------getMethod : "+className);
        Method declaredMethod = null;
        try {
            Class<?> aCass = Class.forName(className);
            declaredMethod = aCass.getDeclaredMethod(methodName, clzs);
            declaredMethod.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return declaredMethod;
        }
    }

    public static void changeCountry(Person person, String newCountry){
        Method method = getMethod(person.getClass().getName(), "setCountry", String.class);
        try {
            method.invoke(person, newCountry);
            System.out.println("country = " + person.country);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用反射操作数组
     * 1 利用反射修改数组中的元素
     * 2 利用反射获取数组中的每个元素
     */
    public static void testArrayClass(){
        System.out.println("----------testArrayClass : ");
        String[] strArray = new String[]{"1", "2", "3"};
        Array.set(strArray, 0, "5");
        Class clazz = strArray.getClass();
        if (clazz.isArray()){
            int len = Array.getLength(strArray);
            for (int i = 0;i < len;i++){
                Object object = Array.get(strArray, i);
                System.out.println("----> object = "+object+", className = "+ object.getClass().getName());
            }
        }
    }

    public static void getGenericType() {
        System.out.println("----------getGenericType : ");
        try {
//            HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
            Method method = Person.class.getDeclaredMethod("getGenericHelper", HashMap.class, String.class);
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            if (null == genericParameterTypes || genericParameterTypes.length < 1) {
                return;
            }

            for (Type genericParameterType : genericParameterTypes){
                System.out.println("----> genericParameterType=" + genericParameterType);
                if (genericParameterType instanceof ParameterizedType){
                    ParameterizedType parameterizedType=(ParameterizedType)genericParameterType;
                    Type rawType = parameterizedType.getRawType();
                    System.out.println("----> rawType=" + rawType);
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    if (actualTypeArguments == genericParameterTypes || actualTypeArguments.length<1) {
                        return;
                    }

                    for (int i = 0; i < actualTypeArguments.length; i++) {
                        Type type = actualTypeArguments[i];
                        System.out.println("----> type=" + type);
                    }
                } else if (genericParameterType == String.class){
                    System.out.println("----> rawType=" + genericParameterType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
