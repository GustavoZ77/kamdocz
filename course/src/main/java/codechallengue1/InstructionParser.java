package codechallengue1;

import codechallengue1.observable.HotObservableSubscribe;
import codechallengue1.observer.ImprimeObserver;
import codechallengue1.observer.RestaObserver;
import codechallengue1.observer.SumaObserver;
import rx.Observable;
import rx.subjects.PublishSubject;

public class InstructionParser {

    void start() {
        PublishSubject<String[]> publishSubject = PublishSubject.create();

        publishSubject.subscribe(new ImprimeObserver());
        publishSubject.subscribe(new RestaObserver());
        publishSubject.subscribe(new SumaObserver());

        Observable<String[]> cli = Observable.create(new HotObservableSubscribe());
        cli.subscribe(publishSubject::onNext,
                throwable -> {
                    System.err.println(throwable.getMessage());
                    publishSubject.onError(throwable);
                },
                () -> {
                    System.out.println("Proceso Completado");
                    publishSubject.onCompleted();
                }
        );
    }
}
