# Java 后端学习路线（寒假实习冲刺版）

> **目标**：2027 寒假实习（Java 后端开发岗）  
> **周期**：约 10 周（2026-10 底 → 2027-01 中）  
> **主线**：Java 后端 + vibe coding（AI 辅助编码）加速  
> **方法**：每个阶段用 AI 工具（Cursor / 通义灵码 / Claude Code / WorkBuddy）边学边问、生成练习、解释报错

---

## 一、总览路线图

| 阶段   | 时间      | 核心内容                       | 产出         |
| ---- | ------- | -------------------------- | ---------- |
| 阶段 0 | 2-3 天   | 环境 + vibe coding 工具        | 可运行开发环境    |
| 阶段 1 | 第 1-3 周 | Java SE 核心 + 每日算法          | 夯实语言基础     |
| 阶段 2 | 第 4-5 周 | MySQL + Redis + 工具链        | 数据库与工程化    |
| 阶段 3 | 第 6-7 周 | Spring Boot + MyBatis + 八股 | 能写 CRUD 接口 |
| 阶段 4 | 第 8-9 周 | 完整项目实战（含 AI 差异化）           | GitHub 作品集 |
| 阶段 5 | 第 10 周+ | 简历 + 投递 + 模拟面试             | 拿 offer    |

---

## 二、阶段 0：环境与工具（第 0 周，2-3 天）

- [ ] 安装 **JDK 21（LTS）**、**IntelliJ IDEA 2026.2 社区版**（均由本机助手装到 `F:/实习` 下）
- [ ] 安装 **Maven 3.9.9**、**Git**（Git 已装，配好 GitHub 账号）
- [ ] MySQL / Redis / Docker **推迟到阶段 2** 再安装并学习，先不占时间
- [ ] 选一个 vibe coding 工具并跑通：让 AI 解释一段报错、生成 3 道练习题
- [ ] 建好本仓库 `F:/实习`，后续项目/笔记都放这里

> 📚 本阶段文档：IntelliJ IDEA 新手指南 — <https://www.jetbrains.com/idea/learn/（环境已由助手装好，你只学怎么用> IDEA 写代码）

---

## 三、阶段 1：Java SE 核心（第 1-3 周）

**目标**：能手写 Java 基础代码，应对笔试与面试八股。

- [ ] 语法基础：变量/流程控制/数组/字符串
- [ ] 面向对象：类与对象、继承、多态、抽象类、接口
- [ ] 核心类库：集合（List/Set/Map 源码与区别）、泛型、异常、常用 API
- [ ] 进阶：反射、注解、Lambda、Stream API
- [ ] 并发基础：线程创建、锁（synchronized / Lock）、线程池、volatile
- [ ] JVM 基础（八股重点）：内存模型、GC 垃圾回收、类加载
- [ ] **每日 LeetCode 1-2 题（强制用 Java 写）**，优先「热题 100 / 面试 150」

> 里程碑：能脱离 AI 手写一个线程安全的单例 + 用 Stream 处理集合。

> 📚 本阶段文档：廖雪峰 Java 教程 — <https://www.liaoxuefeng.com/wiki/1252599548343744（语法→集合→IO→并发→JVM> 一个站讲全）

---

## 四、阶段 2：数据库 + 工具链（第 4-5 周）

**目标**：能设计表、写高效 SQL、理解索引与缓存。

- [ ] 安装 **MySQL 8**、**Redis**（Docker 可选，用于后续部署）
- [ ] **MySQL**：SQL 增删改查、多表关联、窗口函数
- [ ] MySQL 进阶（八股）：索引（B+树）、事务与隔离级别、锁、慢查询优化
- [ ] **Redis**：5 种数据结构、持久化、缓存穿透/击穿/雪崩、分布式锁
- [ ] **Maven**：依赖管理、生命周期、多模块
- [ ] **Git**：分支、commit、rebase、PR 工作流
- [ ] **Linux 基础**：常用命令、vim、部署基本操作
- [ ] 继续每日算法（转向中等难度）

> 里程碑：能手画 MySQL 索引 B+树结构 + 口述缓存三大问题对策。

> 📚 本阶段文档：菜鸟教程 MySQL — <https://www.runoob.com/mysql/mysql-tutorial.html（深入可选极客时间《MySQL> 实战 45 讲》）

---

## 五、阶段 3：Spring Boot 入门 + 八股（第 6-7 周）

**目标**：独立写出一个带数据库的 REST 接口服务。

