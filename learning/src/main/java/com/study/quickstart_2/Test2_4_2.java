package com.study.quickstart_2;

import java.util.Scanner;

public class Test2_4_2 {
    public static void main(String[] args) {
        /*
        请用if ... else编写一个程序，用于计算体质指数BMI，并打印结果。
        BMI = 体重(kg) / 身高(m)的平方
        BMI结果：
        过轻：低于18.5
        正常：18.5 ~ 25
        过重：25 ~ 28
        肥胖：28 ~ 32
        非常肥胖：高于32
         */
        double Weight,Height,BMI;
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入身高：");
        Height=sc.nextDouble();
        System.out.println("请输入体重：");
        Weight=sc.nextDouble();
        BMI=Weight/(Height*Height);
        if(BMI<18.5){
            System.out.println("过轻");
        }else if(BMI>=18.5 && BMI<25){
            System.out.println("正常");
        }else if(BMI>=25 && BMI<30){
            System.out.println("过重");
        } else if (BMI>=28&&BMI<32) {
            System.out.println("肥胖");
        }else{
            System.out.println("非常肥胖");
        }
    }
}
