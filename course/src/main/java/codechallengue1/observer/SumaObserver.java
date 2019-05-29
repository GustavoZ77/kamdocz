package codechallengue1.observer;

import codechallengue1.ValidUtils;
import rx.Observer;

public class SumaObserver implements Observer<String[]> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(String[] strings) {
        if (strings[0].equals("suma")) {
            if (strings.length > 2) {
                if (ValidUtils.isFloat(strings[1]) && ValidUtils.isFloat(strings[2])) {
                    System.out.println("suma " + (Float.parseFloat(strings[1]) + Float.parseFloat(strings[2])));
                } else {
                    System.err.println("Operación suma Invalida");
                }
            } else {
                System.err.println("Operación suma Invalida");
            }
        }
    }
}
