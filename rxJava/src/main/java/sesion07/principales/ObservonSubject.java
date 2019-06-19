package sesion07.principales;

import rx.Observable;
import rx.internal.schedulers.CachedThreadScheduler;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class ObservonSubject {
    public static void log(String s) {
        System.out.println("[" + Thread.currentThread().getName() + "]  " + s);
    }

    public static void main(String... a) throws InterruptedException {
        Observable<Long> intervalo = Observable.interval(100, TimeUnit.MILLISECONDS).take(100);
//        Observable<Long> pares = intervalo.filter(i -> i%2==0);
//        Observable<Long> impares = intervalo.filter(i -> i%2!=0);

        PublishSubject<Long> control = PublishSubject.create();

        intervalo.observeOn(Schedulers.newThread()).subscribe(control);

        PublishSubject<Long> sujetoimpar = PublishSubject.create();
        PublishSubject<Long> sujetopar = PublishSubject.create();

        control.subscribe(sujetoimpar);
        control.subscribe(sujetopar);

        sujetoimpar.observeOn(Schedulers.newThread()).subscribe(l -> { // QUe pasa con ObserveOn o SubscribeOn??
           if(l%2!=0)
               log("S-IMP: " + l);
        });

        sujetopar.observeOn(Schedulers.newThread()).subscribe(l -> {
            if(l%2==0)
                log("S-PAR: " + l);
        });

        Thread.sleep(10000);
    }
}

