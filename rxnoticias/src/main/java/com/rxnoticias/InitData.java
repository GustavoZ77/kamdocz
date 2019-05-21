package com.rxnoticias;

import com.rxnoticias.domain.NewsType;
import com.rxnoticias.domain.Noticia;

import java.util.Arrays;
import java.util.List;

public class InitData {

    private List<Noticia> noticias;

    public InitData() {
        noticias = Arrays.asList(
                new Noticia(1, "Neymar se va al Madrid!", NewsType.DEPORTIVO),
                new Noticia(1, "Venesuela declara guerra a USA!", NewsType.POLITICA),
                new Noticia(1, "Chabelo muere!", NewsType.ESPECTACULOS),
                new Noticia(1, "El chicharito se retira!", NewsType.ESPECTACULOS),
                new Noticia(1, "Grizman nuevo integrante cule!", NewsType.DEPORTIVO),
                new Noticia(1, "AMLO la caga!", NewsType.POLITICA),
                new Noticia(1, "Matan a Trump!", NewsType.POLITICA),
                new Noticia(1, "Barcelona gana la Champions!", NewsType.DEPORTIVO)
        );
    }

    public List<Noticia> getNoticias () {
        return noticias;
    }
}
