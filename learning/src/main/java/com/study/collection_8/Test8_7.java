package com.study.collection_8;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//对key来进行排序
//在使用treemap的时候，放入的key必须实现 Comparable 接口，String Integer这些类已经实现
public class Test8_7 {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("orange", 1);
        map.put("apple", 2);
        map.put("pear", 3);
        for (String key : map.keySet()) {
            System.out.println(key);
        }
        Map<Student, Integer> map2 = new TreeMap<>(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                if(o1.score==o2.score){
                    return 0;//两个值相等的时候必须返回0
                }
                return o1.score>o2.score?-1:1;
            }
        });//实现那个Comparator的接口
        map2.put(new Student("Tom", 77), 1);
        map2.put(new Student("Bob", 66), 2);
        map2.put(new Student("Lily", 99), 3);
        for (Student key : map2.keySet()) {
            System.out.println(key.getName());
        }
        System.out.println(map2.get(new Student("Bob", 66)));
    }
}
