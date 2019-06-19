package sesion07.principales;

import rx.Observable;
import rx.schedulers.Schedulers;

public class ObservonV1 {

    public static void log(String s){
        System.out.println("[" + Thread.currentThread().getName() +"]  " + s);
    }
    public static void main(String ... a) throws InterruptedException{
        Observable<Integer> entrada = Observable.just(1,2,3,4,5,6);
        log("iniciando..."); //observeOn, especifica el Hilo en el que se ejecutaran las operaciones seguientes
        entrada.observeOn(Schedulers.newThread())
                .map((v)->{
                    log("mapeando");
                    return v*v;
                }).subscribe(c->log(c.toString()));
        log("terminando...");
        Thread.sleep(200);
        log("Fin.");
    }
}
