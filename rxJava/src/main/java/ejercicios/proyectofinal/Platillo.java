package ejercicios.proyectofinal;

public class Platillo {

    private String bebida;
    private Comida comida;
    private String postre;

    public Platillo (Long id) {
        this.bebida = id.toString();
        this.comida = new Comida(id);
        this.postre = id.toString();
    }

    public String getBebida () {
        return bebida;
    }

    public Comida getComida () {
        return comida;
    }

    public String getPostre () {
        return postre;
    }
}
