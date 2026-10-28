package com.study.quickstart_2;

public class Test2_5_3 {
    public static void main(String[] args) {
        // 用二维数组表示的学生成绩:
        int[][] scores = {
                { 82, 90, 91 }, // 学生甲的语数英成绩
                { 68, 72, 64 }, // 学生乙的语数英成绩
                { 95, 91, 89 }, // ...
                { 67, 52, 60 },
                { 79, 81, 85 },
        };
        // TODO:
        double average = 0;
        double sum=0;
//        System.out.println(scores.length);
        for(int j=0;j<scores.length;j++){
            for(int i:scores[j]){
                sum+=i;
            }
        }
        average = (double)sum/(scores.length*3);

        System.out.println(average);
        if (Math.abs(average - 77.733333) < 0.000001) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }
    }
}
