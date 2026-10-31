# Java 后端学习笔记

> 用途：边学边记。路线规划在 `Java后端学习路线.md`（打卡表也在那），本文件只放**你真正学到的内容**：概念、代码、踩坑、面试题。  
> 用法：每天学完，把当天的要点复制到对应阶段的「笔记区」，或直接告诉 WorkBuddy「把 xxx 记到笔记里」，我来帮你填。  
> 约定：代码块用 `java` 标注；面试题单独攒到第五节，方便冲刺期背。

---

## 0. 速记区（随手记，不拘格式）

- 2026-10-28：环境搭好（IDEA + JDK21 + Maven），`learning` 工程能编译运行。Maven 标准目录：源码在 `src/main/java`，测试在 `src/test/java`，编译产物在 `target/`（可删）。

---

## 1. 阶段 1 · Java SE 核心

### 1.0 JDK / JRE / JVM（环境基础）

- **JDK 包含 JRE（含 Java 虚拟机 JVM）以及编译器、调试器等开发工具**。三者是包含关系：JDK ⊃ JRE ⊃ JVM。
  - **JVM**：Java 虚拟机，负责运行字节码（.class），实现"一次编译，到处运行"。
  - **JRE** = JVM + 运行时类库（Runtime Library），只能**运行**程序。
  - **JDK** = JRE + 开发工具（编译器 javac、调试器等），能**开发 + 运行**。
  - 底层 JVM 按操作系统有不同实现（Windows / Linux / macOS 等），所以同一份字节码各平台都能跑。
  ![JDK/JRE/JVM 结构图](notes-images/jdk-jre-结构图.png)
  - 易错/面试点：只装 JRE 能跑 Java 程序但**不能编译**；开发必须装 JDK。我们装的 JDK 21 在 `F:/实习/jdk-21`。

### 1.1 基础语法（变量/类型/运算符/流程控制）

> 模板：概念一句话 + 关键代码 + 易错点

- **变量与类型**（示例，照这个格式记）
  - 八种基本类型：`byte short int long float double char boolean`
  - `int` 4 字节、`long` 加 `L`、`double` 默认浮点、`float` 加 `f`
  ```java
  int a = 10;
  long b = 10L;
  double c = 3.14;
  boolean flag = true;
  ```
  - 易错：`==` 比基本类型值，比对象要比内容（如 `String` 用 `.equals`）
- **基本数据类型**（共 8 种，牢记分组）
  - 整数：`byte`、`short`、`int`、`long`
  - 浮点：`float`、`double`
  - 字符：`char`
  - 布尔：`boolean`
  - 记忆：4 整数 + 2 浮点 + 1 字符 + 1 布尔
- **包装类型（Wrapper Types，每种基本类型都有对应引用类型）**
  - 因为包装类型非常有用，Java 核心库为**每种基本类型都提供了对应的包装类**（都在 `java.lang` 下，不用 import）：


    | 基本类型      | 对应的引用类型（包装类）          |
    | --------- | --------------------- |
    | `boolean` | `java.lang.Boolean`   |
    | `byte`    | `java.lang.Byte`      |
    | `short`   | `java.lang.Short`     |
    | `int`     | `java.lang.Integer`   |
    | `long`    | `java.lang.Long`      |
    | `float`   | `java.lang.Float`     |
    | `double`  | `java.lang.Double`    |
    | `char`    | `java.lang.Character` |
  - **为什么需要包装类**（基本类型是"值"，包装类是"对象"）：
    1. **泛型只能用引用类型**：`List<int>` 不合法，`List<Integer>` 才行。
    2. **可以表示"空值 null"**：基本类型不能为 null，包装类可以（如数据库字段可能为空的场景用 `Integer` 而非 `int`）。
    3. **自带工具方法 / 常量**：如 `Integer.parseInt("123")`、`Integer.MAX_VALUE`、`Character.isDigit('5')`。
  - **自动装箱（autoboxing）与拆箱（unboxing）**：Java 5+ 编译器自动在基本类型与包装类间转换，写起来像直接用基本类型。
    ```java
    Integer n = 100;        // 自动装箱：等价于 Integer.valueOf(100)
    int x = n;              // 自动拆箱：等价于 n.intValue()
    List<Integer> list = new ArrayList<>();
    list.add(1);            // 1 自动装箱成 Integer
    ```
  - 易错/面试点：
    - **`Integer` 缓存**：`Integer.valueOf(-128~127)` 会复用缓存对象，所以 `Integer a=100; Integer b=100; a==b` 为 `true`；但 `a=200; b=200; a==b` 为 `false`（超出缓存，新建对象）。比较包装类内容**用 `equals`**，别用 `==`。
    - **拆箱空指针**：`Integer n = null; int x = n;` 会抛 `NullPointerException`（拆箱时调用 `intValue()`）。
    - 包装类是**不可变对象**（和 `String` 一样），运算产生新对象。
  - **补充 / 深化（面试常问）**
    - **核心库提供的包装类型就是把基本类型"包装成 class"**：本质是引用类型（对象），才能放进集合、当作 `Object` 传递、赋值为 `null`。
    - **自动装箱 / 拆箱是编译期完成的**（JDK ≥ 1.5）：编译器把 `Integer n = 100;` 改写成 `Integer.valueOf(100)`，把 `int x = n;` 改写成 `n.intValue()`。**运行时没有"自动转换"开销这一概念，开销来自 `valueOf` / `xxxValue` 这些方法调用本身**。
    - **装箱 / 拆箱会影响执行效率**：每次装箱都可能产生对象（除走缓存外），在**循环 / 高频调用**里反复装箱拆箱会增加对象创建与 GC 压力；性能敏感处优先用基本类型。
    - **包装类型的比较必须使用 `equals()`**：`==` 比的是对象引用（且受缓存区间影响，结果不稳定），只有 `equals` 才是比"值"。
    - **整数与浮点数的包装类型都继承自 `Number`**：`Byte/Short/Integer/Long/Float/Double` 都继承抽象类 `Number`（提供 `intValue()/doubleValue()/...` 等互相转换方法）；而 `Boolean` 和 `Character` **不继承 `Number`**（它们不是数值）。
    - **包装类型提供了大量实用方法**：解析（`Integer.parseInt` / `Double.parseDouble`）、进制/字符串（`Integer.toBinaryString` / `toHexString`）、极值常量（`Integer.MAX_VALUE` / `MIN_VALUE`）、判断（`Character.isDigit/isLetter`）、`valueOf` 等。
- **Java 内置的核心类**（不需要 import，直接用）
  - `Object`：所有类的祖宗，一切类默认继承它（面试常考 `equals`/`hashCode`/`toString`）
  - `String`：字符串（不可变）
  - `StringBuilder` / `StringBuffer`：可变字符串（后者线程安全）
  - `Math`：数学计算，`Math.random()`、`Math.max(a, b)`
  - `System`：`System.out.println` 就来自它
  - `Integer / Double / Character / Boolean`…：8 个基本类型对应的**包装类**（基本类型 ↔ 对象互转、用于泛型）
  - `Class`：反射用（运行时获取类信息）
  - `Thread`：多线程
  - `Exception / RuntimeException`：异常类
- **常用内置包（都需要 `import` 导入）**
  - `java.util`（工具包，超级常用）：集合、日期、随机数
    - `ArrayList`、`HashMap`、`HashSet`、`Random`、`Date`、`Scanner`（控制台输入）
    ```java
    import java.util.Scanner;
    Scanner sc = new Scanner(System.in);
    ```
  - `java.io`（文件、读写数据流）：`File`、`FileInputStream`、`BufferedReader`，读写本地文件
  - `java.math`（高精度数字）：`BigDecimal`（精确小数，财务计算）、`BigInteger`（超大整数）
  - `java.net`（网络）：`Socket`、`URL`，写网络请求
  - `java.nio`（新版文件 IO，NIO）：高性能文件操作，后面才学
