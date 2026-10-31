package com.study.collection_8;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//集合类用于实现iterable接口，返回iterator对象
//泛型类 类定义用<T> 预留类型 一份模版代码可以适配多种类型
class ReverseList<T> implements Iterable<T> {//实现Iterable 这个接口这个类的对象可以用for each循环
    private List<T> list=new ArrayList<>();//成员变量 真正用来存储数据的地方
    public void add(T t){
        list.add(t);
    }
    @Override
    public Iterator<T> iterator(){//返回一个迭代器
        return new ReverseIterator(list.size());
    }
    //用内部类来返回一个Iterator对象
    class ReverseIterator implements Iterator<T>{
        int index;
        public ReverseIterator(int index){
            this.index=index;
        }
        @Override
        public boolean hasNext() {
            return index>0;
        }
        @Override
        public T next() {
            //取对应下标的元素需要
            index--;
            return ReverseList.this.list.get(index);
        }
    }

}
public class Test8_14 {
    public static void main(String[] args) {

        ReverseList<String> rlist = new ReverseList<>();
        rlist.add("Apple");
        rlist.add("Orange");
        rlist.add("Pear");
        for (String s : rlist) {
            System.out.println(s);
        }
    }



}
