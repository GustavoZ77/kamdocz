package sesion06.principales;

import rx.Observable;
import rx.schedulers.Schedulers;

public class SchedulerLlamable {

    public static void logea(String log){
        System.out.println("[ "+ Thread.currentThread().getName() + " ]  " + log);
    }

    public static  Observable<Integer> getOperando(long duracion) {
        return Observable.fromCallable(
                ()->{
                    logea("Calculando el Operando, tiempo estimado: " + duracion);
                    Thread.currentThread().sleep(duracion);
                    return new Integer( (int)duracion/10);
                }
        );

    }

    public static void main(String ... a) throws InterruptedException {
        logea("Iniciando...");
        Observable<Integer> llamable = getOperando(300)
                .subscribeOn(Schedulers.newThread())
                .doOnCompleted(()->logea("Completado."));
        Observable<Integer> llamable2 = getOperando(400)
                .subscribeOn(Schedulers.newThread())
                .doOnCompleted(()->logea("Completado."));

//        llamable.subscribe( (v)->logea(v.toString()) );
//        llamable2.subscribe( (v)->logea(v.toString()) );

        Observable.zip(
                llamable,
                llamable2,
                (x,y)-> x+y
                )
                .subscribe( (v)->logea(v.toString()) );

        logea("FIn...");
        Thread.sleep(2000);
    }
}
