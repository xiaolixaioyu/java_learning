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

- **字符串 `String` 是不可变对象（immutable）**
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