- **流程控制：`break` / `continue`（循环里用）**
  - `break` 语句可以**跳出当前循环**（提前结束整个循环）。
    - 通常配合 `if`，在满足条件时提前结束整个循环。
    - `break` **总是跳出最近的一层循环**（嵌套循环时只退出内层）。
  - `continue` 语句可以**提前结束本次循环**（跳过本次剩余代码，进入下一次迭代）。
    - 通常配合 `if`，在满足条件时提前结束本次循环。
  ```java
  for (int i = 0; i < 10; i++) {
      if (i == 3) continue;   // i==3 这次跳过，继续 i==4
      if (i == 8) break;      // 整个循环在 i==8 时结束
      System.out.println(i);    // 输出 0 1 2 4 5 6 7
  }
  ```
  - 易错：嵌套循环里 `break` 只跳一层；要跳多层可用**带标签的 `break`**（如 `outer:` + `break outer;`）。
- **`==` vs `equals()`（面试题常客，重点记）**
  - **`==`**：比较**两个变量里存的地址（引用）是不是同一个**，即是否指向堆里同一个对象。
    - 比基本类型：比的是"值"是否相等。
    - 比引用类型：比的是"内存地址/引用"是否同一个对象。
  - **`equals()`**：`Object` 类默认实现也是比较地址（跟 `==` 效果一样）；**很多类（如 `String`）重写了它，用来比较对象里面的"内容"是否一样**。
  - 易错/结论：比较两个字符串内容是否相等必须用 `str1.equals(str2)`，**不能用 `==`**（除非是同一个对象或字符串常量池命中）。
  ```java
  String a = new String("hello");
  String b = new String("hello");
  a == b;          // false：两个不同对象，地址不同
  a.equals(b);     // true：内容相同（String 重写了 equals）
  ```

- **JavaBean（一种符合命名规范的 class，主要用来"装数据"）**
  - **用途**：JavaBean 主要用来**传递数据**——把一组相关的数据组合成一个 JavaBean，便于在网络/方法间传输。此外，JavaBean 可以被 **IDE 工具自动分析**，一键生成读写属性的代码，**主要用在图形界面（GUI）的可视化设计**里（拖个控件、自动生成 getter/setter）。
  - **本质**：JavaBean 是**符合一套命名规范的普通 class**，它通过 **getter / setter 方法来"定义"属性（property）**——注意字段（field）是私有的，对外暴露的是 getter/setter。
  - **"属性（property）"是通用叫法，不是 Java 语法规定**：属性名由 getter/setter 的方法名推导出来（`getXxx()` → 属性 `xxx`），**和字段名不一定相同**；Java 语言层面只有字段和方法，没有"属性"这个关键字。
  - **可以用 IDE 快速生成 getter / setter**：在 IDEA 里 `Alt+Insert`（或右键 → Generate）→ Getter and Setter，全选字段即可生成，不用手写。
  - **运行时读取属性列表**：可用 `java.beans.Introspector.getBeanInfo(Class)` 拿到 `BeanInfo`，再取 `getPropertyDescriptors()` 得到所有属性（很多框架如 Spring、序列化库底层都靠这个反射式内省来读写 Bean）。
  - JavaBean 规范要点（常见约定）：
    - `public` class；
    - **字段 `private`**，通过 `public` 的 `getXxx()` / `setXxx()` 读写（`boolean` 类型可用 `isXxx()` 作 getter）；
    - 提供 **`public` 无参构造器**（便于框架反射创建实例）；
    - 方法命名：`getXxx()` 无参返回类型 T，`setXxx(T value)` 无返回值。
  ```java
  public class Person {            // 一个最简单的 JavaBean
      private String name;         // 私有字段
      private int age;
      public Person() {}           // 无参构造器（规范推荐）
      public String getName() { return name; }   // getter → 属性 name
      public void setName(String name) { this.name = name; }  // setter
      public int getAge() { return age; }
      public void setAge(int age) { this.age = age; }
  }
  // 内省获取属性列表（name、age）
  // BeanInfo info = Introspector.getBeanInfo(Person.class);
  // PropertyDescriptor[] pds = info.getPropertyDescriptors();
  ```
  - 易错/面试点：属性名由 getter/setter 推导，**`getXxx()` 去掉 `get` 再把首字母小写**得属性名；若写成 `getNAME()` 内省会解析成属性 `NAME`（首字母保持）。框架（Spring 注入、JSON 序列化如 Jackson）都按"属性"而非"字段"来读写，所以想让某个字段可序列化/可注入，就得有对应 getter/setter（或用注解）。

- **`enum`（枚举类型）**
  - **定义的 `enum` 类型总是继承自 `java.lang.Enum`**，且**无法再被继承**（编译器禁止 `extends` 别的类，因为已经隐式继承了 `Enum`；也禁止被别的类继承，因为被 `final` 修饰）。
  - **只能定义出 `enum` 的实例，而无法通过 `new` 操作符创建**：枚举的实例（如 `RED`、`GREEN`）必须在 `enum` 体内显式列出，JVM 保证它们**在类加载时就创建好**，任何代码都不能 `new` 出新的枚举实例。
  - **定义的每个实例都是引用类型的唯一实例**：枚举实例是**单例**级别的——同一个枚举值在 JVM 里只有一个对象，因此枚举实例之间可以用 `==` 安全比较（也推荐用 `==` 而非 `equals`）。
  - **可以将 `enum` 类型用于 `switch` 语句**：`switch` 直接判断枚举值，比用 `int` 常量更安全（编译期类型检查，不会传错值）。
  - 补充/示例：枚举还可带字段、构造器、`abstract` 方法（每个实例可重写），常用于表示固定集合（星期、状态机、错误码）。
  ```java
  enum Color { RED, GREEN, BLUE }   // 只能这样"定义"出实例，不能 new Color()
  Color c = Color.RED;
  switch (c) {                      // enum 可直接用于 switch
      case RED:  System.out.println("红"); break;
      case GREEN:System.out.println("绿"); break;
      default:  System.out.println("蓝");
  }
  // c == Color.RED  // 枚举实例唯一，可用 == 比较
  ```
  - 易错/面试点：枚举**不能继承也不能被继承**；不能 `new`；比较用 `==` 即可；`values()` 返回所有实例数组、`valueOf("RED")` 按名取实例（`Enum` 自带方法）。

- **`record`（Java 14+ 的 Data Class，传数据的更优解）**
  - **从 Java 14 开始提供 `record` 关键字**，可以**非常方便地定义 Data Class**（只装数据、几乎无逻辑的类，如之前手写 JavaBean 的场景）。
  - **用 `record` 定义的是不变类（immutable）**：`record Point(int x, int y) {}` 会自动生成 `private final` 字段、`public` 的访问器（方法名就是字段名 `x()`/`y()`，**不是** `getX()`）、`equals()`/`hashCode()`/`toString()`，且**不可被继承、字段不可修改**。
  - **可以编写 Compact Constructor 对参数进行验证**：在 `record` 体内写一个**没有参数列表的构造器**（紧凑构造器），用来校验/规范化入参（校验失败抛异常），无需手写完整构造器体和字段赋值。
  - **可以定义静态方法**（以及静态字段、静态初始化块）：在 `record` 体内直接写 `static` 方法即可，常用于工厂方法或常量。
  ```java
  public record Point(int x, int y) {
      // Compact Constructor：校验参数（失败抛异常），无需手写字段赋值
      public Point {
          if (x < 0 || y < 0) throw new IllegalArgumentException("坐标不能为负");
      }
      // 静态方法（工厂 / 常量）
      public static Point origin() { return new Point(0, 0); }
  }
  Point p = new Point(3, 4);
  int a = p.x();          // 访问器叫 x()，不是 getX()
  Point o = Point.origin();
  ```
  - 易错/面试点：① `record` 是**不可变**且**不能被继承**（隐式 `final`）；② 访问器命名是 `x()` 而非 `getX()`（和 JavaBean/Lombok 区分开，别混）；③ 与 JavaBean 对比：`record` 更简洁、自动得到 `equals/hashCode/toString`，适合"纯数据载体"，而需要可变状态或复杂行为的仍用普通 class / JavaBean；④ 我们项目用 JDK 21，`record` 可直接用。

