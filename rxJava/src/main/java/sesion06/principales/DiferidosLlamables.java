package sesion06.principales;

import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class DiferidosLlamables {

    public static String value;

    public static Observable<String> alMomento() {
        return Observable.just(value);
    }

    public static Observable<String> alSubscribirse() {
        return Observable.defer(()->Observable.just(value));
    }

    public static Observable<String> alSubscribirse2() {

        return Observable.defer(()->
        {
            List<String> valores = new ArrayList<>();
            valores.add(value);
            valores.add(value + "2");
            return Observable.from(valores);
        });
    }

    public static Observable<String> alLlamarlo() {
        return Observable.fromCallable(() -> value);
    }

    public static Observable<List> alLlamarlo2() {
        return Observable.fromCallable( () ->
        {
            List<String> valores = new ArrayList<>();
            valores.add(value);
            valores.add(value + "2");
            return valores;
        } );
    }


    public static void main(String ... arg){

        Observable observable1 = alMomento();
        Observable observable2 = alSubscribirse();
        Observable observable3 = alSubscribirse2();
        Observable observable4 = alLlamarlo();
        Observable observable5 = alLlamarlo2();

        value = "soy inevitable!";

        observable1.subscribe(System.out::println);
        System.out.println("====================");
        observable2.subscribe(System.out::println);
        System.out.println("====================");
        observable3.subscribe(System.out::println);
        System.out.println("====================");
        observable4.subscribe(System.out::println);
        System.out.println("====================");
        observable5.subscribe(System.out::println);

    }
}
