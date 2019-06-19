package sesion07.testeable;

import org.junit.Test;
import rx.observers.TestSubscriber;

public class NumerosObservablesTest {

    @Test
    public void testGetObservableString(){
        NumerosObservables numerosObservables = new NumerosObservables();
        numerosObservables.add("a");
        numerosObservables.add("b");
        numerosObservables.add("c");

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        numerosObservables.getObservableString().subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(3);
        testSubscriber.assertValues("a", "b", "c");
    }

    @Test
    public void testGetObservableStringException(){
        NumerosObservables numerosObservables = new NumerosObservables();
        numerosObservables.add("a");
        numerosObservables.add("b");
        numerosObservables.add("c");

        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        numerosObservables.getObservableString().map(Integer::new).subscribe(testSubscriber);

        testSubscriber.assertError(NumberFormatException.class);
        testSubscriber.assertNotCompleted();
        testSubscriber.assertNoValues();
    }

    @Test
    public void testGetObservableInteger() {
        NumerosObservables numerosObservables = new NumerosObservables();
        numerosObservables.add("a");
        numerosObservables.add("2");
        numerosObservables.add("c");
        numerosObservables.add("4");

        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        numerosObservables.getObservableInteger().subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(2);
        testSubscriber.assertValues(2, 4);
    }

}
