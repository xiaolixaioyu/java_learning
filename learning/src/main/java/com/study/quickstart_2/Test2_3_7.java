package com.study.quickstart_2;

public class Test2_3_7 {
    public static void main(String[] args) {
        int []ns=new int[3];
        System.out.println(ns.length);
        for(int i=0;i<ns.length;i++){
            System.out.println(ns[i]);
        }
        String name[]=new String[4];
        String name1[]={"ysq","ljh"};
        System.out.println(name1.length);
        System.out.println(name1);
        for(int i=0;i<name1.length;i++){
            System.out.println(name1[i]);
        }
        name1[0]="ysq1";
        for(int i=0;i<name1.length;i++){
            System.out.println(name1[i]);
        }
    }
}
