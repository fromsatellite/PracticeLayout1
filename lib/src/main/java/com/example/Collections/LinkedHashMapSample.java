package com.example.Collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapSample {

    public static void testRemove(){
        LinkedHashMap<String, String> accessOrderedMap = new LinkedHashMap<String, String>(16, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
//                return super.removeEldestEntry(eldest);
                return size() > 3;
            }
        };

        accessOrderedMap.put("Project1", "one");
        accessOrderedMap.put("Project2", "two");
        accessOrderedMap.put("Project3", "three");
//        accessOrderedMap.forEach((k,v)->{
//            System.out.println(k+":"+v);
//        });
        for (Map.Entry<String, String> entry : accessOrderedMap.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        // 模拟访问
        accessOrderedMap.get("Project2");
        accessOrderedMap.get("Project2");
        accessOrderedMap.get("Project3");
        System.out.println("Iterate over should be not affected:");
        for (Map.Entry<String, String> entry : accessOrderedMap.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        // 触发删除
        accessOrderedMap.put("Project4", "four");
        System.out.println("Oldest entry should be removed:");
        for (Map.Entry<String, String> entry : accessOrderedMap.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