- **`BigInteger`（任意大小的整数）**
  - **`BigInteger` 用于表示任意大小的整数**：突破 `long`（最大值约 9.2×10¹⁸）的上限，位数只受内存限制，适合超大整数运算（如密码学、阶乘、高精度计算）。
  - **`BigInteger` 是不变类（immutable），并且继承自 `Number`**：和 `Integer/Long` 一样继承抽象类 `Number`（提供 `longValue()/doubleValue()/...` 等互转方法），且**每次运算都返回新的 `BigInteger`**，原值不变。
  - **转换成基本类型时用 `longValueExact()` 等方法保证准确**：普通 `longValue()` 在数值超出 `long` 范围时会**静默截断**（不报错、结果错）；而 `longValueExact()` 若超出范围会**直接抛 `ArithmeticException`**，能及时发现溢出。同理还有 `intValueExact()`。
  ```java
  BigInteger a = new BigInteger("123456789012345678901234567890"); // 远超 long
  BigInteger b = BigInteger.valueOf(100);   // 常用工厂方法（缓存小值）
  BigInteger sum = a.add(b);                // 不变类：返回新对象，a、b 不变
  long n = sum.longValueExact();            // 超出 long 范围会抛 ArithmeticException
  ```
  - 易错/面试点：① `BigInteger` 不变、继承 `Number`；② 运算用 `add/subtract/multiply/divide/mod`（不能用 `+ - * /` 运算符）；③ **转基本类型优先用 `xxxValueExact()`**，避免 `longValue()` 静默截断导致隐蔽 bug；④ 相等比较用 `equals()`（不是 `==`，因为它不是枚举那种唯一实例）。

- **`BigDecimal`（精确的小数，财务计算首选）**
  - **`BigDecimal` 用于表示精确的小数，常用于财务计算**：`float`/`double` 是二进制浮点、有精度误差（如 `0.1 + 0.2 ≠ 0.3`），涉及钱**绝不能用 `double` 直接算**；`BigDecimal` 用十进制精确表示，适合金额、利率等要求零误差的场景。
  - **比较 `BigDecimal` 的值是否相等，必须使用 `compareTo()` 而不能使用 `equals()`**：因为 `equals()` **同时比较"值"和"标度（scale，小数位数）"**——`new BigDecimal("1.0")` 和 `new BigDecimal("1.00")` 值相同但 scale 不同，`equals()` 返回 `false`；而 `compareTo()` 只看数值大小，返回 `0` 才表示相等。
  ```java
  BigDecimal x = new BigDecimal("1.0");
  BigDecimal y = new BigDecimal("1.00");
  x.equals(y);        // false：scale 不同（1 位 vs 2 位）
  x.compareTo(y) == 0;// true：数值相等，这才是"值相等"的正确判断
  ```
  - 补充/易错点：
    - **创建方式**：优先用 `BigDecimal.valueOf(0.1)` 或 `new BigDecimal("0.1")`（字符串构造最精确）；**别用 `new BigDecimal(0.1)`**（传入的 double 本身已带误差，会写成 0.1000000000000000055…）。
    - **运算用方法**：`add/subtract/multiply`，**除法 `divide` 必须指定舍入模式**（如 `divide(b, RoundingMode.HALF_UP)`），否则除不尽会抛 `ArithmeticException`。
    - `BigDecimal` 也是**不变类、继承 `Number`**，每次运算返回新对象。
    - 财务结果想"保留 2 位"用 `setScale(2, RoundingMode.HALF_UP)`。

- **常用工具类（Math / HexFormat / Random / SecureRandom）**
  - **`Math`：数学计算**
    - 静态方法，直接用：`Math.abs(x)`（绝对值）、`Math.max(a,b)`/`Math.min(a,b)`、`Math.pow(a,b)`（幂）、`Math.sqrt(x)`（开方）、`Math.floor/ceil/round`（取整）、常量 `Math.PI`/`Math.E`。
    - `Math.random()` 返回 `[0,1)` 的 `double`（内部用一个 `Random`），需要整数随机更推荐直接用 `Random`。
  - **`HexFormat`：格式化十六进制数（Java 17+）**
    - 在 `java.util` 包下，用来**把字节/字节数组和十六进制字符串互相转换**，比手写 `Integer.toHexString` 更规范、可定制分隔符/大小写。
    ```java
    HexFormat hf = HexFormat.of();
    String s = hf.formatHex(new byte[]{0x1a, (byte)0xff}); // "1aff"
    byte[] b = hf.parseHex("1aff");                        // 还原成字节数组
    ```
  - **`Random`：生成伪随机数（`java.util`）**
    - `nextInt()`（任意 int）、`nextInt(bound)`（0 到 bound-1）、`nextDouble()`、`nextBoolean()`；可传**种子（seed）**，相同种子产生相同序列（可复现，便于测试）。
    - "伪随机"：由算法按种子推算，并非真随机。
  - **`SecureRandom`：生成安全的随机数（`java.security`）**
    - 密码学强度的随机数，用于**安全敏感场景**：生成令牌（token）、盐值（salt）、密钥、验证码等。
    - 比 `Random` **更慢但不可预测**；**绝不要用 `Random` 生成密码/密钥等机密值**。
  ```java
  Random r = new Random();
  int n = r.nextInt(100);          // 0~99 伪随机
  SecureRandom sr = new SecureRandom();
  byte[] token = new byte[16];
  sr.nextBytes(token);             // 安全随机字节（用于 token/salt）
  ```
  - 易错/面试点：① `Random` 是**伪随机、可设种子复现**，只用于一般随机；② **安全/机密场景必须用 `SecureRandom`**；③ `HexFormat` 是 Java 17 才加的（我们 JDK 21 可用），做字节↔十六进制转换首选它；④ `Math.random()` 本质是 `Random`，需要可控随机建议直接用 `Random`。

### 1.2 数组 / 字符串 / 集合（List/Set/Map）

#### 1.2.1 字符串 `String`
- **`String` 是不可变对象（immutable）**
  - `String` 一旦创建，**内容就不能再被修改**；字符串里的字符数据被设计为不可变。
  - **字符串操作不改变原字符串，而是返回一个新字符串**。例如 `s.toUpperCase()`、`s.replace(...)`、`s.substring(...)` 都不会改动 `s` 本身，而是生成新的 `String` 返回，原串依然不变。
  - 易错/面试点：因为不可变，**不要用 `String` 在循环里做大量拼接**（每次拼接都产生新对象，O(n²) 且产生大量垃圾），应使用 `StringBuilder` / `StringBuffer`。常量池（字符串字面量复用）也是不可变带来的优化。
  ```java
  String s = "Hello";
  String t = s.toUpperCase();   // s 不变，t 是新的 "HELLO"
  System.out.println(s);        // 仍是 "Hello"
  ```
- **常用字符串操作**
  - **提取子串**：`s.substring(begin)` / `s.substring(begin, end)`（含头不含尾）。
  - **查找**：`s.indexOf("x")`（返回下标，-1 表示没找到）、`s.contains("x")`、`s.startsWith(...)` / `s.endsWith(...)`。
  - **替换**：`s.replace(old, new)`（字符/串替换）、`s.replaceAll(regex, new)`（正则替换）。
  - **大小写转换**：`s.toUpperCase()` / `s.toLowerCase()`。
  - 记忆：这些操作都**返回新 String**，原串不变。
- **Java 用 Unicode 编码表示 `String` 和 `char`**
  - `String` 和 `char` 内部都以 **Unicode** 表示字符，因此一个 `char` 能表示绝大多数常用字符；个别生僻字/emoji 超出一个 `char`（UTF-16 用 2 个 `char` 代理对表示）。
- **编码转换 = `String` ↔ `byte[]`，必须指定编码**
  - `String` 是字符序列，`byte[]` 是字节序列，二者**转换时需要指定字符集（编码）**，否则会用平台默认编码（各环境不一致，容易乱码）。
  - 👉 **转换为 `byte[]` 时，始终优先考虑 `UTF-8` 编码**，避免平台差异导致的乱码。
  ```java
  // String -> byte[]（明确指定 UTF-8）
  byte[] data = s.getBytes(StandardCharsets.UTF_8);
  // byte[] -> String（用什么编码写出，就用什么编码读回）
  String back = new String(data, StandardCharsets.UTF_8);
  ```
  - 面试点：`new String(bytes)` 不指定编码会用**默认编码**（JVM 启动的 file.encoding，可能随系统变），生产代码务必显式传 `StandardCharsets.UTF_8`。读写文件、网络传输、URL 参数同理都要定编码。

#### 1.2.2 数组（待补：快速入门已学基础，后续补多维数组 / `Arrays` 工具类 / 数组与集合互转）
- （待学）

