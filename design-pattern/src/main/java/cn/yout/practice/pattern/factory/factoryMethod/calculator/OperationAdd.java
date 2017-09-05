package cn.yout.practice.pattern.factory.factoryMethod.calculator;

public class OperationAdd extends Operation{
    @Override
    public int getResult() {
        return getNumberA() + getNumberB();
    }
}
