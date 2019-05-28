package com.kenzan.reactive.training.homework.common.data;

import com.kenzan.reactive.training.homework.common.subscriber.NewsSubscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kenzan.reactive.training.homework.common.types.NewsType.*;

public class SubscriberData {
    private SubscriberData() {}

    public static List<NewsSubscriber> getNewsSubscribers() {
        final List<NewsSubscriber> newsSubscribers = new ArrayList<>();

        newsSubscribers.add(NewsSubscriber.create("Subscriber1", Arrays.asList(SPORTS)));
        newsSubscribers.add(NewsSubscriber.create("Subscriber2", Arrays.asList(ENTERTAINMENT)));
        newsSubscribers.add(NewsSubscriber.create("Subscriber3", Arrays.asList(POLITICS, ENTERTAINMENT), Arrays.asList(ENTERTAINMENT)));
        newsSubscribers.add(NewsSubscriber.create("Subscriber4", Arrays.asList(SPORTS, ENTERTAINMENT)));
        newsSubscribers.add(NewsSubscriber.create("Subscriber5", Arrays.asList(SPORTS, ENTERTAINMENT, POLITICS)));

        return newsSubscribers;
    }
}
