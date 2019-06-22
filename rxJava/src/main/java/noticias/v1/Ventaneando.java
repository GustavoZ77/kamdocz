package noticias.v1;

import noticias.Noticia;
import noticias.Tipo;
import rx.Subscriber;

public class Ventaneando extends Subscriber<Noticia> {

    public Ventaneando(){
        System.out.println("Contruyendo Ventaneando...");
    }

    @Override
    public void onCompleted() {
        System.out.println("El pseudo periodismo de espectaculos ha terminado");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("La transmision de chismes fue interrumpida!!" + throwable.getMessage());
    }

    @Override
    public void onNext(Noticia noticia) {
        if(noticia.getTipo().equals(Tipo.ESPECTACULO)){
            System.out.println("Pedrito Sola dice: " + noticia.getNoticia());
        }
    }
}
