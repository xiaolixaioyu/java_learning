package com.study.collection_8;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class Test8_6 {
    public static void main(String[] args) {
        //使用枚举map
        Map<DayOfWeek, String > map = new HashMap<>();
        map.put(DayOfWeek.MONDAY,"周一");
        map.put(DayOfWeek.TUESDAY,"周二");
        map.put(DayOfWeek.TUESDAY,"周三");
        map.put(DayOfWeek.TUESDAY,"周四");
        map.put(DayOfWeek.FRIDAY,"周五");
        map.put(DayOfWeek.SATURDAY,"周六");
        map.put(DayOfWeek.SUNDAY,"周日");

        System.out.println(map.get(DayOfWeek.MONDAY));
        System.out.println("时间戳");
        long ls=System.currentTimeMillis();
        Instant instant = Instant.now();
//        System.out.println(instant);
        //要转成东八区
        LocalDateTime ldt=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DayOfWeek day=ldt.getDayOfWeek();
        System.out.println(map.get(day));

    }

}
