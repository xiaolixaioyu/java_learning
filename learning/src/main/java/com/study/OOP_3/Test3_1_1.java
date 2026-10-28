package com.study.OOP_3;
class Person {
    private String name;
    private int age;
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
    }
    public Person() {

    }
}
public class Test3_1_1 {
    public static void main(String[] args) {
            Person ming = new Person();
            ming.setName("小明");
            ming.setAge(12);
            System.out.println(ming.getAge());
    }


}
