package com.study.quickstart_2;

import java.util.Scanner;

public class Test2_4_1 {
    public static void main(String[] args) {
        // 输入上次考试成绩 本次考试成绩 输出提高的百分比

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入上次考试成绩：");
        int lastpoint=sc.nextInt();
        System.out.println("请输入这次考试成绩：");
        int currentpoint=sc.nextInt();
        double score=((double)(currentpoint-lastpoint)/lastpoint)*100;//得保证其中有一个是强转之后再除
        System.out.printf("成绩提高百分比 :%.2f%%\n",score);

    }
}
