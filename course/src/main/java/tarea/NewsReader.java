package tarea;

import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;

import java.util.List;
import java.util.function.Predicate;

public class NewsReader {

    private Predicate<News> isSports = news -> news.getType().equals(NewsType.SPORTS);
    private Predicate<News> isSpectacles = news -> news.getType().equals(NewsType.SPECTACLES);
    private Predicate<News> isPolitics = news -> news.getType().equals(NewsType.POLITICS);

    private Subscriber<News> politicsReader = new Subscriber<News>() {
        @Override
        public void onCompleted() {}

        @Override
        public void onError(Throwable e) {}

        @Override
        public void onNext(News news) {
            if (isPolitics.test(news)) {
                System.out.println("[politicsReader] : " + news.getTitle());
            } else if (isSpectacles.test(news)) {
                unsubscribe();
                System.err.println("[politicsReader] Spectacles news? wakala, desuscribiendo");
            }
        }
    };

    public void readNews(){
        List<News> news = new NewsRepository().getNews();

        Observable<News> objectObservable = Observable.create(subscriber -> {
            subscriber.onStart();
            news.forEach(subscriber::onNext);
            subscriber.onCompleted();
        });

        ConnectableObservable<News> newsObservable = objectObservable.publish();

        newsObservable.subscribe(n -> {
            if (isSports.test(n)) {
                System.out.println("[sportsReader] : " + n.getTitle());
            }
        });
        newsObservable.subscribe(n -> {
            if (isSpectacles.test(n)) {
                System.out.println("[spectaclesReader] : " + n.getTitle());
            }
        });
        newsObservable.subscribe(politicsReader);
        newsObservable.subscribe(n -> {
            if (isSpectacles.or(isSports).test(n)) {
                System.out.println("[spectacles&sportsReader] : " + n.getTitle());
            }
        });
        newsObservable.subscribe(n -> System.out.println("[allReader] : " + n.getTitle()));

        newsObservable.connect();
    }
}
