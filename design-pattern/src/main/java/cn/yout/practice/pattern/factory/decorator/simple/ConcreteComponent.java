package cn.yout.practice.pattern.factory.decorator.simple;

public class ConcreteComponent extends Component {
    @Override
    public void Operation() {
        System.out.println("具体对象的操作");
    }
}
