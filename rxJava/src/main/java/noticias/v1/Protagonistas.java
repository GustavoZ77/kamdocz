package noticias.v1;

import noticias.Noticia;
import noticias.Tipo;
import rx.Subscriber;

public class Protagonistas extends Subscriber<Noticia> {

    public Protagonistas(){
        System.out.println("Construyendo Protagonistas");
    }
    @Override
    public void onCompleted() {
        System.out.println("El  periodismo deportivo ha terminado");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("QUeee paso con la transmision Dr... que fue interrumpida!!" + throwable.getMessage());
    }

    @Override
    public void onNext(Noticia noticia) {
        if(noticia.getTipo().equals(Tipo.DEPORTIVO)) {
            System.out.println("Impresionanti dice: " + noticia.getNoticia());
        }
    }
}