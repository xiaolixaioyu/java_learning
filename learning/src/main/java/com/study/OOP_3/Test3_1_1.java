package com.study.oop_3;
import com.study.quickstart_2.Test2_4_2;
class Person {
    private String name;
    private int age;
    private static int count=0;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        count++;
    }
    public Person() {
        count++;
    }
    public static int getCount(){
        return count;//统计实例创建的个数
    }

}
public class Test3_1_1 {
    public static void main(String[] args) {
            Person ming = new Person();
            ming.setName("小明");
            ming.setAge(12);
            System.out.println(ming.getAge());
            System.out.println(Person.getCount());
    }


}
