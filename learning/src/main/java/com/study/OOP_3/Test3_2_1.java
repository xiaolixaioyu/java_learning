package com.study.oop_3;

import java.util.Arrays;
import java.util.Locale;

class Score {
    private int[] scores;
    public Score(int[] scores) {
//        this.scores = scores;//进行修复避免外面数组的更改影响到当前对象的int[]字段
          this.scores=Arrays.copyOf(scores, scores.length);
    }

    public void printScores() {
        System.out.println(Arrays.toString(scores));
    }
}
public class Test3_2_1 {
    public static void main(String[] args) {
    //1. Java中字符串不可变  是通过final来实现的
        //字符串并没有变 变量s的指向变了
        String s="hello";
        System.out.println(s);
        s=s.toUpperCase();
        System.out.println(s);
        String s1="world";
        String s2=s1.toUpperCase();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("---------------------------------------------");
    //去除首尾空白字符串
        String s3="    \t \r  \n  hello \n      ";
        System.out.print(s3);
        System.out.println("trim之后的效果");
        s3=s3.trim();
        System.out.println(s3);
        System.out.println("---------------------------------------------");
    //替换子串
        String s4="hello";
        System.out.println("替换前"+s4);
        s4=s4.replace('l','y');
        System.out.println("替换后"+s4);
        System.out.println("---------------------------------------------");
        String s5="A,B,C,D";
        String []arr=s5.split(",");//返回的是String的数组
        for(String str:arr){
            System.out.println(str);
        }
        System.out.println("---------------------------------------------");
        //类型转换
        /*
        观察两次输出，由于Score内部直接引用了外部传入的int[]数组，
        这会造成外部代码对int[]数组的修改，影响到Score类的字段。如果外部代码不可信，这就会造成安全隐患。
        请修复Score的构造方法，使得外部代码对数组的修改不影响Score实例的int[]字段。
         */
        int[] scores = new int[] { 88, 77, 51, 66 };
        Score s6 = new Score(scores);
        s6.printScores();
        scores[2] = 99;
        s6.printScores();

    }
}