- [ ] **Spring 核心**：IoC / DI、AOP、Bean 生命周期
- [ ] **Spring Boot**：自动配置、起步依赖、配置文件、常用注解
- [ ] **Spring MVC**：Controller/Service/Mapper 分层、RESTful 风格、参数校验
- [ ] **MyBatis / MyBatis-Plus**：映射、动态 SQL、分页、代码生成器
- [ ] 鉴权：**JWT** 登录鉴权、拦截器
- [ ] 日志：SLF4J + Logback
- [ ] 单元测试：JUnit 5 + MockMvc
- [ ] 八股同步复习：Spring 循环依赖、Bean 作用域、事务失效场景

> 里程碑：用 Spring Boot + MyBatis 写完一个「用户注册/登录 + 增删改查」最小可用后端。

> 📚 本阶段文档：Spring Boot 中文文档 — <https://springdoc.cn/spring-boot/（动手练配> Spring 官方 Guides <https://spring.io/guides）>

---

## 六、阶段 4：项目实战（第 8-9 周）

**目标**：做出能写进简历、可演示的 1-2 个项目（全推 GitHub）。

**项目 A（必做，保底）**：博客 / 论坛 / 简易商城后端

- [ ] 需求拆解 + 数据库设计（ER 图）
- [ ] Spring Boot + MySQL + MyBatis-Plus 实现
- [ ] Redis 做缓存（如热门文章 / 验证码）
- [ ] JWT 鉴权 + 全局异常处理 + 统一返回格式
- [ ] 写单元测试，Docker 部署上线（或本地可运行 demo）
- [ ] 完善 README（架构图 + 接口列表 + 启动步骤）

**项目 B（加分，差异化）**：接入大模型的智能后端

- [ ] 在 A 基础上加一个 AI 模块：用 Java/Python 调 DeepSeek / 智谱 / OpenAI API
- [ ] 场景示例：智能问答、文章摘要、RAG（向量库检索增强）
- [ ] 暴露成 REST 接口，前端简单调用即可演示
- [ ] 这一步同时覆盖「AI 应用开发」差异化，简历含金量最高

> 里程碑：面试官打开 GitHub 能本地跑起来、且你能逐行讲清关键代码。

> 📚 本阶段文档：若依 Ruoyi — <https://www.ruoyi.vip/（读一个完整> Spring Boot 项目比看教程快；AI 模块查各模型官方文档）

---

## 七、阶段 5：求职冲刺（第 10 周 +）

- [ ] 简历：突出 Java/Spring、两个项目、技术栈；**AI 生成的代码必须自己讲过**
- [ ] 投递渠道：实习僧、BOSS直聘、牛客校招、**导师/学长内推（成功率最高）**
- [ ] 八股总复习：JavaGuide 过一遍、Spring/MySQL/Redis/JVM 重点
- [ ] 算法保持手感：每日 1 题，重点二叉树 / 动态规划 / 回溯
- [ ] 模拟面试：手写核心逻辑 + 逐行解释项目代码，录屏复盘

> 📚 本阶段文档：牛客网 — <https://www.nowcoder.com/（面经+笔试+内推；算法手感用> LeetCode 中国 <https://leetcode.cn/）>

---

## 八、计算机基础（贯穿全程，面试必考）

- [ ] **数据结构与算法**：数组/链表/树/图/排序/查找（LeetCode 为主）
- [ ] **计算机网络**：TCP 三次握手、HTTP/HTTPS、状态码、REST
- [ ] **操作系统**：进程线程、死锁、内存管理（概念级即可）
- [ ] **设计模式**：单例、工厂、策略、观察者（面试常问）

---

## 九、资源索引（每阶段一个最全文档）

- **总纲 / 八股（全程）**：JavaGuide — <https://github.com/Snailclimb/JavaGuide>
- **阶段 0 环境**：IDEA 新手指南 — <https://www.jetbrains.com/idea/learn/>
- **阶段 1 Java SE**：廖雪峰 Java 教程 — <https://www.liaoxuefeng.com/wiki/1252599548343744>
- **阶段 2 数据库**：菜鸟教程 MySQL — <https://www.runoob.com/mysql/mysql-tutorial.html（深入选极客时间《MySQL> 实战 45 讲》）
- **阶段 3 Spring Boot**：Spring Boot 中文文档 — <https://springdoc.cn/spring-boot/>
- **阶段 4 项目**：若依 Ruoyi — <https://www.ruoyi.vip/>
- **阶段 5 求职**：牛客网 — <https://www.nowcoder.com/（算法用> LeetCode 中国 <https://leetcode.cn/）>

> 工具类（Redis / Maven / Linux / Git）随用随查官方文档即可，不必提前系统学。

---

## 十、每周节奏模板

- 周一~周五：2h 理论（看视频/文档）+ 1h 算法 + 1h vibe coding 练手
- 周末：半天复盘本周知识点 + 推进项目
- 每阶段结束：对照「里程碑」自测，不过关不进下一阶段

