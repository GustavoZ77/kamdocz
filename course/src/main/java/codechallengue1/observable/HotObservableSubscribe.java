package codechallengue1.observable;

import rx.Observable;
import rx.Subscriber;

import java.util.Scanner;

public class HotObservableSubscribe implements Observable.OnSubscribe<String[]> {

    @Override
    public void call(Subscriber<? super String[]> subscriber) {
        Scanner teclado = new Scanner(System.in);

        while (!subscriber.isUnsubscribed()) {
            System.out.println("Introduce Commando");
            String[] linea = teclado.nextLine().split("\\s+");

            if (linea.length > 0 && !linea[0].isEmpty()) {
                if (linea[0].equals("adios")) {
                    subscriber.onCompleted();
                    subscriber.unsubscribe();
                } else {
                    subscriber.onNext(linea);
                }
            } else {
                subscriber.onError(new Exception("Entrada Invalida"));
            }

        }
    }
}

