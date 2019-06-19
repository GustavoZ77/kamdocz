package sesion07.testeable;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;

public class NumerosObservables {

    List<String> numeros;

    public NumerosObservables(){
        numeros = new ArrayList<String>();
    }

    public void add(String s){
        this.numeros.add(s);
    }

    public Observable<String> getObservableString(){
        return Observable.defer(() -> Observable.from(this.numeros));
    }

    public Observable<Integer> getObservableInteger(){
        return Observable.defer (
                () -> Observable.from(this.numeros)
                .filter(v -> v.matches("\\d+"))
                .map(Integer::new)
        );
    }

}
