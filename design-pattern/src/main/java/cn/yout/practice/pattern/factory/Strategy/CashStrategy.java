package cn.yout.practice.pattern.factory.Strategy;

/**
 * 策略类，定义所有支持的算法的公共接口。
 */
public abstract class CashStrategy {
    public abstract double acceptCash(int number,double price);
}
