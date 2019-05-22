package Sesion03;

import rx.Observable;

import java.util.function.BiFunction;

public class HolaFunciones {

    private static BiFunction<Integer, Integer, Integer> construyeSuma(){
        return (o, o2) -> {
            System.out.print("Suma: ");
            return o + o2;
        };
    }
    private static BiFunction<Integer, Integer, Integer> construyeMult(){
        return (o, o2) -> {
            System.out.print("Producto: ");
            return o * o2;
        };
    }

    private static BiFunction<Integer, Integer, Integer> construyeRest(){
        return (o, o2) -> {
                System.out.print("Resta: ");
                return o - o2;
        };
    }

    public static void main (String... ar){
        Observable<BiFunction> triFuncion = Observable.create( (s) -> {
            s.onNext(construyeSuma());
            s.onNext(construyeMult());
            s.onNext(construyeRest());
            s.onCompleted();
        });

        triFuncion.subscribe((f)-> System.out.println(f.apply(3,4)));

    }
}
