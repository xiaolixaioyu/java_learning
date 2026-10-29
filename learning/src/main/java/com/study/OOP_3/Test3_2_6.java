package com.study.oop_3;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

//枚举类
public class Test3_2_6 {
    public static void main(String[] args) {
        Weekday day;
        System.out.println("请输入星期：SUN/MON/TUE/WED/THU/FRI/SAT");
        Scanner sc = new Scanner(System.in);
        day = Weekday.valueOf(sc.next());
        if(day==Weekday.SUN||day==Weekday.SAT){
            System.out.println("work at home");
        }else{
            System.out.println("work at office");
        }
        System.out.println("----------------------------------------------------------");
        long startTime = System.currentTimeMillis();//拿到当前时间戳
        Instant startInstant = Instant.ofEpochMilli(startTime);//UTC标准时间点 世界标准时间 0时区
        LocalDate startDate = startInstant.atZone(ZoneId.systemDefault()).toLocalDate();//获取电脑的默认时区并且扔掉时 分 秒 保留日期
        DayOfWeek  startDayOfWeek = startDate.getDayOfWeek();//用java自带的星期枚举类从日期对象中读出今天是星期几
        Weekday1 day1 =switch (startDayOfWeek){//左边变量接受switch返回的结果
            case MONDAY -> Weekday1.MON;
            case TUESDAY -> Weekday1.TUE;
            case WEDNESDAY -> Weekday1.WED;
            case THURSDAY -> Weekday1.THU;
            case FRIDAY -> Weekday1.FRI;
            case SATURDAY -> Weekday1.SAT;
            case SUNDAY -> Weekday1.SUN;
        };
        System.out.println(day1.toString());
    }
}
enum Weekday{
    SUN,MON,TUE,WED,THU,FRI,SAT;
}
enum Weekday1{//先写枚举常量、在写成员变量、构造方法、方法
    MON(1,"星期一"),
    TUE(2,"星期二"),
    WED(3,"星期三"),
    THU(4,"星期四"),
    FRI(5,"星期五"),
    SAT(6,"星期六"),
    SUN(7,"星期日");

    private final int dayvalue;
    private final String chinese;
    private Weekday1(int dayvalue,String chinese){
        this.dayvalue = dayvalue;
        this.chinese= chinese;
    }
    @Override //重写方法
    public String toString() {
        return this.chinese;
    }
    //简化了new对象


}
