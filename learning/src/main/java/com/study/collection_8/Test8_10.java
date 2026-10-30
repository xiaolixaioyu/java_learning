package com.study.collection_8;

import java.util.LinkedList;
import java.util.Queue;

/*
队列Queue实现了一个先进先出（FIFO）的数据结构：

通过add()/offer()方法将元素添加到队尾；
通过remove()/poll()从队首获取元素并删除；
通过element()/peek()从队首获取元素但不删除。
 */
public class Test8_10 {
    public static void main(String[] args) {
        Queue<String> queue=new LinkedList<>();
        queue.add("apple");
        queue.add("orange");
        queue.add("pear");
        System.out.println("返回队首元素 而且并不删除");
        System.out.println(queue.peek());
        for(String s: queue){
            System.out.println(s);
        }
        System.out.println("返回队首元素 并且删除");
        System.out.println(queue.poll());
        for(String s: queue){
            System.out.println(s);
        }
    }
}
