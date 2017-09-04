package cn.yout.practice.pattern.factory.decorator.dress;

public class Jacket extends Clothes{
    @Override
    public void dress() {
        System.out.print("夹克 ");
        super.dress();
    }
}