## 十一、避坑

- 别只背框架不学原理：八股 + 手写能力是后端面试硬门槛。
- 别把 vibe coding 当甩手掌柜：生成代码必须读懂再提交。
- 别贪多：先把 Java 后端主干打通，AI 差异化是锦上添花。
- 别等完美再投：项目能跑就投，面试反馈反过来指导学习。

---

## 十二、每日计划打卡表

> 用法：每天睡前花 2 分钟，把当天完成的 `[ ]` 改成 `[x]`。每「周」对应路线图一个阶段，跟不上的周可以顺延，但**阶段里程碑不过关不进下一阶段**。  
> 算法题默认用 **Java** 写（顺带练语言）；vibe coding 工具（Cursor/通义灵码/Claude Code/WorkBuddy）贯穿每天，遇到报错先让 AI 解释再改。

### 准备期（阶段 0 · 环境，约 3 天）

- [ ] 10/28（周三）：确认 JDK21 + IDEA 已装到 `F:/实习`，跑通第一个 HelloWorld
- [ ] 10/29（周四）：确认 Maven 已装 + 配好 Git/GitHub；选一个 vibe coding 工具并跑通
- [ ] 10/30（周五）：让 AI 解释一段报错 + 生成 3 道练习；建好 `F:/实习` 仓库结构

### 第 1 周（阶段 1 · Java SE 入门）10/31–11/6

- [ ] 10/31（周六）：Java 基础语法（变量/类型/运算符）；LeetCode 1 题
- [ ] 11/01（周日）：流程控制（if/switch/for/while）；LeetCode 1 题
- [ ] 11/02（周一）：数组 + 字符串常用 API；LeetCode 1 题
- [ ] 11/03（周二）：方法/重载/递归；LeetCode 1 题
- [ ] 11/04（周三）：面向对象入门（类/对象/封装/构造器）；LeetCode 1 题
- [ ] 11/05（周四）：继承/多态/抽象类；LeetCode 2 题
- [ ] 11/06（周五）：本周复习 + 周测（脱离 AI 手写代码）

### 第 2 周（阶段 1 · Java SE 深入）11/7–11/13

- [ ] 11/07（周六）：接口 vs 抽象类 + Object/String 不可变；LeetCode 1 题
- [ ] 11/08（周日）：集合 List/Set/Map 区别与底层；LeetCode 1 题
- [ ] 11/09（周一）：泛型 + 异常体系；LeetCode 1 题
- [ ] 11/10（周二）：IO 流（字节/字符）；LeetCode 1 题
- [ ] 11/11（周三）：反射 + 注解；LeetCode 1 题
- [ ] 11/12（周四）：Lambda + Stream API 实战；LeetCode 2 题
- [ ] 11/13（周五）：用 Stream 处理集合小练习 + 复盘

### 第 3 周（阶段 1 · 并发 + JVM + 算法加强）11/14–11/20

- [ ] 11/14（周六）：线程创建/状态/调度；LeetCode 1 题
- [ ] 11/15（周日）：synchronized / Lock / 线程安全；LeetCode 1 题
- [ ] 11/16（周一）：线程池原理与参数；LeetCode 1 题
- [ ] 11/17（周二）：volatile / JMM 内存模型；LeetCode 1 题
- [ ] 11/18（周三）：JVM 内存结构 + GC 基础；LeetCode 1 题
- [ ] 11/19（周四）：设计模式（单例/工厂/策略）；LeetCode 2 题
- [ ] 11/20（周五）：阶段 1 里程碑自测（线程安全单例 + Stream）

### 第 4 周（阶段 2 · MySQL）11/21–11/27

- [ ] 11/21（周六）：安装 MySQL8 + 基础 SQL（建库/表/CRUD）；LeetCode 1 题
- [ ] 11/22（周日）：约束/多表关联/子查询；LeetCode 1 题
- [ ] 11/23（周一）：索引（B+树）原理；LeetCode 1 题
- [ ] 11/24（周二）：事务/隔离级别/锁；LeetCode 1 题
- [ ] 11/25（周三）：慢查询优化 + Explain；LeetCode 1 题
- [ ] 11/26（周四）：Maven 依赖管理 + 多模块；LeetCode 2 题
- [ ] 11/27（周五）：手画 B+树 + 口述隔离级别

### 第 5 周（阶段 2 · Redis + Git/Linux）11/28–12/4