#### 1.2.3 集合 Collection / Map（当前在学）
- **集合框架两大根接口**
  - `Collection`：存放**单个元素**，三大子接口——`List`（有序、可重复）、`Set`（无序、不可重复）、`Queue`（队列 / FIFO）。
  - `Map`：存放**键值对 key-value**，代表实现 `HashMap`、`TreeMap`、`LinkedHashMap`。
- **集合总览（核心概念）**
  - 集合的目的：为了**便于处理一组类似的数据**。
  - 三大集合类型（按用途区分）：
    - **`List`**：一种**有序表**的集合（元素有先后次序，可重复，按索引访问）。
    - **`Set`**：一种**保证没有重复元素**的集合（元素唯一，判重用 `equals`+`hashCode`）。
    - **`Map`**：一种通过**键值 key-value 查找的映射表**集合（按 key 快速定位 value）。
  - **定义位置与特性**：Java 的集合类定义在 **`java.util` 包**中，**支持泛型**（如 `List<String>`、`Map<Integer,String>`），主要提供 `List`、`Set`、`Map` 这 3 种集合类。
  - **统一遍历**：Java 集合使用**统一的 `Iterator` 接口**遍历（for-each 底层也是 `Iterator`）。
  - **避坑**：**尽量不要使用遗留接口 / 遗留集合类**（如 `Vector`、`Hashtable`、`Enumeration`、`Dictionary`、原始类型 `List` 不带泛型），改用 `ArrayList`/`HashMap` + 泛型 + `Iterator`；遗留类线程安全但性能差、设计陈旧。
- **为什么用集合而不是数组**
  - 数组长度固定、类型单一；集合可**动态扩容**、泛型保证**类型安全**、内置丰富 API（增删改查、排序、遍历），是 Java 后端最高频 API。
- **`List` 接口（有序、可重复、按索引访问）**
  - **定义**：`List` 是**按索引顺序访问、长度可变的有序表**；元素有先后次序、可重复，按下标（0 基）访问。
  - **实现类选择**：**优先使用 `ArrayList` 而不是 `LinkedList`**。`ArrayList` 底层是**动态数组**（随机访问 O(1)、尾部增删快），绝大多数场景够用；`LinkedList` 是双向链表（头尾增删 O(1) 但随机访问慢、内存开销大），日常几乎不需要。
  - **遍历**：可以直接用 **for-each** 遍历 `List`（底层走 `Iterator`）：
    ```java
    List<String> list = new ArrayList<>(List.of("A", "B", "C"));
    for (String s : list) {         // 直接 for-each
        System.out.println(s);
    }
    ```
  - **和数组互转**：
    - `List → 数组`：`list.toArray(new String[0])`（传空数组是惯用法，JVM 自动按大小创建）。
    - `数组 → List`：`Arrays.asList("A","B","C")` 或 `List.of("A","B","C")`（`List.of` 返回不可变列表）。
    ```java
    String[] arr = list.toArray(new String[0]);     // List → 数组
    List<String> l2 = Arrays.asList(arr);           // 数组 → 可改 List
    List<String> l3 = List.of("x", "y");            // 不可变 List
    ```
  - **查找依赖 `equals()`（重要）**：在 `List` 中**查找元素**（如 `contains()`、`indexOf()`、`remove(Object)`）时，实现类通过元素的 **`equals()`** 方法判断两个元素是否相等。因此**放入的元素必须正确覆写 `equals()`**（Java 标准库的 `String`、`Integer` 等已覆写，可直接用）。
    - 编写 `equals()` 可借助 **`Objects.equals(a, b)`** 安全判等（自动处理 `null`，避免 NPE）：
      ```java
      @Override
      public boolean equals(Object o) {
          if (this == o) return true;                       // 同引用
          if (o == null || getClass() != o.getClass()) return false;
          Person p = (Person) o;
          return Objects.equals(name, p.name);              // 借助 Objects.equals 处理 null
      }
      ```
    - **不必覆写的情况**：**如果不在 `List` 中查找元素**（只按索引访问、遍历），就**不必覆写 `equals()`**——只有需要"按内容比较/查找/去重"时才必须覆写。
  - 面试点：`ArrayList` vs `LinkedList` 的底层与适用场景；`List` 查找靠 `equals` 而非 `==`；自定义对象进 `List` 若要做 `contains/indexOf` 必须覆写 `equals`（通常也要覆写 `hashCode`）；`Arrays.asList`/`List.of` 返回的列表可变性差异。
- **`equals()` 方法的正确编写方法（总结）**
  1. **先确定"相等"逻辑**：明确哪些字段相等就认为两个实例相等（只比关键业务字段，不必比所有字段）。
  2. **用 `instanceof` 判断类型**：先判断传入的 `Object` 是不是当前类型，是才继续比较字段，否则直接返回 `false`（注意：`instanceof` 对 `null` 返回 `false`，天然处理了 null 入参）。
  3. **分类比较字段**：**引用类型用 `Objects.equals(a, b)` 比较，基本类型直接用 `==` 比较**（浮点可用 `Float.compare`/`Double.compare` 规避 NaN/精度问题）。
  4. **`Objects.equals()` 的目的**：省去自己判 `null` 的麻烦——**两个引用都是 `null` 时也认为相等**（返回 `true`），避免手动判空导致的 `NullPointerException`。
  5. **何时不必实现**：**如果没有调用 `List.contains()`、`indexOf()` 这类方法（也含 `Set`/`Map` 的查找/去重），放入的元素就不需要实现 `equals()`**。
  ```java
  @Override
  public boolean equals(Object o) {
      if (this == o) return true;                              // 同引用，必相等
      if (!(o instanceof Person p)) return false;              // instanceof 判断类型（null 直接 false）
      return Objects.equals(name, p.name)                       // 引用类型：Objects.equals（自动处理 null）
             && age == p.age;                                  // 基本类型：直接用 ==
  }
  ```
  - 补充：覆写 `equals()` 时**通常必须同时覆写 `hashCode()`**（相等对象必须有相等哈希码，否则放进 `HashSet`/`HashMap` 会出 bug）；用 IDE（IDEA：`Alt+Insert` → `equals() and hashCode()`）可一键生成规范实现。
- **`Map` 接口（键值对映射表）**
  - **定义**：`Map` 是一种**映射表**，可以通过 **key 快速查找 value**（按键定位值，不是按索引也不是按元素）。
    ```java
    Map<String, Integer> map = new HashMap<>();
    map.put("apple", 10);          // 存入 key-value
    Integer v = map.get("apple");   // 通过 key 快速取 value → 10
    ```
  - **遍历方式**：
    - 遍历 **`keySet()`**：拿到所有 key，再 `get(key)` 取 value（多一次查找，效率略低）：
      ```java
      for (String key : map.keySet()) {        // 遍历 key
          Integer value = map.get(key);
          System.out.println(key + " = " + value);
      }
      ```
    - 遍历 **`entrySet()`**：直接拿到 `key-value` 键值对（`Map.Entry`），**推荐**（一次遍历即得 key 和 value，更高效）：
      ```java
      for (Map.Entry<String, Integer> e : map.entrySet()) {   // 直接取 key-value
          System.out.println(e.getKey() + " = " + e.getValue());
      }
      ```
  - **最常用实现**：**`HashMap`** 是日常最常用的 `Map` 实现（基于哈希表，增删改查近似 O(1)）；其他实现还有 `TreeMap`（按 key 排序，红黑树）、`LinkedHashMap`（保持插入顺序）。
  - 面试点：`Map` **不继承 `Collection`**（独立家族）；遍历优先用 `entrySet()` 而非 `keySet()+get()`；自定义对象作 **key 必须同时正确覆写 `equals()` 和 `hashCode()`**（否则作为查找 key 会失效）；`HashMap` key 允许一个 `null`、value 允许多个 `null`。
