package cn.yout.practice.pattern.factory.decorator.teacheDress;

public class ConcreteDecorator {

    public static class TShirt extends Finery{
        @Override
        public void show() {
            System.out.print("T恤 ");
            super.show();
        }
    }

    public static class BigTrouser extends Finery{
        @Override
        public void show() {
            System.out.print("垮裤 ");
            super.show();
        }
    }

}
