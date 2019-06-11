package noticias;

public class Noticia {

    Tipo tipo;
    String noticia;

    public Tipo getTipo() {
        return tipo;
    }

    public String getNoticia() {
        return noticia;
    }

    public String toString() {
        return "[" + tipo + "] " + noticia;
    }

    public Noticia(Tipo tipo, String noticia) {
        this.noticia = noticia;
        this.tipo = tipo;
        System.out.println(this.toString());
    }
}
