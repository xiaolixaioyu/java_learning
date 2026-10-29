package com.study.collection_8;
import java.util.ArrayList;
import java.util.List;
/*
List 是一种有序表 按照放入元素的先后顺序存放 每个元素都可以通过索引确定自己的位置
ArrayLsit LinkedList
数组list
链表list

 */
public class Test8_2 {
    public static void main(String[] args) {
        // 构造从start到end的序列：
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        // 随机删除List中的一个元素:
        int removed = list.remove((int) (Math.random() * list.size()));
        int found = findMissingNumber(start, end, list);//下标是随机的，进行删除
        System.out.println(list.toString());
        System.out.println("missing number: " + found);
        System.out.println(removed == found ? "测试成功" : "测试失败");
    }

    static int findMissingNumber(int start, int end, List<Integer> list) {
        //给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字
        for (int i = start; i <= end; i++) {
            if (list.contains(i)) {

            }else{
                return i;
            }
        }
        return -1;//如果没有找到删除元素的话
    }
}

