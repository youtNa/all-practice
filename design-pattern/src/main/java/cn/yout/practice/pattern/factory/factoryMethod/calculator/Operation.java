package cn.yout.practice.pattern.factory.factoryMethod.calculator;

public abstract class Operation {
    private int numberA;
    private int numberB;
    /**
     * 计算结果方法
     */
    public abstract int getResult();

    public int getNumberA() {
        return numberA;
    }

    public void setNumberA(int numberA) {
        this.numberA = numberA;
    }

    public int getNumberB() {
        return numberB;
    }

    public void setNumberB(int numberB) {
        this.numberB = numberB;
    }
}