- **`Map` 深入：key 的 `equals()`/`hashCode()` 规范 + `EnumMap` + `HashMap`/`SortedMap`**
  - **key 是"内容相等"而非"同一对象"**：两个 key 的**内容应该一样**（不是同一个对象实例），所以要用 `equals()` 判等，并用 `hashCode()` 定位桶——**`equals()` 和 `hashCode()` 都必须正确覆写**。
  - **正确使用 `Map` 的两条硬性保证**：
    1. **key 必须正确覆写 `equals()`**：相等的两个 key 实例调用 `equals()` **必须返回 `true`**。
    2. **key 必须正确覆写 `hashCode()`**，且**严格遵循规范**：
       - 若两个对象**相等** → 两个对象的 `hashCode()` **必须相等**；
       - 若两个对象**不相等** → 两个对象的 `hashCode()` **尽量不要相等**（哈希冲突越少，`HashMap` 越快）。
  - **写成对实例 `a`、`b` 的等式**：
    - `a.equals(b) == true` ⇒ `a.hashCode() == b.hashCode()`（**相等必同哈希码**）
    - `a.equals(b) == false` ⇒ `a.hashCode()` 与 `b.hashCode()` **尽量不相等**（**不相等尽量不同哈希码**）
    - 记忆口诀：**相等的对象哈希码必相等；不相等的对象哈希码尽量不相等**。
  - **key 是 `enum` → 用 `EnumMap`**：若 key 是枚举类型，**推荐 `EnumMap`**——内部用数组实现，**既保证速度、也不浪费空间**（普通 `HashMap` 为哈希表预留空间，枚举场景没必要）。
    - 面向抽象编程：声明时**持有 `Map` 接口**，实际 `new EnumMap<>(Xxx.class)`，便于以后替换实现：
      ```java
      Map<Day, String> map = new EnumMap<>(Day.class);   // 持有 Map 接口，实际用 EnumMap
      ```
  - **`HashMap`：以空间换时间，key 无序**：
    - 基于哈希表，用"空间换时间"换取近似 O(1) 的增删改查；
    - 实现原理决定**内部的 key 是无序的**——遍历 `HashMap` 的 key 时，**顺序不可预测**（但每个 key 都会遍历一次且**仅一次**）。
  - **`SortedMap`：按 key 顺序遍历，最常用 `TreeMap`**：
    - `SortedMap` 在遍历时**严格按照 key 的顺序**遍历，最常用的实现类是 **`TreeMap`**（红黑树）。
    - 作为 `SortedMap` 的 key **必须实现 `Comparable` 接口**，或在构造时**传入 `Comparator`**。
    - 必须**严格按 `compare()` 规范实现比较逻辑**（保持与 `equals` 一致：若 `compare==0` 则 `equals` 应为 true），否则 `TreeMap` 会**不能正常工作**（判重/查找出错）。
  - 面试点：`HashMap` 无序、`TreeMap` 有序（且 key 需 `Comparable`/`Comparator`）；`equals` 与 `hashCode` 的契约（相等必同哈希码）；枚举 key 用 `EnumMap` 省空间又快；`LinkedHashMap` 保插入序、`TreeMap` 保 key 排序序。
- **`Properties`：读写配置文件（`java.util`）**
  - **用途**：写应用程序经常需要**读写配置文件**（如用户设置）。Java 默认配置文件以 **`.properties`** 为扩展名，格式是**每行 `key=value`**，**以 `#` 开头的是注释**。Java 集合库提供 **`Properties`** 类专门用于读写 `.properties`。
    ```properties
    # 这是注释
    app.name=MyApp
    app.version=1.0
    ```
  - **编码**：`.properties` 文件**可以使用 UTF-8 编码**（读取时指定 UTF-8，避免中文乱码）。
  - **读取来源灵活**：可以从**文件系统**、**classpath**（打包进 jar 后更常用），或**任何其他地方**读取 `.properties`。
    ```java
    // 从 classpath 读取（推荐，jar 内可用）
    try (var in = Main.class.getResourceAsStream("/config.properties")) {
        var props = new Properties();
        props.load(new InputStreamReader(in, StandardCharsets.UTF_8));  // 指定 UTF-8
        String name = props.getProperty("app.name");                   // 读
    }
    // 从文件系统读取
    try (var reader = new FileReader("config.properties", StandardCharsets.UTF_8)) {
        var props = new Properties();
        props.load(reader);
    }
    ```
  - **写入**：
    ```java
    var props = new Properties();
    props.setProperty("app.name", "MyApp");        // 写
    try (var out = new FileWriter("config.properties", StandardCharsets.UTF_8)) {
        props.store(out, "comments");              // 持久化（第二个参数是注释头）
    }
    ```
  - **⚠️ 关键避坑**：读写 `Properties` 时，**只用 `getProperty()` / `setProperty()` 方法**，**不要调用它从 `Hashtable` 继承来的 `get()` / `put()` 等方法**——`get/put` 操作的是 `Object` 类型且绕过了 Properties 的字符串语义，类型不安全、易出错。
  - 面试点：`Properties` 继承自 `Hashtable<Object,Object>`（是一个"老式 Map"），但**应使用 `getProperty/setProperty`**；`.properties` 默认 ISO-8859-1（旧规范），**显式用 UTF-8 读写**防乱码；配置放 classpath 便于随 jar 分发。
- **`Set` 接口（存储不重复的元素集合）**
  - **有序性区分**：`Set` 接口**不保证有序**；而 **`SortedSet`** 接口**保证元素有序**。
    - **`HashSet` 无序**：因为它只实现了 `Set` 接口，**没实现 `SortedSet`** → 遍历顺序不可预测（同 `HashMap` 的 key 无序）。
    - **`TreeSet` 有序**：因为它实现了 `SortedSet` 接口 → 遍历时按元素排序顺序。
  - **用途**：`Set` 用来存储**不重复的元素集合**；可以**利用 `Set` 去除重复元素**（如把 `List` 丢进 `HashSet` 再取回，重复项自动消除）。
    ```java
    Set<String> set = new HashSet<>(List.of("a", "b", "a", "c"));   // 自动去重 → [a, b, c]
    set.add("a");          // 重复 add 无效（Set 不允许重复）
    set.size();            // 3
    ```
  - **元素要求（与 Map 的 key 完全一致）**：
    - **放入 `HashSet` 的元素 = 作为 `HashMap` 的 key 的要求** → 必须正确覆写 `equals()` 和 `hashCode()`。
    - **放入 `TreeSet` 的元素 = 作为 `TreeMap` 的 key 的要求** → 必须实现 `Comparable` 接口或传入 `Comparator`（即 `SortedSet` 的元素要可比较）。
  - **遍历**：`SortedSet` 按元素的**排序顺序**遍历；也可以**自定义排序算法**（传入 `Comparator` 改变排序规则）。
    ```java
    // TreeSet 默认按元素自然序（String 按字典序）；可自定义 Comparator 倒序
    Set<String> sorted = new TreeSet<>(Comparator.reverseOrder());
    sorted.addAll(List.of("banana", "apple", "cherry"));   // 遍历 → cherry, banana, apple
    ```
  - 面试点：`HashSet` 无序、`TreeSet` 有序（实现 `SortedSet`）；`Set` 去重靠 `equals`+`hashCode`；`HashSet`/`TreeSet` 的元素约束分别等同于 `HashMap`/`TreeMap` 的 key；`LinkedHashSet` 保插入序。
- **`Queue` / `Deque` 接口（队列，FIFO）**
  - **`Queue` 实现先进先出（FIFO）**：队尾进、队首出。
    - **入队**：`add()` / `offer()` —— 把元素加到**队尾**（`add` 满时抛异常，`offer` 满时返回 `false`，容量队列推荐用 `offer`）。
    - **出队并删除**：`remove()` / `poll()` —— 从**队首**取元素并删除（`remove` 空时抛异常，`poll` 空时返回 `null`，推荐用 `poll` 安全取值）。
    - **只看队首不删除**：`element()` / `peek()` —— 取队首元素（`element` 空时抛异常，`peek` 空时返回 `null`）。
    ```java
    Queue<String> q = new LinkedList<>();   // LinkedList 实现了 Queue
    q.offer("A"); q.offer("B");              // 入队尾：A, B
    q.peek();                                // "A"（队首，不删除）
    q.poll();                                // "A"（取出并删除，剩 B）
    ```
  - **⚠️ 不要把 `null` 添加到队列**：`poll()`/`peek()` 空队列时返回 `null` 来表示"无元素"，若队列本身允许 `null` 元素，就无法区分"取到 null 元素"还是"队列空"，易出 bug——所以**队列元素应避免 `null`**。
  - **`PriorityQueue`：优先队列（按优先级出队）**：
    - 从队首获取元素时，**总是获取优先级最高的元素**（不是先入先出）。
    - **默认按元素比较顺序排序**：元素**必须实现 `Comparable` 接口**（`Integer`、`String` 等都已实现，自然序）。
    - 也可以**通过 `Comparator` 自定义排序算法**，此时元素**就不必实现 `Comparable`**（`new PriorityQueue<>(comparator)`）。
    ```java
    PriorityQueue<Integer> pq = new PriorityQueue<>();   // 自然序（小顶堆）
    pq.offer(3); pq.offer(1); pq.offer(2);
    pq.poll();                                           // 1（总是最小/优先级最高）
    ```
  - 面试点：`Queue` 的 `offer`/`poll`/`peek` 与 `add`/`remove`/`element` 的区别（前者满/空时返回 `null`/`false` 不抛异常，更安全）；`PriorityQueue` 是优先队列非 FIFO、默认小顶堆、元素需 `Comparable` 或传 `Comparator`；`Deque`（双端队列，`ArrayDeque`）既可当栈也可当队列。
