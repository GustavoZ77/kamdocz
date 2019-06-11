package sesion06.principales;

import rx.Observable;
import rx.subjects.PublishSubject;


public class SubjectObserverFrio {

    public static void main(String ... ar) {
        String[] palabras = new String[]{
                "hola", "como", "estas","?"
        };

        Observable<String> palabrasObservable = Observable.from(palabras);

        PublishSubject<String> publicistaObservador = PublishSubject.create();

        publicistaObservador.subscribe(p -> {
            System.out.println("Hola " + p);
        });
        publicistaObservador.onNext("adios");
        palabrasObservable.subscribe(publicistaObservador);

    }
}
