package com.study.collection_8;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
PriorityQueue和Queue的区别在于，它的出队顺序与元素的优先级有关，对PriorityQueue调用remove()或poll()方法，返回的总是优先级最高的元素。
根据优先级来出队
，放入PriorityQueue的元素，必须实现Comparable接口，PriorityQueue会根据元素的排序顺序决定出队的优先级。

如果我们要放入的元素并没有实现Comparable接口怎么办？PriorityQueue允许我们提供一个Comparator对象来判断两个元素的顺序。
我们以银行排队业务为例，实现一个PriorityQueue：
 */
public class Test8_11 {
    public static void main(String[] args) {
        Queue<User> queue=new PriorityQueue<>(new UserComparator());
        queue.add(new User("Bob","A1"));
        queue.add(new User("Alice","A2"));
        queue.add(new User("Bddd","V1"));
        System.out.println("输出最高优先级的User:");
        System.out.println(queue.poll());
    }
}
class  UserComparator implements Comparator<User> {
    //实现这个比较的接口
    //最小堆  越靠前 返回-1
    public int compare(User o1, User o2) {
        if(o1.number.charAt(0)==o2.number.charAt(0)){
            return o1.number.compareTo(o2.number);
        } else if(o1.number.charAt(0)=='V'){
             return -1;
        }else{
            return 1;
        }
    }
}
class User{
    public final String name;
    public final String number;
    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }
    @Override
    public String toString() {
        return name+":"+number;
    }
}

