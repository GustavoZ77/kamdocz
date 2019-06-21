import com.rxnoticias.NumerosObservables;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.Stack;
import java.util.function.BiFunction;

public class TestFromCallable {

    @Test
    public void testIntergerFromCallable() {

        TestSubscriber testSubscriber = new TestSubscriber();

        NumerosObservables.getIntegerObs(3).subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(3);
        testSubscriber.assertValues(3);

    }

    @Test
    public void testBifuntion() {

        TestSubscriber testSubscriber = new TestSubscriber();

        NumerosObservables.getSumaIntegerObs()
                .subscribe(testSubscriber);

        testSubscriber.assertCompleted();
    }

    @Test
    public void testZip() {

        TestSubscriber testSubscriber = new TestSubscriber();

        Stack<Integer> numbers = new Stack<>();
        numbers.push(3);
        numbers.push(5);
        numbers.push(6);
        numbers.push(7);

        Observable.range(0, numbers.size() - 1)
                .doOnNext(i -> {
                    Integer totalNum = 0;

                    if (!numbers.empty()) {
                        totalNum = NumerosObservables.getTotal(
                                NumerosObservables.getSumaIntegerObs(), Observable.just(numbers.pop()),
                                Observable.just(numbers.pop())).;
                    }
                    return totalNum;
                });

        NumerosObservables.getTotal(
                NumerosObservables.getSumaIntegerObs(), Observable.just(numbers.pop()), Observable.just(numbers.pop()))
                .doOnNext(System.out::println)
                .flatMap(num -> {
                            Integer totalNum = 0;
                            while (!numbers.empty()) {
                                 totalNum = NumerosObservables.getTotal(
                                        NumerosObservables.getMultiplicarIntegerObs(), Observable.just(num),
                                        Observable.just(numbers.pop())).doOnNext();
                            }
                            return totalNum;
                        })
                .doOnNext(System.out::println)
                .subscribe(testSubscriber);

        testSubscriber.assertCompleted();
    }


}