- **`Deque` 接口（双端队列 Double Ended Queue）**
  - 双端队列：两端（队首 / 队尾）都能进能出，于是方法成对出现，统一以 `First` / `Last` 区分：
    - **添加**：`addFirst()`/`offerFirst()`（队首）、`addLast()`/`offerLast()`（队尾）。
    - **获取并删除**：`removeFirst()`/`pollFirst()`（队首）、`removeLast()`/`pollLast()`（队尾）。
    - **获取但不删除**：`getFirst()`/`peekFirst()`（队首）、`getLast()`/`peekLast()`（队尾）。
    ```java
    Deque<String> dq = new ArrayDeque<>();
    dq.offerLast("A"); dq.offerLast("B");     // 队尾进：A, B
    dq.offerFirst("X");                       // 队首进：X, A, B
    dq.peekFirst();                           // "X"（队首）
    dq.pollLast();                            // "B"（队尾出）
    ```
  - **方法命名习惯**：**总是调用 `xxxFirst()` / `xxxLast()`** 这组方法，以便与 `Queue` 的 `add/offer/remove/poll/element/peek`（只有"一端"语义）清晰区分开，避免混淆。
  - **⚠️ 同样避免把 `null` 添加到队列**：`pollFirst/pollLast/peekFirst/peekLast` 在空队列时返回 `null` 表示"无元素"，队列含 `null` 就无法区分"取到 null"还是"队列空"。
  - 补充：`Deque` 既能当**队列（FIFO）**又能当**栈（LIFO）**——当栈时用 `push()`（= `addFirst`）/`pop()`（= `removeFirst`），推荐用 `ArrayDeque` 而非遗留的 `Stack` 类。

- **`Stack` 栈（后进先出 LIFO）**
  - **定义**：栈是一种 **LIFO（Last-In-First-Out，后进先出）** 的数据结构。
  - **三个核心操作**（返回类型都带 `E`，即栈中元素类型）：
    - 压栈：`push(E)` —— 把元素压入栈顶
    - 弹出：`pop(E)` —— 把**栈顶元素弹出**（取走并删除）
    - 取栈顶但不弹出：`peek(E)` —— 只看栈顶，不删除
  - **在 Java 中用 `Deque` 实现栈的功能**：用 `ArrayDeque` 当栈，且**只调用 `push()` / `pop()` / `peek()`** 这三个方法，避免调用 `Deque` 的其他方法（如 `offerLast`/`pollFirst` 等），否则会破坏"只能栈顶进出"的语义。
    ```java
    Deque<String> stack = new ArrayDeque<>();
    stack.push("A");        // 压栈：栈顶 A
    stack.push("B");        // 压栈：栈顶 B（A 在下面）
    stack.peek();           // "B"（看栈顶，不弹出）
    stack.pop();            // "B"（弹出栈顶，剩 A）
    ```
  - **⚠️ 不要使用遗留类 `Stack`**：`java.util.Stack` 是早期 JDK 的遗留类（继承自 `Vector`，线程安全但设计过时、性能差）；现代写法统一用 `ArrayDeque` 当栈。
  - 面试点：栈是 LIFO（`Queue` 是 FIFO）；Java 里用 `Deque`+`push/pop/peek` 模拟栈、用 `ArrayDeque` 实现；**不要用遗留 `Stack` 类**；`pop()`/`peek()` 空栈会抛 `NoSuchElementException`（`Deque` 的 `poll`/`peek` 返回 `null`，注意区分）。

- **遍历集合：`Iterator` / `for-each` / `Iterable`**
  - **自定义集合类想支持 `for-each` 循环，只需满足两个条件**：
    1. 集合类**实现 `Iterable<T>` 接口**（该接口要求实现 `iterator()` 方法）；
    2. `iterator()` **返回一个 `Iterator` 对象**，由这个 `Iterator` 去迭代集合内部数据。
    ```java
    // 典型骨架：自己写的集合实现 Iterable 就能被 for-each 遍历
    public class MyList<T> implements Iterable<T> {
        public Iterator<T> iterator() {
            return new MyIterator();   // 返回知道如何迭代的内部类
        }
    }
    // 之后就能：for (T x : myList) { ... }
    ```
  - **`Iterator` 是一种抽象的数据访问模型**（与具体集合实现解耦）。
  - **使用 `Iterator` 模式迭代的好处**：
    - 对**任何集合都采用同一种访问模型**（统一写法，不关心底层是数组还是链表还是树）；
    - 调用者对集合**内部结构一无所知**（封装得好，黑盒访问）；
    - 集合类返回的 **`Iterator` 对象自己知道如何迭代**（"怎么走"的逻辑封在迭代器里）。
  - **Java 提供的标准迭代器模型**：集合类实现 `java.util.Iterable` 接口、返回 `java.util.Iterator` 实例；`for-each` 语法糖底层就是调用这套标准接口（`Collection` 已实现，`List`/`Set` 天然可用）。
  - **迭代器核心方法**：`boolean hasNext()`（是否还有下一个）、`E next()`（取下一个）、`void remove()`（删除刚返回的，可选）。
  - 面试点：`for-each` 的本质是 `Iterable`+`Iterator`；遍历时**不要用集合自身的 `remove()` 改结构**（会 `ConcurrentModificationException`），要用 `Iterator.remove()`；`Iterator` 把"怎么遍历"封进迭代器，实现"统一访问 + 隐藏内部结构"。

- **泛型参数 `<T>`（占位类型）**
  - 以 **`ReverseList<T>`** 为例：其中的 **`<T>` 是泛型参数**，代表一个**占位类型**——意思是"这个容器可以存放**某一种类型**的对象，**在使用时才确定 T 到底是什么类型**"（如 `ReverseList<String>` 表示只能放 `String`，`ReverseList<Integer>` 表示只能放 `Integer`）。
  - 好处：编译期类型安全（放错类型直接报错）、免去手动 cast。
  - 进阶：`ReverseList<T>` 要实现 `Iterable<T>`，其 `iterator()` 就返回 `Iterator<T>`，`next()` 返回 `T`——把 `<T>` 沿"集合 → 迭代器 → 元素"一路传递，保证类型一致。
  - 关联：泛型完整内容见 `1.5 异常/泛型/IO/反射/注解`（通配符 `? extends`/`? super`、`PECS`、泛型擦除等），此处先建立"`<T>` 是占位类型、使用时确定"的直觉。

- **（待续）** 顺序已更新——`List`、`Map`、`Set`、`Queue`/`Deque`、栈、遍历（`Iterator`/`Iterable`/`for-each`）已学；剩余「`Collections`/`Arrays` 工具类 → 线程安全集合（`ConcurrentHashMap` 等）」继续按序学，边学边记。
-

### 1.3 面向对象（类/继承/多态/接口/抽象类）

- **`this` 变量（指向当前实例）**
  - 在方法内部，可以使用一个隐含的变量 `this`，它**始终指向当前实例**（即调用该方法的那个对象）。
  - 通过 `this.field` 可以访问当前实例的字段（成员变量），常用于区分**同名的参数/局部变量**与成员变量。
  ```java
  public class Person {
      private String name;
      public void setName(String name) {
          this.name = name;   // this.name 是成员变量，右边的 name 是参数
      }
  }
  ```
  - 易错/记忆：`this` 只能在**实例方法**和**构造器**里用；**静态方法（static）里没有 `this`**（静态方法不依赖实例）。
