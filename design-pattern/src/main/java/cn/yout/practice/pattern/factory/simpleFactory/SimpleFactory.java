package cn.yout.practice.pattern.factory.simpleFactory;

import java.util.Scanner;

/**
 * 以实现简单计算器作为例子描述简单工厂模式
 */
public class SimpleFactory {

    public static void main(String[] args) throws Exception{
        //输入
        System.out.print("请选择输入数字A：");
        Scanner scanner = new Scanner(System.in);
        double parameterA = scanner.nextDouble();
        System.out.print("请输入运算符号（+／-／*／／）：");
        scanner = new Scanner(System.in);
        String operate = scanner.nextLine();
        System.out.print("请输入数字B：");
        scanner = new Scanner(System.in);
        double parameterB = scanner.nextDouble();

        //计算
        Operation operation = OperationFactory.createOperation(operate);
        operation.setParameterA(parameterA);
        operation.setParameterB(parameterB);
        double result = operation.getResult();
        System.out.println("输出结果为：" + result);
    }
}
