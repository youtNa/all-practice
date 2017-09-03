package cn.yout.practice.pattern.factory.Decorator.Dress;

public class Clothes extends Body{
    private Body body;

    public void setBody(Body body){
        this.body = body;
    }

    @Override
    public void dress() {
        if (body != null){
            body.dress();
        }
    }
}
