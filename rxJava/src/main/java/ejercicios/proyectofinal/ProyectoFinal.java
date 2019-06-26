package ejercicios.proyectofinal;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
  20 ordenes por minuto
 Ordenes incluyen: bebida, entrada, plato fuerte y postre
 Por lo que existen 3 cocineros principales: Bar man, que se encarga de las bebidas, Repostero que se encarga de preparar el postre y un Chef que se encarga de preparar la entrada y plato fuerte.
 El chef a su vez tiene 2 ayudantes de cocina; en otras palabras, el chef tiene dos subscriptores.
  Las labores generales de la cocina son: Preparar alimento y emplatar.

Ejemplo de Salida por cada orden emitida:

        [Hilo-Barman] Preparando bebida 1 …
        [Hilo-Repostero] Preparando postre 1 ….
        [Hilo-Barman] Sirviendo (Emplatando) bebida 1 …
        [Hilo-Pinche1] Preparando Entrada 1 ….
        [Hilo-Pinche2] Preparando Plato Fuerte 1 ….
        [Hilo-Pinche1] Emplatando Entrada 1 ….
        [Hilo-Pinche2] Emplatando Plato Fuerte 1 ….
        [Hilo-Repostero] Emplatando postre 1 … */

public class ProyectoFinal {

    public static void main(String... args) throws InterruptedException {

        Observable<Platillo> ordenes = Observable.interval(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread()).map(Platillo::new);

        PublishSubject<Comida> chef = PublishSubject.create();
        PublishSubject<String> barman = PublishSubject.create();
        PublishSubject<String> repostero = PublishSubject.create();

        ordenes.map(Platillo::getBebida).subscribe(barman);
        ordenes.map(Platillo::getComida).subscribe(chef);
        ordenes.map(Platillo::getPostre).subscribe(repostero);

        PublishSubject<String> pinche1 = PublishSubject.create();
        PublishSubject<String> pinche2 = PublishSubject.create();

        ordenes.map(Platillo::getComida).map(Comida::getEntrada).subscribe(pinche1);
        ordenes.map(Platillo::getComida).map(Comida::getPlatoFuerte).subscribe(pinche2);

        barman.map(bebida -> preparacion.call(bebida, "barman", "bebida"))
                .subscribe(sub -> sub.subscribe(System.out::print));

        repostero.map(postre -> preparacion.call(postre, "repostero", "postre"))
                .subscribe(sub -> sub.subscribe(System.out::print));

        pinche1.map(comida -> preparacion.call(comida, "pinche1", "Entrada"))
                .subscribe(sub -> sub.subscribe(System.out::print));

        pinche1.map(comida -> preparacion.call(comida, "pinche2", "Plato Fuerte"))
                .subscribe(sub -> sub.subscribe(System.out::print));

        Thread.sleep(2000);
    }

    private static Func3<String, String, String, Observable<String>> preparacion = (bebida, hiloName, postre) ->
            Observable.just("Preparado", "Emplatado")
                    .map(step -> {
                        ProyectoFinal.renameThread.call("hilo-" + hiloName);
                        return String.format("[%s] %s %s %s\n", Thread.currentThread().getName(), step, postre, bebida);
                    });

    private static Action1<String> renameThread = newName -> {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        if(name.contains("RxComputationScheduler")) {
            name = name.replaceFirst("RxComputationScheduler", newName);
            thread.setName(name);
        }
    };

}