- [ ] 11/28（周六）：安装 Redis + 5 种数据结构；LeetCode 1 题
- [ ] 11/29（周日）：Redis 持久化 + 过期策略；LeetCode 1 题
- [ ] 11/30（周一）：缓存穿透/击穿/雪崩 + 分布式锁；LeetCode 1 题
- [ ] 12/01（周二）：Git 分支/commit/rebase/PR 工作流；LeetCode 1 题
- [ ] 12/02（周三）：Linux 常用命令 + vim；LeetCode 1 题
- [ ] 12/03（周四）：用 Java JDBC 连 MySQL 写个小 demo；LeetCode 2 题
- [ ] 12/04（周五）：阶段 2 里程碑自测

### 第 6 周（阶段 3 · Spring Boot 入门）12/5–12/11

- [ ] 12/05（周六）：Spring IoC/DI + 第一个 Spring Boot 项目；LeetCode 1 题
- [ ] 12/06（周日）：自动配置/起步依赖/配置文件；LeetCode 1 题
- [ ] 12/07（周一）：Spring MVC（Controller/Service/Mapper 分层）；LeetCode 1 题
- [ ] 12/08（周二）：RESTful 风格 + 参数校验；LeetCode 1 题
- [ ] 12/09（周三）：MyBatis 映射 + 动态 SQL；LeetCode 1 题
- [ ] 12/10（周四）：MyBatis-Plus + 代码生成器；LeetCode 2 题
- [ ] 12/11（周五）：搭好一个空 Spring Boot 骨架 + 复盘

### 第 7 周（阶段 3 · Spring Boot 进阶 + 八股）12/12–12/18

- [ ] 12/12（周六）：AOP + 拦截器；LeetCode 1 题
- [ ] 12/13（周日）：JWT 登录鉴权实现；LeetCode 1 题
- [ ] 12/14（周一）：全局异常处理 + 统一返回；LeetCode 1 题
- [ ] 12/15（周二）：单元测试 JUnit5 + MockMvc；LeetCode 1 题
- [ ] 12/16（周三）：八股（循环依赖/Bean 作用域/事务失效）；LeetCode 1 题
- [ ] 12/17（周四）：完成「用户注册登录 + CRUD」最小可用后端；LeetCode 2 题
- [ ] 12/18（周五）：阶段 3 里程碑自测（面试官视角跑通）

### 第 8 周（阶段 4 · 项目 A 实战）12/19–12/25

- [ ] 12/19（周六）：项目 A 需求拆解 + ER 图 + 建表；LeetCode 1 题
- [ ] 12/20（周日）：Spring Boot + MyBatis-Plus 搭框架 + 基础 CRUD；LeetCode 1 题
- [ ] 12/21（周一）：用户模块（注册/登录/JWT）；LeetCode 1 题
- [ ] 12/22（周二）：核心业务模块（文章/商品 CRUD）；LeetCode 1 题
- [ ] 12/23（周三）：Redis 缓存（热门/验证码）；LeetCode 1 题
- [ ] 12/24（周四）：全局异常处理 + 日志 + 接口文档；LeetCode 1 题
- [ ] 12/25（周五）：单元测试 + 本地跑通 demo

### 第 9 周（阶段 4 · 项目 A 收尾 + 项目 B 差异化）12/26–1/1

- [ ] 12/26（周六）：项目 A 完善 README + 架构图 + 部署；LeetCode 1 题
- [ ] 12/27（周日）：推 GitHub + 写清启动步骤；LeetCode 1 题
- [ ] 12/28（周一）：项目 B 选 AI 场景（智能问答/摘要）；LeetCode 1 题
- [ ] 12/29（周二）：接入 DeepSeek/智谱 API（Java 调）；LeetCode 1 题
- [ ] 12/30（周三）：暴露 AI 模块 REST 接口；LeetCode 1 题
- [ ] 12/31（周四）：前端简单调用演示联调；LeetCode 1 题
- [ ] 01/01（周五）：阶段 4 里程碑自测（GitHub 能跑 + 逐行讲代码）

### 第 10 周（阶段 5 · 求职冲刺）1/2–1/8

- [ ] 01/02（周六）：写简历（Java/Spring/两项目/技术栈）；LeetCode 1 题
- [ ] 01/03（周日）：八股总复习 JavaGuide 一轮；LeetCode 1 题
- [ ] 01/04（周一）：投递（实习僧/BOSS/牛客 + 内推）；LeetCode 1 题
- [ ] 01/05（周二）：算法重点（二叉树/DP/回溯）；LeetCode 1 题
- [ ] 01/06（周三）：模拟面试（手写 + 讲项目）；LeetCode 1 题
- [ ] 01/07（周四）：复盘面经 + 补薄弱点；LeetCode 1 题
- [ ] 01/08（周五）：继续投递 + 准备下周面试

> 进度自查：完成一天就回头看路线图对应阶段的「里程碑」，能讲清楚再打勾进下一天。日期从 2026-10-28 起，遇放假/加班可顺延，但阶段里程碑不过关不进下一阶段。
