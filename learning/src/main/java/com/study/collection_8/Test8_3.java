package com.study.collection_8;
//import com.study.oop_3.Person;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Person{
    String name;
    Person(String name){
        this.name=name;
    }
    Person(){

    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Person){//判断左边对象是不是右边这个类型的
            return Objects.equals(name,((Person)o).name);
        }else{
            return false;
        }
    }
}
class Person1 {
    String firstName;
    String lastName;
    int age;

    public Person1(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Person1) {
            return Objects.equals(((Person1) o).firstName, this.firstName) && Objects.equals(((Person1) o).lastName, this.lastName) && (((Person1) o).age) == this.age;
        } else {
            return false;
        }
    }
}

//引入person类
public class Test8_3 {
    public static void main(String[] args) {
        ArrayList <Person>list = new ArrayList<>();
        list.add(new Person("Bob"));
        list.add(new Person("Alice"));
        list.add(new Person("exo"));
        System.out.println("--------------------------不是内置类的list的contain是比较要重写equal==-----------------");
        System.out.println(list.contains(new Person("Bob")));
        System.out.println("---------------------------练习--------------------------------");
        List<Person1> list1 = List.of(
                new Person1("Xiao", "Ming", 18),
                new Person1("Xiao", "Hong", 25),
                new Person1("Bob", "Smith", 20)
        );
        boolean exist = list1.contains(new Person1("Bob", "Smith", 20));
        System.out.println(exist ? "测试成功!" : "测试失败!");
    }
}