- **包（package）与文件命名规则**
  - Java 规定：**一个包下，类名不能重名**。
  - 若声明为 `public class 类名`，则**文件名必须和这个类名完全一模一样**（大小写、下划线都不能差），否则编译报错。
  - 记忆：非 public 的类文件名可不同，但 `public` 类必须与文件名一致。
- **继承（inheritance，强大的代码复用方式）**
  - 继承是面向对象编程的一种强大的**代码复用**方式：子类自动获得父类的字段和方法。
  - **Java 只允许单继承**（一个类只能有一个直接父类，可多层继承）；所有类最终的**根类都是 `Object`**（不写 `extends` 也默认继承它）。
  - `protected` 修饰的成员**允许子类访问**父类的字段和方法（也允许同包其他类访问）。
  - 子类的构造方法可以通过 **`super()`** 调用父类的构造方法（必须写在构造器第一行）。
  - **向上转型（upcasting，安全）**：子类对象可以安全地赋值给更抽象的类型（父类/接口），自动进行。
    ```java
    Person p = new Student();   // Student 是 Person 的子类，安全
    ```
  - **向下转型（downcasting，强制）**：把父类引用转回子类，**最好先用 `instanceof` 判断**，否则类型不符会抛 `ClassCastException`。
    ```java
    if (p instanceof Student s) {   // Java 16+ 模式匹配，安全向下转型
        s.study();
    }
    ```
  - **is-a vs has-a**：子类与父类是 **is 关系**（学生 is a 人）才能用继承；若是 **has 关系**（人 has 一个地址）应当用组合/字段，**不能用继承**。
- **方法覆写（Override，子类改变父类行为）**
  - 子类可以**覆写（Override）**&#x7236;类的方法，在子类中**改变父类方法的行为**（方法签名必须一致：`@Override` 注解可帮编译器检查）。
  - 覆写常用于：扩展/修正父类逻辑、实现多态。
- **多态（Polymorphism）**
  - Java 的方法调用**总是作用于运行期对象的实际类型**，这种"实际类型决定调用哪个方法"的行为称为**多态**。
  - 结合向上转型：`Person p = new Student(); p.run();` 实际调用的是 `Student.run()`（运行期类型），而非 `Person.run()`。
  - 多态的要件：**继承 + 覆写 + 向上转型**；好处是扩展性好（新增子类无需改调用方代码）。
- **`final` 修饰符（多种作用）**
  - `final` 修饰**方法** → 阻止该方法被覆写（子类不能改）。
  - `final` 修饰 **class** → 阻止该类被继承（如 `String` 就是 final 类）。
  - `final` 修饰 **field（字段）** → 必须在**创建对象时初始化**（声明时或构造器里），之后**不可修改**（即常量）。
  - 记忆口诀：final = "定死"——方法定死不可改、类定死不可继承、字段定死不可变。
- **抽象类与抽象方法（abstract）**
  - 用 `abstract` 定义的方法是**抽象方法**：**只有定义，没有实现**（无方法体 `{}`）。
  - 抽象方法定义了**子类必须实现的接口规范**（相当于"契约"）。
  - **包含抽象方法的 class 必须被声明为抽象类（`abstract class`）**。
  - 从抽象类继承的子类**必须实现（覆写）所有抽象方法**；如果**不实现**，则该子类**仍必须声明为抽象类**。
  - **面向抽象编程**：调用方只关心抽象方法的定义，不关心子类的具体实现——解耦、易扩展（依赖抽象而非具体）。
  ```java
  abstract class Animal {
      abstract void sound();     // 抽象方法：子类必须实现
  }
  class Dog extends Animal {
      void sound() { System.out.println("汪汪"); }   // 必须实现，否则 Dog 也要 abstract
  }
  ```
- **静态字段与静态方法（static）**
  - **静态字段（`static` field）**：属于**所有实例"共享"**&#x7684;字段，实际上是属于 **class** 的字段（不属于某个对象），一处修改所有实例都看得到。
    ```java
    class Person {
        static int count = 0;   // 所有 Person 实例共享，存在 class 上
    }
    ```
  - **静态方法（`static` method）**：调用**不需要实例**；方法内**无法访问 `this`**（没有当前实例），但可以访问**静态字段**和**其他静态方法**。
  - 静态方法常用于**工具类**和辅助方法（如 `Math.max()`、`Arrays.sort()`）。
  - 调用静态方法**直接用类名**：`Math.max(a, b)`、`Person.count`，无需 `new`。
  - 记忆：`static` = 属于"类级别"，与具体对象无关；实例方法能用 `this`、能访问实例字段和静态字段，静态方法则不能访问实例成员。
- **访问权限与作用域（封装原则）**
  - Java 内建的访问权限共 **4 种**：`public` / `protected` / `private` / **package 权限**（不写修饰符，默认同包可见）。
  - **封装原则**：如果不确定是否需要 `public`，就**不要声明为 `public`**——尽可能少地暴露对外的字段和方法（最小暴露）。
  - **package 权限的妙用**：把方法定义为 package 权限有助于**测试**——只要测试类和被测试类位于**同一个 package**，测试代码就能访问被测试类的 package 权限方法（无需为了测试改 public）。
  - **局部变量**：在**方法内部定义的变量是局部变量**，作用域从声明开始、到**所在块（`{}`）结束**。
  - **`.java` 文件规则**：**一个 `.java` 文件只能包含一个 `public` 类，但可以包含多个非 public 类**；若有 `public` 类，**文件名必须和该 public 类名完全相同**（与前面的"包与文件命名规则"一致）。
  - 澄清：**`final` 不是访问权限**，它可修饰 class / field / method（见上文 `final` 修饰符）。
  - 速记：4 种权限（public/protected/private/package）+ 局部变量块级作用域 + 一个文件一个 public 类 + final 非权限。


- **内部类（Inner Class / Anonymous Class / Static Nested Class）**
  - Java 的内部类可分为 **三种**：**Inner Class（成员内部类）**、**Anonymous Class（匿名内部类）**、**Static Nested Class（静态嵌套类）**。
  - **Inner Class 与 Anonymous Class 本质相同**：都是**非静态内部类**，必须**依附于 Outer Class 的实例**而存在，隐含持有 `Outer.this` 引用，因而能访问 Outer Class 的 **private 成员**。
  - **Static Nested Class 是独立类**：不依附外部类实例（`static`），但**同样拥有 Outer Class 的 private 访问权限**（访问外部类**实例**成员时需显式给一个外部类实例）。
  ```java
  public class Outer {
      private int secret = 42;     // private 字段
      private static int s = 1;
      // 1) Inner Class：必须依附 Outer 实例
      class Inner {
          void show() {
              System.out.println(secret);          // 直接访问 Outer 的 private
              System.out.println(Outer.this.secret); // 显式 Outer.this
          }
      }
      // 2) Static Nested Class：独立，不需 Outer 实例
      static class Nested {
          void show() {
              System.out.println(s);   // 可访问 Outer 的 private 静态成员
              // System.out.println(secret); // 错：无 Outer 实例，不能访问实例成员
          }
      }
      void demo() {
          // 3) Anonymous Class：Inner 的"一次性"写法（常用于接口/抽象类的就地实现）
          Inner a = new Inner() {
              @Override void show() { System.out.println("匿名"); }
          };
      }
  }
  ```
  - 创建方式差异： Inner 需先有外部实例 `Outer.Inner i = new Outer().new Inner();`；Static Nested 不需要 `Outer.Nested n = new Outer.Nested();`。
  - 易错/面试点：
    - **非静态内部类持有外部类引用** → 若内部类实例生命周期更长（如被 static 变量持有、或被线程持有），会导致**外部类无法被 GC（内存泄漏隐患）**；不依赖外部实例成员时优先用 `static nested`。
    - **匿名内部类**常用于一次性实现接口/抽象类（如 `new Thread(...)`、`Comparator`、`Runnable`、事件监听）；Java 8+ 很多场景可用 **Lambda** 替代。
    - 记忆：Inner/Anonymous = 绑实例 + 持 `Outer.this` + 能摸 private；Static Nested = 独立 + 也能摸 private（静态成员直接摸，实例成员需给实例）。

