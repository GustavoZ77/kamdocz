package sesion07.testeable.lab;

import rx.Observable;

import java.util.function.BiFunction;

public class OperableRx {

    public Observable<Integer> operandoObservable(Integer valor) {
        return Observable.fromCallable( () -> valor );
    }

    public Observable<BiFunction<Integer, Integer, Integer>> operadorMultObservable() {
        return Observable.fromCallable(() -> (x, y) -> x * y);
    }

    public Observable<BiFunction<Integer, Integer, Integer>> operadorSumaObservable() {
        return Observable.fromCallable(() -> (x, y) -> x + y);
    }

    public Observable<BiFunction<Integer, Integer, Integer>> operadorRestaObservable() {
        return Observable.fromCallable(() -> (x, y) -> x - y);
    }

    public Observable<Integer> operacionObservable(Observable<BiFunction<Integer, Integer, Integer>> f,
                                                   Observable<Integer> operando1,
                                                   Observable<Integer> operando2) {
        //Ponga uds aqui su logica para regresar un Observable de un entero, resultante de aplicar el parametro funcion
        // a los operandos 1 y 2. Use el operador zip.
        Observable<Integer> result = Observable.zip(f, operando1, operando2, (x, y, z) -> x.apply(y, z));
        return result;
    }
}
