package com.study.collection_8;

import java.util.*;

/*
我们知道，Map用于存储key-value的映射，对于充当key的对象，是不能重复的，并且，不但需要正确覆写equals()方法，还要正确覆写hashCode()方法。

如果我们只需要存储不重复的key，并不需要存储映射的value，那么就可以使用Set。

Set用于存储不重复的元素集合，它主要提供以下几个方法：

将元素添加进Set<E>：boolean add(E e)
将元素从Set<E>删除：boolean remove(Object e)
判断是否包含元素：boolean contains(Object e)
 */
class Message{
    public final int sequence;
    public final String message;
    //不可变的好处是：线程安全 可以当做key不会变 简单安全防止无意篡改
    public Message(int sequence, String message) {
        this.sequence = sequence;
        this.message = message;
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof Message){
            return Objects.equals((message), ((Message)o).message)&&((Message) o).sequence == sequence;
        }else{
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(sequence, message);
    }
}
public class Test8_9 {
    /*
    在聊天软件中，发送方发送消息时，
    遇到网络超时后就会自动重发，
    因此，接收方可能会收到重复的消息，在显示给用户看的时候，需要首先去重。请练习使用Set去除重复的消息：
     */
    public static void main(String[] args) {
        Set<Student> set = new HashSet<Student>();
        set.add(new Student("Bob", 66));
        set.add(new Student("Lily", 99));
        System.out.println("该Set集合的大小：");
        System.out.println(set.size());
        System.out.println("该Set是否包含这个元素：");
        System.out.println(set.contains(new Student("Bob", 66)));
        System.out.println("---------------------------------------------------------------");
        List<Message> list = new ArrayList<Message>();
        list.add(new Message(1,"Hello World!"));
        list.add(new Message(1,"Hello World!"));
        list.add(new Message(1,"Hello World!"));
        list.add(new Message(2,"Bye"));
        for(Message m : list){
            System.out.println(m.sequence+" "+m.message);

        }
       list= process(list);//注意接收生成的全新的对象
        System.out.println("去除重复的消息");
        for(Message m : list){
            System.out.println(m.sequence+" "+m.message);

        }

    }
    static List<Message> process(List<Message> list) {
        // TODO: 按sequence去除重复消息
        Set<Message> set = new LinkedHashSet<Message>();
        for(Message m : list){
            set.add(m);
        }
        return new ArrayList<Message>(set);
    }
}
