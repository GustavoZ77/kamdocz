package sesion06.principales;

import com.sun.tools.javac.util.Assert;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;

public class SujetoFibon {
    public static void main(String ... a){
        List<Integer> fiboSerie = new ArrayList<Integer>();
        fiboSerie.add(1);
        fiboSerie.add(1);

        PublishSubject<Integer> fibon = PublishSubject.create();

        fibon.subscribe(
                (f) -> {
                    Integer v = fiboSerie.get(fiboSerie.size()-1) + fiboSerie.get(fiboSerie.size()-2);
                    fiboSerie.add(v);
                }
        );

        fibon.subscribe( (f) -> System.out.println(fiboSerie.get(f-1)) );

        for ( int i = 3; i < 20; i++)
            fibon.onNext( i );

        //Check
        Assert.check(fiboSerie.size() == 19);
    }
}
