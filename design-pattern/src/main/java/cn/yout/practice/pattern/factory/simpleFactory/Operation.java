package cn.yout.practice.pattern.factory.simpleFactory;

public class Operation {
    private double parameterA;
    private double parameterB;

    public double getParameterA() {
        return parameterA;
    }

    public void setParameterA(double parameterA) {
        this.parameterA = parameterA;
    }

    public double getParameterB() {
        return parameterB;
    }

    public void setParameterB(double parameterB) {
        this.parameterB = parameterB;
    }

    public double getResult(){
        return 0;
    }
}
