package sesion06.principales;

import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;

public class ObservableModificable {

    public static void main(String ... arg){

        List<String> frasesObservables = new ArrayList<>();
        frasesObservables.add("Hola Mundo");
        frasesObservables.add("Hello world");
        frasesObservables.add("Óla mundo");

        PublishSubject<String> publicador = PublishSubject.create();

        publicador.subscribe( (f)->{
            if(f.contains(" ")){
                String[] palabras = f.split("\\s" );
                for(String palabra : palabras){
                    frasesObservables.add(palabra);
                }
            }
        });

        publicador.filter( (f)->!f.contains(" ") ).subscribe(System.out::println);

        int index = 0;
        do {
            publicador.onNext( frasesObservables.get(index++) );
        } while(index < frasesObservables.size());


        //////////////// Observable
        Observable<String> observable = Observable.create((s) -> {
                    int i = 0;
                    do {
                        s.onNext(frasesObservables.get(i++));
                    } while (i < frasesObservables.size());
                }
        );

        observable.subscribe((f) -> {
            if (f.contains(" ")) {
                String[] palabras = f.split("\\s");
                for (String palabra : palabras) {
                    frasesObservables.add(palabra);
                }
            } else {
                System.out.println(f);
            }
        });

    }
}
