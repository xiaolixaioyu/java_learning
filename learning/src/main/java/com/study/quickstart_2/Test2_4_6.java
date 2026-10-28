package com.study.quickstart_2;

public class Test2_4_6 {
    public static void main(String[] args) {
        int[] ns = { 1, 4, 9, 16, 25 };
        for (int i=4; i>=0; i--) {
            System.out.println(ns[i]);
        }
        int sum = 0;
        //采用 for each循环
        for (int i : ns) {
            // TODO
            sum += i;
        }
        System.out.println(sum); // 55

        double pi = 0;
        int i=1;
        int count=1;//做计数器
        for (;i<=10000000;i=i+2){
            // TODO
            if(count%2==1){//奇数
                pi+=1.0/(double)i;
            }else {
                pi-= 1.0/ (double) i;
            }
            count++;
//            System.out.println(pi);
        }
        pi=pi*4;
        System.out.println(pi);
    }
}
