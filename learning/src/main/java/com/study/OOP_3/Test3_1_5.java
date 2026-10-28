package com.study.oop_3;
class Income{
    protected double income;
        public Income(double income){
            this.income=income;
        }
        public double getTax(){
            return income*0.1;//税率是10%
        }
}
class SalaryIncome extends Income{
    public SalaryIncome(double income){
        super(income);//直接调用父类的有参构造
    }
    @Override
    public double getTax(){
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}
class RoyaltyIncome extends Income{
    public RoyaltyIncome(double income){
        super(income);
    }

    @Override
    public double getTax() {
        return income*0.2;
    }
}
public class Test3_1_5 {
    public static void main(String[] args) {
        // TODO: 给一个有工资收入和稿费收入的小伙伴算税:
        Income[] incomes = new Income[] { new Income(3000), new SalaryIncome(7500), new RoyaltyIncome(12000) };
        double total = 0;
        // TODO:
        for (Income income : incomes) {
            total+=income.getTax();
        }
        System.out.println(total);
    }
}
