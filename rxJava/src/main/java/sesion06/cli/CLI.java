package sesion06.cli;

import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.function.Consumer;

public class CLI {
     /// Usar subjects aqui, ya no hacer esto para sacar el resultado
    public static void ponResultado(String resultado){
        Observable.just(resultado).subscribe(System.out::println);
    }

    public static void main(String ... a){
        ConnectableObservable cli = new ConsoleObservable().publish();
        Consumer<String> echo = CLI::ponResultado;

        cli.subscribe(MotorEjecucion.sumar.apply(echo));
        // usar este approach para tadas las funcines haciendo un CLI resilente.
        cli.filter((linea)-> linea.toString().startsWith("resta "))
                .map(
                        (linea) -> {
                            String[] operands = linea.toString().replace("resta ", "").split("\\s");
                            Integer[] operandsI = new Integer[]{ new Integer(operands[0]), new Integer(operands[1])};
                            return operandsI;
                        }
                )
                .doOnError( MotorEjecucion.onError.apply(echo,"resta"))
                .retry()
                .subscribe(MotorEjecucion.resta.apply(echo));
        cli.subscribe(MotorEjecucion.imprime.apply(echo), MotorEjecucion.onError.apply(echo,"imprime"));
        cli.subscribe(MotorEjecucion.hora.apply(echo), MotorEjecucion.onError.apply(echo,"hora"));
        cli.subscribe(MotorEjecucion.fecha.apply(echo), MotorEjecucion.onError.apply(echo,"fecha"));
        cli.filter((linea)-> !linea.toString().startsWith("suma "))
            .filter((linea)-> !linea.toString().startsWith("resta "))
            .filter((linea)-> !linea.toString().startsWith("hora "))
            .filter((linea)-> !linea.toString().startsWith("fecha "))
            .filter((linea)-> !linea.toString().startsWith("imprime "))
                .subscribe((linea)->System.out.println("Operacion no reconocida: " + linea));

        cli.connect();
    }
}
