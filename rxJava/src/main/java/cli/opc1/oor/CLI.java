package cli.opc1.oor;

import rx.Observable;
import rx.observables.ConnectableObservable;

public class CLI {

    public static void ponResultado(String resultado){
        Observable.just(resultado).subscribe(System.out::println);
    }

    public static void main(String ... a){

        ConnectableObservable cli = new ConsoleObservable().publish();

        cli.subscribe( FabricaSubscriptores.subscriptorSuma(CLI::ponResultado) );
        cli.subscribe( FabricaSubscriptores.subscriptorResta(CLI::ponResultado) );
        cli.subscribe( FabricaSubscriptores.subscriptorImprime(CLI::ponResultado) );
        cli.subscribe( FabricaSubscriptores.subscriptorHora(CLI::ponResultado) );
        cli.subscribe( FabricaSubscriptores.subscriptorFecha(CLI::ponResultado) );

        cli.connect();
    }
}
