package com.rxnoticias;

import com.rxnoticias.domain.NewsType;
import com.rxnoticias.domain.Noticia;
import rx.Observable;
import rx.Subscription;

import java.util.List;
import java.util.stream.Collectors;

public class SuscripcionesV2 {

    public static void main(String... args) {

        List<Noticia> noticias = new InitData().getNoticias();

        Observable<Noticia> noticiaObs = Observable.from(noticias);

        SubscriptionMechanism subs = new SubscriptionMechanism();

        // Subscripcion Deportiva
        Subscription deportiva = subs.createSubscription(1, noticiaObs, null, NewsType.DEPORTIVO);
        Subscription espectaculos = subs.createSubscription(2, noticiaObs, null, NewsType.ESPECTACULOS);
        Subscription depEspec = subs.createSubscription(3, noticiaObs, null, NewsType.DEPORTIVO, NewsType.ESPECTACULOS);
        Subscription depEspecPol = subs.createSubscription(4, noticiaObs, NewsType.POLITICA, NewsType.DEPORTIVO, NewsType.ESPECTACULOS, NewsType.POLITICA);

//        noticiaObs
//            .filter(noticia -> noticia.getNewsType().equals(NewsType.DEPORTIVO))
//            .subscribe(noticia -> System.out.println("Noticia Deportiva: " + noticia.getDescripcion()));

        // Subscripcion de Espectaculos
//        noticiaObs
//            .filter(noticia -> noticia.getNewsType().equals(NewsType.ESPECTACULOS))
//            .subscribe(noticia -> System.out.println("Noticia Espectaculos: " + noticia.getDescripcion()));
//
//        // Subscripcion de Espectaculos y Deportiva
//        noticiaObs
//            .filter(noticia -> noticia.getNewsType().equals(NewsType.ESPECTACULOS)
//                            || noticia.getNewsType().equals(NewsType.DEPORTIVO))
//            .subscribe(noticia -> System.out.println("Noticia Espectaculos o Deportivos: " + noticia.getDescripcion()));
//
//        // Subscripcion de Espectaculos, Deportiva y Politica
//        noticiaObs
//                .filter(noticia -> noticia.getNewsType().equals(NewsType.POLITICA)
//                                || noticia.getNewsType().equals(NewsType.ESPECTACULOS)
//                                || noticia.getNewsType().equals(NewsType.DEPORTIVO))
//                .subscribe(noticia -> System.out.println("Noticia Deportivo, Espectaculos o Politica: " + noticia.getDescripcion()));
//
//        // Subscripcion de Politica
//            // Des-subscribir cuando encuentre un espectaculo
//        Observable<Noticia> politicaObs = Observable.create(s -> {
//
//            List<Noticia> noticiasPolitica = noticias.stream()
//                    .filter(noticia -> noticia.getNewsType().equals(NewsType.POLITICA)
//                            || noticia.getNewsType().equals(NewsType.ESPECTACULOS))
//                    .collect(Collectors.toList());
//
//            for(Noticia noticia : noticiasPolitica) {
//                if(noticia.getNewsType().equals(NewsType.ESPECTACULOS)) {
//                    s.unsubscribe();
//                    break;
//                } else {
//                    s.onNext(noticia);
//                }
//            }
//            s.onCompleted();
//        });
//
//        politicaObs
//            .subscribe(
//                    noticia -> System.out.println("Politica: " + noticia.getDescripcion()),
//                    err -> System.out.println(err),
//                    () -> System.out.println("Completed"));
//
    }
}
