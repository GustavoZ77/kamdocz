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
        numbers.push(2);
        numbers.push(2);
        numbers.push(2);
        numbers.push(3);
        numbers.push(3);

        Observable.range(0, numbers.size() - 1)
                .flatMap(i -> {
                    if (!numbers.empty() || numbers.size() >= 2 ) {
                        return NumerosObservables.getTotal(
                                NumerosObservables.getMultiplicarIntegerObs(), Observable.just(numbers.pop()),
                                Observable.just(numbers.pop()));
                    }
                    return Observable.just(0);
                })
                .flatMap(num -> {
                    System.out.println("Multiply: " + num);
                    if (!numbers.empty()) {
                        return NumerosObservables.getTotal(
                                NumerosObservables.getSumaIntegerObs(), Observable.just(num),
                                Observable.just(numbers.pop()));
                    }
                    return Observable.just(num);
                })
                .flatMap(num -> {
                    System.out.println("Sum: " + num);
                    if (!numbers.empty()) {
                        return NumerosObservables.getTotal(
                                NumerosObservables.getRestarIntegerObs(), Observable.just(num),
                                Observable.just(numbers.pop()));
                    }
                    return Observable.just(num);
                })
                .map(num -> String.format("Resta: %d", num))
                .doOnNext(System.out::println)
                .subscribe(testSubscriber);


        testSubscriber.assertValue("Resta: 9");


    }


}
