package com.christian;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class NewsFilter {
    List<String> newsList = Arrays.asList("DEPORTIVA", "ESPECTACULOS", "POLITICA");

    BiFunction<String, NEWS_TYPE, String> filterNews = (news, type) -> {
        return news.contains(type.name()) ?
                new String("[" + type.name() + "]" + news.substring(0, news.length() - type.name().length() - 1)) : "";
    };

    public void filter(List<String> news) {
        ConnectableObservable newsObservable = Observable.from(news).publish();

        Subscription deportiva = newsObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(filterNews.apply(s, NEWS_TYPE.DEPORTIVA));
            }
        });

        Subscription espectaculos = newsObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(filterNews.apply(s, NEWS_TYPE.ESPECTACULOS));
            }
        });

        Subscription politica = newsObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

                System.out.println(filterNews.apply(s, NEWS_TYPE.POLITICA));

                if(s.contains(NEWS_TYPE.ESPECTACULOS.name()))
                    unsubscribe();
            }
        });

        Subscription especPol = newsObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(filterNews.apply(s, NEWS_TYPE.ESPECTACULOS));
                System.out.println(filterNews.apply(s, NEWS_TYPE.POLITICA));
            }
        });

        Subscription especPolDep = newsObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(filterNews.apply(s, NEWS_TYPE.ESPECTACULOS));
                System.out.println(filterNews.apply(s, NEWS_TYPE.POLITICA));
                System.out.println(filterNews.apply(s, NEWS_TYPE.DEPORTIVA));
            }
        });


        Subscription subscription = newsObservable.connect();

    }
}
