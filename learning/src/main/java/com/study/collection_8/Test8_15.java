package com.study.collection_8;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test8_15 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("apple");
        list.add("pear");
        list.add("orange");
        System.out.println("---------未排序之前输出--------------");
        for(String s:list){
            System.out.println(s);
        }
        System.out.println("---------排序之后的输出--------------");
        Collections.sort(list);
        for(String s:list){
            System.out.println(s);
        }
        System.out.println("-----------------洗牌之前的输出-------------");
        System.out.println(list);
        System.out.println("-----------------洗牌之后的输出-------------");
        Collections.shuffle(list);
        System.out.println(list);
    }


}
