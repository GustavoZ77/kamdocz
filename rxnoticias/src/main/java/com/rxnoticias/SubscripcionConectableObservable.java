package com.rxnoticias;

import com.rxnoticias.domain.NewsType;
import com.rxnoticias.domain.Noticia;
import static com.rxnoticias.utils.ThreadUtils.sleep;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import java.util.List;

public class SubscripcionConectableObservable {

    public static void main(String... args) {

        List<Noticia> noticias = new InitData().getNoticias();

        ConnectableObservable<Noticia> connectable = ConnectableObservable.from(noticias).publish();

        // Subscripcion Deportiva
        connectable
                .observeOn(Schedulers.computation())
                .filter(noticia -> noticia.getNewsType().equals(NewsType.DEPORTIVO))
                .subscribe(noticia -> {
                    sleep(300);
                    System.out.println("Noticia Deportiva: " + noticia.getDescripcion());
                });

        // Subscripcion de Espectaculos
        connectable
                .observeOn(Schedulers.computation())
                .filter(noticia -> noticia.getNewsType().equals(NewsType.ESPECTACULOS))
                .subscribe(noticia -> {
                    sleep(600);
                    System.out.println("Espectaculos Deportiva: " + noticia.getDescripcion());
                });

        // Subscripcion de Espectaculos y Deportiva
        connectable
                .filter(noticia -> noticia.getNewsType().equals(NewsType.ESPECTACULOS)
                        || noticia.getNewsType().equals(NewsType.DEPORTIVO))
                .subscribe(noticia -> System.out.println("Noticia Espectaculos o Deportivos: " + noticia.getDescripcion()));

        // Subscripcion de Espectaculos, Deportiva y Politica
        connectable
                .filter(noticia -> noticia.getNewsType().equals(NewsType.POLITICA)
                        || noticia.getNewsType().equals(NewsType.ESPECTACULOS)
                        || noticia.getNewsType().equals(NewsType.DEPORTIVO))
                .subscribe(noticia -> System.out.println("Noticia Deportivo, Espectaculos o Politica: " + noticia.getDescripcion()));

        sleep(2000);
        connectable.connect();
        sleep(5000);

    }


}
