package com.kenzan.reactive.training.homework.session3;

import com.kenzan.reactive.training.homework.common.data.NewsData;
import com.kenzan.reactive.training.homework.common.data.SubscriberData;
import com.kenzan.reactive.training.homework.common.model.News;
import com.kenzan.reactive.training.homework.common.subscriber.NewsSubscriber;
import com.kenzan.reactive.training.homework.common.subscriber.NewsSubscriberHandler;
import rx.Observable;

import java.util.List;

public class Main {
    public static void main(String []args) {
        final Observable<News> observable = Observable.from(NewsData.getNews());
        final List<NewsSubscriber> subscriberList = SubscriberData.getNewsSubscribers();

        NewsSubscriberHandler.subscribe(observable, subscriberList);
    }
}
