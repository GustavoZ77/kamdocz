package cli.opc1.frp;

import rx.Observable;
import rx.observables.ConnectableObservable;
import java.util.function.Consumer;

public class CLI {

    public static void ponResultado(String resultado){
        Observable.just(resultado).subscribe(System.out::println);
    }

    public static void main(String ... a){
        ConnectableObservable cli = new ConsoleObservable().publish();
        Consumer<String> echo = CLI::ponResultado;

        cli.subscribe(MotorEjecucion.sumar.apply(echo), MotorEjecucion.onError.apply(echo,"suma"));
        cli.subscribe(MotorEjecucion.resta.apply(echo), MotorEjecucion.onError.apply(echo,"resta"));
        cli.subscribe(MotorEjecucion.imprime.apply(echo), MotorEjecucion.onError.apply(echo,"imprime"));
        cli.subscribe(MotorEjecucion.hora.apply(echo), MotorEjecucion.onError.apply(echo,"hora"));
        cli.subscribe(MotorEjecucion.fecha.apply(echo), MotorEjecucion.onError.apply(echo,"fecha"));

        cli.connect();
    }
}
