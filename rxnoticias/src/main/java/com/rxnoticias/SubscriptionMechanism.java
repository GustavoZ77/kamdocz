package com.rxnoticias;

import com.rxnoticias.domain.NewsType;
import com.rxnoticias.domain.Noticia;
import rx.Observable;
import rx.Subscription;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class SubscriptionMechanism {

    private Subscription subscription;

    public Subscription createSubscription(int num, Observable<Noticia> stream, NewsType reject, NewsType ... newsTypes) {
        List<NewsType> newsTypesList = Arrays.asList(newsTypes);

        subscription = stream
                .filter(noticia ->
                        newsTypesList.stream()
                            .anyMatch(newsType -> noticia.getNewsType().equals(newsType)))
                .subscribe(noticia -> {
                    StringJoiner noticiasAccepted = new StringJoiner(", ");

                        newsTypesList.stream()
    //                            .filter(newsType -> noticia.getNewsType().equals(newsType))
                                .forEach(newsType -> {
                                    noticiasAccepted.add(newsType.name());
                                });

                        System.out.printf("Noticias: %s, Subs #%d %s\n", noticiasAccepted.toString(), num, noticia.getDescripcion());

                        if(reject != null && noticia.getNewsType().equals(reject)) {
                            this.stopSubscriber();
                        }
                });

        return subscription;
    }

    private void stopSubscriber () {
        subscription.unsubscribe();
        System.out.println("subscripcion finalizada...");
    }

}
