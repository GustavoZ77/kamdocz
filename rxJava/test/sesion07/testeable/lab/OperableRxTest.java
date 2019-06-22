package sesion07.testeable.lab;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import rx.observables.ConnectableObservable;
import rx.observers.TestSubscriber;

import rx.Observable;

import java.util.Stack;
import java.util.function.BiFunction;

public class OperableRxTest {

    private OperableRx operableRx;
    private TestSubscriber<Integer> testSubscriberI;
    private TestSubscriber<BiFunction<Integer, Integer, Integer>> testSubscriberF;

    @Before
    public void setup(){
        testSubscriberI = new TestSubscriber<>();
        testSubscriberF = new TestSubscriber<>();
        operableRx = new OperableRx();
    }

    @Test
    public void testOperandoObservable(){
        Integer valor = 5;

        operableRx.operandoObservable(valor).subscribe(testSubscriberI);

        testSubscriberI.assertCompleted();
        testSubscriberI.assertNoErrors();
        testSubscriberI.assertValue(valor);
    }

    @Test
    public void testOperadorSumaObservable() {
        Integer resultadoEsperado = 5;

        operableRx.operadorSumaObservable().subscribe(testSubscriberF);

        testSubscriberF.assertCompleted();
        testSubscriberF.assertNoErrors();
        testSubscriberF.assertValueCount(1); //1 elemento
        Integer resultado = testSubscriberF
                .getOnNextEvents()
                .get(0) // Obtengo el primer evento que recibio el subscriptor de prueba
                .apply(3,2);
        Assert.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testOperadorRestaObservable() {
        // Ponga ud. aqui su unit test code para el metodo: operadorRestaObservable
    }

    @Test
    public void testOperadorMultObservable() {
        // Ponga ud. aqui su unit test code para el metodo: operadorMultObservable
    }

    @Test
    public void testOperacionObservable() {
        Observable multiplicacion = operableRx.operadorMultObservable();
        Observable op1 = operableRx.operandoObservable(3);
        Observable op2 = operableRx.operandoObservable(3);

        operableRx.operacionObservable(multiplicacion, op1, op2).subscribe(testSubscriberI);

        testSubscriberI.assertCompleted();
        testSubscriberI.assertNoErrors();
        testSubscriberI.assertValue(3*3);
    }

    @Test
    public void testRealmentePerronDe3() {
        Stack< Observable< BiFunction<Integer, Integer, Integer> > > funciones = new Stack<>();
        Stack< Observable<Integer> > operandos = new Stack<>();

        String comando = "suma 2 resta 1 multiplica 4 3"; // ((4*3) - 1) + 2 = 13
        String[] factores = comando.split(" ");

        ConnectableObservable<String> interprete = Observable.from(factores).publish();

        interprete.filter(operacion -> operacion.equals("suma"))
                .subscribe(s -> funciones.push( operableRx.operadorSumaObservable() ));
        interprete.filter(operacion -> operacion.equals("resta"))
                .subscribe(s -> funciones.push( operableRx.operadorRestaObservable() ));
        interprete.filter(operacion -> operacion.equals("multiplica"))
                .subscribe(s -> funciones.push( operableRx.operadorMultObservable() ));
        interprete.filter(operando -> operando.matches("\\d+"))
                .map(Integer::new)
                .subscribe(o -> operandos.push( operableRx.operandoObservable(o) ));
        interprete.connect();

        while ( !funciones.empty() ) { // Si aun dudan del poder de las pilas, tenemos que hablar.
            operandos.push( operableRx.operacionObservable( funciones.pop(), operandos.pop(), operandos.pop()) );
        }

        operandos.pop().subscribe(testSubscriberI);
        testSubscriberI.assertValue(13);

    }

    @Test
    public void testRealmentePerronDe4() {
        // Ponga ud. aqui su test realmente perron para una operacion con 4 operadores
        // ejemplo: (((2 * 30) + 3)* -2) - 15)
    }

    @Test
    public void testRealmentePerronDe7() {
        // Ponga ud. aqui su test realmente perron para una operacion con 7 operadores
        // ejemplo: (((((((2 * 30) + 3)* -2) - 15) + 1) - 3) * 3)
    }
}
