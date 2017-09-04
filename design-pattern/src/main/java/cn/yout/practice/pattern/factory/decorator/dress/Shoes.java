package cn.yout.practice.pattern.factory.decorator.dress;

public class Shoes extends Clothes{

    @Override
    public void dress() {
        System.out.print("鞋子 ");
        super.dress();
    }
}
