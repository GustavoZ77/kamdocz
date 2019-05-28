package com.kenzan.reactive.training.homework.common.subscriber;

import com.kenzan.reactive.training.homework.common.model.News;
import com.kenzan.reactive.training.homework.common.subscriber.NewsSubscriber;
import rx.Observable;

import java.util.List;

public class NewsSubscriberHandler {
    private NewsSubscriberHandler() {}

    public static void subscribe(Observable<News> observable, List<NewsSubscriber> subscriberList) {
        subscriberList.stream().forEach(subscriber -> subscribe(observable, subscriber));
    }

    public static void subscribe(Observable<News> observable, NewsSubscriber subscriber) {
        if (observable != null && subscriber != null) {
            observable
                    .filter(e -> subscriber.getAllowedTypes().contains(e.getType()))
                    .subscribe(subscriber);
        }
    }
}
