package noticias.v2;

import noticias.Noticia;
import noticias.Tipo;
import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.ArrayList;
import java.util.List;

public class PublicistaConectable {
    public static void main(String ... a) {

        List<Noticia> noticia = new ArrayList<Noticia>();
        noticia.add(new Noticia(Tipo.POLITICO, "Fox dice: legalicenla"));
        noticia.add(new Noticia(Tipo.ESPECTACULO, "Chismes"));
        noticia.add(new Noticia(Tipo.DEPORTIVO, "GOooool, Tigres a la final"));
        noticia.add(new Noticia(Tipo.ESPECTACULO, "Escandalo"));
        System.out.println();
        ConnectableObservable<Noticia> periodista = Observable.from(noticia).publish();

        periodista.subscribe(new Ventaneando());
        periodista.subscribe(new Protagonistas());
        periodista.subscribe(new Aristegui());
        periodista.subscribe(new SDP());

        periodista.connect();
    }
}
