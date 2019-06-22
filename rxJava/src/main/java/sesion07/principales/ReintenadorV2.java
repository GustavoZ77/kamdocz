package sesion07.principales;

import sesion07.testeable.NumerosObservables;

public class ReintenadorV2 {

    public static void main(String ... a) {

        NumerosObservables numerosObservables = new NumerosObservables();
        numerosObservables.add("1");
        numerosObservables.add("2");
        numerosObservables.add("3");
        numerosObservables.add("IV");
        numerosObservables.add("5");
        numerosObservables.add("6");
        numerosObservables.add("VII");
        numerosObservables.add("8");

//        numerosObservables.getObservableString()
//        .map(Integer::new).onErrorReturn(e -> 0).
//                subscribe(
//                        System.out::println
//                );

        numerosObservables.getObservableString()
        .map(Integer::new).onErrorResumeNext(numerosObservables.getObservableInteger()).
                subscribe(
                        System.out::println,
                        (t)->{System.out.println("Numero no valido");}
                );
    }
}