### 1.4 Java 程序基础：classpath（JVM 如何搜索 .class）

- **什么是 classpath**
  - classpath 是 **JVM 用到的一个环境变量**，用来**指示 JVM 如何搜索 class（.class 字节码文件）**。
  - Java 是**编译型语言**：源码 `.java` 经 `javac` 编译成 `.class`（字节码），**只有 `.class` 才是 JVM 真正能执行的文件**。
  - 当 JVM 要加载一个类（例如 `abc.xyz.Hello`），它按 classpath 指明的路径去搜索对应的 `Hello.class`；**包名直接映射成目录层级**：`abc.xyz.Hello` → 去 `abc/xyz/` 目录下找 `Hello.class`。
  - classpath 的每一项可以是**目录**，也可以是 **.jar / .zip 文件**（JVM 会进 jar 里按包名找 class）。
  ```bash
  # 编译：.java -> .class（产物放到 out/ 目录）
  javac -d out src/abc/xyz/Hello.java
  # 运行：用 -cp 告诉 JVM 去哪找 .class；JVM 按包名 abc.xyz 找 Hello.class
  java -cp out abc.xyz.Hello
  ```
  - 易错/澄清：
    - classpath 找的是 **.class（按包名转目录）**，不是 .java 源码；包 `abc.xyz` 必须放在 `abc/xyz/` 目录里。
    - 网上很多"如何设置 classpath"的教程把系统 `CLASSPATH` 环境变量永久配一长串——**大部分不靠谱且没必要**：现代开发用 `java -cp <路径>`、或直接靠当前目录 `.`、或交给 IDE / Maven / Spring Boot 自动管理即可，**别手改系统 CLASSPATH**。
    - 多个路径用 `;`（Windows）/ `:`（Linux·macOS）分隔；`-cp` 与 `-classpath` 等价。
    - 运行 `java` 时不指定 `-cp` 时，默认 classpath 就是**当前目录 `.`**（仅含当前目录，不含子目录）。
- **JVM 如何按 classpath 搜索（路径与顺序）**
  - JVM 通过 classpath 决定**去哪些路径、按什么顺序**搜索 class：从左到右逐个目录 / jar 扫描，**命中的第一个就加载**（所以顺序很重要，靠前的优先）。
  - classpath 的每一项可以是**目录**，也可以是 **.jar / .zip 文件**（JVM 会进 jar 里按包名找 class）。
  - 👉 **强烈建议不要设置系统环境变量 `CLASSPATH`**，建议**始终用 `java -cp <路径>` 传入**。原因：系统级 `CLASSPATH` 会污染所有 Java 程序、难排查冲突；`-cp` 作用于当次运行、清晰可控，且 IDE / Maven / Spring Boot 默认都走 `-cp`（或等价机制）。
- **jar 包（Java Archive）**
  - jar 包**本质上就是 zip 格式**，相当于一个**目录**的压缩包，里面可以包含很多 `.class` 文件，方便**下载、分发和复用**。
  - 因为 JVM 把 jar 当成"目录"看待，所以 `java -cp mylib.jar abc.xyz.Hello` 能直接从 jar 里按包名找到 `Hello.class`。
  - 打包示例：
    ```bash
    # 把 out/ 下所有 .class 打成 app.jar（普通 jar，无 Main-Class）
    jar -cvf app.jar -C out .
    # 运行：把 app.jar 放进 classpath 即可
    java -cp app.jar abc.xyz.Hello
    ```
- **MANIFEST.MF（jar 包的"说明书"）**
  - jar 里 `META-INF/MANIFEST.MF` 这个文件可以提供 jar 的元信息，最常用的是 **`Main-Class`** 字段，指明"双击/命令行入口类"。
  - 指定了 `Main-Class` 后，就能**直接运行整个 jar**：`java -jar app.jar`（JVM 会读 MANIFEST 里的 Main-Class 自动启动，无需再写 `-cp` 和全限定类名）。
  - 指定入口类打包示例：
    ```bash
    # 用 -e 指定入口类 abc.xyz.Hello，生成的 jar 可直接 java -jar 运行
    jar -cvfe app.jar abc.xyz.Hello -C out .
    # 直接运行（无需 -cp、无需写类名）
    java -jar app.jar
    ```
  - 面试点：`java -jar` 与 `java -cp` 的区别——`-jar` 以 jar 内部的 MANIFEST `Class-Path`/Main-Class 为准（此时 `-cp` 失效）；`-cp` 则显式指定搜索路径。Spring Boot 的可执行 jar 就是靠 MANIFEST 里的 `Start-Class` + 自定义类加载器实现的。
- **JDK 版本与 class 文件兼容性（编译/运行版本要匹配）**
  - **高版本 JDK 可以编译输出低版本兼容的 `.class` 文件**（向下兼容字节码）。例如 JDK 21 用 `javac -source 8 -target 8` 可产出 Java 8 能跑的 class。
  - ⚠️ **但要注意**：低版本 JDK **可能不存在高版本 JDK 才添加的类和方法**。即使字节码版本兼容，运行时若调用了高版本才有的 API（如 `List.of()` 是 Java 9 才加的），低版本 JVM 就会 **`NoSuchMethodError` / `ClassNotFoundException`** 等运行时报错——**字节码能加载 ≠ API 能用**。
  - 👉 **最佳实践**：**运行时用哪个 JDK 版本，编译时就尽量用同一版本的 JDK 编译源码**。这样能编译期就暴露 API 不兼容，而不是拖到运行时才崩。
  - 编译版本控制示例：
    ```bash
    # 用 JDK 21 编译，但想让产物能在 Java 8 上跑（仅当代码没用到 9+ 的 API）
    javac -source 8 -target 8 -bootclasspath jdk8-rt.jar Hello.java
    # Maven 推荐用 release（既设 source/target，又校验 API 只在目标版本内）：
    # <maven.compiler.release>8</maven.compiler.release>
    ```
  - 面试点：`-source/-target` 只管**语法和字节码版本**，**不校验**你用到的类库 API 是否在目标 JDK 里存在；`--release <N>`（或 Maven 的 `release`）会同时限制可访问的 API，是更安全的选择。我们项目用 JDK 21，就统一用 JDK 21 编译运行。

### 1.5 异常 / 泛型 / IO / 反射 / 注解

-

### 1.6 Lambda / Stream API

-

### 1.7 并发基础（线程/synchronized/线程池/JMM）

-

### 1.8 JVM 基础（内存结构/GC）

-

### 1.9 设计模式（单例/工厂/策略）

-

---

## 2. 阶段 2 · 数据库与工具

### 2.1 MySQL（SQL/索引/事务/锁/优化）

-

### 2.2 Redis（结构/持久化/缓存问题/分布式锁）

-

### 2.3 Git / Maven / Linux 常用

-

---

## 3. 阶段 3 · Spring Boot

### 3.1 IoC/DI / 自动配置 / 起步依赖

-

### 3.2 Spring MVC（Controller/Service/Mapper 分层）

-

### 3.3 MyBatis / MyBatis-Plus

-

### 3.4 AOP / 拦截器 / JWT / 全局异常处理

-

### 3.5 八股专题（循环依赖/Bean 作用域/事务失效）

-

---

## 4. 阶段 4 · 项目实战

### 4.1 项目 A：博客/待办后端（架构/ER 图/关键点）

-

### 4.2 项目 B：接入大模型的智能后端（API 调用/REST 暴露）

-

---

## 5. 面试题积累（冲刺期背这个）

> 格式：`问题 ｜ 我的答案要点 ｜ 参考`

- Q：Java 基本类型 vs 包装类？ ｜ 基本类型在栈、默认值明确；包装类有方法、可用于泛型；自动装箱拆箱有性能/空指针隐患 ｜ 《Java 核心技术》
-

---

## 6. 错题 / 踩坑本

> 格式：`现象 ｜ 原因 ｜ 解决`

- 现象：Maven 构建说找不到 JDK ｜ 原因：PATH 里旧 JDK8 在前 ｜ 解决：把 JDK21 的 bin 前置到 PATH，或显式设 `JAVA_HOME`
-

---

> 提示：`target/`、`.idea/`、`*.iml` 是自动生成的，不要往笔记外的事上纠结；笔记只记"人话理解的干货"。
