package cli.opc1.frp;

import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

import java.util.Scanner;

public class ConsoleObservable<String> extends ConnectableObservable<String> {
    public ConsoleObservable() {
        super((s)-> {
            while(!s.isUnsubscribed()){
                System.out.print("rx-CLI> ");
                String comando =  (String)new Scanner(System.in).nextLine();
                if(comando.equals("adios"))
                    s.unsubscribe();
                else
                    s.onNext(comando);
            }
        });
    }

    @Override
    public void connect(Action1<? super Subscription> action1) {
        super.connect();
    }
}
