package principales;

import rx.Observable;

import java.math.BigInteger;

public class HolaInfinito {

    public static void main(String ... args) {
        Observable<Integer> infinito32 = Observable.create((s) -> {
             Integer n = 0;
             while(!s.isUnsubscribed()){
                 s.onNext(n);
                 n++;
             }
        });

        infinito32.subscribe(System.out::println);
    }

}
