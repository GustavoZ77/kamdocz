package noticias.v1;

import noticias.Noticia;
import noticias.Tipo;
import rx.Subscriber;

public class Aristegui extends Subscriber<Noticia> {
    @Override
    public void onCompleted() {
        System.out.println("Esto ha sido todo en Aristegui Noticias");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Un momento, un momento le paramos aqui que tenemos unas fallas..." + throwable.getMessage());
    }

    @Override
    public void onNext(Noticia noticia) {
        if(noticia.getTipo().equals(Tipo.POLITICO)){
            System.out.println("Investigaciones especiales presenta:" + noticia.getNoticia());
        } else if(noticia.getTipo().equals(Tipo.ESPECTACULO)){
            System.out.println("Aristegui Noticias no comparte este tipo de informacion: " + noticia.getNoticia());
            this.unsubscribe();
        }
    }
}
