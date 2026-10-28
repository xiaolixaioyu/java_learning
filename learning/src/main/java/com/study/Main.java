package com.study;

import java.util.List;

/**
 * Java 后端学习工程入口。
 * 阶段1（Java SE）的练习：在 src/main/java/com/study 下新增类即可。
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Java 学习工程已启动 ===");
        System.out.println("JDK 版本: " + System.getProperty("java.version"));
        System.out.println("项目路径: " + System.getProperty("user.dir"));

        // 现代 Java 语法示例（JDK 21 的 var / Stream / 不可变集合）
        List<String> stack = List.of("Java", "Spring Boot", "Maven");
        stack.stream()
             .map(s -> "👉 " + s)
             .forEach(System.out::println);

        System.out.println("1111");
    }
}
