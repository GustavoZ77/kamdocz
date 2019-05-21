package com.yobijoss.rxjavaplayground.samples.push;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PushPullStream {

    public static void main(String[] args) {

        List<String> names = Stream.of("Joss", "Angel", "Martha").collect(Collectors.toList());

        Observable<String> observable = Observable.create((ObservableEmitter<String> e) -> {
            //pushear los nombres
            names.forEach(e::onNext);
            e.onComplete();
        });

        //pull, subscribirse y recibir del emitter?
        Disposable disposable = observable.subscribe(System.out::println, Throwable::printStackTrace);

        disposable.dispose();

        DisposableObserver disposableComplete = observable.subscribeWith(new DisposableObserver<String>() {

            @Override
            public void onNext(String value) {
                System.out.println("From Disposable Observer" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("From Disposable Observer: onComplete");

            }
        });

        disposableComplete.dispose();

    }
}
