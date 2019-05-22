package Sesion03;

import rx.Observable;

import java.util.Scanner;

public class HolaInfinitoControlado {

    public static void main(String... ar) {
        Scanner teclado = new Scanner(System.in);

        Observable<String> cli = Observable.create(subscriber -> {
            while (!subscriber.isUnsubscribed()) {
                String linea = teclado.nextLine();

                if (linea.equals("adios")) {
                    subscriber.onCompleted();
                    subscriber.unsubscribe();
                } else if (linea.equals("error")) {
                    subscriber.onError(new Exception("Todo se está quemando"));
                } else {
                    subscriber.onNext(linea);
                }
            }
        });

        System.out.println("Te imitare hasta que escribas 'adios'");

        cli.subscribe(System.out::println,
                throwable -> System.err.println(throwable.getMessage()),
                () -> System.out.println("this was completed"));
    }
}
