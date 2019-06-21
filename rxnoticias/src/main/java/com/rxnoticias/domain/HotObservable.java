package com.rxnoticias.domain;

import com.rxnoticias.utils.ThreadUtils;
import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class HotObservable {

    public static void main(String... args) {
        Scanner teclado = new Scanner(System.in);

        PublishSubject<String> subject = PublishSubject.create();

        subject
            .filter(line -> line.contains("resta"))
            .map(line -> line.split(" "))
            .subscribe(lineArr -> {
                        if(lineArr.length == 3) {
                            int result = Integer.valueOf(lineArr[1]) - Integer.valueOf(lineArr[2]);
                            System.out.println(result);
                        } else {
                            System.out.println("Operacion de resta invalida");
                        }
                    },
                    err -> System.out.println(err.getLocalizedMessage()));

        subject
            .filter(line -> line.contains("suma"))
            .map(line -> line.split(" "))
            .subscribe(lineArr -> {
                        if(lineArr.length == 3) {
                            int result = Integer.valueOf(lineArr[1]) + Integer.valueOf(lineArr[2]);
                            System.out.println(result);
                        } else {
                            System.out.println("Operacion de suma invalida");
                        }
                    },
                    err -> System.out.println(err.getLocalizedMessage()));

        subject
            .filter(line -> line.contains("imprime"))
            .map(line -> line.split(" "))
            .subscribe(
                    lineArr -> System.out.println(lineArr[1]),
                    err -> System.out.println(err.getLocalizedMessage()));

        // Hot observable
        Observable<String> hotObservable = Observable.create(s -> {
            while (!s.isUnsubscribed()) {
                String linea = teclado.nextLine();

                if (linea.equals("bye")) {
                    s.unsubscribe();
                    s.onCompleted();
                    } else {
                        s.onNext(linea);
                    }
            }
        });

        hotObservable
            .subscribe(
                line -> subject.onNext(line),
                err -> System.out.println(err.getLocalizedMessage()),
                () -> System.out.println("Completed..."));

    }

}
