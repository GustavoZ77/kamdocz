package com.yobijoss.rxjavaplayground.samples;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Main {

    public static void main(String... args) {
        MainObservable mainObservable = new MainObservable();

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("on subscribe " + d);
            }

            @Override
            public void onNext(Object value) {
                System.out.println("onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println(e);
            }

            @Override
            public void onComplete() {
                System.out.println("On complete");
            }
        };

        mainObservable.getJustObservable().subscribe(observer);

        mainObservable.getObservableForCreate().subscribe(observer);

        mainObservable.getObservableForDefer().subscribe(observer);
        mainObservable.getObservableForDefer().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("On subscribe anon " + d);
            }

            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("on Complete Anon");
            }
        });

    }
}
