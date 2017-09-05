package cn.yout.practice.pattern.factory.factoryMethod.leifeng;

public class UndergraduateFactory implements LeifengFactory {
    @Override
    public Leifeng createLeifeng() {
        return new Undergraduate();
    }
}
