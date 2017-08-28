package cn.yout.practice.pattern.factory.SimpleFactory;

public class OperationFactory {

    public static Operation createOperation(String operate) throws Exception{
        Operation operation;
        switch (operate){
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMul();
                break;
            case "/":
                operation = new OperationDiv();
                break;
            default:
                throw new Exception("运算符号输入错误。");
        }
        return operation;
    }

    public static class OperationAdd extends Operation {
        @Override
        public double getResult(){
            return getParameterA() + getParameterB();
        }
    }

    public static class OperationSub extends Operation{
        @Override
        public double getResult(){
            return getParameterA() - getParameterB();
        }
    }

    public static class OperationMul extends Operation{
        @Override
        public double getResult(){
            return getParameterA() * getParameterB();
        }
    }

    public static class OperationDiv extends Operation{
        @Override
        public double getResult(){
            return getParameterA() / getParameterB();
        }
    }
}
