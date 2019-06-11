package principales;

import rx.Observable;

import java.util.Scanner;

public class HolaInfinitoControlado {

    public static void main(String ... ar) {
        Scanner teclado = new Scanner(System.in);
        Observable cli = Observable.create( (s) -> {
            while (!s.isUnsubscribed()){
                String linea = teclado.nextLine();

                if(linea.equals("adios")){
                    s.unsubscribe();
                } else {
                    s.onNext(linea);
                }
            }
        });
        System.out.println("Te imitare hasta que escribas 'adios'");

        cli.subscribe(System.out::println);
    }
}
