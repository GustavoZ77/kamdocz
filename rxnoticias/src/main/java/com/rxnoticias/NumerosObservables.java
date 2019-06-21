package com.rxnoticias;

import rx.Observable;

import java.util.function.BiFunction;

public class NumerosObservables {

    public static Observable<Integer> getIntegerObs(Integer entero) {
        return Observable.fromCallable(() -> entero);
    }

    public static Observable<BiFunction<Integer, Integer, Integer>> getSumaIntegerObs() {
        return Observable.fromCallable(() -> (x, y) -> x + y);
    }

    public static Observable<BiFunction<Integer, Integer, Integer>> getRestarIntegerObs() {
        return Observable.fromCallable(() -> (x, y) -> x - y);
    }

    public static Observable<BiFunction<Integer, Integer, Integer>> getMultiplicarIntegerObs() {
        return Observable.fromCallable(() -> (x, y) -> Math.multiplyExact(x, y));
    }

    public static Observable<Integer> getTotal(
            Observable<BiFunction<Integer, Integer, Integer>> f,
            Observable<Integer> a,
            Observable<Integer> b) {

        return Observable.zip(f, a, b, (x, y, z) -> x.apply(y, z));
    }

}
