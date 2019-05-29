package codechallengue1.observer;

import rx.Observer;

import java.util.Arrays;

public class ImprimeObserver implements Observer<String[]> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(String[] strings) {
        if (strings[0].equals("imprime")) {
            if (strings.length >= 2) {
                System.out.println(String.join(" ", Arrays.copyOfRange(strings, 1, strings.length)));
            } else {
                System.err.println("Operación Imprime Invalida");
            }
        }
    }
}
