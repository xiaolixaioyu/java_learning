package com.study.oop_3;


class Student extends Person {
    protected int score;

    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    public int getScore() { return score; }
}
//定义PrimaryStudent，从Student继承，并新增一个grade字段
class PrimaryStudent extends Student {
    // TODO
    protected int grade;
    public PrimaryStudent(String name, int age,int score,int grade ){
        super(name,age,score);
        this.grade = grade;
    }
}
public class Test3_1_4 {
    public static void main(String[] args) {
        Person p = new Person("小明", 12);
        Student s = new Student("小红", 20, 99);
        // TODO: 定义PrimaryStudent，从Student继承，新增grade字段:
        Student ps = new PrimaryStudent("小军", 9, 100, 5);
        System.out.println(ps.getScore());
    }
}
