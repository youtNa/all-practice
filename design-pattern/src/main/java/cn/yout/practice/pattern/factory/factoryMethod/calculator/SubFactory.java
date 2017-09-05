package cn.yout.practice.pattern.factory.factoryMethod.calculator;

public class SubFactory implements IFactory{
    @Override
    public Operation createOperation() {
        return new Operation() {
            @Override
            public int getResult() {
                return getNumberA() - getNumberB();
            }
        };
    }
}
