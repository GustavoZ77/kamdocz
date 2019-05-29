package tarea;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewsRepository {

    public List<News> getNews() {
        return Stream.of(
                new News("Personas que le digan ‘six’ a las cervezas obtendrán su nacionalidad gringa", NewsType.POLITICS),
                new News("José José y Felipe Calderón inauguran el torneo Hígado de Acero para que demuestres de qué estás hecho", NewsType.SPORTS),
                new News("Luego de ver “Un extraño enemigo” Masiosare demanda a Amazon por difamación", NewsType.SPECTACLES),
                new News("Confirman que hay más gente afiliada al Blockbuster que al PRI", NewsType.POLITICS),
                new News("Liverpool: campeón de la Champions League despues de 27 años", NewsType.SPORTS),
                new News("Jose José vuelve a cantar de nuevo, claman fans verlo en el auditorio nacional", NewsType.SPECTACLES)
        ).collect(Collectors.toList());

    }
}
