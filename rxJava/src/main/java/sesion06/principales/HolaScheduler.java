package sesion06.principales;

import rx.Observable;
import rx.schedulers.Schedulers;

public class HolaScheduler {

    public static void logea(String log){
        System.out.println("[ "+ Thread.currentThread().getName() + " ]  " + log);
    }

    public static void main(String ... a) throws InterruptedException {

        logea("Creando Observable...");

        Observable<String> publicista = Observable
                .just("A", "B", "C", "D")
                .doOnCompleted(()->logea("Completado"));

        logea("Iniciando Subscripcion1 a Observable...");
        publicista.subscribeOn(Schedulers.newThread())
                .subscribe(HolaScheduler::logea);

        logea("Iniciando Subscripcion2 a Observable...");
        publicista.subscribeOn(Schedulers.newThread())
                .subscribe(HolaScheduler::logea);

        logea("FIN");

        Thread.sleep(300);
    }
}
