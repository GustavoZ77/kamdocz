package noticias.v1;

import noticias.Noticia;
import rx.Subscriber;

public class SDP extends Subscriber<Noticia> {
    @Override
    public void onCompleted() {
        System.out.println("SDP ha terminado");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error en SDP noticias" + throwable.getMessage());

    }

    @Override
    public void onNext(Noticia noticia) {
        System.out.println("SDP>>"+noticia);
    }
}
