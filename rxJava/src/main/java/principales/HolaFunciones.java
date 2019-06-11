package principales;

import rx.functions.Action3;
import rx.functions.Func3;
import rx.Observable;

import java.util.function.BiFunction;

public class HolaFunciones {

    static BiFunction<Integer, Integer, Integer> construyeSuma(){
        return new BiFunction() {
            @Override
            public Object apply(Object o, Object o2) {
                System.out.print("Suma: ");
                return (Integer) o + (Integer) o2;
            }
        };
    }
    static BiFunction<Integer, Integer, Integer> construyeMult(){
        return new BiFunction() {
            @Override
            public Object apply(Object o, Object o2) {
                System.out.print("Producto: ");
                return (Integer) o * (Integer) o2;
            }
        };
    }

    static BiFunction<Integer, Integer, Integer> construyeRest(){
        return new BiFunction() {
            @Override
            public Object apply(Object o, Object o2) {
                System.out.print("Resta: ");
                return (Integer) o - (Integer) o2;
            }
        };
    }

    public static void main (String... ar){
        Observable<BiFunction> triFuncion = Observable.create( (s) -> {
            s.onNext(construyeSuma());
            s.onNext(construyeMult());
            s.onNext(construyeRest());
            s.onCompleted();
        });

        triFuncion.subscribe((f)->
        {
            System.out.println(f.apply(4,3));
        });

    }
}
