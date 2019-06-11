package sesion06.principales;

import rx.Observable;
import rx.subjects.PublishSubject;

public class SubjectObservable {

    public static void main(String ... a){

        PublishSubject<String> publicador = PublishSubject.create();

        publicador.subscribe((s) -> {
            System.out.println("PRIMER subscriptor..." + s);
        });
        publicador.onNext("Mi primer señal");
        publicador.onNext("Mi segunda señal");
        publicador.subscribe((s) -> {
            System.out.println("SEGUNDO subscriptor..." + s);
        });
        publicador.onNext(" Tercer señal");
        publicador.subscribe((s) -> {
            System.out.println("TERCER subscriptor..." + s);
        });
        publicador.onNext(" Cuarta señal");
        publicador.onNext(" Quinta señal");
    }
}
