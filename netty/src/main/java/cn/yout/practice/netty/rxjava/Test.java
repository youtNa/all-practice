package cn.yout.practice.netty.rxjava;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;

/**
 * @author day.na
 * @date 2018/12/13
 */
public class Test {
    public static void main(String[] args) {
        Observable.fromIterable(Arrays.asList("one","two"))
                .map(one -> one + " map" + Thread.currentThread().getName())
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println);
        System.out.println("1111" + Thread.currentThread().getName());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
