package sesion06.principales;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;

public class Reintentador {

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
        obsNumeros.map(Integer::new).retry(3).
                subscribe(
                        System.out::println,
                        (t)->{System.out.println("Despues de 4 intentos no se pudo");}
                );

        obsNumeros
                .doOnNext((n) -> new Integer(n))
                .retry(2)
                .subscribe(
                        System.out::println,
                         (t)->{System.out.println("Despues de 3 intentos no se pudo");}
                         );
    // RetryHandler v2 (Error Handling)
    }
}
