package cn.yout.practice.netty.time;

import java.util.Date;

public class UnixTime {
    private final long value;

    public UnixTime(){
        this(System.currentTimeMillis());
    }

    public UnixTime(long value){
        this.value = value;
    }

    public long getValue(){
        return value;
    }

    @Override
    public String toString(){
        return new Date(value).toString();
    }

}
