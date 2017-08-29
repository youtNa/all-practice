package cn.yout.practice.pattern.factory.Strategy;

/**
 * 以计价器为例子，解释策略模式，其中Context上下文加上了简单工厂模式，
 * 以选择多个计算模型。
 */
public class Strategy {
    public static void main(String[] args) throws Exception{
        String promotion = "折扣";
        Context context = new Context(promotion);
        double total = context.getCash(30,20D);
        System.out.println(total);
    }
}
