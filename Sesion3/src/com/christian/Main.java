package com.christian;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class Main {

    public static void main(String[] args) {

        String[] news = {
                "Personas que le digan ‘six’ a las cervezas obtendrán su nacionalidad gringa,POLITICA",
                "José José y Felipe Calderón inauguran el torneo Hígado de Acero para que demuestres de qué estás hecho,DEPORTIVA",
                "Luego de ver “Un extraño enemigo” Masiosare demanda a Amazon por difamación,ESPECTACULOS",
                "Confirman que hay más gente afiliada al Blockbuster que al PRI,POLITICA"};


        Observable<String> observableNews = Observable.from(news);
        SubscriberMechanics mechanics = new SubscriberMechanics();

        Subscription deportiva = mechanics.createSubscriber(observableNews, null, SeccionesEnum.DEPORTIVA);

        Subscription espectaculos = mechanics.createSubscriber(observableNews, null, SeccionesEnum.ESPECTACULOS);

        Subscription politica = mechanics.createSubscriber(observableNews, SeccionesEnum.ESPECTACULOS, SeccionesEnum.POLITICA);

        Subscription depEspec = mechanics.createSubscriber(observableNews, null, SeccionesEnum.ESPECTACULOS, SeccionesEnum.DEPORTIVA);

        Subscription depEspecPol = mechanics.createSubscriber(observableNews, null, SeccionesEnum.ESPECTACULOS, SeccionesEnum.DEPORTIVA, SeccionesEnum.POLITICA);

    }
}
