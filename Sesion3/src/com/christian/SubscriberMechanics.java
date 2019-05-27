package com.christian;

import rx.Observable;
import rx.Subscription;

public class SubscriberMechanics {

    private Subscription subscription;

    public Subscription createSubscriber(final Observable<String> stream, SeccionesEnum reject, SeccionesEnum ... seccionesEnum) {
        subscription =  stream.subscribe(i -> {
            for(SeccionesEnum secciones : seccionesEnum) {
                if (i.contains(secciones.name())) {
                    String news = i.replace("," + secciones.name(), "");
                    System.out.println("[" + secciones.name() + "]: " + news);
                }

                if(reject != null && i.contains(reject.name())) {
                    System.out.println("[" + secciones.name() + "] no publica noticas de espectaculos desusbcribiendo...");
                    this.stopSubscriber();
                }
            }
        });

        return subscription;
    }

    public void stopSubscriber() {
        subscription.unsubscribe();
    }
}
