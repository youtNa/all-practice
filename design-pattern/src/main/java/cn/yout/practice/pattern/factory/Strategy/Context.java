package cn.yout.practice.pattern.factory.Strategy;

/**
 * 上下文
 */
public class Context {
    static CashStrategy cashStrategy;
    public Context(String type) throws Exception{
        switch (type){
            case "正常":
                cashStrategy = new CashSuper.CashNormal();
                break;
            case "折扣":
                cashStrategy = new CashSuper.CashRebate(0.8);
                break;
            case "满减":
                cashStrategy = new CashSuper.CashReturn(300D,100D);
                break;
            default:
                throw new Exception("活动方式不可选");
        }
    }

    public double getCash(int number,double price){
        return cashStrategy.acceptCash(number,price);
    }

}
