package com.study.oop_3;

public class Test3_2_2 {
    //请使用StringBuilder构造一个INSERT语句
    public static void main(String[] args) {
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String insert = buildInsertSql(table, fields);
        System.out.println(insert);
        String s = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
        System.out.println(s);
        System.out.println(s.equals(insert) ? "测试成功" : "测试失败");
    }

    static String buildInsertSql(String table, String[] fields) {
        /*
        StringBuilder是可变对象，用来高效拼接字符串；

        StringBuilder可以支持链式操作，实现链式操作的关键是返回实例本身；

        StringBuffer是StringBuilder的线程安全版本，现在很少使用。

         */
        // TODO:
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(table).append(" (");
        for(String field : fields){
            sql.append(field).append(",").append(" ");
        }
        sql.delete(sql.length() - 2, sql.length());
        sql.append(") VALUES (?, ?, ?)");
        return sql.toString();
    }
}
