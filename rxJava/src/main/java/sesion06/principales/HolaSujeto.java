package sesion06.principales;

import rx.subjects.PublishSubject;

public class HolaSujeto {

    public static void main(String ... a){
        PublishSubject<Integer> sujeto = PublishSubject.create();

        sujeto.subscribe( (s)-> {
            System.out.println(s*s); //Accion del observador
        }) ;

        sujeto.onNext(3); // Emisor Observable
        sujeto.onNext(7); // Emisor Observable
        sujeto.onNext(11); // Emisor Observable
        sujeto.onNext(15); // Emisor Observable
    }

}
