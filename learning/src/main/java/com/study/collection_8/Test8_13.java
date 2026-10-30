package com.study.collection_8;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import java.util.Stack;

public class Test8_13 {
    public static void main(String[] args) {
        //1. 请利用Stack把一个给定的整数转换为十六进制：
        String hex = toHex(12500);
        if (hex.equalsIgnoreCase("30D4")) {
            System.out.println("测试通过");
        } else {
            System.out.println("测试失败");
        }
        System.out.println("-------------------------------------------------");
//        toHex(12500);
        //2. 请利用Stack把字符串中缀表达式编译为后缀表达式，然后再利用栈执行后缀表达式获得计算结果
        String exp = "1 + 2 * ( 9 - 5 )";
        SuffixExpression se = compile(exp);
        int result = se.execute();
        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * ( 9 - 5 ) ? "✓" : "✗"));
    }
    static String toHex(int n) {
        //利用栈将十进制转为16进制
        //不断对16取余  先得到的余数是高位 利用栈先进后出
        if(n==0){
//            return "0";//排除边界情况
            System.out.println("0");
        }
        //用一个映射表来对应余数  利用数组的下标来映射
        char[] map = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        Stack<Character> stack = new Stack<>();
        while(n>0){
            int tmp=n%16;
            n=n/16;
            stack.push(map[tmp]);
        }
        StringBuffer sb = new StringBuffer();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
    static SuffixExpression compile(String exp) {
        // TODO:
        //最终目标是为了返回后缀表达式 我们首先要进行分词 按照空格进行分割
        String[] tokens = exp.split(" ");
        Stack<String> stack = new Stack<>();//用来存放运算符 左括号
        List<String> list = new ArrayList<>();//用来存放结果
        for(String token : tokens){
           if(token.equals("(")){
               //如果是左括号那么就入栈
               stack.push(token);
           }else if(token.equals(")")){
               //如果是右括号那么就出栈直到遇到左括号
               while(!stack.peek().equals("(")){
                   list.add(stack.pop());
               }
               //并且要去掉左括号
               stack.pop();
           }else if(isOp(token)){
               //如果是运算符 保证比栈顶元素更大才会循环弹出
               while((!stack.isEmpty())&&(!stack.peek().equals("("))&&(priority(stack.peek())>=priority(token))){//当栈非空并且栈顶元素不是（
                    list.add(stack.pop());//比token优先级高的都应该弹出
               }
               stack.push(token);
           }else{
               //如果是数字的话直接加入结果列表中
               list.add(token);
           }
        }
        while(!stack.isEmpty()){
            list.add(stack.pop());
        }

        return new SuffixExpression(list);
    }
    //判断是不是运算符
    static boolean isOp(String s){
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
    //返回优先级数字
    static int priority(String s){
        if(s.equals("*")||s.equals("/")){
            return 2;
        }else if(s.equals("+")||s.equals("-")){
            return 1;
        }else{
            return 0;
        }
    }
}
class SuffixExpression {
    List<String> list;
    public SuffixExpression(List<String> list) {
        this.list = list;
    }

    int execute() {
        // TODO:
        Stack<Integer> stack = new Stack<>();
        for(String s :list){
            //遍历列表中的数据  用这个静态方法
            if(Test8_13.isOp(s)){
//                如果是运算符的话
               int a=stack.pop();
               int b=stack.pop();
               int res=switch(s){
                   case "+" -> b+a;
                   case "-" -> b-a;
                   case "*" -> b*a;
                   case "/" -> b/a;
                   default -> 0;
               };//比较新的switch用法
                stack.push(res);
            }else{
                stack.push(Integer.parseInt(s));
            }

        }
        return stack.pop();
    }
}
