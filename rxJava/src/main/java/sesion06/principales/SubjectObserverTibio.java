package sesion06.principales;

import rx.Observable;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

public class SubjectObserverTibio {

    public static void main(String ... ar) throws InterruptedException {

        Observable<Long> crono = Observable.interval(300, TimeUnit.MILLISECONDS);

        PublishSubject<Long> publicistaObservador = PublishSubject.create();

        Observable otro = publicistaObservador.doOnNext(t -> {
            Long transc = t*300;
            System.out.println("Publicista dice han pasado " + transc + " millisec");
        });

        crono.subscribe(publicistaObservador);

        publicistaObservador.onNext(new Long(600));

        otro.subscribe(t -> {
            System.out.println("Señal " + t);
        });

        Thread.sleep(1000);
    }
}
