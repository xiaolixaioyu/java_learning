package com.study.oop_3;

import java.math.BigDecimal;

public class Test3_2_9 {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("154545.8888800");
        BigDecimal b = new BigDecimal("5.320");
        System.out.println("----------------------------------");
        System.out.println(a.scale());
        System.out.println(a);
        System.out.println("----------------------------------");
        //格式化一个相等但是去掉了末尾0的BigDecimal
        System.out.println(a.stripTrailingZeros());
        System.out.println("-----------------------------------");
        System.out.println(a.setScale(2, BigDecimal.ROUND_HALF_UP));//设置小数点后位数进行四舍五入截断
        System.out.println("------------------------------------");
        //做除法的同时求余数
        BigDecimal []arr =a.divideAndRemainder(b);
        System.out.println("商为："+arr[0]);
        System.out.println("余数为："+arr[1]);
    }
}
