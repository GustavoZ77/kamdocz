package sesion07.testeable.lab;

import rx.Observable;

import java.util.function.BiFunction;

public class OperableRx {

    public Observable<Integer> operandoObservable(Integer valor) {
        return Observable.fromCallable( () -> valor );
    }

    public Observable<BiFunction<Integer, Integer, Integer>> operadorMultObservable() {
        // Ponga ud aqui su logica para retornar un observable de una funcion del tipo x * y
        return  null;
    }

    public Observable<BiFunction<Integer, Integer, Integer>> operadorSumaObservable() {
        // Ponga ud aqui su logica para retornar un observable de una funcion del tipo x + y
        return  null;
    }

    public Observable<BiFunction<Integer, Integer, Integer>> operadorRestaObservable() {
        // Ponga ud aqui su logica para retornar un observable de una funcion del tipo x - y
        return  null;
    }

    public Observable<Integer> operacionObservable(Observable<BiFunction<Integer, Integer, Integer>> funcion,
                                                   Observable<Integer> operando1,
                                                   Observable<Integer> operando2) {
        //Ponga uds aqui su logica para regresar un Observable de un entero, resultante de aplicar el parametro funcion
        // a los operandos 1 y 2. Use el operador zip.
        return  null;
    }
}
