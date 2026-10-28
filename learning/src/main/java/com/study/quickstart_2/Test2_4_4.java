package com.study.quickstart_2;

public class Test2_4_4 {
    public static void main(String[] args) {
        int sum = 0;
        int m = 20;
        int n = 100;
        // 使用while计算M+...+N:
        int i=m;
        while (i<=n) {
            sum+=i;
            i++;
        }
        System.out.println(sum);
    }
}
