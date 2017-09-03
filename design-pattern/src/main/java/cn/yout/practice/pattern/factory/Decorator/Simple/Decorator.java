package cn.yout.practice.pattern.factory.Decorator.Simple;

public abstract class Decorator extends Component{
    private Component component;

    public void SetComponent(Component component){
        this.component = component;
    }

    @Override
    public void Operation() {
        if (component != null){
            component.Operation();
        }
    }
}
