package cn.yout.practice.pattern.factory.simpleFactory;

public class Point {
    private int x,y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void set(int x , int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return x + ":" + y;
    }
}
