package com.yobijoss.rxjavaplayground.samples;

import io.reactivex.*;

import java.util.concurrent.Callable;

public class MainObservable {

    public Observable<String> getJustObservable() {
        // this class emits a stream of data(objects) or events(methods?)
        return Observable.just("a", "b", "c", "d");
    }

    public Observable<String> getObservableForCreate() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                System.out.println("Alguien se suscribió a mi :D " + e.toString());
            }
        });
    }

    public Observable<String> getObservableForDefer() {
        return Observable.defer(new Callable<ObservableSource<String>>() {

            @Override
            public ObservableSource<String> call() throws Exception {
                return new Observable<String>() {
                    @Override
                    protected void subscribeActual(Observer<? super String> observer) {
                        System.out.println("creating new observable for the next observer:  " + observer.toString());
                    }
                };
            }
        });
    }


}
