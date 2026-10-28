package com.study;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 环境自检测试：跑通说明 JDK21 + Maven + JUnit 全链路没问题。
 */
class MainTest {

    @Test
    void environmentWorks() {
        assertNotNull(System.getProperty("java.version"), "JDK 版本不应为空");
        System.out.println("✅ JUnit 测试通过，运行在 JDK " + System.getProperty("java.version"));
    }
}
