package com.study.oop_3;

import java.math.BigInteger;

/*
在Java中，由CPU原生提供的整形最大范围是64位long型整数。使用long型整数可以直接通过CPU指令进行计算，速度非常快
利用BigInterger 内部利用一个数组来模拟一个非常大的整数 表示任意大小的整数
 */
public class Test3_2_8 {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("12345678999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        System.out.println(bigInteger);
        float f = bigInteger.floatValue();
        System.out.println(f);//超过了浮点数的最大范围
        long l = bigInteger.longValueExact();
        System.out.println("超出long所能表示的范围 会抛出异常");

    }
}
