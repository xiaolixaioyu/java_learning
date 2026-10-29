package com.study.oop_3;

import java.util.StringJoiner;

public class Test3_2_3 {
    //利用StringJoiner 用分隔符拼数组
    public static void main(String[] args) {
        String[] Name = {"Bob", "Alice", "Grace"};
        var sj = new StringJoiner(",", "Hello ", "!");//造一个专门用来拼接字符串的盒子 指定开头和结尾
        //string之间用,隔开
        for (String field : Name) {
            sj.add(field);
        }
        System.out.println(sj.toString());
        String[] fields = {"name", "position", "salary"};
        String table = "employee";
        String select = buildSelectSql(table, fields);
        System.out.println(select);
        System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");
    }

    static String buildSelectSql(String table, String[] fields) {
        // TODO:
//            请使用StringJoiner构造一个SELECT语句：
        var sql1 = new StringJoiner(", ", "SELECT ", "");
        for (String field : fields) {
            sql1.add(field);
        }
        //将StringJoin 转为字符串之后在进行手动拼接
        return sql1.toString()+" FROM"+" "+table;
    }
}
