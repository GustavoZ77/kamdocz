package sesion07.principales;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class ObservonV3 {
    public static void log(String s){
        System.out.println("[" + Thread.currentThread().getName() +"]  " + s);
    }

    public static void main(String ... a) throws InterruptedException{
        Observable<Long> intervalo = Observable.interval(100, TimeUnit.MILLISECONDS).take(100);
        //observeOn, especifica el Hilo en el que se ejecutaran las operaciones seguientes
        Observable<Long> pares = intervalo.observeOn(Schedulers.newThread()).filter(i -> i%2==0);

        Observable<Long> impares = intervalo.observeOn(Schedulers.newThread()).filter(i -> i%2!=0);

        pares.subscribeOn(Schedulers.newThread()).subscribe(l -> log("PAR: " + l));
        impares.subscribeOn(Schedulers.newThread()).subscribe(l -> log("IMP: "+ l));

        Thread.sleep(10000);
    }
}
