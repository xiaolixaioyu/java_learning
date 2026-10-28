package com.study.quickstart_2;

import java.util.Arrays;

public class Test2_5_2 {
    public static void main(String[] args) {
        int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
        // 排序前:
        System.out.println(Arrays.toString(ns));
        // TODO:
        //要求降序排序
        for(int i=0;i<ns.length;i++){
            for(int j=i;j<ns.length;j++){
                if(ns[i]<ns[j]){
                    int tmp=ns[j];
                    ns[j]=ns[i];
                    ns[i]=tmp;
                }
            }
        }
        // 排序后:
        System.out.println(Arrays.toString(ns));
        if (Arrays.toString(ns).equals("[96, 89, 73, 65, 50, 36, 28, 18, 12, 8]")) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }
    }
}
