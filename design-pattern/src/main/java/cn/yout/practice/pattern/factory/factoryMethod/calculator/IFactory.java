package cn.yout.practice.pattern.factory.factoryMethod.calculator;

public interface IFactory {
    /**
     *创建operation方法
     */
    public Operation createOperation();
}
