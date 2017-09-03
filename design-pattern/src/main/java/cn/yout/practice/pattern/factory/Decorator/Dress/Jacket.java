package cn.yout.practice.pattern.factory.Decorator.Dress;

public class Jacket extends Clothes{
    @Override
    public void dress() {
        System.out.print("夹克 ");
        super.dress();
    }
}
