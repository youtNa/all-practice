package cn.yout.practice.pattern.factory.strategy;

/**
 * 具体策略类，封装了具体的算法家族或行为，继承于Strategy。
 * 所有的计算模型（算法家族）
 */
public class CashSuper {

    /**
     * 正常
     */
    public static class CashNormal extends CashStrategy{
        CashNormal(){
            super();
        }
        @Override
        public double acceptCash(int number,double price) {
            return number * price;
        }
    }

    /**
     * 折扣
     */
    public static class CashRebate extends CashStrategy{
        double rebate;
        CashRebate(double rebate){
            this.rebate = rebate;
        }

        @Override
        public double acceptCash(int number,double price) {
            return number * price * rebate;
        }
    }

    /**
     * 满减
     */
    public static class CashReturn extends CashStrategy{
        double over;
        double sub;
        CashReturn(double over,double sub){
            this.over = over;
            this.sub = sub;
        }

        @Override
        public double acceptCash(int number,double price) {
            double total = number * price;
            if (total >= over){
                total = total - sub;
            }

            return total;
        }
    }


    /**
     * 测试用
     * @param args args
     */
    public static void main(String[] args){
        System.out.println();
    }
}
