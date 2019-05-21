package com.yobijoss.rxjavaplayground.examples;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread " + Thread.currentThread().getId());

        Observable<Long> observable1 = Observable.interval(1000, TimeUnit.MILLISECONDS).take(10);

        Observable<Long> observable2 = Observable.interval(500, TimeUnit.MILLISECONDS).take(10);

        Observable.merge(observable1, observable2).subscribe(value ->
                System.out.println( Thread.currentThread().getId() + " " + value));

        observable2.blockingFirst();

        //System.out.println("Blocked first " + Thread.currentThread().getId() + " " + observable2.blockingFirst());

        Thread.sleep(15000);

    }

}
