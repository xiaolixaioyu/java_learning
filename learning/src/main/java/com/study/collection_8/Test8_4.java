package com.study.collection_8;

import java.util.*;

//使用map
class Student{
    String name;
    int score;
    Student(String name,int score){
        this.name=name;
        this.score=score;
    }
    String getName(){
        return this.name;
    }
}
class Students{
    //学生对象的集合采用map
    //用Hashmap来做高速缓存 优先查缓存  缓存没有 才去遍历 list数据库/原始列表 并把结果回填缓存 减少开销
    List<Student> list=new ArrayList<>();//原始数据  所有学生对象
    Map<String,Integer> map=new HashMap<>();
    Students(List<Student> list){
        this.list=list;

    }
    int getScore(String name){
        //优先去缓存中查找
        Integer score=map.get(name);
        if(Objects.isNull(score)){
            //缓存没有命中去list中查找
            for(Student s:list){
                if(s.name.equals(name)){
                    score=s.score;
                }
            }
        }
        return score == null ? -1 : score;
    }


}
public class Test8_4 {
    public static void main(String[] args) {
        List<Student> list = List.of(
                new Student("Bob", 78),
                new Student("Alice", 85),
                new Student("Brush", 66),
                new Student("Newton", 99));
        var holder = new Students(list);
        System.out.println(holder.getScore("Bob") == 78 ? "测试成功!" : "测试失败!");
        System.out.println(holder.getScore("Alice") == 85 ? "测试成功!" : "测试失败!");
        System.out.println(holder.getScore("Tom") == -1 ? "测试成功!" : "测试失败!");
    }

}
