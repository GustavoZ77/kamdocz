package ejercicios.proyectofinal;

public class Comida {


    private String entrada;
    private String platoFuerte;

    public Comida (Long id) {
        this.entrada = id.toString();
        this.platoFuerte = id.toString();
    }

    public String getEntrada () {
        return entrada;
    }

    public String getPlatoFuerte () {
        return platoFuerte;
    }
}
