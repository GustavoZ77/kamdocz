package tarea;

import rx.Observer;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class NewsObserver implements Observer<News> {

    private Predicate<News> subscribeValidator;
    private Consumer<News> nextConsumer;
    private Supplier<Void> requestUnsubscribe;

    public NewsObserver(Predicate<News> subscribeValidator, Consumer<News> nextConsumer, Supplier<Void> requestUnsubscribe) {
        this.subscribeValidator = subscribeValidator;
        this.nextConsumer = nextConsumer;
        this.requestUnsubscribe = requestUnsubscribe;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(News news) {
        if (subscribeValidator.test(news)) {
            this.nextConsumer.accept(news);
        } else {
            this.requestUnsubscribe.get();
        }
    }
}
