package cn.yout.practice.pattern.factory.decorator.teacheDress;

/**
 * 大话设计模式提供的应用场景例子
 *
 */
public class Main {
    public static void main(String[] args){

        Person person = new Person("张三");
        ConcreteDecorator.BigTrouser bigTrouser = new ConcreteDecorator.BigTrouser();
        ConcreteDecorator.TShirt tShirt = new ConcreteDecorator.TShirt();

        bigTrouser.decorate(person);
        tShirt.decorate(bigTrouser);
        tShirt.show();
    }

}
