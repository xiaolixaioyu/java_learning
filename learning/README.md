# Java 学习工程（learning）

本工程就是你在 IDEA 中打开的 `learning` 项目，已配置好 **JDK 21 + Maven** 工具链。

## 目录结构
```
learning/
├── pom.xml                      # Maven 配置（JDK 21，已接阿里云镜像）
├── src/
│   ├── main/java/com/study/     # 写代码的地方（Main.java 是入口）
│   └── test/java/com/study/     # 写单元测试的地方
└── .gitignore
```

## 在 IDEA 里使用
1. 打开 IDEA → Open → 选择 `F:\实习\learning`（已打开则忽略）。
2. 若右上角提示 "Maven projects need to be imported"，点 **Load Maven Project**。
3. 确认 SDK：File → Project Structure → SDK 选 `F:\实习\jdk-21`（显示 21.x）。
4. 运行：右键 `Main.java` → Run 'Main.main()'。

## 用终端构建/测试（已验证可用）
```bash
# 设置 JDK（一次性，已写进系统环境变量则不用每次设）
set JAVA_HOME=F:\实习\jdk-21

# 进入工程
cd F:\实习\learning

# 编译 + 跑测试
F:\实习\apache-maven-3.9.9\bin\mvn clean test

# 只编译
F:\实习\apache-maven-3.9.9\bin\mvn clean compile
```

## 学习约定
- 阶段1（Java SE）的每个知识点，在 `com.study` 下新建一个类练习，并配一个测试。
- 算法题用 Java 写，可放到 `src/main/java/com/study/algorithm/`。
- 依赖下载走阿里云镜像（见 `F:\实习\apache-maven-3.9.9\conf\settings.xml`），无需翻墙。
