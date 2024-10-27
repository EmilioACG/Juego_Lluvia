package puppy.code;

public abstract class Comida {
    private String nombre;

    public Comida(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
