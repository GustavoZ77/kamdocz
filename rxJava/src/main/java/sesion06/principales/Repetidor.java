package sesion06.principales;

import rx.Observable;
import rx.observables.ConnectableObservable;
import java.util.ArrayList;
import java.util.List;

public class Repetidor {

    public static void main(String ... a){
        List<String> numeros = new ArrayList<>();
        numeros.add("1");
        numeros.add("2");
        numeros.add("III");
        numeros.add("4");
        numeros.add("5");
        numeros.add("VI");
        numeros.add("7");

        Observable<String> obsNumeros = Observable.from(numeros);
//        obsNumeros.repeat(3).subscribe(
//                (n) -> {
//                    System.out.println("[1] > " + n );
//                },
//                (t) -> {
//                    System.out.println("[1] Error > " + t.getMessage() );
//                },
//                () -> {
//                    System.out.println("[1] Completado" );
//                }
//        );

        //////// Connectable Observable
        ConnectableObservable<String> connObsNumeros = Observable.from(numeros).publish();

        connObsNumeros.subscribe(
                (n) -> {
                    System.out.println("[Connectable1] > " + n );
                },
                (t) -> {
                    System.out.println("[Connectable1] Error > " + t.getMessage() );
                },
                () -> {
                    System.out.println("[Connectable1] Completado" );
                }
        );

        connObsNumeros.repeat(3).subscribe(
                (n) -> {
                    System.out.println("[Connectable2] > " + n );
                },
                (t) -> {
                    System.out.println("[Connectable2] Error > " + t.getMessage() );
                },
                () -> {
                    System.out.println("[Connectable2] Completado" );
                }
        );

        connObsNumeros.connect();
    }

}