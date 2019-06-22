package cli.opc3.frp;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CLI {

    public static void ponResultado(String resultado){
        Observable.just(resultado).subscribe(System.out::println);
    }

    public static void main(String ... a){
        ConnectableObservable cli = new ConsoleObservable().publish();
        Consumer<String> echo = CLI::ponResultado;

        BiFunction<Consumer<String>, String, Action1> onError = (echo1, op) -> (t) -> {
            echo1.accept("Operacion" + op + " mal Formada");
        };

        Observable<BiFunction<Integer, Integer, Integer>> observableSuma =
                Observable.just((Integer o1, Integer o2) -> o1 + o2);
        Observable<BiFunction<Integer, Integer, Integer>> observableResta =
                Observable.just((Integer o1, Integer o2) -> o1 - o2);
        Observable<Function<String, String>> observableHora =
                Observable.just((op) -> {
                    String format = "HH:mm:ss";
                    if (op.contains("12"))
                        format = "h:mm:ss a";
                    return (LocalTime.now().format(DateTimeFormatter.ofPattern(format)));
                });
        Observable<Supplier<String>> observableFecha =
                Observable.just(() -> {
                    LocalDateTime local = LocalDateTime.now();
                    return local.getDayOfMonth() + "/" + local.getMonthValue() + "/" + local.getYear();
                });
        Observable<Function<String, String>> observableImprime =
                Observable.just((op) -> op);
/// Usar multiples subscriber
        cli.subscribe((command) -> {
                    String[] expresion = command.toString().split("\\s");
                    String operacion = expresion[0];
                    Integer op1, op2;
                    switch (operacion){
                        case "suma":
                            op1 = new Integer(expresion[1]);
                            op2 = new Integer(expresion[2]);
                            observableSuma.subscribe((f) -> echo.accept(f.apply(op1, op2).toString()));
                            break;
                        case "resta":
                            op1 = new Integer(expresion[1]);
                            op2 = new Integer(expresion[2]);
                            observableResta.subscribe((f)-> echo.accept(f.apply(op1,op2).toString()));
                            break;
                        case "hora":
                            observableHora.subscribe((f)-> echo.accept(f.apply(expresion[1]) ));
                            break;
                        case "fecha" :
                            observableFecha.subscribe( (f) ->
                                    echo.accept( f.get() ) );
                            break;
                        case "imprime":
                            observableImprime.subscribe( (f) -> echo.accept( f.apply( expresion[1] ) )) ;
                            break;
                        default:
                            echo.accept(operacion  + " no es una operacion valida");
                            break;
                    }
                },
                onError.apply(echo, " []"));
        cli.connect();
    }
}
