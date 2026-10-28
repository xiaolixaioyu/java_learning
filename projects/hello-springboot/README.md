# hello-springboot

Java 后端开发环境的**验证项目**（寒假实习冲刺起点）。

## 技术栈
- Java 21（JDK 21 LTS，Temurin）
- Maven 3.9.9
- Spring Boot 3.4.5（spring-boot-starter-web）
- 开发工具：IntelliJ IDEA 2026.2 社区版

## 目录约定
所有环境（JDK / Maven / IDEA）都装在 `F:/实习` 下，不占用 C 盘：
- JDK：`F:/实习/jdk-21`
- Maven：`F:/实习/apache-maven-3.9.9`
- IDEA：`F:/实习/IntelliJ IDEA 2026.2`

## 运行方式
```bash
# 在 F:/实习/projects/hello-springboot 目录下
mvn spring-boot:run
# 或先打包再运行
mvn clean package
java -jar target/hello-springboot-0.0.1-SNAPSHOT.jar
```

启动后访问：
- http://localhost:8080/hello  →  `Hello, world! 开发环境配置成功`
- http://localhost:8080/health →  `OK`

## 说明
这是环境验证用的极简骨架，后续阶段 3 / 阶段 4 的真实项目会在它基础上扩展（加 MyBatis、MySQL、JWT、AI 模块等）。
