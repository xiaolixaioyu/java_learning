package com.study.quickstart_2;

import java.util.Random;
import java.util.Scanner;

public class Test2_4_3 {
    public static void main(String[] args) {
        /*
        使用switch实现一个简单的石头、剪子、布游戏
         */
        Scanner sc=new Scanner(System.in);
        Random rand=new Random();
        System.out.println("欢迎来到石头剪刀布游戏");
        System.out.println("0: 石头   1：剪刀   2：布  -1：退出");
        int user=sc.nextInt();
        if(user==-1){
            System.out.println("退出游戏");
            return;
        }else if(user<0||user>2){
            System.out.println("输入错误");
            return;
        }
        int computer=rand.nextInt(3);
        String []name={"石头","剪刀","布"};
        System.out.println("你："+name[user]+",电脑："+name[computer]);
        String s;
        switch(user){
            case 0:  s=(computer==1 ? "你赢" :(computer==2?"你输":"平局")); break; //s将这个算出来的字符串存储起来
            case 1:  s=(computer==2 ? "你赢" :(computer==0?"你输":"平局")); break;
            case 2:  s=computer==0 ? "你赢" :(computer==1?"你输":"平局"); break;
            default: s="输入错误"; break;
        }
        System.out.println(s);
    }
}
