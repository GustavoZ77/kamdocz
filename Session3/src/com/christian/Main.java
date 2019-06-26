package com.christian;

import java.util.Arrays;
import java.util.List;

public class Main {
    List<String> newsList = Arrays.asList("DEPORTIVA", "ESPECTACULOS", "POLITICA");

    public static void main(String[] args) {

        List<String> news = Arrays.asList("Personas que le digan ‘six’ a las cervezas obtendrán su nacionalidad gringa,POLITICA",
                "José José y Felipe Calderón inauguran el torneo Hígado de Acero para que demuestres de qué estás hecho,DEPORTIVA",
                "Luego de ver “Un extraño enemigo” Masiosare demanda a Amazon por difamación,ESPECTACULOS",
                "Confirman que hay más gente afiliada al Blockbuster que al PRI,POLITICA");

        NewsFilter filter = new NewsFilter();

        filter.filter(news);
    }

}
